package com.example.amst3;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {
    String token = "";
    String username = "";
    String password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
        this.username = (String)login.getExtras().get("username");
        this.password = (String)login.getExtras().get("password");
    }
    public void Salir(View v){
        this.finish();
        System.exit(0);
    }
    public void revisarSensores(View v){
        Intent red_sensores = new Intent(getBaseContext(), RedSensores.class);
        red_sensores.putExtra("token", token);
        startActivity(red_sensores);
    }
    public void newTemp(View v){
        Intent red_sensores = new Intent(getBaseContext(), NewTemp.class);
        red_sensores.putExtra("token", token);
        red_sensores.putExtra("username", username);
        red_sensores.putExtra("password", password);
        startActivity(red_sensores);
    }
}