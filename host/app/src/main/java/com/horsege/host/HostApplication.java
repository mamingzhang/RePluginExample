package com.horsege.host;

import android.content.Context;
import android.content.Intent;

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
            return super.onPluginNotExistsForActivity(context, plugin, intent, process);
        }
    }

    private class HostEventCallback extends RePluginEventCallbacks{

        public HostEventCallback(Context context) {
            super(context);
        }
    }

}
