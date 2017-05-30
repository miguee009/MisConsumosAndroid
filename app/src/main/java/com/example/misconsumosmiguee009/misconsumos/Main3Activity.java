package com.example.misconsumosmiguee009.misconsumos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    /*Variables a usar*/
    ListView lista;
    String[] valores = new String[]{"Sueldo","Prestamo","Ventas","Servicios"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        /*Usando un adapter y un arreglo de string para poner en funcion nuestra listview*/
        lista = (ListView)findViewById(R.id.listatipos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,valores);
        lista.setAdapter(adapter);

        /*Metodo onclick que efectua una accion al elegir uno de los elementos de la lista
        * Al elegir el elemento el string se envia a activity 2 para ser mostrada en el boton de tipo*/
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main3Activity.this,Main2Activity.class);
                switch (position){
                    case 0:
                        intent.putExtra("DATO","Sueldo");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("DATO","Prestamo");
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("DATO","Ventas");
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("DATO","Servicios");
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
