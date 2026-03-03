package com.palomazapata.spotifyjava.profile.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.palomazapata.spotifyjava.databinding.ActivityProfileBinding;
import com.palomazapata.spotifyjava.login.view.LoginActivity;

/**
 * =============================================================================
 * ProfileActivity - Pantalla de perfil del usuario
 * =============================================================================
 *
 * Recibe el nombre y el correo enviados desde RegisterActivity mediante
 * el Intent (putExtra). Los muestra en pantalla y permite "Cerrar sesión"
 * para volver a Login.
 * =============================================================================
 */
public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recibir y mostrar los datos que nos envió RegisterActivity
        recibirYMostrarDatos();
        // Configurar el botón "Cerrar sesión"
        configurarBotonCerrarSesion();
    }

    /**
     * Recibe los datos del Intent y los muestra en los TextView
     *
     * ¿Qué es getIntent()?
     * ---------------------
     * getIntent() devuelve el Intent con el que se ABRIÓ esta Activity.
     * Ese Intent es el mismo que creamos en RegisterActivity con putExtra().
     *
     * ¿Qué hace getStringExtra()?
     * --------------------------
     * getStringExtra("clave") lee el valor String que se guardó con putExtra("clave", valor).
     * La "clave" debe coincidir exactamente con la usada al enviar ("nombre", "correo").
     *
     * ¿Qué ocurre si la clave no existe?
     * ----------------------------------
     * getStringExtra() devuelve null si no hay ningún valor para esa clave.
     * Por eso comprobamos con (valor != null) y usamos un texto por defecto si hace falta.
     */
    private void recibirYMostrarDatos() {
        // Aquí obtenemos el Intent que abrió esta pantalla
        Intent intent = getIntent();

        // Recuperamos el nombre (clave "nombre"). Si no existe, usamos texto por defecto
        String nombre = intent.getStringExtra("nombre");
        if (nombre == null) {
            nombre = "";
        }
        binding.textViewNombre.setText(nombre);

        // Recuperamos el correo (clave "correo")
        String correo = intent.getStringExtra("correo");
        if (correo == null) {
            correo = "";
        }
        binding.textViewCorreo.setText(correo);
    }

    /**
     * Configura el botón "Cerrar sesión"
     *
     * Al pulsar: volvemos a LoginActivity con un Intent y cerramos esta Activity.
     *
     * ¿Qué hace finish()?
     * -------------------
     * finish() le dice a Android: "termina esta Activity y quítala de la pila".
     * La pantalla se cierra y el usuario vuelve a la pantalla anterior (o a Login si
     * hemos abierto Login con startActivity). Así evitamos que al pulsar "atrás"
     * vuelva a Profile después de cerrar sesión.
     */
    private void configurarBotonCerrarSesion() {
        binding.buttonCerrarSesion.setOnClickListener(v -> {
            // Abrimos LoginActivity para que el usuario vuelva a la pantalla de inicio
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);
            // Cerramos esta Activity (Profile). Al pulsar "atrás" desde Login saldrá de la app
            finish();
        });
    }
}
