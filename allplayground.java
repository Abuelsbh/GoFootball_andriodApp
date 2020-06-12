package com.example.gofootball;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class allplayground extends AppCompatActivity {

    ArrayList <String> names=new ArrayList<>();
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    Button ground1,ground2,ground3,ground4,ground5,ground6,ground7;
    ImageView delete1,delete2,delete3,delete4,delete5,delete6,delete7;
    int nameindex,locationindex,sizeindex,availavleindex,priceindex,cancelationfromindex,cancelationtoindex,counter=0;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allplayground);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ground1=findViewById(R.id.ground1);
        ground2=findViewById(R.id.ground2);
        ground3=findViewById(R.id.ground3);
        ground4=findViewById(R.id.ground4);
        ground5=findViewById(R.id.ground5);
        ground6=findViewById(R.id.ground6);
        ground7=findViewById(R.id.ground7);
        delete1=findViewById(R.id.delete1);
        delete2=findViewById(R.id.delete2);
        delete3=findViewById(R.id.delete3);
        delete4=findViewById(R.id.delete4);
        delete5=findViewById(R.id.delete5);
        delete6=findViewById(R.id.delete6);
        delete7=findViewById(R.id.delete7);

        sqLiteDatabase=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS playgroundvirifay (Name VARCHAR,Location VARCHAR,Size VARCHAR,Avaliablehours VARCHAR,Price VARCHAR,Cancelactionfrom VARCHAR,Cancelactionto VARCHAR)");
        cursor=sqLiteDatabase.rawQuery("SELECT * FROM playgroundvirifay",null);

        nameindex=cursor.getColumnIndex("Name");
        locationindex=cursor.getColumnIndex("Location");
        sizeindex=cursor.getColumnIndex("Size");
        availavleindex=cursor.getColumnIndex("Avaliablehours");
        priceindex=cursor.getColumnIndex("Price");
        cancelationfromindex=cursor.getColumnIndex("Cancelactionfrom");
        cancelationtoindex=cursor.getColumnIndex("Cancelactionto");

        if(cursor.moveToFirst())
        {
            counter++;
            ground1.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+" to "+cursor.getInt(cancelationtoindex));
            names.add(cursor.getString(nameindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            ground2.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
            names.add(cursor.getString(nameindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            ground3.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
            names.add(cursor.getString(nameindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            ground4.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
            names.add(cursor.getString(nameindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            ground5.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
            names.add(cursor.getString(nameindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            ground6.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
            names.add(cursor.getString(nameindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            ground7.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
            names.add(cursor.getString(nameindex));
        }




        if(counter>=1)
        {
            delete1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name=names.get(0);
                    delete();
                }
            });

        }
        if(counter>=2)
        {
            delete2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name=names.get(1);
                    delete();
                }
            });
        }
        if(counter>=3)
        {
            delete3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name=names.get(2);
                    delete();
                }
            });
        }
        if(counter>=4)
        {
            delete4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name=names.get(3);
                    delete();
                }
            });
        }
        if(counter>=5)
        {
            delete5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name=names.get(4);
                    delete();
                }
            });
        }
        if(counter>=6)
        {
            delete6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name=names.get(5);
                    delete();
                }
            });
        }
        if(counter>=7)
        {
            delete7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name=names.get(6);
                    delete();
                }
            });

        }
    }

    private void delete()
    {
        sqLiteDatabase.delete("playgroundvirifay","Name='"+name+"'",null);
        Intent admin=new Intent(allplayground.this, com.example.gofootball.admin.class);
        startActivity(admin);
        finish();
    }
}
