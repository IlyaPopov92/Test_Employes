package com.example.testemployes1.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testemployes1.R;
import com.example.testemployes1.pojo.Employee;

import java.util.List;

public class EmployeAdapter extends RecyclerView.Adapter <EmployeAdapter.EmployeViewHolder> {

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public EmployeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employees_item, parent, false);
        return new EmployeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeViewHolder holder, int position) {
        Employee employe = employees.get(position);
        holder.textViewName.setText(employe.getName());
        holder.textViewLastName.setText(employe.getLName());

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class  EmployeViewHolder extends RecyclerView.ViewHolder{

        private  TextView textViewName;
        private TextView textViewLastName;

        public EmployeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewLastName = itemView.findViewById(R.id.textViewLastName);
        }
    }
}
