package krunal.com.example.acer.todoapp;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;



public class MainActivity extends AppCompatActivity {

    private static final String TAB6 = MainActivity.class.getSimpleName();
    private MainActivityViewModel mMainActivityViewModel;

    private FloatingActionButton mfab;
    public static final String ID = "id";
    private RecycleAdapter mRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mfab = findViewById(R.id.floatingActionButton);
        mRecycleAdapter = new RecycleAdapter(this);
        RecyclerView mrecycleview = findViewById(R.id.recyclerView);
        mrecycleview.setLayoutManager(new LinearLayoutManager(this));
        mrecycleview.setAdapter(mRecycleAdapter);
        mRecycleAdapter.SetOnClickListener(taskEntity -> {
            int getid = taskEntity.getId();

            Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
            intent.putExtra(ID,getid);
            startActivity(intent);

        });
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mfab.setOnClickListener((v)->{
            Intent intent = new Intent(MainActivity.this,AddActivity.class);
            startActivity(intent);
        });


        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mMainActivityViewModel.delete(mRecycleAdapter.get(viewHolder.getAdapterPosition()));
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mrecycleview);

        mMainActivityViewModel.getTasklist().observe(this, taskEntities -> {
            Log.i(TAB6,"onChange call");
            mRecycleAdapter.addtask(taskEntities);
        });
    }
}
