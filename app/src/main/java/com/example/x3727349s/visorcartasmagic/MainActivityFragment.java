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
import android.widget.AdapterView;
import android.widget.ListView;
import android.os.AsyncTask;
import org.json.JSONException;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<Cartas> items;

    private CartasAdapter adapter;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCartas = (ListView) view.findViewById(R.id.lvCartas);

        items = new ArrayList<>();
        adapter = new CartasAdapter(
                getContext(),
                R.layout.titol_cartes,
                items
        );

        lvCartas.setAdapter(adapter);

        //Detall
        lvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Cartas cartas = (Cartas) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("cartas", cartas);
                startActivity(intent);
            }
        });

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
        protected ArrayList<Cartas> doInBackground(Void... params) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            String rarity = preferences.getString("rarity", "All");
            String colors = preferences.getString("colors", "white");

            CartasApi api = new CartasApi();
            ArrayList<Cartas> result;
            try {
                if (rarity.equals("All")) {

                    result = api.getCartes();

                } else {
                    result = api.getCartesFiltro(rarity, colors);
                }

                /*if (rarity.equals("White")) {

                    result = api.getCartes();

                } else {
                    result = api.getCartesFiltro(colors);
                }*/

                Log.d("CARDS", result != null ? result.toString() : null);

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
                adapter.add(cartap.get(i));
            }

        }
    }



}
