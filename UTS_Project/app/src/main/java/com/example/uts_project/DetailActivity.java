package com.example.uts_project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    TextView title,description;
    ImageView mainImage;

    String data1,data2;
    int myimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = findViewById(R.id.textView_title1);
        description = findViewById(R.id.textView_description2);
        mainImage = findViewById(R.id.imageView3);

        getData();
        setData();
    }
    public void getData()
    {
        if(getIntent().hasExtra("myimage") && getIntent().hasExtra("data1")
        && getIntent().hasExtra("data2"))
        {
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            myimage = getIntent().getIntExtra("myimage",1);
        }else
            {
                Toast.makeText(this,"There's no Data", Toast.LENGTH_SHORT).show();
            }
    }

    public void setData()
    {
        title.setText(data1);
        description.setText(data2);
        mainImage.setImageResource(myimage);
    }
}
