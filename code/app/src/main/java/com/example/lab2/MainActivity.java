package com.example.lab2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int index = -1;
    private java.util.ArrayList<String> d;
    private android.widget.ArrayAdapter<String>  aa;
    private android.widget.Button b1;
    private android.widget.Button b2;
    private android.widget.ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        d=new java.util.ArrayList<String>();
        String[] c = {
                "Edmonton", "vancouver", "Moscow", "sydney", "Berlin", "Vienna", "Tokyo", "Beijing"
        };
        for (int i = 0; i < c.length; i++) {
            d.add(c[i]);
        }

        b1=findViewById(R.id.a);
        b2=findViewById(R.id.del);
        l=findViewById(R.id.l);
        aa = new android.widget.ArrayAdapter<String>(this, R.layout.content, R.id.city, d);

        l.setAdapter(aa);
        l.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> p,android.view.View q, int i, long j) {
                index = i;
            }
        });
        b1.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                android.widget.EditText a = new android.widget.EditText(MainActivity.this);
                androidx.appcompat.app.AlertDialog.Builder b =
                        new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
                b.setTitle("ADD CITY");
                b.setView(a);
                b.setPositiveButton("CONFIRM", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(android.content.DialogInterface d1, int d2) {
                        String s = a.getText().toString();
                        if (s.length() > 0) {
                            d.add(s);
                            aa.notifyDataSetChanged();
                        }
                    }
                });
                b.setNegativeButton("CANCEL", null);
                b.show();
            }
        });
        b2.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View x) {
                if (index >= 0 && index < d.size()) {
                    d.remove(index);
                    index = -1;
                    aa.notifyDataSetChanged();
                }
            }
        });
    }
}