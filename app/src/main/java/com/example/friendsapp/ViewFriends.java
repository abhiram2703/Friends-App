package com.example.friendsapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewFriends extends AppCompatActivity {
    String apiurl="https://friendsapi-re5a.onrender.com/view";
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_friends);
        txt1=(TextView) findViewById(R.id.tv);
        JsonArrayRequest friend=new JsonArrayRequest(
                Request.Method.GET,
                apiurl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
                        StringBuilder result= new StringBuilder();
                        for (int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject friend=response.getJSONObject(i);
                                String name= friend.getString("name");
                                String fname= friend.getString("friendName");
                                String fnname= friend.getString("friendNickName");
                                String desc= friend.getString("DescribeYourFriend");
                                result.append(name).append(",").append(fname).append(",").append(fnname).append(",").append(desc).append("\n");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        txt1.setText(result.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(friend);

    }
}