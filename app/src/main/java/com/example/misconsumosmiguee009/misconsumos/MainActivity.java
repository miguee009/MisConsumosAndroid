package com.example.misconsumosmiguee009.misconsumos;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /*Variables a usar*/
    Button ingreso,gastos,reiniciar,listmov,salir;
    TextView ingtext,gastext,saltext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Asignando id a nuestras variables*/
        ingtext = (TextView)findViewById(R.id.ingresotext);
        gastext = (TextView)findViewById(R.id.gastostext);
        saltext = (TextView)findViewById(R.id.saldo);
        gastos = (Button)findViewById(R.id.gastos);
        ingreso = (Button)findViewById(R.id.ingreso);
        reiniciar =(Button)findViewById(R.id.reiniciarbd);
        listmov = (Button)findViewById(R.id.movimientos);
        salir= (Button)findViewById(R.id.salir);

        /*"Escuchar" botones el efectuar un click*/
        salir.setOnClickListener(this);
        listmov.setOnClickListener(this);
        reiniciar.setOnClickListener(this);
        ingreso.setOnClickListener(this);
        gastos.setOnClickListener(this);

        /*Leer base de datos para setear los valores de ingreso gasto y saldo*/
        UsuarioSqliteHelper usuario = new UsuarioSqliteHelper(this,"DbUsuario",null,1);
        SQLiteDatabase db = usuario.getReadableDatabase();
        if(db!=null){
            Cursor c = db.rawQuery("select sum(Monto),IngOGas from Usuario where IngOGas = 'Ingreso' ",null);
            c.moveToFirst();
            Integer saldo=c.getInt(0);
            String IngOGas =Integer.toString(c.getInt(0));
            ingtext.setText("$ "+IngOGas);
            c =db.rawQuery("select sum(Monto),IngOGas from Usuario where IngOGas = 'Gasto' ",null);
            c.moveToFirst();
            saldo = saldo - c.getInt(0);
            IngOGas = Integer.toString(c.getInt(0));
            gastext.setText("$ "+IngOGas);
            saltext.setText("$ "+Integer.toString(saldo));
        }
    }
    /*Metodo onclick usado para ejecutar la funcion de los botones*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ingreso:
                Intent intenting = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intenting);
                break;
            case R.id.gastos:
                Intent intentgas = new Intent(MainActivity.this,Main4Activity.class);
                startActivity(intentgas);
                break;
            case R.id.reiniciarbd:
                UsuarioSqliteHelper usuario = new UsuarioSqliteHelper(this, "DbUsuario", null, 1);
                SQLiteDatabase db = usuario.getWritableDatabase();
                db.execSQL("DELETE FROM Usuario");
                db.close();
                ingtext.setText("$ 0");
                gastext.setText("$ 0");
                saltext.setText("$ 0");
                break;
            case R.id.movimientos:
                Intent intentlistmov = new Intent(MainActivity.this,Main6Activity.class);
                startActivity(intentlistmov);
                break;
            case R.id.salir:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Seguro que desea cerrar la aplicacion?")
                        .setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = alert.create();
                dialog.show();
                break;
        }
    }
}
