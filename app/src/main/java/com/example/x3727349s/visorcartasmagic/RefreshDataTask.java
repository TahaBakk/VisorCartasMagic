package com.example.x3727349s.visorcartasmagic;

/**
 * Created by x3727349s on 19/12/16.
 */

 import android.content.Context;
 import android.content.SharedPreferences;
 import android.os.AsyncTask;
 import android.preference.PreferenceManager;
 import android.util.Log;

 import com.alexvasilkov.events.Events;

 import org.json.JSONException;

 import java.io.IOException;
 import java.util.ArrayList;

 class RefreshDataTask extends AsyncTask<Void, Void, Void> {
 private Context context;

        RefreshDataTask(Context context) {
         this.context = context;
     }

     @Override
     protected void onPreExecute() {
         super.onPreExecute();
        Events.post("start-downloading-data");
     }

     @Override
     protected Void doInBackground(Void... voids) {

         SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
         String rarity = preferences.getString("rarity", "All");
         String colors = preferences.getString("colors", "white");

         CartasApi api = new CartasApi();

         ArrayList<Cartas> result;
         try {
             if (rarity.equals("All")) {

                 result = CartasApi.getCartes();

             } else {
                 result = CartasApi.getCartesFiltro(rarity, colors);
             }

             Log.d("CARDS", result != null ? result.toString() : null);

             DataManager.deleteCartas(context);
             DataManager.saveCartas(result, context);

             return null;


         } catch (JSONException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return null;
     }
         @Override
         protected void onPostExecute(Void aVoid) {
             super.onPostExecute(aVoid);

             Events.post("finish-downloading-data");
     }

   }