package br.com.jairrb.mvvmjson.adapters;

import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.jairrb.mvvmjson.R;
import br.com.jairrb.mvvmjson.interfaces.RecyclerViewClickListener;
import br.com.jairrb.mvvmjson.model.Movie;

public class RecyclerViewMovieAdapter extends RecyclerView.Adapter<RecyclerViewMovieAdapter.ViewHolder> {
    private List<Movie> movies;
    private RecyclerViewClickListener listener;

    public RecyclerViewMovieAdapter(List<Movie> movies, RecyclerViewClickListener listener) {
        this.movies = movies;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_movie_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Movie movie = movies.get(position);
        viewHolder.bind(movie);

        viewHolder.itemView.setOnClickListener(v -> {
            listener.onItemClick(movie);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void update(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewYear;
        private TextView textViewGenre;
        private TextView textViewDirector;
        private ImageView imageViewPoster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);

        }

        private void initViews(@NonNull View itemView) {
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewYear = itemView.findViewById(R.id.textViewYear);
            textViewGenre = itemView.findViewById(R.id.textViewGenre);
            textViewDirector = itemView.findViewById(R.id.textViewDirector);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        }

        public void bind(Movie movie){

            textViewTitle.setText(movie.getTitle());

            String year = textViewYear.getContext().getString(R.string.year_format, movie.getYear());
            String genre = textViewGenre.getContext().getString(R.string.genre_format, movie.getGenre());
            String director = textViewDirector.getContext().getString(R.string.director_format, movie.getDirector());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textViewYear.setText(Html.fromHtml(year, Html.FROM_HTML_MODE_COMPACT));
                textViewGenre.setText(Html.fromHtml(genre, Html.FROM_HTML_MODE_COMPACT));
                textViewDirector.setText(Html.fromHtml(director, Html.FROM_HTML_MODE_COMPACT));
            } else {
                textViewYear.setText(Html.fromHtml(year));
                textViewGenre.setText(Html.fromHtml(genre));
                textViewDirector.setText(Html.fromHtml(director));
            }

            Picasso.get()
                    .load(movie.getPoster())
                    .into(imageViewPoster);


        }

    }
}
