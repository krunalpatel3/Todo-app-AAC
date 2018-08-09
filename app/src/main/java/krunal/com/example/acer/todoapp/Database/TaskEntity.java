package krunal.com.example.acer.todoapp.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by acer on 10-02-2018.
 */
@Entity(tableName = "Taskmanager")
public class TaskEntity {

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Task_name")
    private String taskname;

    @ColumnInfo(name = "Time")
    private String time;

    public TaskEntity(String taskname, String time) {
        this.taskname = taskname;
        this.time = time;
    }

    @Ignore
    public TaskEntity(int id,String taskname,String time){
        this.id = id;
        this.taskname = taskname;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTaskname() {
        return taskname;
    }

    public String getTime() {
        return time;
    }
}
