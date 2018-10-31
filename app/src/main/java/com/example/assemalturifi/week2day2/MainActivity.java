package com.example.assemalturifi.week2day2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etSharedPreff;
    private TextView tvSharedPreff;
    private Button createStudent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        countRecords();

        readRecords();

    }
    public void bindViews(){

        createStudent=findViewById(R.id.createStudent);
        createStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getRootView().getContext();

                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

                final EditText editTextStudentFirstname = (EditText) formElementsView.findViewById(R.id.editTextStudentFirstname);
                final EditText editTextStudentEmail = (EditText) formElementsView.findViewById(R.id.editTextStudentEmail);

                new AlertDialog.Builder(context)
                        .setView(formElementsView)
                        .setTitle("Create Student")
                        .setPositiveButton("Add",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        dialog.cancel();
                                        String studentFirstname = editTextStudentFirstname.getText().toString();
                                        String studentEmail = editTextStudentEmail.getText().toString();

                                        Student student = new Student();
                                        student.name= studentFirstname;
                                        student.email= studentEmail;
                                        boolean createSuccessful = new TableControllerStudent(context).create(student);
                                        if(createSuccessful){
                                            Toast.makeText(context, "Student information was saved.", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(context, "Unable to save student information.", Toast.LENGTH_SHORT).show();
                                        }
                                        countRecords();
                                        ((MainActivity) context).readRecords();
                                    }


                                }).show();
            }
        });






        etSharedPreff=findViewById(R.id.etSharedPref);
        tvSharedPreff=findViewById(R.id.tvSharedPref);


    }


    public void onSharedPrefrences(View view) {

        SharedPreferences sharedPreferences=getSharedPreferences("MysharedPref", Context.MODE_PRIVATE);
        // this the way how you do the shared prefrences

        SharedPreferences.Editor editor=sharedPreferences.edit();

        switch (view.getId()){
            case R.id.btnSaveData:

                editor.putString("edittext",etSharedPreff.getText().toString());
                editor.apply();
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();;

                break;
            case R.id.btnGetData:

                String etValue=sharedPreferences.getString("edittext","Dafault String");
                tvSharedPreff.setText(etValue);
                Toast.makeText(this,"Data retrieved",Toast.LENGTH_SHORT).show();;

                break;
        }

    }
    public void countRecords() {
        int recordCount = new TableControllerStudent(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");

    }

    public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<Student> students = new TableControllerStudent(this).read();

        if (students.size() > 0) {

            for (Student obj : students) {

                int id = obj.id;
                String studentFirstname = obj.name;
                String studentEmail = obj.email;

                String textViewContents = studentFirstname + " - " + studentEmail;

                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewStudentItem);

                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerStudentRecord());
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }

}
//Week 2 Daily 4
//Create an application that have
//1. A database(choose any kind of database) that
//	a) should do CRUD operations (Create, read, update and delete)
//	b) should save an Image for each record as a BLOB
//	c) should have Auto-Increment Primary Key to retrieve a unique record.
//	d) should have the UI to save and retrieve the record with image from db.
//2. Feature to write and read from files
//	a) write any simple string data to a file
//	b) read same data from the file and update the view
//	c) should have class to encapsulate the read/write operation.
//Use Best Practices
