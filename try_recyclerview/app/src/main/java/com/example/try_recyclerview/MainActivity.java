package com.example.try_recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private Button buttonRemove;
    private EditText ediTextInsert;
    private EditText editTextRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createExampleList();
        buildRecyclerView();

        //BINDING
        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        ediTextInsert = findViewById(R.id.edittext_insert);
        editTextRemove = findViewById(R.id.edittext_remove);

        //onClickListener
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Here to get the position
                int position = Integer.parseInt(ediTextInsert.getText().toString());
                //its Insert item
                insertItem(position);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                //its Remove
                removeItem(position);
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
    }

    public void insertItem(int position)
    {
        //menambah ke Arraylist dikenali dengan nama mExampleList
        //.add (menambah), Posisi data yg akan di tambahkan
        //Berhubung ngambil Gambar, jadi di tambahkan gambar yg di ingin kan
        mExampleList.add(position, new ExampleItem(R.drawable.ic_android,"New Item At Position"+position, "This is line 2"));
        mAdapter.notifyItemInserted(position);
    }
    public void removeItem(int position)
    {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeItem(int position, String text)
    {
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }

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
                changeItem(position,"Clicked");
            }
        });
    }
}
//TODO : HAD SOME MUCH TO DO !
