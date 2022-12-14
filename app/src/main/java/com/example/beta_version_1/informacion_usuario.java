package com.example.beta_version_1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class informacion_usuario extends AppCompatActivity {
    base_datos admin;
    String usuario,pass;
    TextView user,contra,fecha,telefono,gmail;
    MainActivity inicio=new MainActivity();

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_informacion_usuario);
        user=findViewById(R.id.select_usuario);
        contra=findViewById(R.id.select_pass);
        fecha=findViewById(R.id.select_fecha);
        telefono=findViewById(R.id.select_telefono);
        gmail=findViewById(R.id.select_gmail);
        admin=new base_datos(this,"bd1",null,1);
        informacion_de_usuario();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void informacion_de_usuario(){
        SQLiteDatabase tabla=admin.getWritableDatabase();
        Cursor a=tabla.rawQuery("select * from usuario where nombre='"+sacar_referencias()+"'",null);
        if (a.moveToFirst()){
            user.setText(a.getString(1));
            contra.setText(a.getString(2));
            gmail.setText(a.getString(3));
            telefono.setText(a.getString(4));
            fecha.setText(a.getString(5));
        }

    }
    public String sacar_referencias(){
        SharedPreferences referencia=getSharedPreferences("cuenta_informacio", Context.MODE_PRIVATE);
        return  referencia.getString("usuario",null);
    }
}