package br.com.jairrb.mvvmjson.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import br.com.jairrb.mvvmjson.R;
import br.com.jairrb.mvvmjson.adapters.RecyclerViewMovieAdapter;
import br.com.jairrb.mvvmjson.interfaces.RecyclerViewClickListener;
import br.com.jairrb.mvvmjson.model.Movie;
import br.com.jairrb.mvvmjson.viewmodel.MovieViewModel;

public class MovieActivity extends AppCompatActivity implements RecyclerViewClickListener {
    private RecyclerView recyclerViewMovies;
    private ProgressBar progressBar;
    private RecyclerViewMovieAdapter adapter;
    private List<Movie> movies = new ArrayList<>();
    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.camera);
        getSupportActionBar().setTitle(" Movies");


        progressBar = findViewById(R.id.progressBar);
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewMovieAdapter(movies, this);

        recyclerViewMovies.setHasFixedSize(true);
        recyclerViewMovies.setAdapter(adapter);


        // Fazer a inicialização do view model
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getMovies();

        // Adicionar os observables
        //Update
        viewModel.getMovieLiveData().observe(this, movies -> adapter.update(movies));
        //Loading
        viewModel.getLoadingLiveData().observe(this, isLoading -> {
            if (isLoading){
                progressBar.setVisibility(View.VISIBLE);
            }else {
                progressBar.setVisibility(View.GONE);
            }
        });
        //Error
        viewModel.getErrorLiveData().observe(this, throwable -> {
            Snackbar.make(recyclerViewMovies, throwable.getMessage(), Snackbar.LENGTH_SHORT).show();
        });

    }

    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("MOVIE",movie);
        startActivity(intent);

    }
}
