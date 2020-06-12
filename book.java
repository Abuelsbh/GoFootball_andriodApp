package com.example.gofootball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class book extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase,sqLiteDatabasee;
    TextView txt,txtprice,playground;
    EditText timefrom,timeend;
    Button register;
    Spinner dayspinner;
    ArrayList<String> days=new ArrayList<>();
    Boolean verify=true;
    String currentaccount,day,time,playgroundname;
    NavigationView nvDrawer;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        playground=findViewById(R.id.playgroundname);
        txt=findViewById(R.id.txt);
        timefrom=findViewById(R.id.editstarttime);
        timeend=findViewById(R.id.editendtime);
        register=findViewById(R.id.register);
        txtprice=findViewById(R.id.price);
        dayspinner=findViewById(R.id.dayspinner);

        days.add("Monday");
        days.add("Tuesday");
        days.add("Wednesday");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");

        ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,days);
        dayspinner.setAdapter(adapter);

        dayspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = days.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        playgroundname=bundle.getString("playgroundname");
        playground.setText(playgroundname+" playground");

        sqLiteDatabase=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS '"+currentaccount+"0' (playgroundname VARCHAR,day VARCHAR,hour VARCHAR)");

        nvDrawer = (NavigationView) findViewById(R.id.nv);
        Menu nav_Menu = nvDrawer.getMenu();
        nav_Menu.findItem(R.id.newplayground).setVisible(false);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time=timefrom.getText().toString()+" to "+timeend.getText().toString();
                sqLiteDatabase.execSQL("INSERT INTO '"+currentaccount+"0'(playgroundname,day,hour) VALUES ('" + playgroundname + "','" + day + "','" + time + "')");
                Bundle bundleeee=new Bundle();
                bundleeee.putString(getString(R.string.email),currentaccount);
                Intent mybookingsintent=new Intent(book.this,all_booking.class);
                mybookingsintent.putExtra(getString(R.string.bundle),bundleeee);
                startActivity(mybookingsintent);
                finish();
            }
        });
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
                Intent addintent=new Intent(book.this,profile.class);
                addintent.putExtra(getString(R.string.bundle),bundlee);
                startActivity(addintent);
                finish();
                break;
            case R.id.booking:
                Bundle bundleee=new Bundle();
                bundleee.putString(getString(R.string.email),currentaccount);
                Intent bookintent=new Intent(book.this,booking.class);
                bookintent.putExtra(getString(R.string.bundle),bundleee);
                startActivity(bookintent);
                finish();
                break;
            case R.id.mybookings:
                Bundle bundleeee=new Bundle();
                bundleeee.putString(getString(R.string.email),currentaccount);
                Intent mybookingsintent=new Intent(book.this,all_booking.class);
                mybookingsintent.putExtra(getString(R.string.bundle),bundleeee);
                startActivity(mybookingsintent);
                finish();
                break;
            case R.id.logout:
                Intent logoutintent=new Intent(book.this,MainActivity.class);
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
