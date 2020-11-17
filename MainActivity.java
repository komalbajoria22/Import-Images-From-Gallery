package com.komal.importimagesfromcamera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    public void getPhoto(){

    Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    startActivity(i);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if(requestCode==1){
        if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            getPhoto();

        }

    }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent= new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,1);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if(requestCode==1 && resultCode==RESULT_OK && data!=null){
                Uri selectedImages=data.getData();
                try {
                    Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImages);
                    imageView=(ImageView)findViewById(R.id.imageView);
                    imageView.setImageBitmap(bitmap);

                }
                catch (Exception e){
                    e.printStackTrace();
                }



            }
        }

    }
