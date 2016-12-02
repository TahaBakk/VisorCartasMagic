package com.example.x3727349s.visorcartasmagic;

import android.content.Context;
import android.net.Uri;

import java.util.ArrayList;

import nl.littlerobots.cupboard.tools.provider.UriHelper;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by x3727349s on 02/12/16.
 */

public class DataManager {

    private static UriHelper URI_HELPER = UriHelper.with(CartasContentProvider.AUTHORITY);
    private static Uri CARTA_URI = URI_HELPER.getUri(Cartas.class);

        static void saveCartas(ArrayList<Cartas> cartas, Context context) {
            cupboard().withContext(context).put(CARTA_URI, Cartas.class, cartas);
        }

    static void deleteCartas(Context context) {
        cupboard().withContext(context).delete(CARTA_URI, "_id > ?", "1");
        }


}
