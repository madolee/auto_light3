package com.example.auto_light3;import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.media.AudioManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private CameraManager cameraManager;
    private String cameraID;
    private AudioManager audio;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);


        try {
            cameraID = cameraManager.getCameraIdList()[0]; // 0 is for back camera
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try{
            cameraManager.setTorchMode(cameraID, true);
            audio.setStreamVolume(AudioManager.STREAM_MUSIC,
                    (int) (audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC) * 0.95),
                    AudioManager.FLAG_PLAY_SOUND);

            Thread.sleep(20000_000);
            cameraManager.setTorchMode(cameraID, false);
            audio.setStreamVolume(AudioManager.STREAM_MUSIC,
                    (int) (audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC) * 0.15),
                    AudioManager.FLAG_PLAY_SOUND);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ActivityCompat.finishAffinity(this);
        System.exit(0);

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void TorchONButtonClick(View view){
        try {
            cameraManager.setTorchMode(cameraID, true);
            audio.setStreamVolume(AudioManager.STREAM_MUSIC,
                    (int) (audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC) * 0.95),
                    AudioManager.FLAG_PLAY_SOUND);

            Thread.sleep(30000_000);

            cameraManager.setTorchMode(cameraID, false);

            Thread.sleep(2000_000);
            audio.setStreamVolume(AudioManager.STREAM_MUSIC,
                    (int) (audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC) * 0.15),
                    AudioManager.FLAG_PLAY_SOUND);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void TorchOFFButtonClick(View view){
        try {
            cameraManager.setTorchMode(cameraID, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}