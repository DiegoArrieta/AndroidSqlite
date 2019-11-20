package com.clase.emeplobd704;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity {

    ListView listViewRegistros;
    ArrayList<String> listaInformacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        listViewRegistros=(ListView)findViewById(R.id.lv_registros);

        consultarRegistros();

        ArrayAdapter adaptador=new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaInformacion);
        listViewRegistros.setAdapter(adaptador);
    }

    private void consultarRegistros() {
        listaInformacion=new ArrayList<String>();
        Creacion x=new Creacion(getApplicationContext(), "Seccion704", null, 1);
        SQLiteDatabase db=x.getWritableDatabase();
        Cursor fila = db.rawQuery("select * from producto",null);

        while(fila.moveToNext()){
            listaInformacion.add(fila.getInt(0)+" - "+fila.getString(1));
        }
    }
}
