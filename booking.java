package com.example.gofootball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class booking extends AppCompatActivity {

    NavigationView nvDrawer;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    String currentaccount,playgroundname;
    ArrayList<String> playgroundnames=new ArrayList<>();
    Button ground1,ground2,ground3,ground4,ground5,ground6,ground7;
    SQLiteDatabase sqLiteDatabase,sqLiteDatabasee;
    Cursor cursor;
    int nameindex,locationindex,sizeindex,availavleindex,priceindex,cancelationfromindex,cancelationtoindex,counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ground1=findViewById(R.id.ground1);
        ground2=findViewById(R.id.ground2);
        ground3=findViewById(R.id.ground3);
        ground4=findViewById(R.id.ground4);
        ground5=findViewById(R.id.ground5);
        ground6=findViewById(R.id.ground6);
        ground7=findViewById(R.id.ground7);

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
            playgroundnames.add(cursor.getString(nameindex));
            ground1.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+" to "+cursor.getInt(cancelationtoindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            playgroundnames.add(cursor.getString(nameindex));
            ground2.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            playgroundnames.add(cursor.getString(nameindex));
            ground3.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            playgroundnames.add(cursor.getString(nameindex));
            ground4.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            playgroundnames.add(cursor.getString(nameindex));
            ground5.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            playgroundnames.add(cursor.getString(nameindex));
            ground6.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
        }
        if(cursor.moveToNext())
        {
            counter++;
            playgroundnames.add(cursor.getString(nameindex));
            ground7.setText("Playground Name: " + cursor.getString(nameindex) + "\n" + "Location: " + cursor.getString(locationindex) + "\n" + "Size: " + cursor.getString(sizeindex) + "\n" + "Avaliable Hours: " + cursor.getInt(availavleindex) + "\n" + "Price: " + cursor.getInt(priceindex) + "\n" + "Cancelation Hours From: " + cursor.getInt(cancelationfromindex)+"to "+cursor.getInt(cancelationtoindex));
        }

        nvDrawer=findViewById(R.id.nv);
        drawerLayout=findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawercontent(nvDrawer);

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra(getString(R.string.bundle));
        currentaccount=bundle.getString("email");

        nvDrawer = (NavigationView) findViewById(R.id.nv);
        Menu nav_Menu = nvDrawer.getMenu();
        nav_Menu.findItem(R.id.newplayground).setVisible(false);

        if(counter>=1)
        {
            ground1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playgroundname=playgroundnames.get(0);
                    gotobookactivity();
                }
            });
        }
        if(counter>=2)
        {
            ground2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playgroundname=playgroundnames.get(1);
                    gotobookactivity();
                }
            });
        }
        if(counter>=3)
        {
            ground3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playgroundname=playgroundnames.get(2);
                    gotobookactivity();
                }
            });
        }
        if(counter>=4)
        {
            ground4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playgroundname=playgroundnames.get(3);
                    gotobookactivity();
                }
            });
        }
        if(counter>=5)
        {
            ground5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playgroundname=playgroundnames.get(4);
                    gotobookactivity();
                }
            });
        }
        if(counter>=6)
        {
            ground6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playgroundname=playgroundnames.get(5);
                    gotobookactivity();
                }
            });
        }
        if(counter>=7)
        {
            ground7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playgroundname=playgroundnames.get(6);
                    gotobookactivity();
                }
            });
        }
    }

    private void gotobookactivity()
    {
        Bundle bundlee=new Bundle();
        bundlee.putString(getString(R.string.email),currentaccount);
        bundlee.putString(getString(R.string.playgroundname),playgroundname);
        Intent bookintent=new Intent(booking.this,book.class);
        bookintent.putExtra(getString(R.string.bundle),bundlee);
        startActivity(bookintent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {return true;}
        return super.onOptionsItemSelected(item);
    }
    public void selectItemDrawer(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.profile:
                Bundle bundlee=new Bundle();
                bundlee.putString(getString(R.string.email),currentaccount);
                Intent profileintent=new Intent(booking.this,profile.class);
                profileintent.putExtra(getString(R.string.bundle),bundlee);
                startActivity(profileintent);
                finish();
                break;
            case R.id.booking:
                break;
            case R.id.mybookings:
                Bundle bundleeee=new Bundle();
                bundleeee.putString(getString(R.string.email),currentaccount);
                Intent mybookingsintent=new Intent(booking.this,all_booking.class);
                mybookingsintent.putExtra(getString(R.string.bundle),bundleeee);
                startActivity(mybookingsintent);
                finish();
                break;
            case R.id.logout:
                Intent logoutintent=new Intent(booking.this,MainActivity.class);
                sqLiteDatabasee=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
                sqLiteDatabasee.execSQL("DELETE FROM stilllogin");
                startActivity(logoutintent);
                finish();
                break;
            default:
                break;
        }
    }
    public void setupDrawercontent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectItemDrawer(menuItem);
                return true;
            }
        });
    }

}
