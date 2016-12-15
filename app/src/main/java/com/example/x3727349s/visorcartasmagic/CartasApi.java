package com.example.x3727349s.visorcartasmagic;

import android.net.Uri;
import java.io.IOException;
import java.util.ArrayList;
import android.util.Log;
import android.support.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by x3727349s on 18/10/16.
 */

class CartasApi {

    private final static String BASE_URL = "https://api.magicthegathering.io/v1/cards";
    private static final int PAGES = 10;

    static ArrayList<Cartas> getCartes() throws JSONException, IOException {

        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .build();

        String url = builtUri.toString();
        return doCall(url);

    }

    static ArrayList<Cartas> getCartesFiltro(String rarity, String color) throws IOException {

        String url = getUrl(rarity, color, "box_office.json");

        Log.d("URL", url);
        return doCall(url);

    }


    private static ArrayList<Cartas> doCall(String url) throws IOException {

        String JsonResponse=null;
        ArrayList<Cartas> cartas = new ArrayList<>();

        for (int i = 0; i < PAGES; i++) {
            JsonResponse = HttpUtils.get(url);
            ArrayList<Cartas> list = processJson(JsonResponse);
            cartas.addAll(list);
        }
        //JsonResponse = HttpUtils.get(url);

        //return processJson(JsonResponse);
        return cartas;
    }


    private static String getUrl(String rarity, String color, String s) {

        Uri builtUri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter("rarity", rarity)
                .appendQueryParameter("colors", color)
                .build();

                return builtUri.toString();
    }


    private static String getUrlPage(int page) {

        Uri builtUri = Uri.parse(BASE_URL)

               .buildUpon()
               .appendQueryParameter("page", String.valueOf(page))
               .build();

        return builtUri.toString();
    }


    private static ArrayList<Cartas> processJson(String jsonResponse)  {

        ArrayList<Cartas> cartas = new ArrayList<>();

        try {
            JSONObject data = null;
            data = new JSONObject(jsonResponse);

                JSONArray jsonCartas = data.getJSONArray("cards");

                for (int i = 0; i < jsonCartas.length(); i++) {

                    JSONObject jsonCarta = jsonCartas.getJSONObject(i);

                    Cartas carta = new Cartas();

                    carta.setName(jsonCarta.getString("name"));
                    carta.setRarity(jsonCarta.getString("rarity"));
                    carta.setColors(jsonCarta.getString("colors"));
                    carta.setImageUrl(jsonCarta.getString("imageUrl"));
                    carta.setText(jsonCarta.getString("text"));


                    cartas.add(carta);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        return cartas;

    }


}
