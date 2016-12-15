package com.example.x3727349s.visorcartasmagic;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.app.ProgressDialog;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;





import com.example.x3727349s.visorcartasmagic.databinding.FragmentMainBinding;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;



/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    //private ArrayList<Cartas> items;

    //private CartasAdapter adapter;// lo cambiamos por la de cartasCursorAdapter que es mas facil y menos pasos
    private CartasCursorAdapter adapter;
    private ProgressDialog dialog;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_main, container, false);
        //ListView lvCartas = (ListView) view.findViewById(R.id.lvCartas);

        FragmentMainBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false);

        View view = binding.getRoot();

        //items = new ArrayList<>();
        //cambiando la classe de cartasAdapter por la cartasCursorAdapter
        /*adapter = new CartasAdapter(
                getContext(),
                R.layout.titol_cartes,
                items
        );*/

        adapter = new CartasCursorAdapter(getContext(), Cartas.class);

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");//mensaje a mostrar mientras carga las cartas

        binding.lvCartas.setAdapter(adapter);

        //Detall
        binding.lvCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Cartas cartas = (Cartas) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("cartas", cartas);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(0,null, this);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
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

        if (id == R.id.action_refresh) {
            refresh();
            return true;
        } else if (id == R.id.action_settings) {
            Intent i = new Intent(getContext(), SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {

        RefreshDataTask task = new RefreshDataTask();
        task.execute();

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return DataManager.getCursorLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }


    /*private class RefreshDataTask extends AsyncTask<Void, Void, ArrayList<Cartas>> {*/
    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {
        //mientras se esta ejecutando que lo muestre el msj
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }
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
        protected Void doInBackground(Void... voids) {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
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

                DataManager.deleteCartas(getContext());
                DataManager.saveCartas(result, getContext());


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        /*@Override
        protected void onPostExecute(ArrayList<Cartas> cartap) {
           super.onPostExecute(cartap);
            adapter.clear();
            for(int i = 0; i < cartap.size(); i++)
            {
                adapter.add(cartap.get(i));
            }*/
            return null;
        }
        //despues de ejecutarlo lo quita
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            }
    }
}



