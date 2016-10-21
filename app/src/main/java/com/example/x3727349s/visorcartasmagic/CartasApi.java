package com.example.x3727349s.visorcartasmagic;

import android.net.Uri;

import java.io.IOException;

/**
 * Created by x3727349s on 18/10/16.
 */

public class CartasApi {

        private final String BASE_URL = "https://api.magicthegathering.io/v1/cards";

        /*String getCartes(String cards) {
            Uri builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    //.appendPath("lists")
                    //.appendPath("movies")
                    //.appendPath("box_office.json")
                   // .appendQueryParameter("country", pais)
                    .build();
            String url = builtUri.toString();*/

        String getCartes() {
            Uri builtUri = Uri.parse(BASE_URL)
                    .buildUpon()
                    .build();
            String url = builtUri.toString();

            try {
                String JsonResponse = HttpUtils.get(url);
                return JsonResponse;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

}
