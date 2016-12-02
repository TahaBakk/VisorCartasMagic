package com.example.x3727349s.visorcartasmagic;

import nl.littlerobots.cupboard.tools.provider.CupboardContentProvider;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by x3727349s on 02/12/16.
 */

public class CartasContentProvider extends CupboardContentProvider {

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".provider";

        static {
            cupboard().register(Cartas.class);
        }

        public CartasContentProvider() {
            super(AUTHORITY, 1);
        }

}
