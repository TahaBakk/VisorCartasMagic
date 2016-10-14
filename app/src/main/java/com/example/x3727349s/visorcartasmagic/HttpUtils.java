package com.example.x3727349s.visorcartasmagic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by x3727349s on 14/10/16.
 */

public class HttpUtils {

    public static String get (String dataUrl) throws IOException{

        URL url = new URL(dataUrl);
        String response = null;

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            response = readStream(in);


        }finally {
            urlConnection.disconnect();
        }

        return response;
    }



}
