package krunal.com.example.acer.todoapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import krunal.com.example.acer.todoapp.Database.TaskEntity;

/**
 * Created by acer on 10-02-2018.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleView>{

    private Context context;
    private LayoutInflater mlayoutInflater;
    private List<TaskEntity> mlistTask = new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;

    RecycleAdapter(Context context){
        this.context = context;
        this.mlayoutInflater = LayoutInflater.from(context);
    }

    public void SetOnClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecycleView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mlayoutInflater.inflate(R.layout.list,parent,false);
        return new RecycleView(view);
    }

    @Override
    public void onBindViewHolder(RecycleView holder, int position) {
        TaskEntity task = mlistTask.get(position);
        holder.mtaskname.setText(task.getTaskname());
        holder.mtime.setText(task.getTime());
        holder.Bind(mlistTask.get(position),mOnItemClickListener);
    }


    @Override
    public int getItemCount() {
        return mlistTask.size();
    }

    TaskEntity get(int position){
        return mlistTask.get(position);
    }

    void addtask(List<TaskEntity> addtask){
        this.mlistTask = addtask;
        notifyDataSetChanged();
    }

    static class RecycleView extends RecyclerView.ViewHolder{

        TextView mtaskname;
        TextView mtime;

        RecycleView(View itemView) {
            super(itemView);
            mtaskname = itemView.findViewById(R.id.name);
            mtime = itemView.findViewById(R.id.time);
        }

        void Bind(TaskEntity taskEntity, OnItemClickListener listener){
            itemView.setOnClickListener(v -> {
                listener.OnClick(taskEntity);
            });
        }
    }
}
