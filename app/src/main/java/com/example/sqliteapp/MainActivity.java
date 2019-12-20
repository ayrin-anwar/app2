package com.example.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.StringBuffer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Databasehelper databasehelper;
    public EditText nameEditText,ageEditText,genderEditText;
    public TextView TextView3,TextView4,TextView5;
    public Button addbtn,showbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         databasehelper=new Databasehelper(this);
       SQLiteDatabase sqLiteDatabase =databasehelper.getWritableDatabase();
        nameEditText=findViewById(R.id.nameEditText);

        ageEditText=findViewById(R.id.ageEditText);
        genderEditText=findViewById(R.id.genderEditText);

        TextView3=findViewById(R.id.textView3);
        TextView4=findViewById(R.id.textView4);
        TextView5=findViewById(R.id.textView5);
        addbtn=findViewById(R.id.addbtn);
        addbtn.setOnClickListener(this);
        showbtn=findViewById(R.id.showbtn);
        showbtn.setOnClickListener(this);




    }
    public void onClick(View view)
    {
        String name= nameEditText.getText().toString();

        String age= ageEditText.getText().toString();
        String gender= genderEditText.getText().toString();
        if(view.getId()==R.id.addbtn)
        {
            long rowId=databasehelper.insertData(name,age,gender);
            if(rowId>0)
            {
                Toast.makeText(getApplicationContext(),"row "+rowId+" is inserted",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"row is  not inserted",Toast.LENGTH_LONG).show();
            }
        }
        if(view.getId()==R.id.showbtn)
        {
            Cursor cursor=databasehelper.displayAllData();
            if(cursor.getCount()==0)
            {
                showData("error","no data found");
                return;
            }

            StringBuffer stringbuffer =new StringBuffer();
            while(cursor.moveToNext())
            {
                stringbuffer.append("ID:"+cursor.getString(0)+"\n") ;
                stringbuffer.append("NAME:"+cursor.getString(1)+"\n") ;
                stringbuffer. append("AGE:" + cursor.getString(2) + "\n");
                stringbuffer.append("GENDER:"+cursor.getString(3)+"\n") ;
            }
            showData("Resultset",stringbuffer.toString());
        }


    }
    public void showData(String title,String message)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }


}
