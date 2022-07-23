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

public class KRSActivity extends AppCompatActivity implements View.OnClickListener {
    private String nama, prodi, semester, npm, detailKRS, detailNama, detailSemester, detailProdi;
    private TextView tvNama, tvSemester, tvProdi, tvKRS;
    private Button btnLoad;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krs);

        load();

        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            npm = bundle.getString("username");
        }

        tvKRS = findViewById(R.id.TextViewKRS);

//        tvNama = findViewById(R.id.tvNama);
//        tvProdi = findViewById(R.id.tvProdi);
//        tvSemester = findViewById(R.id.tvSemester);

        btnLoad.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait ...");
    }

    private void load() {
        btnLoad= findViewById(R.id.btnLoad);
    }

    @Override
    public void onClick(View view) {
        loaddataKRS(npm);
        detailKRS ="";
//        detailNama="";
//        detailProdi="";
//        detailSemester="";

        detailKRS = SetterGetter.getKRS();

//        nama = SetterGetter.getNama();
//        prodi = SetterGetter.getProdi();
//        semester = SetterGetter.getSemester();

        tvKRS.setText(detailKRS);
//        tvNama.setText(detailNama);
//        tvProdi.setText(detailProdi);
//        tvSemester.setText(detailSemester);
    }

    private void loaddataKRS(String npm) {
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_KRS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            if (!jsonObject.getBoolean("error")) {
                                jsonArray.length();
                                String dataKRS ="";
                                String KRS = "";
//                                String dataNama = "";
//                                String dataProdi = "";
//                                String dataSemester = "";
                                for (int i=0; i < jsonArray.length();
                                     i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String Nama_Mtk = object.getString("Nama_Mtk");
                                    String Jml_Sks = object.getString("Jml_Sks");
                                    String Nama_Dosen = object.getString("Nama_Dosen");

                                    KRS = "Matakuliah : " + Nama_Mtk + "\n" +
                                          "SKS        : " + Jml_Sks + "\n" +
                                          "Nama Dosen : " + Nama_Dosen + "\n" ;


//                                    dataNama = nama;
//                                    dataProdi = prodi;
//                                    dataSemester = semester;
////                                    tvNama.setText(nama);
////                                    tvProdi.setText(prodi);
////                                    tvSemester.setText(semester);
                                    dataKRS = dataKRS+KRS;
                                }
                                SetterGetter.setKRS(dataKRS);
//                                SetterGetter.setNama(jsonObject.getString(dataNama));
//                                SetterGetter.setProdi(jsonObject.getString(dataProdi));
//                                SetterGetter.setSemester(jsonObject.getString(dataSemester));

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