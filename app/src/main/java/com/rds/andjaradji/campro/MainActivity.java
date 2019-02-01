package com.rds.andjaradji.campro;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView camImage;
    private Button  takePhotoBtn;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camImage = (ImageView)findViewById(R.id.camImageID);
        takePhotoBtn = (Button)findViewById(R.id.buttonTakePhotoID);

        if (!hasCamera()){
            takePhotoBtn.setEnabled(false);
        }

    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void launchCamera(View view){
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camIntent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode ==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){
            Bundle camExtras = data.getExtras();
            Bitmap photo = (Bitmap)camExtras.get("data");
            camImage.setImageBitmap(photo);
        }
    }
}
