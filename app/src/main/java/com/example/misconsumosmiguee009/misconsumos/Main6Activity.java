package com.example.misconsumosmiguee009.misconsumos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main6Activity extends AppCompatActivity {

    /*Declaracion de variable listview*/
    ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        /*Abrimos la base de datos para leerlos y mostrarlos en una listview*/
        UsuarioSqliteHelper usuario = new UsuarioSqliteHelper(this,"DbUsuario",null,1);
        SQLiteDatabase db = usuario.getReadableDatabase();
        if(db!=null){
            Cursor c = db.rawQuery("select * from Usuario",null);
            int cantidad = c.getCount();
            int i = 0;
            String[] arreglo = new String[cantidad];
            if(c.moveToFirst()){
                do{
                    String linea = c.getString(3)+ " Tipo: "+ c.getString(1)+" $: "+Integer.toString(c.getInt(0))+" Desc: "+ c.getString(2);
                    arreglo[i]=linea;
                    i++;
                }while(c.moveToNext());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,arreglo);
            lista = (ListView) findViewById(R.id.listamovimientos);
            lista.setAdapter(adapter);
        }
    }
}
