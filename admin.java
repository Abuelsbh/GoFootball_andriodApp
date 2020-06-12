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
import android.widget.TextView;

public class admin extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase,sqLiteDatabasee,sqLiteDatabaseee;
    Cursor cursor;
    TextView txt;
    Button confirm,rejection,logout,viewall;
    int nameindex,locationindex,sizeindex,availavleindex,priceindex,cancelationfromindex,cancelationtoindex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txt=findViewById(R.id.txt);
        confirm=findViewById(R.id.confirm);
        rejection=findViewById(R.id.rejection);
        viewall=findViewById(R.id.viewallplayground);
        logout=findViewById(R.id.logout);

        sqLiteDatabase=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS playgroundsnotvirifay (Name VARCHAR,Location VARCHAR,Size VARCHAR,Avaliablehours VARCHAR,Price VARCHAR,Cancelactionfrom VARCHAR,Cancelactionto VARCHAR)");
        cursor=sqLiteDatabase.rawQuery("SELECT * FROM playgroundsnotvirifay",null);

        nameindex=cursor.getColumnIndex("Name");
        locationindex=cursor.getColumnIndex("Location");
        sizeindex=cursor.getColumnIndex("Size");
        availavleindex=cursor.getColumnIndex("Avaliablehours");
        priceindex=cursor.getColumnIndex("Price");
        cancelationfromindex=cursor.getColumnIndex("Cancelactionfrom");
        cancelationtoindex=cursor.getColumnIndex("Cancelactionto");
        if(cursor.moveToFirst())
        {
            txt.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+" to "+cursor.getInt(cancelationtoindex));

            sqLiteDatabasee=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
            sqLiteDatabasee.execSQL("CREATE TABLE IF NOT EXISTS playgroundvirifay (Name VARCHAR,Location VARCHAR,Size VARCHAR,Avaliablehours VARCHAR,Price VARCHAR,Cancelactionfrom VARCHAR,Cancelactionto VARCHAR)");

            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqLiteDatabasee.execSQL("INSERT INTO playgroundvirifay(Name,Location,Size,Avaliablehours,Price,Cancelactionfrom,Cancelactionto) VALUES ('" + cursor.getString(nameindex) + "','" + cursor.getString(locationindex) + "','" + cursor.getString(sizeindex) + "','" + cursor.getInt(availavleindex) + "','" + cursor.getInt(priceindex) + "','"+cursor.getInt(cancelationfromindex)+"','"+cursor.getInt(cancelationtoindex)+"')");
                    sqLiteDatabase.delete("playgroundsnotvirifay","Name='"+cursor.getString(nameindex)+"'",null);
                    if(cursor.moveToNext())
                    {
                        txt.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+" to "+cursor.getInt(cancelationtoindex));
                    }
                    else
                    {
                        txt.setText("");
                    }
                }
            });
            rejection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqLiteDatabase.delete("playgroundsnotvirifay","Name='"+cursor.getString(nameindex)+"'",null);
                    if(cursor.moveToNext())
                    {
                        txt.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+" to "+cursor.getInt(cancelationtoindex));
                    }
                    else
                    {
                        txt.setText("");
                    }
                }
            });
        }
        else
        {
            txt.setText("");
        }
        sqLiteDatabaseee=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allplaygoundintent=new Intent(admin.this,allplayground.class);
                startActivity(allplaygoundintent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainintent=new Intent(admin.this,MainActivity.class);
                sqLiteDatabaseee.execSQL("DELETE FROM stilllogin");
                startActivity(mainintent);
                finish();
            }
        });
    }
}
