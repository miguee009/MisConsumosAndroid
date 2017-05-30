package com.example.misconsumosmiguee009.misconsumos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    /*Variables a usar*/
    Button Tipo,Guardar,IrMenu;
    EditText Montotext,DescripText;
    String Tipos,Descripcion,IngOGas;
    Integer monto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*Asignando id a nuestras variables*/
        Montotext = (EditText) findViewById(R.id.textingresos);
        DescripText = (EditText) findViewById(R.id.descripcion);
        Tipo = (Button) findViewById(R.id.BotonTipo);
        Guardar =(Button)findViewById(R.id.guardaring);
        IrMenu =(Button)findViewById(R.id.IrMenuing);

        /*"Escuchar" botones el efectuar un click*/
        IrMenu.setOnClickListener(this);
        Tipo.setOnClickListener(this);
        Guardar.setOnClickListener(this);

        /*Obtener datos de otra activity*/
        Intent datrecv= getIntent();
        Bundle extra = datrecv.getExtras();

        if(extra!=null){
            String dato = extra.getString("DATO");
            Tipo.setText(dato);
        }

    }
    /*Metodo onclick usado para ejecutar la funcion de los botones*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BotonTipo:
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
                break;
            case R.id.guardaring:

                Tipos = Tipo.getText().toString();
                Descripcion = DescripText.getText().toString();
                IngOGas = "Ingreso";

                if(!Tipos.contentEquals("Elegir Tipo")) {
                    if(!Montotext.getText().toString().isEmpty()) {
                        monto = Integer.parseInt(Montotext.getText().toString());
                        UsuarioSqliteHelper usuario = new UsuarioSqliteHelper(this, "DbUsuario", null, 1);
                        SQLiteDatabase db = usuario.getWritableDatabase();
                        db.execSQL("INSERT INTO Usuario(Monto,Tipo,Descripcion,IngOGas) VALUES (" + monto + ",'" + Tipos + "','" + Descripcion + "','" + IngOGas + "')");
                        db.close();
                        Toast.makeText(this, "Ingreso Guardado", Toast.LENGTH_LONG).show();
                        Tipo.setText("Elegir Tipo");
                        Montotext.setText("");
                        DescripText.setText("");
                    }else{
                        Toast.makeText(this, "Ingresa un Monto!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(this, "Elegi un tipo!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.IrMenuing:
                Intent intentm = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intentm);
                break;
        }
    }
}