package com.example.demoroomdatabase.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoroomdatabase.R;
import com.example.demoroomdatabase.data.AppDatabase;
import com.example.demoroomdatabase.data.entity.EmployeeEntity;
import com.example.demoroomdatabase.util.Util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EmployeesRecyclerAdapter.OnNoteListener ,EmployeeDialog.TranferDataEmploy {

    private AppDatabase mDb;
    private TextView tvEmploy;
    private EditText etName, etId, ettAdress;
    private EmployeesRecyclerAdapter employeesRecyclerAdapter;
    private RecyclerView rvEmployee;
    public static Date currentDate = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvEmployee = findViewById(R.id.rvEmployee);
        tvEmploy = findViewById(R.id.tvEmploy);
        etName = findViewById(R.id.etName);
        ettAdress = findViewById(R.id.etAdress);
        etId = findViewById(R.id.etId);
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());
        initRecyclerviewEmployee();

    }

    private void populateEmployList() {
        List<EmployeeEntity> employeeEntityList = mDb.employDao().findAllEmploySync();
        employeesRecyclerAdapter.setEmployeeArraylist(employeeEntityList);
    }

    public void save(View view) {
        String name = etName.getText().toString().trim();
        String address = ettAdress.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address)) {
            Toast.makeText(this, R.string.error_name_adress_empty, Toast.LENGTH_SHORT).show();
        } else {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.name = name;
            employeeEntity.adrress = address;
            employeeEntity.setCreatedAt(currentDate);
            mDb.employDao().insertEmploy(employeeEntity);
            Toast.makeText(this, R.string.label_save_success, Toast.LENGTH_SHORT).show();
            etName.setText("");
            ettAdress.setText("");
        }
    }

    public void getAll(View view) {
        populateEmployList();
    }

    public void deleteAll(View view) {
        mDb.employDao().deleteAll();
        populateEmployList();
    }

    public void findById(View view) {
        String id = etId.getText().toString().trim();
        tvEmploy.setText("");
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this, R.string.error_id_employee_empty, Toast.LENGTH_SHORT).show();
        }
        try {
            EmployeeEntity employeeEntity = mDb.employDao().findEmployById(Integer.parseInt(id));
            tvEmploy.append(getString(R.string.label_id) + employeeEntity.employId + "\n" +
                    getString(R.string.label_name) + employeeEntity.name + "\n" +
                    getString(R.string.label_address) +
                    employeeEntity.adrress +
                    "\n" +getString(R.string.label_created_date) + Util.getFormattedDateString(employeeEntity.getCreatedAt()));
            etId.setText("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void initRecyclerviewEmployee() {
        employeesRecyclerAdapter = new EmployeesRecyclerAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvEmployee.setLayoutManager(layoutManager);
        rvEmployee.setHasFixedSize(true);
        rvEmployee.setAdapter(employeesRecyclerAdapter);
    }

    @Override
    public void onItemClick(final EmployeeEntity employeeEntity) {
        EmployeeDialog employeeDialog = EmployeeDialog.newInstance(employeeEntity);
        employeeDialog.show(getSupportFragmentManager(),null);
        employeeDialog.setListener(this);
    }

    @Override
    public void onItemLongClick(final EmployeeEntity employeeEntity) {
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you want delete it ?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDb.employDao().deleteEmploy(employeeEntity);
                        populateEmployList();
                    }
                }).show();
    }

    @Override
    public void employee(EmployeeEntity employeeEntity) {
        mDb.employDao().updateEmploy(employeeEntity);
        populateEmployList();
    }
}
