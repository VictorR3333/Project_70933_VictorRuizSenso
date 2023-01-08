package com.example.project_70933_victorruizsenso;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.a70933_VictorRuizSenso_Project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class details_marker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_marker);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String json1, json2, id="", name="", gaelic="", latitude="", longitude="", typeName="";


        Button back = (Button) findViewById(R.id.back_btm);
        String nam = getIntent().getStringExtra("name");

        try {
            InputStream places = getAssets().open("places.json");
            int size1 = places.available();
            byte[] buffer1 = new byte[size1];
            places.read(buffer1);
            places.close();
            InputStream types = getAssets().open("place_types.json");
            int size2 = types.available();
            byte[] buffer2 = new byte[size2];
            types.read(buffer2);
            types.close();

            json1 = new String(buffer1, "UTF-8");
            json2 = new String(buffer2, "UTF-8");
            JSONArray jsonPlaces = new JSONArray(json1);
            JSONArray jsonTypes = new JSONArray(json2);

            for(int i=0; i<jsonPlaces.length(); i++) {
                for(int y=0; y<jsonTypes.length(); y++) {
                    JSONObject place = jsonPlaces.getJSONObject(i);
                    JSONObject type = jsonPlaces.getJSONObject(y);
                    if (place.getString("name").equals(nam)) {
                        id = place.getString("id");
                        name = place.getString("name");
                        gaelic = place.getString("gaelic_name");
                        String typeID = place.getString("place_type_id");
                        latitude = place.getString("latitude");
                        longitude = place.getString("longitude");
                        if (type.getString("id").equals(typeID)) {
                            typeName = type.getString("name");
                        }
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }

        TextView id_value = (TextView) findViewById(R.id.view_id);
        TextView name_value = (TextView) findViewById(R.id.view_name);
        TextView gaelic_value = (TextView) findViewById(R.id.view_gaelic);
        TextView type_value = (TextView) findViewById(R.id.view_type);
        TextView lati_value = (TextView) findViewById(R.id.view_lati);
        TextView longi_value = (TextView) findViewById(R.id.view_longi);

        id_value.setText(id);
        name_value.setText(name);
        gaelic_value.setText(gaelic);
        type_value.setText(typeName);
        lati_value.setText(latitude);
        longi_value.setText(longitude);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}