package com.example.try_recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Activity2 extends AppCompatActivity {

    private static final String TAG = Activity2.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


        Intent intent = getIntent();
        ExampleItem exampleItem = intent.getParcelableExtra("Example Item");

        //use the data
        ExampleItem mExampleItem = null;
        String line1 = exampleItem.getmText1();
        String line2 = exampleItem.getmText2();

//        try {
////            ImageView imagview = findViewById(R.id.image_activity2);
////            Bitmap gambar = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mExampleItem.getPathImage());
////            imagview.setImageBitmap(gambar);
//
//        }catch (IOException e)
//        {
//            Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
//            Log.e(TAG, e.getMessage());
//        }
        TextView textView1 = findViewById(R.id.text1_activity2);
        textView1.setText(line1);

        TextView textView2 = findViewById(R.id.text2_activity2);
        textView2.setText(line2);
    }
}
