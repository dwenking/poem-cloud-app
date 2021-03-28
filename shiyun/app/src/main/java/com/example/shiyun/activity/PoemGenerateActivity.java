package com.example.shiyun.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.example.shiyun.R;
import com.example.shiyun.poemGenerate.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PoemGenerateActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;

    private ImageView picture;

    private Uri imageUri;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_generate);
        Button takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);


        takePhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                File outputImage = new File(getExternalCacheDir(),
                        "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(PoemGenerateActivity.this,
                            "com.example.cameraalbumtest.fileprovider", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }

                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        InputStream is = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(is);
                        picture.setImageBitmap(bitmap);


                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);

                        is = getContentResolver().openInputStream(imageUri);
                        List<String> my_poem = Main.main(is);
                        textView = (TextView) findViewById(R.id.poem_text);
                        String poem_to_show = "";
                        for (int i = 0; i < my_poem.size(); i++)
                        {
                            poem_to_show += my_poem.get(i);
                            if (i == 0 || i == 2)
                                poem_to_show += "\n";
                        }
                        textView.setText(poem_to_show);

//                        new Thread(new Runnable(){
//                            @Override
//                            public void run() {
//
//                            }
//                        }).start();



                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }


}