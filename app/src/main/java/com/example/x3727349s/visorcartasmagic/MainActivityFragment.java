package com.example.x3727349s.visorcartasmagic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.AsyncTask;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCartas = (ListView) view.findViewById(R.id.lvCartas);

        /*String[] data = {
                "Black Lotus",
                "Time Walk",
                "Ancestral Recall",
                "Lightning Bolt",
                "Demonic Tutor",
                "Birds of Paradise"
        };
        items = new ArrayList<>(Arrays.asList(data));*/
        items = new ArrayList<>();
        adapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.titol_cartes,
                R.id.tvCartes,
                items
        );
        lvCartas.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_cartes_fragment, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_refresh){
            refresh();
            return true;
        }else if (id == R.id.action_settings) {
            Intent i = new Intent(getContext(), SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {

        RefreshDataTask task = new RefreshDataTask();
        task.execute();

    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Cartas>> {


        /*@Override
        protected ArrayList<Cartas> doInBackground(Void... params) {

            CartasApi api = new CartasApi();
            ArrayList<Cartas> result = null;
            try {
                result = api.getCartes();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("DEBUG", result.toString());

            return result;
        }*/
        @Override
        protected ArrayList<Cartas> doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

            String rarity = preferences.getString("rarity", "Uncommon");
            String colors = preferences.getString("colors", "White");

            CartasApi api = new CartasApi();
            ArrayList<Cartas> result;
            try {
                if (rarity.equals("Uncommon")) {

                    result = api.getCartes();

                } else {
                    result = api.getCartesFiltro(rarity);
                }

                /*if (rarity.equals("White")) {

                    result = api.getCartes();

                } else {
                    result = api.getCartesFiltro(colors);
                }*/

                Log.d("DEBUG", result != null ? result.toString() : null);

                return result;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Cartas> cartap) {
           super.onPostExecute(cartap);
            adapter.clear();
            for(int i = 0; i < cartap.size(); i++)
            {
                adapter.add(cartap.get(i).getName());
            }

        }
    }



}
