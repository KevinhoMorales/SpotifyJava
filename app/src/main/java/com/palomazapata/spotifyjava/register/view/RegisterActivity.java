package com.palomazapata.spotifyjava.register.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.palomazapata.spotifyjava.databinding.ActivityRegisterBinding;
import com.palomazapata.spotifyjava.profile.view.ProfileActivity;

/**
 * =============================================================================
 * RegisterActivity - Clase principal de la pantalla de Registro
 * =============================================================================
 *
 * ¿Qué es una Activity?
 * ---------------------
 * Una Activity es un componente de Android que representa UNA PANTALLA de la app.
 * Es como una "ventana" con la que el usuario puede interactuar.
 *
 * Ciclo de vida básico:
 * - onCreate(): se llama cuando la Activity se crea (primera vez que se muestra)
 * - Aquí configuramos la vista, los listeners y las validaciones.
 *
 * Esta Activity extiende AppCompatActivity para tener compatibilidad con
 * versiones antiguas de Android y soporte de Material Design básico.
 * =============================================================================
 */
public class RegisterActivity extends AppCompatActivity {

    /*
     * VIEWBINDING - ¿Qué es?
     * ----------------------
     * ViewBinding es una característica de Android que genera automáticamente
     * una clase por cada archivo XML de layout. En lugar de usar findViewById(R.id.xxx),
     * obtenemos referencias directas y tipadas a todas las vistas.
     *
     * El nombre del archivo generado es: ActivityRegisterBinding (por activity_register.xml)
     */
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el layout usando ViewBinding
        // inflate() convierte el XML en objetos View y nos devuelve el binding
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar el listener del botón "Registrarse"
        configurarBotonRegistrarse();
    }

    /**
     * Configura el comportamiento del botón "Registrarse"
     *
     * ¿Qué es un Listener?
     * --------------------
     * Un Listener (o "oyente") es un objeto que "escucha" eventos del usuario.
     * Cuando el usuario hace una acción (ej: hacer click), el Listener recibe
     * una notificación y ejecuta el código que nosotros definimos.
     *
     * OnClickListener: se ejecuta cuando el usuario hace click en el botón.
     */
    private void configurarBotonRegistrarse() {
        binding.buttonRegistrarse.setOnClickListener(v -> {
            // Obtenemos el texto de cada campo (trim() quita espacios en blanco al inicio y final)
            String usuario = binding.editTextUsuario.getText().toString().trim();
            String email = binding.editTextEmail.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();
            String repetirPassword = binding.editTextRepetirPassword.getText().toString().trim();

            // VALIDACIÓN 1: ¿Qué es una validación básica?
            // Comprobamos si algún campo está vacío. Si es así, mostramos un Toast y salimos.
            if (usuario.isEmpty() || email.isEmpty() || password.isEmpty() || repetirPassword.isEmpty()) {
                mostrarToast("Por favor completa todos los campos");
                return;
            }

            // VALIDACIÓN 2: Comprobamos si las contraseñas coinciden
            if (!password.equals(repetirPassword)) {
                mostrarToast("Las contraseñas no coinciden");
                return;
            }

            // Si llegamos aquí, todo está correcto. Navegamos a Profile enviando nombre y correo.
            /*
             * ¿Qué es putExtra()?
             * -------------------
             * putExtra(clave, valor) permite AÑADIR DATOS al Intent.
             * Es como meter información en un "sobre" que llevará la nueva Activity.
             *
             * ¿Qué significa clave-valor?
             * --------------------------
             * Usamos un nombre (clave) para identificar cada dato, y el valor (String, int, etc.).
             * En ProfileActivity recuperaremos con getStringExtra("nombre") usando la misma clave.
             *
             * Cómo funciona el paso de datos entre Activities:
             * - El Intent es el "mensajero" que lleva los datos.
             * - La Activity que recibe usa getIntent() para obtener ese Intent y luego getStringExtra(clave).
             */
            Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
            intent.putExtra("nombre", usuario);
            intent.putExtra("correo", email);
            startActivity(intent);
            // Cerramos Register para que al pulsar "atrás" desde Profile no vuelva a la pantalla de registro
            finish();
        });
    }

    /**
     * Muestra un mensaje breve en pantalla
     *
     * ¿Qué es un Toast?
     * -----------------
     * Un Toast es un mensaje pequeño que aparece temporalmente en la parte
     * inferior de la pantalla. Es ideal para feedback rápido al usuario
     * (ej: "Guardado", "Error", "Completa los campos").
     *
     * Características:
     * - Se muestra unos segundos y desaparece solo
     * - No interrumpe la interacción del usuario
     * - LENGTH_SHORT: aproximadamente 2 segundos
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
