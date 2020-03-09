package com.example.demoroomdatabase.presentation;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.demoroomdatabase.R;
import com.example.demoroomdatabase.data.entity.EmployeeEntity;
import com.example.demoroomdatabase.util.Util;

import java.util.ArrayList;
import java.util.List;

public class EmployeesRecyclerAdapter extends RecyclerView.Adapter<EmployeesRecyclerAdapter.ViewHolder> {

    private List<EmployeeEntity> employeeEntities;
    private OnNoteListener mOnNoteListener;

    public EmployeesRecyclerAdapter( OnNoteListener onNoteListener) {
        this.employeeEntities = new ArrayList<>();
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_employee_list_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        EmployeeEntity employeeEntity = employeeEntities.get(position);
        holder.tvId.setText("ID :"+ employeeEntity.employId);
        holder.tvAddress.setText("Address :"+ employeeEntity.adrress);
        holder.tvName.setText("Name : " + employeeEntity.name);
        holder.tvCreateDate.setText("Created date : "+ Util.getFormattedDateString(employeeEntity.getCreatedAt()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnNoteListener != null) {
                    mOnNoteListener.onItemClick(employeeEntities.get(position));
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnNoteListener != null) {
                    mOnNoteListener.onItemLongClick(employeeEntities.get(position));
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeEntities.size();
    }

   public void setEmployeeArraylist(List<EmployeeEntity> employeeEntities){
        this.employeeEntities = employeeEntities;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvId, tvName, tvAddress,tvCreateDate;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvCreateDate = itemView.findViewById(R.id.tvCreateDate);
        }

    }

    public interface OnNoteListener {
        void onItemClick(EmployeeEntity employeeEntity);
        void onItemLongClick(EmployeeEntity employeeEntity);
    }

}
