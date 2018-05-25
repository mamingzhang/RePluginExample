package com.horsege.host.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.horsege.host.R;
import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.externalPluginBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.externalPluginBtn: {
                installExternalPlugin();
                break;
            }
        }
    }

    private void installExternalPlugin() {
        String demo1PluginPath = getFilesDir() + File.separator + "plugin1.apk";
        File demo1PluginFile = new File(demo1PluginPath);
        if (demo1PluginFile.exists()) {
            demo1PluginFile.delete();
        }

        String demo1ApkPath = "external" + File.separator + "plugin1.apk";

        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = getAssets().open(demo1ApkPath);
            outputStream = openFileOutput("plugin1.apk", Context.MODE_PRIVATE);
            int count = 0;
            byte[] buffer = new byte[1024];
            while ((count = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        PluginInfo pluginInfo = null;
        if (demo1PluginFile.exists()) {
            pluginInfo = RePlugin.install(demo1PluginPath);
        }

        if (pluginInfo != null) {
            RePlugin.startActivity(this, RePlugin.createIntent(pluginInfo.getName(), "com.horsege.plugin1.activity.MainActitity"));
        }
    }
}
