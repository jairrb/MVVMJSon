package br.com.jairrb.mvvmjson.adapters;

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

public class RecyclerViewDetailAdapter extends RecyclerView.Adapter<RecyclerViewDetailAdapter.ViewHolder> {
    private List<String> imagesMovie;

    public RecyclerViewDetailAdapter(List<String> imagesMovie ) {
        this.imagesMovie = imagesMovie;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_detail_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final String image = imagesMovie.get(position);
        viewHolder.bind(image);

    }

    @Override
    public int getItemCount() {
        return imagesMovie.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewDetail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);

        }

        private void initViews(@NonNull View itemView) {
            imageViewDetail = itemView.findViewById(R.id.imageViewDetail);
        }

        public void bind(String movie){
            Picasso.get()
                    .load(movie)
                    .into(imageViewDetail);


        }

    }
}
