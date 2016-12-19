package com.example.x3727349s.visorcartasmagic;

import android.content.Intent;
import android.databinding.DataBindingUtil;
//import android.databinding.tool.Binding;
//import android.databinding.tool.DataBindingBuilder;
import android.support.v4.app.Fragment;
import android.os.Bundle;
//import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
import com.alexvasilkov.events.Event;

import com.alexvasilkov.events.Events;
import com.bumptech.glide.Glide;
import com.example.x3727349s.visorcartasmagic.databinding.FragmentDetailBinding;

/**
 * A placeholder fragment containing a simple view.
 */

public class DetailActivityFragment extends Fragment {

    private FragmentDetailBinding binding;

    public DetailActivityFragment() {
    }

    @Override
    public void onStart() {
            super.onStart();
                Events.register(this);
        }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_detail, container, false);
        //view = inflater.inflate(R.layout.fragment_detail, container, false);

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_detail, container, false);

        View view = binding.getRoot();


        Intent i = getActivity().getIntent();

        if (i != null){
            Cartas cartas = (Cartas) i.getSerializableExtra("cartas");

            if (cartas != null){
                updateui(cartas);
            }
        }

        return view;
    }

    @Events.Subscribe("cartas-selected")
    private void onCartasSelected(Cartas cartas) {
            updateui(cartas);
    }


    private void updateui(Cartas cartas) {
        Log.d("CARTA", cartas.toString());

        binding.tvName.setText(cartas.getName());
        binding.tvRarity.setText(cartas.getRarity());
        binding.tvColor.setText(cartas.getColors());
        binding.tvDescripcion.setText(cartas.getText());
        Glide.with(getContext()).load(cartas.getImageUrl()).into(binding.ivPosterImage);
    }

}
