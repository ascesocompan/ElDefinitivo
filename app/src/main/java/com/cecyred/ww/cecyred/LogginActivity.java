package com.cecyred.ww.cecyred;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import static android.view.ViewAnimationUtils.createCircularReveal;

public class LogginActivity extends AppCompatActivity {
    private TextView textviewusuario,textviewpassword;
    private ImageView cr,imagenregistro;
    private  static final long DURACION_REVELAR=1500;
    private  static  final long DURACION_QUITAR=800;
    private EditText et_usuario, et_contra;
    public static String nombredeusuario, numeroboleta;
    private ProgressBar progresbar;
    private RadioButton RB;
    private boolean isRadioActivated;

    public LogginActivity() {
    }
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);
        et_usuario=(EditText)findViewById(R.id.Usuario);
        et_contra=(EditText)findViewById(R.id.Contraseña);
        progresbar=(ProgressBar)findViewById(R.id.progressBar);
        RB=findViewById(R.id.radioButton);

        isRadioActivated=RB.isChecked();
        RB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //ACTIVADO
                if(isRadioActivated){
                    RB.setChecked(false);
                }
                isRadioActivated=RB.isChecked();
            }
        });
        if(Preferences.obtenerPreferenceBoolean(this,Preferences.PREFERENCE_ESTADO_BUTTON_SESION)){
            Intent i = new Intent(this,Perfil.class);
            startActivity(i);
            finish();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Mostrar() {
        textviewusuario=findViewById(R.id.Usuario);
        textviewpassword=findViewById(R.id.Contraseña);
        Animator animator= createCircularReveal(textviewusuario,0,10,0,textviewusuario.getWidth()*1.5f);
        animator.setDuration(DURACION_REVELAR);
        Animator animator1= createCircularReveal(textviewpassword,0,10,0,textviewpassword.getWidth()*1.5f);
        animator1.setDuration(DURACION_REVELAR);
        Animator animator3= createCircularReveal(RB,0,10,0,textviewpassword.getWidth()*1.5f);
        animator3.setDuration(DURACION_REVELAR);
        textviewpassword.setVisibility(View.VISIBLE);
        animator1.start();
        textviewusuario.setVisibility(View.VISIBLE);
        animator.start();
        RB.setVisibility(View.VISIBLE);
        animator3.start();
        cr=findViewById(R.id.CR);
        Animator animator2= ViewAnimationUtils.createCircularReveal(cr,cr.getWidth()/2,cr.getHeight()/2,cr.getWidth(),0);
        animator2.setDuration(DURACION_QUITAR);
        animator2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation){
                super.onAnimationEnd(animation);
                cr.setVisibility(View.INVISIBLE);
            } });
        animator2.start();
    }

    int ban=1;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void ingreso(View v){

        if(ban==1){
            ban=ban+1;
            Mostrar();
        }else{
            String usuario = et_usuario.getText().toString().trim();
            String contrasena=et_contra.getText().toString().trim();
            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Ingresa el usuario y la contraseña", Toast.LENGTH_SHORT).show();
            }else {
                progresbar.setVisibility(View.VISIBLE);
                Response.Listener<String> respolistener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean succes = jsonResponse.getBoolean("success");

                            if (succes) {
                                progresbar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LogginActivity.this, Perfil.class);
                                LogginActivity.this.startActivity(intent);
                                nombredeusuario=jsonResponse.getString("NomUsuario");
                                numeroboleta=jsonResponse.getString("Boleta");
                                Preferences.savePreferenceBoolean(LogginActivity.this,RB.isChecked(),Preferences.PREFERENCE_ESTADO_BUTTON_SESION);
                                Preferences.savePreferendceString(LogginActivity.this,nombredeusuario,Preferences.PREFERENCE_USUARIO_LOGIN);
                                finish();
                            } else {
                                progresbar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            //e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                };
                requestlogin requestlog = new requestlogin( usuario, contrasena, respolistener);
                RequestQueue queue = Volley.newRequestQueue(LogginActivity.this);
                queue.add(requestlog);
            }
        }
    }

    public void CambioRegistro(View view) {
        Intent intent= new Intent (LogginActivity.this, REGISTROUSUARIO.class);
        startActivity(intent);
    }
    public void CambioSolover(View view) {
        if(Preferences.obtenerPreferenceBoolean(this,Preferences.PREFERENCE_ESTADO_BUTTON_SESION)){
            Intent i = new Intent(this,Perfil.class);
            startActivity(i);
            finish();
        }

    }
}
