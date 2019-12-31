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
    public EditText idEditText;
    public TextView TextView3,TextView4,TextView5;
    public TextView TextView7;
    public Button addbtn,showbtn;
    public Button updatebtn,deletebtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         databasehelper=new Databasehelper(this);
       SQLiteDatabase sqLiteDatabase =databasehelper.getWritableDatabase();
        nameEditText=findViewById(R.id.nameEditText);

        ageEditText=findViewById(R.id.ageEditText);
        idEditText=findViewById(R.id.idEditText);
        genderEditText=findViewById(R.id.genderEditText);

        TextView3=findViewById(R.id.textView3);
        TextView4=findViewById(R.id.textView4);
        TextView5=findViewById(R.id.textView5);
        TextView7=findViewById(R.id.textView7);
        addbtn=findViewById(R.id.addbtn);
        addbtn.setOnClickListener(this);
        showbtn=findViewById(R.id.showbtn);
        showbtn.setOnClickListener(this);
        updatebtn=findViewById(R.id.updatebtn);
        updatebtn.setOnClickListener(this);
        deletebtn=findViewById(R.id.deletebtn);
        deletebtn.setOnClickListener(this);

    }
    public void onClick(View view)
    {
        String name= nameEditText.getText().toString();
        String id= idEditText.getText().toString();
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
        if(view.getId()==R.id.updatebtn)
        {

            Boolean isUpdated=databasehelper.updateData(id,name,age,gender);
            if(isUpdated==true)
            {
                Toast.makeText(getApplicationContext(),"row is updated",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"row is  not updated",Toast.LENGTH_LONG).show();
            }
        }
        if(view.getId()==R.id.deletebtn)
        {

            int value=databasehelper.deleteData(id);
            if(value>0)
            {
                Toast.makeText(getApplicationContext(),"row is deleted",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"row is  not deleted",Toast.LENGTH_LONG).show();
            }
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
