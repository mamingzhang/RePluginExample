package com.horsege.host;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.qihoo360.replugin.RePluginApplication;
import com.qihoo360.replugin.RePluginCallbacks;
import com.qihoo360.replugin.RePluginConfig;
import com.qihoo360.replugin.RePluginEventCallbacks;

public class HostApplication extends RePluginApplication {

    @Override
    protected RePluginConfig createConfig() {
        RePluginConfig config = new RePluginConfig();
        config.setUseHostClassIfNotFound(true);
        config.setVerifySign(!BuildConfig.DEBUG);
        config.setEventCallbacks(new HostEventCallback(this));
        return config;
    }

    @Override
    protected RePluginCallbacks createCallbacks() {
        return new HostCallbacks(this);
    }

    private class HostCallbacks extends RePluginCallbacks {

        public HostCallbacks(Context context) {
            super(context);
        }

        @Override
        public boolean onPluginNotExistsForActivity(Context context, String plugin, Intent intent, int process) {
            if (BuildConfig.DEBUG) {
                Log.e("mmz", "onPluginNotExistsForActivity : " + plugin);
            }

            return super.onPluginNotExistsForActivity(context, plugin, intent, process);
        }
    }

    private class HostEventCallback extends RePluginEventCallbacks {

        public HostEventCallback(Context context) {
            super(context);
        }

        @Override
        public void onInstallPluginFailed(String path, InstallResult code) {
            super.onInstallPluginFailed(path, code);

            if (BuildConfig.DEBUG) {
                Log.e("mmz", "onInstallPluginFailed : " + path + " with code : " + code.name());
            }
        }

        @Override
        public void onStartActivityCompleted(String plugin, String activity, boolean result) {
            super.onStartActivityCompleted(plugin, activity, result);
        }
    }

}
