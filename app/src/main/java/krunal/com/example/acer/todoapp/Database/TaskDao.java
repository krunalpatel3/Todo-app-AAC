package krunal.com.example.acer.todoapp.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by acer on 10-02-2018.
 */
@Dao
public interface TaskDao {

    @Query("select * from Taskmanager")
    LiveData<List<TaskEntity>> getAllTask();

    @Query("select * from Taskmanager where ID = :id")
    TaskEntity getTaskByid(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(TaskEntity taskEntity);

    @Delete
    void deleteTask(TaskEntity taskEntity);

    @Update
    void Update(TaskEntity taskEntity);
}
