package com.example.gofootball;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class profile extends AppCompatActivity {
    TextView email,phone,name,location,eWallet;
    int Email,Name,Phone,Location,EWallet,Typeuser;
    SQLiteDatabase sqLiteDatabase,sqLiteDatabasee;
    Cursor cursor;
    String currentaccount;
    NavigationView nvDrawer;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        nvDrawer=findViewById(R.id.nv);
        drawerLayout=findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawercontent(nvDrawer);

        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        name=findViewById(R.id.name);
        location=findViewById(R.id.location);
        eWallet=findViewById(R.id.eWallet);

        sqLiteDatabase=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
        cursor=sqLiteDatabase.rawQuery("SELECT * FROM accounts",null);

        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra(getString(R.string.bundle));
        currentaccount=bundle.getString("email");

        Email=cursor.getColumnIndex("account");
        Name=cursor.getColumnIndex("name");
        Phone=cursor.getColumnIndex("number");
        Location=cursor.getColumnIndex("Locatio");
        EWallet=cursor.getColumnIndex("Wallet");
        Typeuser=cursor.getColumnIndex("Typeuser");
        cursor.moveToFirst();

        while (!currentaccount.equals(cursor.getString(Email)))
            {
                cursor.moveToNext();
            }

            if (cursor.getString(Typeuser).equals("player"))
            {
                display();
                player();
            }
            else if (cursor.getString(Typeuser).equals("playground owner"))
            {
                display();
                playground_owner();
            }
        }

    private void display()
    {
        email.setText(email.getText()+"\n"+cursor.getString(Email));
        name.setText(name.getText()+"\n"+cursor.getString(Name));
        phone.setText(phone.getText()+"\n"+cursor.getString(Phone));
        location.setText(location.getText()+"\n"+cursor.getString(Location));
        eWallet.setText(eWallet.getText()+"\n"+cursor.getString(EWallet));
    }

    private void player()
    {
        nvDrawer = (NavigationView) findViewById(R.id.nv);
        Menu nav_Menu = nvDrawer.getMenu();
        nav_Menu.findItem(R.id.newplayground).setVisible(false);

    }

    private void playground_owner()
    {
        nvDrawer = (NavigationView) findViewById(R.id.nv);
        Menu nav_Menu = nvDrawer.getMenu();
        nav_Menu.findItem(R.id.booking).setVisible(false);
        nav_Menu.findItem(R.id.mybookings).setVisible(false);
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
                break;
            case R.id.newplayground:
                Bundle bundlee=new Bundle();
                bundlee.putString(getString(R.string.email),currentaccount);
                Intent addintent=new Intent(profile.this,addnewplayground.class);
                addintent.putExtra(getString(R.string.bundle),bundlee);
                startActivity(addintent);
                finish();
                break;
            case R.id.booking:
                Bundle bundleee=new Bundle();
                bundleee.putString(getString(R.string.email),currentaccount);
                Intent bookintent=new Intent(profile.this,booking.class);
                bookintent.putExtra(getString(R.string.bundle),bundleee);
                startActivity(bookintent);
                finish();
                break;
            case R.id.mybookings:
                Bundle bundleeee=new Bundle();
                bundleeee.putString(getString(R.string.email),currentaccount);
                Intent mybookingsintent=new Intent(profile.this,all_booking.class);
                mybookingsintent.putExtra(getString(R.string.bundle),bundleeee);
                startActivity(mybookingsintent);
                finish();
                break;
            case R.id.logout:
                Intent logoutintent=new Intent(profile.this,MainActivity.class);
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
