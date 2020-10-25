package com.example.hw3_special_offer;

import android.location.Geocoder;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

class FenceDataAsyncTask extends AsyncTask<String,Void,String> {
    private static final String TAG = "FenceDataAsyncTask";
    private FenceManager fm;
    private static final String FENCE_URL = "http://www.christopherhield.com/data/fencesll.json";
    public FenceDataAsyncTask(FenceManager fenceManager) {
        this.fm=fenceManager;
    }

    @Override
    protected String doInBackground(String... strings) {

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(FENCE_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null;

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuilder buffer = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            return buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result == null){return;}
        ArrayList<Fence> fences = new ArrayList<>();
        try {
            JSONObject jObj = new JSONObject(result);
            JSONArray jArr = jObj.getJSONArray("fences");
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject fObj = jArr.getJSONObject(i);
                String id = fObj.getString("id");
                String address = fObj.getString("address");
                String website = fObj.getString("website");
                float rad = (float) fObj.getDouble("radius");
                int type = fObj.getInt("type");
                String message = fObj.getString("message");
                String code = fObj.getString("code");
                String color = fObj.getString("fenceColor");
                String logo = fObj.getString("logo");
                Double lat = fObj.getDouble("lat");
                Double lon = fObj.getDouble("lon");

                Fence fence = new Fence(id,address,website,rad,type,message,code,color,logo,lat,lon);
                fences.add(fence);
            }
            fm.addFences(fences);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
