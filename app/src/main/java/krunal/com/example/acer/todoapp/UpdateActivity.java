package krunal.com.example.acer.todoapp;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import krunal.com.example.acer.todoapp.Database.TaskEntity;

public class UpdateActivity extends AppCompatActivity {

    private FloatingActionButton mUpdatefab;
    private EditText mUpdateName;
    private TextView mSetTime;
    private UpdateActivityViewModel mUpdateActivityViewModel;
    private int getid;

    private int mHour;
    private int mMinte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mUpdatefab = findViewById(R.id.floatingActionButton2);
        mUpdateName = findViewById(R.id.UpdateName);
        mSetTime = findViewById(R.id.UpdateSetTime);

        getid = getIntent().getIntExtra(MainActivity.ID,-1);

        mUpdateActivityViewModel = ViewModelProviders.of(this).get(UpdateActivityViewModel.class);
        mUpdateActivityViewModel.QueryById(getid);

        try {
            Thread.sleep(300);

            mUpdateName.setText(UpdateActivityViewModel.TaskName);
            mSetTime.setText(UpdateActivityViewModel.Time);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mSetTime.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinte = calendar.get(Calendar.MINUTE);

            @SuppressLint("SetTextI18n") TimePickerDialog timePicker = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                String gethourOfDay = String.valueOf(hourOfDay);
                String getminute = String.valueOf(minute);

                mSetTime.setText(gethourOfDay+":" + getminute);

            },mHour,mMinte,false);
            timePicker.show();
        });

        mUpdatefab.setOnClickListener(v -> {
            String taskName = mUpdateName.getText().toString().trim();
            String setTime = mSetTime.getText().toString().trim();

            if (!TextUtils.isEmpty(taskName) && !TextUtils.isEmpty(setTime)){
                TaskEntity taskEntity =new TaskEntity(getid,taskName,setTime);
                mUpdateActivityViewModel.Update(taskEntity);
                Toast.makeText(this,"Update Successfully", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Error While Updateing",Toast.LENGTH_SHORT).show();
            }
            finish();
        });



    }
}
