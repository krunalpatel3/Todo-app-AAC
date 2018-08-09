package krunal.com.example.acer.todoapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.util.Log;


import org.w3c.dom.Text;

import krunal.com.example.acer.todoapp.Database.Repository;
import krunal.com.example.acer.todoapp.Database.TaskEntity;

/**
 * Created by acer on 10-02-2018.
 */

public class AddActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    private static final String TAB2= AddActivityViewModel.class.getSimpleName();

    public AddActivityViewModel(Application application){
        super(application);
        Log.i(TAB2,"AddActivityViewModel call");
        this.mRepository = new Repository(application);
    }

    public void insert(TaskEntity task){
        Log.i(TAB2,"insert call");
        mRepository.insert(task);
    }
}
