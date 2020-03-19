package com.example.demoroomdatabase.presentation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.demoroomdatabase.R;
import com.example.demoroomdatabase.data.entity.EmployeeEntity;

import static com.example.demoroomdatabase.presentation.MainActivity.currentDate;

public class EmployeeDialog extends DialogFragment {

    private EditText etName, etAdress;
    private TranferDataEmploy tranferDataEmploy;

    public static EmployeeDialog newInstance(EmployeeEntity employeeEntity) {
        EmployeeDialog dialog = new EmployeeDialog();
        Bundle args = new Bundle();
        args.putSerializable("data", employeeEntity);
        dialog.setArguments(args);
        return dialog;
    }

    public void setListener(TranferDataEmploy tranferDataEmploy) {
        this.tranferDataEmploy = tranferDataEmploy;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_dialog_employee, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCancelable(false);

        final EmployeeEntity employeeEntity = (EmployeeEntity) getArguments().getSerializable("data");
        etName = view.findViewById(R.id.etName);
        etAdress = view.findViewById(R.id.etAdress);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnOk = view.findViewById(R.id.btnOk);

        etName.setText(employeeEntity.name);
        etAdress.setText(employeeEntity.adrress);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EmployeeEntity employeeEntity1 = new EmployeeEntity();
                employeeEntity1.setName(etName.getText().toString());
                employeeEntity1.setAdrress(etAdress.getText().toString());
                employeeEntity1.setCreatedAt(currentDate);
                employeeEntity1.setEmployId(employeeEntity.employId);
                tranferDataEmploy.employee(employeeEntity1);
                dismiss();
            }
        });
    }

    public interface TranferDataEmploy {
        void employee(EmployeeEntity employeeEntity);
    }
}
