package krunal.com.example.acer.todoapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import krunal.com.example.acer.todoapp.Database.Repository;
import krunal.com.example.acer.todoapp.Database.TaskEntity;

/**
 * Created by acer on 26-03-2018.
 */

public class UpdateActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    public static String TaskName;
    public static String Time;

    public UpdateActivityViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new Repository(application);
    }

    void QueryById(int id){
        mRepository.gettaskByid(id);
    }

    void Update(TaskEntity taskEntity){
        mRepository.Update(taskEntity);
    }

}
