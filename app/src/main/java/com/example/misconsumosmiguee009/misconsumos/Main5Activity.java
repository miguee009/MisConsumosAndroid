package com.example.misconsumosmiguee009.misconsumos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    ListView lista;
    String[] valores = new String[]{"Viveres","Diversion","Educacion","Salud","Transporte","Inmueble","Ropa","Personales" +
            "Mascota"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        lista = (ListView)findViewById(R.id.listatiposgastos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,valores);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main5Activity.this,Main4Activity.class);
                switch (position){
                    case 0:
                        intent.putExtra("DATO","Viveres");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("DATO","Diversion");
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("DATO","Educacion");
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("DATO","Salud");
                        startActivity(intent);
                        break;
                    case 4:
                        intent.putExtra("DATO","Transporte");
                        startActivity(intent);
                        break;
                    case 5:
                        intent.putExtra("DATO","Inmueble");
                        startActivity(intent);
                        break;
                    case 6:
                        intent.putExtra("DATO","Ropa");
                        startActivity(intent);
                        break;
                    case 7:
                        intent.putExtra("DATO","Personales");
                        startActivity(intent);
                        break;
                    case 8:
                        intent.putExtra("DATO","Mascota");
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}

