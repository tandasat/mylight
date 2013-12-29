package com.example.mylight;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Bundle;
import android.view.Window;


public class MainActivity extends Activity {

    private Camera camera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // http://micheleschimd.wordpress.com/2012/11/14/turn-on-flash-led-on-samsung-galaxy-ace/
        camera = Camera.open();
        Camera.Parameters camParams = camera.getParameters();
        camParams.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
        camera.setParameters(camParams);
        MyAutoFocusCallback autoFocusCallback = new MyAutoFocusCallback();
        camera.autoFocus(autoFocusCallback);
        camera.startPreview();
        camParams = camera.getParameters();
        camParams.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(camParams);
    }

    public class MyAutoFocusCallback implements AutoFocusCallback {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        camera.release();
    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }
}
