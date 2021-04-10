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
public class Search_Books extends Fragment {
    Button b; String path, name;
    HashMap<String, String> postDataParams;
    static String response = "";

    ArrayList<String> al;
    ArrayAdapter ad;
    ListView lv;
    ArrayList<String> all;
    ArrayAdapter add;
    Spinner sp;

    String s="";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_books, container, false);
        b = (Button) view.findViewById(R.id.button3);
        lv=(ListView)view.findViewById(R.id.listView);



        all = new ArrayList<String>();
        sp = (Spinner) view.findViewById(R.id.spinner5);
        all.add("ICSE");
        all.add("CBSE");
        all.add("UNDER-GRADUATE");
        all.add("POST-GRADUATE");
        all.add("PREPARATION");
        all.add("GENERAL");
        all.add("OTHERS");

        add = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, all);
        sp.setAdapter(add);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             s = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al=new ArrayList<String>();

                new GetData().execute();

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
            path = "http://xx7.site90.com/indexbooks.php";

            postDataParams = new HashMap<String, String>();
            postDataParams.put("category",s);

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

                JSONArray data = obj.getJSONArray("data");

                success = obj.getInt("success");

                if (success == 1) {


                    for (int i = 0; i < data.length(); i++) {

                        JSONObject jo = data.getJSONObject(i);


                        al.add( "BOOK NAME: "+jo.getString("name")+"\nAUTHOR: "+jo.getString("author")+"\nEDITION: "+jo.getString("edition")+"\nSUBJECT: "+jo.getString("subject")+"\nSTANDARD: "+jo.getString("standard")+"\nPRICE: "+jo.getString("price")+"\nUSER NAME: "+jo.getString("sname")+"\nLOCATION: "+jo.getString("location")+"\nCONTACT: "+jo.getString("contact")+"\n");
                    }


                } else {
                    Log.d("success  ::", "no");
                }

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

                ad=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,al);
                lv.setAdapter(ad);
                //Toast.makeText(show.this,name, Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
            }
        }



    }


}



