package com.cecyred.ww.cecyred;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TerminosyCondiciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminosy_condiciones);
    }
    public void atras(View view) {
        Intent intent= new Intent (TerminosyCondiciones.this, REGISTROUSUARIO.class);
        startActivity(intent);
    }
}
