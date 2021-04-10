package com.insergo.bnb_ak;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 6/28/2017.
 */
public class Share_Books extends Fragment {

    EditText e, e1, e2, e3, e4, e5;
    String s, s1, s2, name;
    Button b, b2;
    ArrayList<String> al;
    ArrayAdapter ad;
    String path;
    HashMap<String, String> postDataParams;
    static String response = "";


    Spinner sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share_books, container, false);

        e = (EditText) view.findViewById(R.id.editText11);
        e1 = (EditText) view.findViewById(R.id.editText);
        e2 = (EditText) view.findViewById(R.id.editText2);
        e3 = (EditText) view.findViewById(R.id.editText3);
        e4 = (EditText) view.findViewById(R.id.editText4);
        e5 = (EditText) view.findViewById(R.id.editText14);

        b = (Button) view.findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetData().execute();

            }
        });

        al = new ArrayList<String>();
        sp = (Spinner) view.findViewById(R.id.spinner);
        al.add("ICSE");
        al.add("CBSE");
        al.add("UNDER-GRADUATE");
        al.add("POST-GRADUATE");
        al.add("PREPARATION");
        al.add("GENERAL");
        al.add("OTHERS");

        ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, al);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        al = new ArrayList<String>();
        sp = (Spinner) view.findViewById(R.id.spinner2);
        al.add("MATHEMATICS");
        al.add("COMMERCE");
        al.add("ARTS");
        al.add("HISTORY");
        al.add("GEOGRAPHY");
        al.add("GENERAL");
        al.add("MECHANICAL");
        al.add("CIVIL");
        al.add("COMPUTERS");
        al.add("BIOLOGY");
        al.add("PHARMACY");
        al.add("PHYSICS");
        al.add("CHEMISTRY");
        al.add("CORE");
        al.add("ELECTRICAL");
        al.add("ELECTRONICS");
        al.add("SOFTWARE TECH");
        al.add("GENERAL SCIENCE");
        al.add("OTHERS");


        ad = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, al);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s1 = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        al = new ArrayList<String>();
        sp = (Spinner) view.findViewById(R.id.spinner3);
        al.add("10th");
        al.add("12th");
        al.add("Ist year");
        al.add("IInd year");
        al.add("IIIrd year");
        al.add("IVth year");
        al.add("Vth year");
        al.add("PG Ist year");
        al.add("PG IInd year");
        al.add("PHD.");


        ad = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, al);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                s2 = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;


    }

    class GetData extends AsyncTask<Void, Void, Void> {

        int success = 0;
        ProgressDialog pDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            path = "http://xx7.site90.com/insertbooks.php";

            postDataParams = new HashMap<String, String>();
            postDataParams.put("sname", e.getText().toString());
            postDataParams.put("name", e1.getText().toString());
            postDataParams.put("author", e2.getText().toString());
            postDataParams.put("edition", e3.getText().toString());
            postDataParams.put("category", s);
            postDataParams.put("subject", s1);
            postDataParams.put("standard", s2);
            postDataParams.put("price", e4.getText().toString());
            postDataParams.put("contact", e5.getText().toString());
            postDataParams.put("location", "doon");

            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Loading... Please wait !!!");
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                HTTPURLConnection service = new HTTPURLConnection();

                response = service.ServerData(path, postDataParams);

                Log.d("locality", " result :  :  " + response);

                JSONObject obj = new JSONObject(response);


                success = obj.getInt("success");


            } catch (Exception e) {
                Log.d("Exception ", "" + e);

            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            pDialog.dismiss();


            if (success == 1) {
                Toast.makeText(getContext(), "RECORD ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                e1.setText("");
                e2.setText("");
                e3.setText("");
                e.setText("");
                e4.setText("");
                e5.setText("");


            } else {


                Toast.makeText(getContext(), "Error!RECORD NOT ADDED", Toast.LENGTH_SHORT).show();

            }


        }

    }
}


