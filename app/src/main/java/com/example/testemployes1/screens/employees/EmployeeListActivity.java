package com.example.testemployes1.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.testemployes1.R;
import com.example.testemployes1.adapters.EmployeAdapter;
import com.example.testemployes1.pojo.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity implements EmployeesListView {

    private RecyclerView recycleViewEmployes;
    private EmployeAdapter adapter;
//    private EmployeeViewModel viewModel;
    private EmployeeListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new EmployeeListPresenter(this);
        recycleViewEmployes = findViewById(R.id.recycleViewEmployes);
        adapter = new EmployeAdapter();
        adapter.setEmployees(new ArrayList<Employee>());
        recycleViewEmployes.setLayoutManager(new LinearLayoutManager(this));
        recycleViewEmployes.setAdapter(adapter);
        presenter.loadData();
      /*  viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(EmployeeViewModel.class);
        //возвращаемы тип LiveData можем вызвать метод observe
        //подписываемся на getEmployees из EmployeeViewModel и следим из EmployeeListActivity за ее изменениями
        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
            }
        });

        viewModel.getError().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if(throwable!=null) {
                    Toast.makeText(EmployeeListActivity.this, "Error111", Toast.LENGTH_SHORT).show();
                    viewModel.clearErrors();
                }
            }
        });
        viewModel.loadData();*/
    }



    @Override
    protected void onDestroy() {
        presenter.disposableaDispose();
        super.onDestroy();
    }

    @Override
    public void showData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

    }
}
