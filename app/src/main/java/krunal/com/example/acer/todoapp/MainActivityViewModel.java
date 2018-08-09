package krunal.com.example.acer.todoapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;


import java.util.List;


import krunal.com.example.acer.todoapp.Database.Repository;
import krunal.com.example.acer.todoapp.Database.TaskEntity;

/**
 * Created by acer on 10-02-2018.
 */

public class MainActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    private static final String TAB4 = MainActivityViewModel.class.getSimpleName();

    private LiveData<List<TaskEntity>> mtasklist;

    public MainActivityViewModel(Application application){
        super(application);
        Log.i(TAB4,"MainActivityViewModel call");
        this.mRepository = new Repository(application);
        this.mtasklist = mRepository.getMlist();
    }

    public LiveData<List<TaskEntity>> getTasklist(){
        Log.i(TAB4,"getTaskList call");
        return mtasklist;
    }

    public void delete(TaskEntity task){
        mRepository.delete(task);
    }

}
