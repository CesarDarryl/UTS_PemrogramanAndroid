package com.example.try_recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.PipedOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ExampleItem> mExampleList;

    private RecyclerView mRecyclerView; //this contain recylceview and re create on XML layout
    private ExampleAdapter mAdapter; // Bridge between Data and Aray List and RecycleView
    private RecyclerView.LayoutManager mLayourManager; //Responsible align single item

    private Button buttonInsert;
    private EditText ediTextInsert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();

        //BINDING
        buttonInsert = findViewById(R.id.button_insert);

        //onClickListener
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Here to get the position
//                int position = Integer.parseInt(ediTextInsert.getText().toString());
                int position = 0;
                //its Insert item
                insertItem(position);

                //this onClick is for adding the new Item. using setter getter from the Item
                //TODO :
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

    public void insertItem(int position)
    {
        //menambah ke Arraylist dikenali dengan nama mExampleList
        //.add (menambah), Posisi data yg akan di tambahkan
        //Berhubung ngambil Gambar, jadi di tambahkan gambar yg di ingin kan
        mExampleList.add(position, new ExampleItem(R.drawable.ic_android,"New Item At Position"+position, "This is line 2"));
        mAdapter.notifyItemInserted(position);

        //TODO : Inserting item Should open another Activity then pharsing the data
    }
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
        mExampleList.add(new ExampleItem(R.drawable.ic_android,"Line 1","line2"));
        mExampleList.add(new ExampleItem(R.drawable.ic_directions,"Line 3","line4"));
        mExampleList.add(new ExampleItem(R.drawable.ic_cake,"Line 5","line6"));
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
