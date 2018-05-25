package com.example.watch.serverconnect;

import android.app.Activity;
import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by watch on 5/23/2018.
 */

    public class Connect {
    private final Activity main;
    private List<String> list;
    private String URL = "http://10.0.2.2/", GET_URL = "android/get_data.php?status=0", SENT_URL = "android/sent_data.php";

    public Connect() {

        main = null;

    }

    public Connect(Activity mainA) {
        main = mainA;
        list = new ArrayList<String>();
    }

    public List<String> getData() {

        String url = URL + GET_URL;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
                Toast.makeText(main, list.get(0), Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(main, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(main.getApplicationContext());
        requestQueue.add(stringRequest);

        return list;
    }

    private void showJSON(String response) {
        String comment = "";
        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject collegeData = result.getJSONObject(i);
                comment = collegeData.getString("comment");
                list.add(comment);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sentData(String value){ //methos ãËÁèÅèÒÊØ´ ÍÑ¾à´·¢éÍÁÙÅ¢Öé¹°Ò¹¢éÍÁÙÅ
        StrictMode.enableDefaults(); //¨Ó¡Ñ´àÇÍÃìàÇÍÃìªÑè¹áÍ¹´ÃÍÂ´ì·Õèãªé¿Õà¨ÍÃì¹Õéä´é
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try{
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("isAdd","true")); //¢éÍÁÙÅ·Õè¨ÐÊè§ä» µÑÇ¹Õéà»ç¹á¤èµÑÇ¨Ø´ª¹Ç¹
            nameValuePairs.add(new BasicNameValuePair("comment", value)); //¢éÍÁÙÅ·Õè¨ÐÊè§ä» µÑÇ¹Õéà»ç¹¢éÍÁÙÅ·ÕèàÃÒà¢ÕÂ¹
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL + SENT_URL);//Change IP to you WebServer
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
            httpclient.execute(httppost);

            Toast.makeText(main, "Completed.", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(main, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}





