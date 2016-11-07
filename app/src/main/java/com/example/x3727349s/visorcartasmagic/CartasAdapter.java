package com.example.x3727349s.visorcartasmagic;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by x3727349s on 07/11/16.
 */

public class CartasAdapter extends ArrayAdapter<Cartas> {


    public CartasAdapter(Context context, int resource , List<Cartas> objects) {
        super(context, resource, objects);
    }
}
