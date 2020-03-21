package com.richyeoh.android.dawdler;

import androidx.core.content.FileProvider;

public final class DawdlerInitializer extends FileProvider {
    @Override
    public boolean onCreate() {
        Utils.init();
        return true;
    }
}
