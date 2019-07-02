package br.com.jairrb.mvvmjson.repository;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.com.jairrb.mvvmjson.model.MoviesResponse;
import io.reactivex.Single;

public class MovieRepository {
    public Single<MoviesResponse> getListMovies(Context context){
        try {
            AssetManager manager = context.getAssets();
            InputStream newJson = manager.open("movies.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(newJson));
            Gson gson = new Gson();

            MoviesResponse response = gson.fromJson(reader, MoviesResponse.class);

            return Single.just(response);

        } catch (IOException e) {

            e.printStackTrace();
            return Single.error(e);

        }
    }
}
