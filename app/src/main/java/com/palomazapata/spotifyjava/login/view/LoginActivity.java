package com.palomazapata.spotifyjava.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.palomazapata.spotifyjava.databinding.ActivityLoginBinding;
import com.palomazapata.spotifyjava.register.view.RegisterActivity;

/**
 * =============================================================================
 * LoginActivity - Clase principal de la pantalla de Login
 * =============================================================================
 *
 * ¿Qué es una Activity?
 * ---------------------
 * Una Activity es un componente de Android que representa UNA PANTALLA de la app.
 * Es como una "ventana" con la que el usuario puede interactuar.
 *
 * Ciclo de vida básico:
 * - onCreate(): se llama cuando la Activity se crea (primera vez que se muestra)
 * - Aquí configuramos la vista, los listeners, etc.
 *
 * Esta Activity extiende AppCompatActivity para tener compatibilidad con
 * versiones antiguas de Android y soporte de Material Design básico.
 * =============================================================================
 */
public class LoginActivity extends AppCompatActivity {

    /*
     * VIEWBINDING - ¿Qué es?
     * ----------------------
     * ViewBinding es una característica de Android que genera automáticamente
     * una clase por cada archivo XML de layout. En lugar de usar findViewById(R.id.xxx),
     * obtenemos referencias directas y tipadas a todas las vistas.
     *
     * Ventajas:
     * - Evita errores de ClassCastException (si el ID no existe, falla en compilación)
     * - Código más limpio: binding.editTextUsuario en vez de findViewById(R.id.editTextUsuario)
     * - Sin necesidad de findViewById (más rápido)
     *
     * El nombre del archivo generado es: ActivityLoginBinding (por activity_login.xml)
     */
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el layout usando ViewBinding
        // inflate() convierte el XML en objetos View y nos devuelve el binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar el listener del botón de login
        configurarBotonLogin();
        // Configurar la navegación hacia la pantalla de registro
        configurarNavegacionARegistro();
    }

    /**
     * Navegación desde Login hacia Register
     *
     * ¿Qué es un Intent?
     * ------------------
     * Un Intent es un objeto que describe una "intención" de hacer algo:
     * - Abrir otra pantalla (Activity)
     * - Lanzar un servicio, abrir un enlace, etc.
     *
     * ¿Qué es un Intent explícito?
     * ----------------------------
     * Es un Intent donde indicamos EXPLÍCITAMENTE qué componente abrir
     * (por ejemplo: RegisterActivity). Indicamos la clase de destino y el contexto.
     *
     * ¿Qué hace startActivity()?
     * --------------------------
     * startActivity(intent) le dice a Android: "abre la pantalla que pide este Intent".
     * Android crea la nueva Activity y la muestra. La pantalla actual (Login) queda
     * debajo en la pila; al pulsar "atrás" el usuario vuelve a Login.
     */
    private void configurarNavegacionARegistro() {
        binding.textViewNoTienesCuenta.setOnClickListener(v -> {
            // Aquí creamos el Intent: queremos abrir RegisterActivity
            Intent intent = new Intent(this, RegisterActivity.class);
            // startActivity() inicia la nueva pantalla
            startActivity(intent);
        });
    }

    /**
     * Configura el comportamiento del botón "Iniciar sesión"
     *
     * ¿Qué es un Listener?
     * --------------------
     * Un Listener (o "oyente") es un objeto que "escucha" eventos del usuario.
     * Cuando el usuario hace una acción (ej: hacer click), el Listener recibe
     * una notificación y ejecuta el código que nosotros definimos.
     *
     * OnClickListener: se ejecuta cuando el usuario hace click en la vista.
     */
    private void configurarBotonLogin() {
        binding.buttonLogin.setOnClickListener(v -> {
            // Obtener el texto de los campos (trim() quita espacios en blanco)
            String usuario = binding.editTextUsuario.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();

            // Validar: si algún campo está vacío
            if (usuario.isEmpty() || password.isEmpty()) {
                mostrarToast("Por favor completa todos los campos");
                return;
            }

            // Si llegamos aquí, ambos campos tienen texto
            mostrarToast("Login correcto (modo prueba)");
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
     * - LENGTH_SHORT: ~2 segundos | LENGTH_LONG: ~3.5 segundos
     */
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
