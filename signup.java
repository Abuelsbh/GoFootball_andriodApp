package com.example.gofootball;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    String Email,Password,Confirmpass,Phone,Firstname,Lastname,Location,EWallet;
    EditText email,password,confirmpass,firstname,lastname,phone,location,eWallet;
    String typeuser;
    Spinner spinner;
    Button signup;
    SQLiteDatabase sqLiteDatabase;
    ProgressDialog loadingBar;
    private static int ID=0;
    private ArrayList<String> typeofacc=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sqLiteDatabase=this.openOrCreateDatabase("Gofootball",MODE_PRIVATE,null);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        phone=findViewById(R.id.phone);
        location=findViewById(R.id.location);
        eWallet=findViewById(R.id.eWallet);
        mAuth= FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirmpass=findViewById(R.id.confirmpass);
        signup=findViewById(R.id.signup);
        spinner=findViewById(R.id.spinner);
        loadingBar=new ProgressDialog(this);

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS accounts (name VARCHAR ,account VARCHAR,number VARCHAR,Locatio VARCHAR ,Wallet VARCHAR,Typeuser VARCHAR,id INT(10))");

        typeofacc.add("player");
        typeofacc.add("playground owner");

        ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,typeofacc);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeuser = typeofacc.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createnewaccount();
            }
        });
    }


    private void createnewaccount()
    {
        Email=email.getText().toString();
        Password=password.getText().toString();
        Confirmpass=confirmpass.getText().toString();
        Phone=phone.getText().toString();
        Firstname=firstname.getText().toString();
        Lastname=lastname.getText().toString();
        Location=location.getText().toString();
        EWallet=eWallet.getText().toString();

        if(Email.isEmpty())
        {
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }
        else if(Password.isEmpty())
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else if(Confirmpass.isEmpty())
        {
            Toast.makeText(this, "Please confirm your password...", Toast.LENGTH_SHORT).show();
        }
        else if(Firstname.isEmpty())
        {
            Toast.makeText(this, "Please write your First name ...", Toast.LENGTH_SHORT).show();
        }
        else if(Lastname.isEmpty())
        {
            Toast.makeText(this, "Please write your last name...", Toast.LENGTH_SHORT).show();
        }
        else if(Phone.isEmpty())
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if(Location.isEmpty())
        {
            Toast.makeText(this, "Please write your location...", Toast.LENGTH_SHORT).show();
        }
        else if(EWallet.isEmpty())
        {
            Toast.makeText(this, "Please write your eWallet...", Toast.LENGTH_SHORT).show();
        }
        else if(!Password.equals(Confirmpass))
        {
            Toast.makeText(this, "your password don't match with your confirm...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please Wait, While we are creating you new account...");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        sendemailnotificationmessage();
                        loadingBar.dismiss();
                    }
                    else
                    {
                        String message=task.getException().getMessage();
                        Toast.makeText(signup.this, "Error "+message, Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();

                    }
                }
            });
        }
    }
    private void sendemailnotificationmessage()
    {
        FirebaseUser user=mAuth.getCurrentUser();
        if(user !=null)
        {

            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task)
                {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(signup.this, "Registration successful ,Please verify your account", Toast.LENGTH_LONG).show();
                        sendusertologinactivity();
                        mAuth.signOut();
                    }
                    else
                    {
                        String error=task.getException().getMessage();
                        Toast.makeText(signup.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                    }
                }
            });
        }
    }
    private void sendusertologinactivity()
    {
        String Name = Firstname + ' ' + Lastname;
        sqLiteDatabase.execSQL("INSERT INTO accounts(name,account,number,Locatio,Wallet,Typeuser,id) VALUES ('" + Name + "','" + Email + "','" + Phone + "','" + Location + "','" + EWallet + "','"+typeuser+"','" + ID + "')");
        ID++;
        Intent loginintent=new Intent(signup.this,MainActivity.class);
        startActivity(loginintent);
        finish();
    }
}
