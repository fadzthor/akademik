package com.fadzthor.akademik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UKTActivity extends AppCompatActivity implements View.OnClickListener {
    private String npm, detailUKT;
    private TextView tvUKT;
    private Button btnLoad;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ukt);

        load();

        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            npm = bundle.getString("username");
        }

        tvUKT = findViewById(R.id.TextViewUKT);

        btnLoad.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait ...");
    }

    private void load() {
        btnLoad= findViewById(R.id.btnLoad);
    }

    @Override
    public void onClick(View view) {
        loaddataUKT(npm);
        detailUKT = "";

        detailUKT = SetterGetter.getUKT();

        tvUKT.setText(detailUKT);
    }

    private void loaddataUKT(String npm) {
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_UKT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if (!jsonObject.getBoolean("error")) {
                                jsonArray.length();
                                String dataUKT ="";
                                String UKt = "";
                                for (int i=0; i < jsonArray.length();
                                     i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String Jenjang = object.getString("Jenjang");
                                    String Nama_Prodi = object.getString("Nama_Prodi");
                                    String UKT = object.getString("UKT");

                                    UKt = "Jenjang       : " + Jenjang + "\n" +
                                          "Program Studi : " + Nama_Prodi + "\n" +
                                          "UKT           : " + UKT + "\n" ;

                                    dataUKT = dataUKT+UKt;
                                }
                                SetterGetter.setUKT(dataUKT);
                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        jsonObject.getString("message"),
                                        Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("npm",npm);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}