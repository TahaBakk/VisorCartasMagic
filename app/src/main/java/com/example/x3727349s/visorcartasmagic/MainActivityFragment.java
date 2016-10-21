package com.example.x3727349s.visorcartasmagic;

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
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {

        RefreshDataTask task = new RefreshDataTask();
        task.execute();

    }

    private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Cartas>> {


        @Override
        protected ArrayList<Cartas> doInBackground(Void... voids) {

            CartasApi api = new CartasApi();
            ArrayList<Cartas> result = null;
            try {
                result = api.getCartes();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.d("DEBUG", result.toString());

            return result;
        }


        @Override
        protected void onPostExecute(ArrayList<Cartas> cartap) {
            adapter.clear();
            for (Cartas carta : cartap){
                adapter.add(carta.getName());
            }

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ListView lvCartas = (ListView) view.findViewById(R.id.lvCartas);
/*
        String[] data = {
            "Black Lotus",
            "Time Walk",
            "Ancestral Recall",
            "Lightning Bolt",
            "Demonic Tutor",
            "Birds of Paradise"
        };
*/

        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.titol_cartes,
                R.id.tvCartes,
                items
        );
        lvCartas.setAdapter(adapter);





        return view;
    }
}
