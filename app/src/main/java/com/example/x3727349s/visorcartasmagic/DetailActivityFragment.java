package com.example.x3727349s.visorcartasmagic;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent i = getActivity().getIntent();

        if (i != null){
            Cartas cartas = (Cartas) i.getSerializableExtra("Cartas");

            if (cartas != null){
                updateui(cartas);
            }



        }

        return view;
    }

    private void updateui(Cartas cartas) {
        Log.d("CARTA", cartas.toString());
    }
}
