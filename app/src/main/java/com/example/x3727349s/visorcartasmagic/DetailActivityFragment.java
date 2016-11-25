package com.example.x3727349s.visorcartasmagic;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {


    private View view;
    private ImageView ivPosterImage;
    private TextView tvName;
    private TextView tvRarity;
    private TextView tvColor;
    private TextView tvDescripcion;


    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_detail, container, false);
        view = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent i = getActivity().getIntent();

        if (i != null){
            Cartas cartas = (Cartas) i.getSerializableExtra("cartas");

            if (cartas != null){
                updateui(cartas);
            }
        }

        return view;
    }

    private void updateui(Cartas cartas) {
        Log.d("CARTA", cartas.toString());

        ivPosterImage = (ImageView) view.findViewById(R.id.ivPosterImage);
        tvName = (TextView) view.findViewById(R.id.tvName);
        tvRarity = (TextView) view.findViewById(R.id.tvRarity);
        tvColor = (TextView) view.findViewById(R.id.tvColor);
        tvDescripcion = (TextView) view.findViewById(R.id.tvDescripcion);

        tvName.setText(cartas.getName());
        tvRarity.setText(cartas.getRarity());
        tvColor.setText(cartas.getColors());
        tvDescripcion.setText(cartas.getText());
        Glide.with(getContext()).load(cartas.getImageUrl()).into(ivPosterImage);
    }

}
