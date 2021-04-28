package com.example.project.db;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static final String TAG = MovieAdapter.class.getSimpleName();
    private List<Movie> movieList;
    private LayoutInflater layoutInflater;

    public MovieAdapter(Activity context) {
        this.movieList = null;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inicjalizowanie widoku
        View view = layoutInflater.inflate(R.layout.list_row, parent, false);
        ViewHolder movieViewHolder = new ViewHolder(view);
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        boolean isSelected = false;
        holder.bindToViewHolder(position, isSelected);
    }

//        Movie movie = movieList.get(position);
//        db = AppDatabase.getDB(context);
//        holder.rowId.setText(movie.getMovieID());
//        holder.rowTitle.setText(movie.getTitle());
//    }

    @Override
    public int getItemCount() {
        if(movieList !=null) return movieList.size();
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rowId;
        TextView rowTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowId = itemView.findViewById(R.id.positionId);
            rowTitle = itemView.findViewById(R.id.positionTitle);
        }

        public void bindToViewHolder(int position, boolean isSelected){
            String id = String.valueOf(movieList.get(position).getMovieID());
            rowId.setText(id);
            rowTitle.setText(movieList.get(position).getTitle());

            itemView.setActivated(isSelected);
        }
    }

    public void setMovieList(List<Movie> newMovieList){
        movieList = newMovieList;
        notifyDataSetChanged();
    }
}
