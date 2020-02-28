package com.richyeoh.android.dawdler;

import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.ConcurrentHashMap;

public final class LiveDataBus {
    private ConcurrentHashMap<String, StickyLiveData<?>> mStickyLiveDataMap = new ConcurrentHashMap<>();
    private static LiveDataBus sLiveDataBus = null;

    private LiveDataBus() {
    }

    public static LiveDataBus get() {
        if (sLiveDataBus == null) {
            synchronized (LiveDataBus.class) {
                if (sLiveDataBus == null) {
                    sLiveDataBus = new LiveDataBus();
                }
            }
        }
        return sLiveDataBus;
    }

    public <T> StickyLiveData<T> with(String eventName) {
        StickyLiveData<T> liveData = (StickyLiveData<T>) mStickyLiveDataMap.get(eventName);
        if (liveData == null) {
            liveData = new StickyLiveData<>(eventName);
            mStickyLiveDataMap.put(eventName, liveData);
        }
        return liveData;
    }

    public class StickyLiveData<T> extends LiveData<T> {
        private String mEventName;
        private T mData;
        private int mVersion;

        StickyLiveData(String eventName) {
            mEventName = eventName;
        }

        public StickyLiveData<T> sendValue(T value) {
            Thread currentThread = Thread.currentThread();
            Thread mainThread = Looper.getMainLooper().getThread();
            return sendValue(value, currentThread == mainThread);
        }

        public StickyLiveData<T> sendValue(T value, boolean mainThread) {
            mData = value;
            mVersion++;
            if (mainThread) {
                setValue(value);
            } else {
                postValue(value);
            }
            return this;
        }

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            super.observe(owner, new WrapperObserver<>(observer, this, true));
            owner.getLifecycle().addObserver((LifecycleEventObserver) (source, event) -> {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    mStickyLiveDataMap.remove(mEventName);
                }
            });
        }

        public class WrapperObserver<V> implements Observer<V> {
            private Observer<? super V> mObserver;
            private StickyLiveData<V> mLiveData;
            private boolean isSticky;
            private int mVersion;

            WrapperObserver(Observer<? super V> observer, StickyLiveData<V> liveData, boolean sticky) {
                mObserver = observer;
                mLiveData = liveData;
                isSticky = sticky;
                mVersion = liveData.mVersion;
            }

            @Override
            public void onChanged(V v) {
                if (mVersion > mLiveData.mVersion) {
                    if (isSticky && mLiveData.mData != null) {
                        mObserver.onChanged(v);
                    }
                    return;
                }
                mVersion++;
                mObserver.onChanged(v);
            }
        }
    }
}
