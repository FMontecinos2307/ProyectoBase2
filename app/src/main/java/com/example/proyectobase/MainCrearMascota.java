package com.example.proyectobase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainCrearMascota extends AppCompatActivity {

    Button btn_add;
    EditText nombre, edad, color;
    private FireBaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_crear_mascota);

        this.setTitle("Crear mascota");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mfirestore = FireBaseFirestore.getInstance();

        nombre = findViewById(R.id.etNombre);
        edad = findViewById(R.id.etEdad);
        color = findViewById(R.id.etColor);
        btn_add = findViewById(R.id.btnRegistrar);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = nombre.getText().toString();
                String e = edad.getText().toString();
                String c = color.getText().toString();

                if (n.isEmpty() && e.isEmpty() && c.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    postPet(n, e, c);
                }
            }
        });
    }

    private void postPet(String n, String e, String c) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombre);
        map.put("edad", edad);
        map.put("colar", color);

        mFirestore.collection("Mascotas").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado Exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error Al Ingresar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean onSupporNavigateUp() { //Flecha Hacia Atrás
        onBackPressed();
        return false;
    }

}