package com.example.x3727349s.visorcartasmagic;

import android.net.Uri;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by x3727349s on 18/10/16.
 */

class CartasApi {

        private final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

        ArrayList<Cartas> getCartes() throws JSONException {
                        Uri builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .build();
            String url = builtUri.toString();
            return doCall(url);

        }

    @Nullable
    private ArrayList<Cartas> doCall(String url) throws JSONException {
        String JsonRespon=null;
        try {
            JsonRespon = HttpUtils.get(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processJson(JsonRespon);

    }

    private ArrayList<Cartas> processJson(String jsonResponse) throws JSONException {

        ArrayList<Cartas> cartas = new ArrayList<>();

        JSONObject data = new JSONObject(jsonResponse);
        JSONArray jsonCartas = data.getJSONArray("Cards");
            for (int i = 0; i < jsonCartas.length(); i++) {
                JSONObject jsonCarta = jsonCartas.getJSONObject(i);

                Cartas carta = new Cartas();
                carta.setName(jsonCarta.getString("name"));
                //carta.setName(jsonCarta.getString("rarity"));
                //carta.setName(jsonCarta.getString("toughness"));
               // carta.setName(jsonCarta.getString("power"));
                //carta.setName(jsonCarta.getString("manaCost"));
                //carta.setName(jsonCarta.getString("imageUrl"));
                //carta.setName(jsonCarta.getString("text"));

                cartas.add(carta);
            }
        return cartas;

    }


}
