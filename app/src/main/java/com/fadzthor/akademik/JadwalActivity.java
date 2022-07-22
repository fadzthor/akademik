package com.fadzthor.akademik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class JadwalActivity extends AppCompatActivity implements View.OnClickListener {
    private String npm, hari, detailJadwal;
    private TextView tvHari, tvDetailJadwal;
    private ProgressDialog progressDialog;
    private Button btnSenin, btnSelasa, btnRabu, btnKamis, btnJumat, btnSabtu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        init();

        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            npm = bundle.getString("username");
        }

        tvHari = findViewById(R.id.TextViewHari);
        tvDetailJadwal = findViewById(R.id.TextViewDetailJadwal);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait ...");

        btnSenin.setOnClickListener(this);
        btnSelasa.setOnClickListener(this);
        btnRabu.setOnClickListener(this);
        btnKamis.setOnClickListener(this);
        btnJumat.setOnClickListener(this);
        btnSabtu.setOnClickListener(this);
    }

    private void init (){
        btnSenin= findViewById(R.id.btnSenin);
        btnSelasa= findViewById(R.id.btnSelasa);
        btnRabu= findViewById(R.id.btnRabu);
        btnKamis= findViewById(R.id.btnKamis);
        btnJumat= findViewById(R.id.btnJumat);
        btnSabtu= findViewById(R.id.btnSabtu);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSenin){
            hari = btnSenin.getText().toString().trim();
        }else if(view == btnSelasa){
            hari = btnSelasa.getText().toString().trim();
        }else if(view == btnRabu){
            hari = btnRabu.getText().toString().trim();
        }else if(view == btnKamis){
            hari = btnKamis.getText().toString().trim();
        }else if(view == btnJumat){
            hari = btnJumat.getText().toString().trim();
        }else if(view == btnSabtu){
            hari = btnSabtu.getText().toString().trim();
        }
        detailJadwal = "";

        tvHari.setText("Jadwal Perkuliahan Hari" +hari);

        loaddatajadwal(npm,hari);

        detailJadwal = SetterGetter.getJadwalKuliah();

        tvDetailJadwal.setText(detailJadwal);
    }

    private void loaddatajadwal(String npm, String hari) {
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_JADWAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if (!jsonObject.getBoolean("error")) {
                                String jadwal = "";
                                String jadwalAll = "";
                                for (int i=0; i < jsonArray.length();
                                     i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String Nama_Mtk = object.getString("Nama_Mtk");
                                    String Jml_Sks = object.getString("Jml_Sks");
                                    String Jam_Mulai = object.getString("Jam_Mulai");
                                    String Jam_selesai = object.getString("Jam_selesai");
                                    String Nama_Dosen = object.getString("Nama_Dosen");

                                    jadwal = "Matakuliah : " + Nama_Mtk + "(" + Jml_Sks + " Sks) \n " +
                                             "Jam        : " + Jam_Mulai + " sd " + Jam_selesai + "\n" +
                                             "Dosen      : " + Nama_Dosen + "\n \n";

                                    jadwalAll = jadwalAll + jadwal;
                                }
                                SetterGetter.setJadwalKuliah(jadwalAll);
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
                        progressDialog.dismiss();
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("npm",npm);
                params.put("hari",hari);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}