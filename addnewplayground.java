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
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class addnewplayground extends AppCompatActivity {
    NavigationView nvDrawer;
    DrawerLayout drawerLayout;
    String currentaccount;
    SQLiteDatabase sqLiteDatabase,sqLiteDatabasee;
    ActionBarDrawerToggle toggle;
    Button register;
    EditText name,priceperhour,location,size,availablehours,cancelationperiodfrom,cancelationperiodto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewplayground);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        register=findViewById(R.id.register);
        name=findViewById(R.id.editname);
        location=findViewById(R.id.editlocation);
        size=findViewById(R.id.editsize);
        availablehours=findViewById(R.id.editavailablehours);
        priceperhour=findViewById(R.id.editprice);
        cancelationperiodfrom=findViewById(R.id.editcancelationfrom);
        cancelationperiodto=findViewById(R.id.editcancelationto);


        sqLiteDatabase=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS playgroundsnotvirifay (Name VARCHAR,Location VARCHAR,Size VARCHAR,Avaliablehours VARCHAR,Price VARCHAR,Cancelactionfrom VARCHAR,Cancelactionto VARCHAR)");


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
        nav_Menu.findItem(R.id.booking).setVisible(false);
        nav_Menu.findItem(R.id.mybookings).setVisible(false);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase.execSQL("INSERT INTO playgroundsnotvirifay(Name,Location,Size,Avaliablehours,Price,Cancelactionfrom,Cancelactionto) VALUES ('" + name.getText().toString() + "','" + location.getText().toString() + "','" + size.getText().toString() + "','" + availablehours.getText().toString() + "','" + priceperhour.getText().toString() + "','"+cancelationperiodfrom.getText().toString()+"','"+cancelationperiodto.getText().toString()+"')");
                Bundle bundlee=new Bundle();
                bundlee.putString(getString(R.string.email),currentaccount);
                Intent profileintent=new Intent(addnewplayground.this,profile.class);
                profileintent.putExtra(getString(R.string.bundle),bundlee);
                startActivity(profileintent);
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
                Intent profileintent=new Intent(addnewplayground.this,profile.class);
                profileintent.putExtra(getString(R.string.bundle),bundlee);
                startActivity(profileintent);
                finish();
                break;
            case R.id.newplayground:
                break;
            case R.id.logout:
                Intent logoutintent=new Intent(addnewplayground.this,MainActivity.class);
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
