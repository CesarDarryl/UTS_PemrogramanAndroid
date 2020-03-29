package com.example.try_recyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;

    private static final String TITLE = "title";
    private static final String SUB_TITLE = "sub_title";
    public static final String KEY = "KEY";
    public static final int INSERT_REQUEST = 1;
    public static final String INDEX_KEY = "INDEX";
//    public static final String IMAGE_KEY = "image";
    private static final String TAG = RegisterActivity.class.getCanonicalName();

    private RecyclerView mRecyclerView; //this contain recylceview and re create on XML layout
    private ExampleAdapter mAdapter; // Bridge between Data and Aray List and RecycleView
    private RecyclerView.LayoutManager mLayourManager; //Responsible align single item

    ExampleItem mExampleItem;
    private TextView baris1;
    private TextView baris2;
//    private ImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baris1 = findViewById(R.id.textView1);
        baris2 = findViewById(R.id.textView2);
//        avatar = findViewById(R.id.imageView);

        createExampleList();
        buildRecyclerView();

        //GET THE ITEM BUNDLE
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            baris1.setText(extras.getString(TITLE));
            baris2.setText(extras.getString(SUB_TITLE));
        }

        //onClickListener
        FloatingActionButton fab = findViewById(R.id.fab_button_insert);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Here to get the position
//                int position = Integer.parseInt(ediTextInsert.getText().toString());
                int position = 0;
                //its Insert item
//                insertItem(position);

                //this onClick is for adding the new Item. using setter getter from the Item
                //TODO : using startActivityForResult
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
//                intent.putExtra(KEY,new ExampleItem());
//                startActivityForResult(intent,INSERT_REQUEST);
            }
        });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback  = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)  {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mAdapter.notifyDataSetChanged();
                removeItem(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode == RESULT_OK)
//        {
//            ExampleItem mExampleItem = data.getParcelableExtra(KEY);
//            if(requestCode == INSERT_REQUEST)
//            {
//                mAdapter.notifyDataSetChanged();
//                //TODO : Fix the Null Exception , because they dont know where exatcly the Filed is
//                baris1.setText(String.valueOf(mExampleItem.getmText1()));
//                baris2.setText(String.valueOf(mExampleItem.getmText2()));
////                try
////                {
////                    mAdapter.notifyDataSetChanged();
////                    baris1.setText(String.valueOf(mExampleItem.getmText1()));
////                    baris2.setText(String.valueOf(mExampleItem.getmText2()));
//////                    Bitmap gambar = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mExampleItem.getPathImage());
//////                    avatar.setImageBitmap(gambar);
////                }catch (IOException e)
////                {
////                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
////                    Log.e(TAG, e.getMessage());
////                }
//            }
//        }
//    }
//    public void insertItem(int position)
//    {
//        //menambah ke Arraylist dikenali dengan nama mExampleList
//        //.add (menambah), Posisi data yg akan di tambahkan
//        //Berhubung ngambil Gambar, jadi di tambahkan gambar yg di ingin kan
//        mExampleList.add(position, new ExampleItem(R.drawable.ic_android,"New Item At Position"+position, "This is line 2"));
//        mAdapter.notifyItemInserted(position);
//
//        //TODO : Inserting item Should open another Activity then pharsing the data
//    }
    public void removeItem(int position)
    {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    //when clicked the item .
//    public void changeItem(int position, String text)
//    {
//        mExampleList.get(position).changeText1(text);
//        mAdapter.notifyItemChanged(position);
//    }

    public void createExampleList(){
        mExampleList =  new ArrayList<>();
//        mExampleList.add(new ExampleItem(R.drawable.ic_android,"Line 1","line2"));
//        mExampleList.add(new ExampleItem(R.drawable.ic_directions,"Line 3","line4"));
//        mExampleList.add(new ExampleItem(R.drawable.ic_cake,"Line 5","line6"));
    }

    public void buildRecyclerView(){
        //TODO : Initialize the recyclerview
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);//This important
        mLayourManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayourManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //this when your Clicked the item. it should open New Intent
                Intent intent = new Intent(MainActivity.this,Activity2.class);
                intent.putExtra("Example Item",mExampleList.get(position));
                startActivity(intent);
                //it should open The Acivity but with the value of deafult from the item
            }
        });
    }
}
//TODO : HAD SOME MUCH TO DO !
