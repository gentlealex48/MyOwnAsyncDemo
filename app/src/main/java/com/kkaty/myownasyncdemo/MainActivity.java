package com.kkaty.myownasyncdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button btn;
    String result = "";

    String strUrl = "http://www.telusco.com/addition.htm?t1=3&t2=6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        btn = findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
             int i = Integer.parseInt(num1.getText().toString());
             int j = Integer.parseInt(num2.getText().toString());
             strUrl = "http://www.google.com";


             new  MultiplyTask().execute();
            }
        });
    }

    public class MultiplyTask extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);

            Toast.makeText(MainActivity.this, "The output is "+result,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String value = bf.readLine();
                System.out.println("result is" + value);
                result = value;
            }
            catch (Exception e)
            {
                System.out.println();
            }

            return null;
        }
    }
}
