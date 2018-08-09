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

public class AddActivity extends AppCompatActivity {

    private AddActivityViewModel mAddActivityViewModel;
    private static int mHour,mMinte;
    private TextView mSettime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        FloatingActionButton mfab =findViewById(R.id.floatingActionButton32);
        mAddActivityViewModel = ViewModelProviders.of(this).get(AddActivityViewModel.class);
        EditText mtaskname = findViewById(R.id.editText);
        mSettime = findViewById(R.id.settimetextview);

        mSettime.setOnClickListener((View n) ->{

            final Calendar calendar = Calendar.getInstance();
            mHour = calendar.get(Calendar.HOUR_OF_DAY);
            mMinte = calendar.get(Calendar.MINUTE);

            @SuppressLint("SetTextI18n") TimePickerDialog timePicker = new TimePickerDialog(this, (view, hourOfDay, minute) -> {
                String gethourOfDay = String.valueOf(hourOfDay);
                String getminute = String.valueOf(minute);

                mSettime.setText(gethourOfDay+":" + getminute);

            },mHour,mMinte,false);
            timePicker.show();
        });

        mfab.setOnClickListener((v) -> {
            String taskname = mtaskname.getText().toString().trim();
            String time = mSettime.getText().toString().trim();
            String content = "Set Time";

            if (TextUtils.isEmpty(taskname) || TextUtils.isEmpty(time) || time.equals(content)){
                Toast.makeText(getBaseContext(), " Not Added" ,Toast.LENGTH_LONG).show();
                finish();
            }else {
                mAddActivityViewModel.insert(new TaskEntity(taskname,time));
                Toast.makeText(getBaseContext(), "Added Successfully" ,Toast.LENGTH_LONG).show();
                finish();
            }

        });
    }
}
