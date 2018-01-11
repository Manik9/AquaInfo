package binarykeys.aquainfo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ACER on 10/6/2017.
 */

public class Activity2 extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private RecyclerView recyclerView;
    private List<Values> values;
    private GridLayoutManager gridLayout;
    private ValuesAdapter valuesAdapter;
    private ProgressDialog p;
    private FirebaseAuth mAuth;
    private Set<String> set;
    private ArrayList<String> query;
    private Spinner spinner;
    private ValuesAdapter valuesAdapterQuery;
    private ArrayAdapter<String> spinnerArrayAdapter;

    @Override
    public void onCreate(Bundle savedIns) {
        super.onCreate(savedIns);
        setContentView(R.layout.recycler_view);

        set=new HashSet<>();
        spinner=findViewById(R.id.spinner2);
        p = new ProgressDialog(this);
        recyclerView = findViewById(R.id.recyclerView);
        mAuth = FirebaseAuth.getInstance();
        values = new ArrayList<>();


        if (isNetworkConnected()) {
            Tasky t = new Tasky();
            t.execute();
        } else {
            networkConnection();
        }



        spinner.setOnItemSelectedListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gridLayout = new GridLayoutManager(this, 1);

        valuesAdapter = new ValuesAdapter(this, values);
        recyclerView.setAdapter(valuesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:

                LogoutDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onBackPressed() {
        LogoutDialog();
    }

    public void LogoutDialog() {

        View v = getLayoutInflater().inflate(R.layout.logout_dialog, null);
        Button CanLogout = v.findViewById(R.id.CancelLogoutConfirmation);
        Button Logout = v.findViewById(R.id.LogoutConfirmation);
        AlertDialog.Builder abd = new AlertDialog.Builder(this);
        abd.setView(v);
        final AlertDialog ad = abd.show();
        CanLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.hide();
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.hide();
                mAuth.signOut();
                startActivity(new Intent(Activity2.this, LoginActivity.class));
                finish();
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void networkConnection() {
        if (!isNetworkConnected()) {
            AlertDialog.Builder a = new AlertDialog.Builder(Activity2.this);
            a.setCancelable(false);
            a.setTitle("No Internet");
            a.setMessage("Pay your bills. \uD83D\uDE05 ");
            a.setIcon(R.drawable.aplogo);
            a.setPositiveButton("I have already paid", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Activity2.this, Activity2.class));
                    finish();
                }
            });
            a.show();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String s=adapterView.getItemAtPosition(i).toString();
        List<Values> v=new ArrayList<>();
        ((TextView) adapterView.getChildAt(i)).setTextColor(Color.WHITE);
        ((TextView) adapterView.getChildAt(i)).setTextSize(20);
        for(Values vv:values){
            if(vv.getId().equalsIgnoreCase(s))
                v.add(vv);
        }
        valuesAdapterQuery = new ValuesAdapter(this, v);
        recyclerView.setAdapter(valuesAdapterQuery);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        recyclerView.setAdapter(valuesAdapter);
    }


    private class Tasky extends AsyncTask<Long, Void, Void> {

        @Override
        protected Void doInBackground(Long... longs) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://210.212.213.230:8082/asdf/GetData")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                JSONArray array = new JSONArray(response.body().string());

                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);
                    //   Log.v("Dater",object.getString("ID"));
                    Values value = new Values(object.getString("nodeid"),//--
                            object.getString("temp"),//--
                            object.getString("turbidity"),//--
                            String.valueOf(object.getDouble("ph")),//--
                            String.valueOf(object.getDouble("conductivity")),//--
                            String.valueOf(object.getDouble("do")),//--
                            String.valueOf(object.getDouble("orp")),//--
                            object.getString("time"),//time 2018-8-17 00:00:00.00
                            String.valueOf(object.getDouble("salinity")),//--
                            String.valueOf(object.getDouble("ecoli")),//--
                            String.valueOf(object.getDouble("floride")),//--
                            String.valueOf(object.getDouble("arsenic")),//--
                            String.valueOf(object.getDouble("rescl")),//--
                            object.getString("nodelat"),
                            object.getString("nodelong")
                    );
                    set.add(value.getId().toString());
                    Activity2.this.values.add(value);
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p.setMessage("Loading");
            p.setCancelable(false);
            p.setTitle("Please Wait");
            p.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                query=new ArrayList<>();
                query.addAll(set);
                //query.add("2");
                valuesAdapter.notifyDataSetChanged();
                spinnerArrayAdapter = new ArrayAdapter<>(Activity2.this,   android.R.layout.simple_spinner_item, query);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                spinner.setAdapter(spinnerArrayAdapter);
            } finally {
                p.hide();
            }
        }
    }


}