package com.example.appcomanda;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.content.Intent;
//import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    private EditText et1, et2, et3, et4;

    @Override // ... código para inicializar elementos de la interfaz de usuario ...
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }

    public void Insertar(View v){  //Base de datos abierta para lectura
        AdminSQLiteOpenHelper admim = new AdminSQLiteOpenHelper(this, "administracion", null,1 );
        SQLiteDatabase db = admim.getReadableDatabase();
        String documento = et1.getText().toString(); //Obtener entrada del usuario
        String nombre = et2.getText().toString();
        String correo = et3.getText().toString();
        String telefono = et4.getText().toString();

        ContentValues registro = new ContentValues();
        //Crear objeto de valores de contenido
        registro.put("documento", documento);
        registro.put("nombre", nombre);
        registro.put("correo", correo);
        registro.put("telefono", telefono);
        db.insert("clientes",null,registro);//Insertar datos en la BD
        db.close();//Cerrar conexión de BD

        et1.setText("");//Borrar campos de entrada
        et2.setText("");
        et3.setText("");
        et4.setText("");

        Toast.makeText(this,"Se cargaron los datos de nuevo usuario", Toast.LENGTH_LONG).show();
    }

    public void Consultar(View v){
        AdminSQLiteOpenHelper admim =new AdminSQLiteOpenHelper(this, "administracion", null,1 );
        SQLiteDatabase db = admim.getReadableDatabase();
        String doc = et1.getText().toString();
//Ejecutar consulta SQL
        Cursor fila = db.rawQuery("SELECT nombre, correo, telefono FROM clientes WHERE documento=" + doc,null);

        if (fila.moveToFirst()){//Verificar resultados
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        }else {
            Toast.makeText(this, "No existe un registro con ese documento", Toast.LENGTH_LONG).show();
        }
        db.close();//cierra la conexión a la bd
    }

    public void Eliminar(View v){
        AdminSQLiteOpenHelper admim =new AdminSQLiteOpenHelper(this, "administracion", null,1 );
        SQLiteDatabase db = admim.getReadableDatabase();
        String doc = et1.getText().toString();

        int cant = db.delete("clientes", "documento=" + doc,null);
        db.close();

        et1.setText("");//Borrar campos de entrada
        et2.setText("");
        et3.setText("");
        et4.setText("");

        if (cant == 1){
            Toast.makeText(this,"Se borro el cliente con docmuento ingresado", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"No existe un registro con ese documento", Toast.LENGTH_SHORT).show();
        }
    }

    public void Modificar (View v){
        AdminSQLiteOpenHelper admim = new AdminSQLiteOpenHelper(this, "administracion", null,1 );
        SQLiteDatabase db = admim.getReadableDatabase();
        String doc = et1.getText().toString();//Obtener entrada del usuario
        String nombre = et2.getText().toString();
        String correo = et3.getText().toString();
        String telefono = et4.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("nombre", nombre);//Crear objeto de valores de contenido
        registro.put("correo", correo);
        registro.put("telefono", telefono);

        int cant = db.update("clientes", registro,"documento=" + doc, null);

        et1.setText("");//Borrar campos de entrada
        et2.setText("");
        et3.setText("");
        et4.setText("");

        if (cant == 1){
            Toast.makeText(this,"Se modificaron los datos del cliente", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"No existe un registro con ese documento", Toast.LENGTH_SHORT).show();
        }
    }

    public void Limpiar (View v){

        et1.setText("");//Borrar campos de entrada
        et2.setText("");
        et3.setText("");
        et4.setText("");

        Toast.makeText(this,"Se limpiaron los campos de registro", Toast.LENGTH_LONG).show();
    }

    public void onclick(View view) {
        Intent myintent = new Intent(MainActivity3.this,MainActivity.class);
        startActivity(myintent);
    }

    public void onclic(View view) {
        Intent myintent = new Intent(MainActivity3.this,MainActivity4.class);
        startActivity(myintent);
    }
}