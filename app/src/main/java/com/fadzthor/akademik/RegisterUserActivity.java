package com.fadzthor.akademik;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnRegister;
    private EditText textUsername, textPassword, textEmail;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        btnRegister =findViewById(R.id.btn_Signup);
        textUsername = findViewById(R.id.etUsername);
        textPassword = findViewById(R.id.etPassword);
        textEmail = findViewById(R.id.etEmail);
        progressDialog = new ProgressDialog(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view == btnRegister)
            registerUser();
    }

    private void registerUser(){
        final String username = textUsername.getText().toString().trim();
        final String email = textEmail.getText().toString().trim();
        final String password = textPassword.getText().toString().trim();

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
        @Nullable

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String>params= new HashMap<>();
            params.put("username",username);
            params.put("password",password);
            params.put("email",email);
            return params;
        }
    };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}