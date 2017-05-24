package com.example.misconsumosmiguee009.misconsumos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity implements View.OnClickListener {

    Button Tipo, Guardar,IrMenu;
    EditText Montotext;
    EditText DescripText;
    String Tipos, Descripcion;
    String IngOGas;
    Integer monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Montotext = (EditText) findViewById(R.id.textgastos);
        DescripText = (EditText) findViewById(R.id.descripciongastos);
        Tipo = (Button) findViewById(R.id.BotonTipogastos);
        Guardar = (Button) findViewById(R.id.guardargastos);
        IrMenu = (Button)findViewById(R.id.IrMenuGas);

        IrMenu.setOnClickListener(this);
        Tipo.setOnClickListener(this);
        Guardar.setOnClickListener(this);

        Intent datrecv = getIntent();
        Bundle extra = datrecv.getExtras();

        if (extra != null) {
            String dato = extra.getString("DATO");
            Tipo.setText(dato);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BotonTipogastos:
                Intent intent = new Intent(Main4Activity.this, Main5Activity.class);
                startActivity(intent);
                break;
            case R.id.guardargastos:
                Tipos = Tipo.getText().toString();
                monto = Integer.parseInt(Montotext.getText().toString());
                Descripcion = DescripText.getText().toString();
                IngOGas ="Gasto";
                UsuarioSqliteHelper usuario = new UsuarioSqliteHelper(this, "DbUsuario", null, 1);
                SQLiteDatabase db = usuario.getWritableDatabase();
                db.execSQL("INSERT INTO Usuario(Monto,Tipo,Descripcion,IngOGas) VALUES (" + monto + ",'" + Tipos + "','" + Descripcion + "','"+ IngOGas +"')");
                db.close();
                Toast.makeText(this, "Gasto Guardado", Toast.LENGTH_LONG).show();
                Tipo.setText("ELEGIR TIPO");
                Montotext.setText("");
                DescripText.setText("");
                break;
            case R.id.IrMenuGas:
                Intent intentg = new Intent(Main4Activity.this,MainActivity.class);
                startActivity(intentg);
                break;
        }
    }
}