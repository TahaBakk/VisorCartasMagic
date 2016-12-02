package com.example.x3727349s.visorcartasmagic;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import android.widget.ImageView;
import android.widget.TextView;
import android.databinding.DataBindingUtil;
import com.example.x3727349s.visorcartasmagic.databinding.TitolCartesBinding;

import java.util.List;

/**
 * Created by x3727349s on 07/11/16.
 */

public class CartasAdapter extends ArrayAdapter<Cartas> {


    public CartasAdapter(Context context, int resource , List<Cartas> objects) {
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

            // Obtenim l'objecte en la possició corresponent
            Cartas cartas = getItem(position);
            Log.w("CARTA: ", cartas.toString());

            TitolCartesBinding binding = null;


              // Mirem a veure si la View s'està reusant, si no es així "inflem" la View
              // https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView#row-view-recycling
              if (convertView == null) {

                  LayoutInflater inflater = LayoutInflater.from(getContext());
                  //convertView = inflater.inflate(R.layout.titol_cartes, parent, false);
                  binding = DataBindingUtil.inflate(inflater, R.layout.titol_cartes, parent, false);
           }else{
                  binding = DataBindingUtil.getBinding(convertView);
              }

              // Unim el codi en les Views del Layout
              /*TextView tvName = (TextView) convertView.findViewById(R.id.tvCartas);
              TextView tvRarity = (TextView) convertView.findViewById(R.id.tvRarity);
              ImageView ivPosterImage = (ImageView) convertView.findViewById(R.id.ivPosterImage);*/

              // Mostrem les dades dels objectes (provinents del JSON) en el layout
              //tvName.setText(cartas.getName());
              //tvRarity.setText(" -----" + cartas.getRarity() + "-----");
                binding.tvCartas.setText(cartas.getName());
                Glide.with(getContext()).load(cartas.getImageUrl()).into(binding.ivPosterImage);

              // Retornem la View replena per a mostrarla
              return binding.getRoot();
        }


}
