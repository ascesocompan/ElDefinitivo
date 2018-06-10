package com.cecyred.ww.cecyred;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ConfiguracionFragment extends Fragment {
    private Button botonCerrarjaja;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_configuracion,container,false);

    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        botonCerrarjaja=view.findViewById(R.id.buttonCerrarSesion);
        botonCerrarjaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.savePreferenceBoolean(view.getContext(),false,Preferences.PREFERENCE_ESTADO_BUTTON_SESION);
                Intent intent = new Intent(ConfiguracionFragment.this.getContext(), LogginActivity.class);
                startActivity(intent);

                getActivity().finish();
            }
        });
    }
}
