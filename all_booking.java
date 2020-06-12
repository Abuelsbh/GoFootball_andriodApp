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
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class all_booking extends AppCompatActivity {

    String currentaccount;
    NavigationView nvDrawer;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    TextView ticket1,ticket2,ticket3;
    SQLiteDatabase sqLiteDatabase,sqLiteDatabasee;
    Cursor cursor;
    int playgroundindex,dayindex,hourindex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_booking);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nvDrawer = (NavigationView) findViewById(R.id.nv);
        Menu nav_Menu = nvDrawer.getMenu();
        nav_Menu.findItem(R.id.newplayground).setVisible(false);


        ticket1=findViewById(R.id.ticket1);
        ticket2=findViewById(R.id.ticket2);
        ticket3=findViewById(R.id.ticket3);

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

        sqLiteDatabase=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '"+currentaccount+"0' (playgroundname VARCHAR,day VARCHAR,hour VARCHAR)");
        cursor=sqLiteDatabase.rawQuery("SELECT * FROM '"+currentaccount+"0'",null);

        playgroundindex=cursor.getColumnIndex("playgroundname");
        dayindex=cursor.getColumnIndex("day");
        hourindex=cursor.getColumnIndex("hour");

        if(cursor.moveToFirst())
        {
            ticket1.setText(cursor.getString(playgroundindex) +" "+ cursor.getString(dayindex) +" "+ cursor.getString(hourindex) );
        }
        if(cursor.moveToNext())
        {
            ticket2.setText(cursor.getString(playgroundindex) +" "+ cursor.getString(dayindex) +" "+ cursor.getString(hourindex) );
        }
        if(cursor.moveToNext())
        {
            ticket3.setText(cursor.getString(playgroundindex) +" "+ cursor.getString(dayindex) +" "+ cursor.getString(hourindex) );
        }
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
                Intent addintent=new Intent(all_booking.this,profile.class);
                addintent.putExtra(getString(R.string.bundle),bundlee);
                startActivity(addintent);
                finish();
                break;
            case R.id.booking:
                Bundle bundleee=new Bundle();
                bundleee.putString(getString(R.string.email),currentaccount);
                Intent bookintent=new Intent(all_booking.this,booking.class);
                bookintent.putExtra(getString(R.string.bundle),bundleee);
                startActivity(bookintent);
                finish();
                break;
            case R.id.mybookings:
                break;
            case R.id.logout:
                Intent logoutintent=new Intent(all_booking.this,MainActivity.class);
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
