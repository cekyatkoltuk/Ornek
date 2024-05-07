package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Camera extends AppCompatActivity {
    private static final int VIDEO_ACTION_CODE = 101;
    private static final int IMAGE_ACTION_CODE = 102;
    Button b5,b6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);

        b5.setOnClickListener(v ->
        {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent, IMAGE_ACTION_CODE);
        });

        b6.setOnClickListener(v ->
        {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            startActivityForResult(takePictureIntent, VIDEO_ACTION_CODE);
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode!=RESULT_OK) return;

        switch (requestCode){
            case VIDEO_ACTION_CODE:
                VideoView videoView = ((VideoView) findViewById(R.id.videoView));
                videoView.setVideoURI(data.getData());
                videoView.setMediaController(new MediaController(this));
                videoView.requestFocus();
                videoView.start();
                break;
            case IMAGE_ACTION_CODE:
                Bundle extras = data.getExtras();
                ((ImageView)findViewById(R.id.imageView)).setImageBitmap((Bitmap)extras.get("data"));
                break;
            default:
                break;
        }
    }
}
