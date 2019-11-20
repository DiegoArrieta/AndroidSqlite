package com.clase.emeplobd704;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ApplicationErrorReport;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txt_codigo, txt_nombre, txt_precio, txt_cantidad;
    private Button btn_insertar, btn_buscar, btn_eliminar, btn_actualizar, btn_mostrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_codigo = (EditText)findViewById(R.id.edt_codigo);
        txt_nombre = (EditText)findViewById(R.id.edt_nombre);
        txt_cantidad = (EditText)findViewById(R.id.edt_cantidad);
        txt_precio = (EditText)findViewById(R.id.edt_precio);
        btn_insertar = (Button)findViewById(R.id.btn_insertar);
        btn_buscar = (Button)findViewById(R.id.btn_buscar);
        btn_eliminar = (Button)findViewById(R.id.btn_eliminar);
        btn_actualizar= (Button)findViewById(R.id.btn_actualizar);
        btn_mostrar = (Button)findViewById(R.id.btn_mostrar_registros);
        btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Creacion x=new Creacion(getApplicationContext(),"Seccion704",null,1);
                SQLiteDatabase db=x.getWritableDatabase();
                int cod,pre,cant;
                String nombre;
                cod=Integer.parseInt(txt_codigo.getText().toString());
                nombre=txt_nombre.getText().toString();
                pre=Integer.parseInt(txt_precio.getText().toString());
                cant=Integer.parseInt(txt_cantidad.getText().toString());
                ContentValues registro=new ContentValues();
                registro.put("codigo",cod);
                registro.put("nombre",nombre);
                registro.put("precio", pre);
                registro.put("cantidad", cant);

                db.insert("producto", null, registro);
                db.close();



            }
        });

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Creacion x=new Creacion(getApplicationContext(), "Seccion704", null, 1);
                SQLiteDatabase db=x.getWritableDatabase();
                int cod=Integer.parseInt(txt_codigo.getText().toString());
                Cursor fila = db.rawQuery("select nombre,precio,cantidad from producto where codigo="+cod,null);
                if(fila.moveToFirst())
                {
                    txt_nombre.setText(fila.getString(0));
                    txt_precio.setText(fila.getString(1));
                    txt_cantidad.setText(fila.getString(2));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Código no encontrado", Toast.LENGTH_SHORT).show();
                    txt_codigo.requestFocus();
                }
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Creacion x=new Creacion(getApplicationContext(), "Seccion704", null, 1);
                SQLiteDatabase db=x.getWritableDatabase();
                int cod=Integer.parseInt(txt_codigo.getText().toString());
                int i=db.delete("producto","codigo="+cod,null);
                db.close();
                if(i==1)
                {
                    Toast.makeText(getApplicationContext(), "Producto COD: "+cod+" eliminado.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Producto COD: "+cod+" no encontrado.", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Creacion x=new Creacion(getApplicationContext(), "Seccion704", null, 1);
                SQLiteDatabase db=x.getWritableDatabase();
                int cod, pre,cant;
                String nombre;
                cod=Integer.parseInt(txt_codigo.getText().toString());
                nombre=txt_nombre.getText().toString();
                pre=Integer.parseInt(txt_precio.getText().toString());
                cant=Integer.parseInt(txt_cantidad.getText().toString());
                ContentValues valor=new ContentValues();
                valor.put("codigo",cod);
                valor.put("nombre",nombre);
                valor.put("precio", pre);
                valor.put("cantidad", cant);
                int i=db.update("producto",valor,"codigo="+cod,null);
                if(i==1)
                {
                    Toast.makeText(getApplicationContext(), "Producto COD: "+cod+" actualizado.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Producto COD: "+cod+" no encontrado.", Toast.LENGTH_LONG).show();
                }


            }
        });

        btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),RegistroActivity.class);
                startActivity(i);
            }
        });
    }
}
