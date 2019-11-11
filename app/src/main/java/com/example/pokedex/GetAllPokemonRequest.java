package com.example.pokedex;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class GetAllPokemonRequest extends AsyncTask<String, Void, String> {

    GetAllPokemonResponse delegate = null;
    private static final String REQUEST_METHOD = "GET";
    private static final int READ_TIMEOUT = 15000;
    private static final int CONNECTION_TIMEOUT = 15000;

    @Override
    protected String doInBackground(String... params) {
        // get string url from params
        String stringUrl = params[0];
        // store result
        String result = "";
        String inputLine;

        try {
            // string to url
            URL requestUrl = new URL(stringUrl);
            // create connection
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();

            // set method and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            // connect to url
            connection.connect();

            // handle result
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            reader.close();
            streamReader.close();

            result = stringBuilder.toString();

        } catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        List<PokemonOverview> pokemonOverviewList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int n = 0; n < jsonArray.length(); n++) {
                JSONObject pokemonJson = jsonArray.getJSONObject(n);
                PokemonOverview pokemonOverview = new PokemonOverview(n+1, pokemonJson.getString("name"), "");
                pokemonOverviewList.add(pokemonOverview);
            }
            delegate.processFinish(pokemonOverviewList);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
