package com.demba.mediquick.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demba.mediquick.R;
import com.demba.mediquick.diseases.CreateUpdateSymptoms;
import com.demba.mediquick.models.Disease;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.ViewHolder> {

//    Declare variables
    Context context;
    ArrayList<Disease> list;

    public DiseaseAdapter(Context context, ArrayList<Disease> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.diseases,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Disease disease = list.get(position);
        holder.disease_id.setText(disease.getDisease_id());
        holder.recycler_disease_name.setText(disease.getDisease_name());
        holder.txt_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,holder.txt_options);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_edit:
                                Intent intent =  new Intent(context, CreateUpdateSymptoms.class);
                                context.startActivity(intent);
                                break;
                            case R.id.menu_delete:

                                break;
                        }
                        return false;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView  recycler_disease_name,txt_options,disease_id;
//        String dis_id = disease_id.getText().toString();
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        recycler_disease_name = itemView.findViewById(R.id.disease_name);
        txt_options = itemView.findViewById(R.id.txt_options);
        disease_id = itemView.findViewById(R.id.disease_id);
        }
    }
}
