package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  String url = "https://mgm.ub.ac.id/todo.php";
  ArrayList<Model> list = new ArrayList<>();
  RecyclerView rvData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    rvData = findViewById(R.id.rv);
    RequestQueue queue = Volley.newRequestQueue(this);

    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONArray>() {
              @Override
              public void onResponse(JSONArray response) {
                // Process JSONArray response
                for (int i = 0; i < response.length(); i++) {
                  try {
                    JSONObject jsonObject = response.getJSONObject(i);

                    Model model = new Model(jsonObject.getInt("id"), jsonObject.getString("what"),
                            jsonObject.getString("time"));

                    list.add(model);
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                }
                ListAdapter adapter = new ListAdapter(MainActivity.this, list);
                RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);

                rvData.setLayoutManager(lm);
                rvData.setAdapter(adapter);
              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        // Process error...
        Log.e("example error", error.getMessage(), error);
      }
    });

    // Add the request to the RequestQueue.
    queue.add(jsonArrayRequest);
  }
}

