package com.example.try_recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    private static final int GALERY_REQUEST_CODE = 1;
    public static final String IMAGE_KEY = "image";
    private static final String TAG = RegisterActivity.class.getCanonicalName();


    private Uri imageUri;
    private Bitmap bitmap = null;
    private EditText line1;
    private EditText line2;
    private ImageView image;
    private int index;
    ExampleItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Binding with the .xml
        line1 = findViewById(R.id.data_input1);
        line2 = findViewById(R.id.data_input2);
        image = findViewById(R.id.avatar);

        //Building parcelable
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            item = extras.getParcelable(MainActivity.KEY);
            index = extras.getInt(MainActivity.INDEX_KEY, 0 );
            line1.setText(item.getmText1());
            line2.setText(item.getmText2());
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        if(resultCode == RESULT_CANCELED)
////        {
////            return;
////        }
////        if(requestCode == GALERY_REQUEST_CODE)
////        {
////            if(data != null)
////            {
////                try
////                {
////                    imageUri = data.getData();
////                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
////                    image.setImageBitmap(bitmap);
////                }
////                catch (IOException e) {
////                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
////                    Log.e(TAG,e.getMessage());
////                }
////            }
////        }
//    }

//    public void handleChangeAvatar(View view) {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, GALERY_REQUEST_CODE);
//    }

    public void handleSubmit(View view) {
        String txt1 = line1.getText().toString();
        String txt2 = line2.getText().toString();
//        Uri path = imageUri;

        Intent intent = new Intent();

        if(!txt1.isEmpty() || !txt2.isEmpty())
        {
            intent.putExtra(MainActivity.KEY, item);
            intent.putExtra(MainActivity.INDEX_KEY, index);
//            intent.putExtra(MainActivity.IMAGE_KEY,path);
            setResult(RESULT_OK,intent);
            finish();
        }else
            {
                Toast.makeText(this, "Data harus di lengkapi", Toast.LENGTH_SHORT).show();
            }
//        ExampleItem exampleItem = new ExampleItem(path,txt1,txt2);
    }
}
