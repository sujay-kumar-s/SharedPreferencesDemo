package com.example.aqua.sharedpreferencesdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText uname;
    EditText pwd;
    CheckBox rmpwd;
    Button loginbtn;
    String stemail="jspiders@gmail.com";
    String stpwd="123";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname= (EditText) findViewById(R.id.usenameET);
        pwd= (EditText) findViewById(R.id.passwordET);
        rmpwd= (CheckBox) findViewById(R.id.rmpwdcb);
        loginbtn= (Button) findViewById(R.id.loginBT);

        //retriev data from sharedpreference
        sp=getSharedPreferences("mypref",MODE_PRIVATE);
        String s1=sp.getString("uname",null);
       String s2= sp.getString("pwd",null);
        Log.d("shared",s1+"=============>"+s2);
        uname.setText(s1);
        pwd.setText(s2);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_email=uname.getText().toString();
                String input_pwd=pwd.getText().toString();
                if(input_email.equals(stemail) && input_pwd.equals(stpwd))
                {
                    Log.e("success","login success");
                        if(rmpwd.isChecked()==true)
                        {
                           //sp=getSharedPreferences("mypref",MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("uname",input_email);
                            editor.putString("pwd",input_pwd);
                            editor.commit();
                            Intent home_intent=new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(home_intent);
                        }
                }else
                {
                    Toast.makeText(MainActivity.this,"Invalid Username or password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
