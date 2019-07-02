package br.com.jairrb.mvvmjson.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.util.List;

import br.com.jairrb.mvvmjson.R;
import br.com.jairrb.mvvmjson.adapters.RecyclerViewDetailAdapter;
import br.com.jairrb.mvvmjson.model.Movie;

public class DetailActivity extends AppCompatActivity {

    private TextView textViewPlot;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewGenre;
    private TextView textViewDirector;
    private TextView textViewWriter;
    private TextView textViewType;
    private TextView textViewActors;
    private RecyclerView recyclerViewDetail;
    private RecyclerViewDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();

        Movie movie = getIntent().getParcelableExtra("MOVIE");

        if (movie != null) {
            List<String> movies = movie.getImages();
            String year = textViewYear.getContext().getString(R.string.year_format, movie.getYear());
            String genre = textViewGenre.getContext().getString(R.string.genre_format, movie.getGenre());
            String writer = textViewWriter.getContext().getString(R.string.writer_format, movie.getWriter());
            String director = textViewDirector.getContext().getString(R.string.director_format, movie.getDirector());
            String type = textViewType.getContext().getString(R.string.type_format, movie.getType());
            String actors = textViewActors.getContext().getString(R.string.actors_format, movie.getActors());

            recyclerViewDetail = findViewById(R.id.recyclerViewDetail);
            recyclerViewDetail.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            adapter = new RecyclerViewDetailAdapter(movies);

            recyclerViewDetail.setHasFixedSize(true);
            recyclerViewDetail.setAdapter(adapter);

            textViewTitle.setText(movie.getTitle());
            textViewPlot.setText(movie.getPlot());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textViewYear.setText(Html.fromHtml(year, Html.FROM_HTML_MODE_COMPACT));
                textViewGenre.setText(Html.fromHtml(genre, Html.FROM_HTML_MODE_COMPACT));
                textViewDirector.setText(Html.fromHtml(director, Html.FROM_HTML_MODE_COMPACT));
                textViewWriter.setText(Html.fromHtml(writer, Html.FROM_HTML_MODE_COMPACT));
                textViewType.setText(Html.fromHtml(type, Html.FROM_HTML_MODE_COMPACT));
                textViewActors.setText(Html.fromHtml(actors, Html.FROM_HTML_MODE_COMPACT));
            } else {
                textViewYear.setText(Html.fromHtml(year));
                textViewGenre.setText(Html.fromHtml(genre));
                textViewDirector.setText(Html.fromHtml(director));
                textViewWriter.setText(Html.fromHtml(writer));
                textViewType.setText(Html.fromHtml(type));
                textViewActors.setText(Html.fromHtml(actors));
            }
        }

    }

    private void initViews() {
        textViewPlot = findViewById(R.id.textViewPlot);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewGenre = findViewById(R.id.textViewGenre);
        textViewWriter = findViewById(R.id.textViewWriter);
        textViewDirector = findViewById(R.id.textViewDirector);
        textViewType = findViewById(R.id.textViewType);
        textViewActors = findViewById(R.id.textViewActors);
    }
}
