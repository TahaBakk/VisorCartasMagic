package com.example.x3727349s.visorcartasmagic;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.x3727349s.visorcartasmagic.databinding.TitolCartesBinding;

/**
 * Created by x3727349s on 02/12/16.
 */

public class CartasCursorAdapter extends CupboardCursorAdapter<Cartas> {

    public CartasCursorAdapter(Context context, Class<Cartas> entityClass) {
          super(context, entityClass);
      }

      @Override
      public View newView(Context context, Cartas model, ViewGroup parent) {
          LayoutInflater inflater = LayoutInflater.from(context);


          TitolCartesBinding binding = DataBindingUtil.inflate(
                  inflater, R.layout.titol_cartes, parent, false);

          return binding.getRoot();
      }
  //esto es lo que me muestra en cada item de la lista utilitzem aquest i deixem de fer servir  el cartasAdapter
      @Override
      public void bindView(View view, Context context, Cartas model) {
          TitolCartesBinding binding = DataBindingUtil.getBinding(view);
          binding.tvCartas.setText(model.getName());
          Glide.with(context).load(model.getImageUrl()).into(binding.ivPosterImage);
      }



}
