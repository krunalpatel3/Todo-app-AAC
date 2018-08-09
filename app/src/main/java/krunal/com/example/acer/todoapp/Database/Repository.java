package krunal.com.example.acer.todoapp.Database;



import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.util.List;

import krunal.com.example.acer.todoapp.AppExecutor;
import krunal.com.example.acer.todoapp.UpdateActivityViewModel;

/**
 * Created by acer on 10-02-2018.
 */

public class Repository {

    private static final String TAB = Repository.class.getSimpleName();

    private LiveData<List<TaskEntity>> mlist;

    private TaskEntity mTaskEntity;

    private AppExecutor mAppExecutor;

    private AppDatabase mAppdatabase;

    public Repository(Context context){
        Log.i(TAB,"Repository call");
        mAppExecutor = new AppExecutor();
        mAppdatabase = AppDatabase.getInstance(context);
        mlist = mAppdatabase.getdoa().getAllTask();
    }

    public LiveData<List<TaskEntity>> getMlist(){
        Log.i(TAB,"getmList call");
        return mlist;
    }

    public void gettaskByid(int id){
        mAppExecutor.diskIO().execute(()->{
           mTaskEntity = mAppdatabase.getdoa().getTaskByid(id);
            UpdateActivityViewModel.TaskName = mTaskEntity.getTaskname();
            UpdateActivityViewModel.Time = mTaskEntity.getTime();
        });
    }

    public void Update(TaskEntity taskEntity){
        mAppExecutor.diskIO().execute(()->{
            mAppdatabase.getdoa().Update(taskEntity);
        });
    }

    public void insert(TaskEntity task){
        new insertAsyTask(mAppdatabase).execute(task);
    }

    public void delete(TaskEntity task){
        new deleteAsytask(mAppdatabase).execute(task);
    }

    static class insertAsyTask extends AsyncTask<TaskEntity,Void,Void>{

        private AppDatabase db;

        insertAsyTask(AppDatabase db){
            this.db= db;
        }

        @Override
        protected Void doInBackground(TaskEntity... taskEntities) {
            db.getdoa().insertTask(taskEntities[0]);
            return null;
        }
    }

    static class deleteAsytask extends AsyncTask<TaskEntity,Void,Void>{

        private AppDatabase db;

        deleteAsytask(AppDatabase db){
            this.db= db;
        }

        @Override
        protected Void doInBackground(TaskEntity... taskEntities) {
            db.getdoa().deleteTask(taskEntities[0]);
            return null;
        }
    }

}
