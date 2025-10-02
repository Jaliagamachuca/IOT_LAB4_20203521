package com.example.l3_julioaliaga;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.l3_julioaliaga.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnIngresar.setOnClickListener(v -> {
            if (tieneInternet()) {
                startActivity(new Intent(MainActivity.this, AppActivity.class));
            } else {
                mostrarDialog();
            }
        });
    }

    //Estaba en duda si el tercer boton debería ser "Futuro" o "Deportes", pero lo dejé como mostraba en la imagen referencial

    // Usé ChatGPT (Modelo GPT-5, 01/10/2025)
    // Prompt usado: "cómo validar la conexión a internet en Android con Java, con un método true o false
    private boolean tieneInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnected();
    }

    private void mostrarDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Sin conexión a Internet")
                .setMessage("Por favor verifica tu conexión.")
                .setPositiveButton("Configuración", (dialog, which) ->
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)))
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
