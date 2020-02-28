package com.richyeoh.android.dawdler;

import androidx.core.content.FileProvider;

class DawdlerFileProvider extends FileProvider {
    @Override
    public boolean onCreate() {
        Utils.init();
        return true;
    }
}
