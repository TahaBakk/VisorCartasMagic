package com.example.x3727349s.visorcartasmagic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
           binding = DataBindingUtil.inflate(
                  inflater, R.layout.lv_pelis_row, parent, false);

          return binding.getRoot();
      }

      @Override
      public void bindView(View view, Context context, Movie model) {
          LvPelisRowBinding binding = DataBindingUtil.getBinding(view);
          binding.tvTitle.setText(model.getTitle());
          binding.tvCriticsScore.setText(model.getCritics_score());
          Glide.with(context).load(model.getPosterUrl()).into(binding.ivPosterImage);
      }



}
