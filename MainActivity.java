package com.example.gofootball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button login;
    TextView signup;
    EditText email,password;
    ProgressDialog loadingBar;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    private Boolean emailAddressChecker;
    private static ArrayList<String> emails=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        mAuth= FirebaseAuth.getInstance();
        loadingBar=new ProgressDialog(this);



        sqLiteDatabase=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS stilllogin (Email VARCHAR)");
        cursor=sqLiteDatabase.rawQuery("SELECT * FROM stilllogin",null);
        int Emailindex=cursor.getColumnIndex("Email");
        if(cursor.moveToFirst())
        {
            if(cursor.getString(Emailindex).equals("admin"))
            {
                Intent adminintent=new Intent(MainActivity.this,admin.class);
                startActivity(adminintent);
                finish();
            }
            else
            {
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.email), cursor.getString(Emailindex));
                Intent loginintent = new Intent(MainActivity.this, profile.class);
                loginintent.putExtra(getString(R.string.bundle), bundle);
                startActivity(loginintent);
                finish();
            }
        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,signup.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equalsIgnoreCase("admin")&&password.getText().toString().equals("admin"))
                {
                    Intent adminintent=new Intent(MainActivity.this,admin.class);
                    Toast.makeText(MainActivity.this, "Hello admin", Toast.LENGTH_SHORT).show();
                    sqLiteDatabase.execSQL("INSERT INTO stilllogin(Email) VALUES ('admin')");
                    startActivity(adminintent);
                    finish();
                }
                else
                {
                    allowingusertologin();
                }
            }
        });
    }

    private void allowingusertologin()
    {
        String Email=email.getText().toString();
        String Password=password.getText().toString();

        if(Email.isEmpty())
        {
            Toast.makeText(this, "please write your email...", Toast.LENGTH_SHORT).show();
        }
        else if(Password.isEmpty())
        {
            Toast.makeText(this, "please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login");
            loadingBar.setMessage("please wait,while we are allowing you to log in into your email");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        verifyemailaddress();
                        loadingBar.dismiss();
                    }
                    else
                    {
                        String message=task.getException().getMessage();
                        Toast.makeText(MainActivity.this, "Error "+message, Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });
        }
    }

    private void verifyemailaddress()
    {
        FirebaseUser user=mAuth.getCurrentUser();
        emailAddressChecker=user.isEmailVerified();
        if(emailAddressChecker)
        {
            String Email=email.getText().toString();
            Bundle bundle=new Bundle();
            bundle.putString(getString(R.string.email),Email);
            Intent loginintent=new Intent(MainActivity.this,profile.class);
            loginintent.putExtra(getString(R.string.bundle),bundle);
            sqLiteDatabase.execSQL("INSERT INTO stilllogin(Email) VALUES ('" + Email + "')");
            startActivity(loginintent);
            finish();
        }
        else
        {
            Toast.makeText(this, "please verify your account first...", Toast.LENGTH_SHORT).show();
            mAuth.signOut();
        }
    }

}
