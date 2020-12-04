package com.example.amst3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NewTemp extends AppCompatActivity {

    private RequestQueue mQueue;
    String token = "";
    String username = "";
    String password = "";
    EditText edtTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_temp);

        mQueue = Volley.newRequestQueue(this);

        Intent login = getIntent();
        token = (String)login.getExtras().get("token");
        username = (String)login.getExtras().get("username");
        password = (String)login.getExtras().get("password");
        edtTemp=(EditText) findViewById(R.id.edtTemp);

    }

    public void nuevaTemp(View v){

        String temperatura=edtTemp.getText().toString();

        Map<String, String> params = new HashMap();
        params.put("username", username);
        params.put("password", password);
        params.put("temperatura", temperatura);

        JSONObject parametros = new JSONObject(params);
        String login_url = "https://amst-labx.herokuapp.com/db/nuevo-jwt";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST, login_url, parametros,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            token = response.getString("token");
                            Intent menuPrincipal = new Intent(getBaseContext(), Menu.class);
                            menuPrincipal.putExtra("token", token);
                            startActivity(menuPrincipal);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(NewTemp.this).create();
                alertDialog.setTitle("Alerta");
                alertDialog.setMessage("No se pudo cambiar la temperatura");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });
        mQueue.add(request);
    }
}