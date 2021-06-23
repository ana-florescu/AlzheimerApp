package com.example.alzheimerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alzheimerapp.PillsActivity;
import com.example.alzheimerapp.databasePills.EntityClass;
import com.example.alzheimerapp.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    Context context;
    List<EntityClass> entityClasses;
    EntityClass ec;


    public EventAdapter(Context context, List<EntityClass> entityClasses) {
        this.context = context;
        this.entityClasses = entityClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listings_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.eventText.setText(entityClasses.get(position).getEventname());
        holder.timeAndDateText.setText(entityClasses.get(position).getEventdate() + " " + entityClasses.get(position).getEventtime());
    }

    @Override
    public int getItemCount() {
        return entityClasses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView eventText, timeAndDateText;
        private Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventText = (TextView) itemView.findViewById(R.id.event);
            timeAndDateText = (TextView) itemView.findViewById(R.id.time_and_date);
            //delete = (Button)itemView.findViewById(R.id.btnDelete);
          //  delete.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            EntityClass ec = new EntityClass();
            int ID = entityClasses.get(getAdapterPosition()).getId();
            ec.setId(ID);
           // removeAt(getAdapterPosition());
//            PillsActivity.data
        }

    }

    public void removeAt(int position) {
        entityClasses.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, entityClasses.size());
    }
}


