package com.example.project.db;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public final static String MOVIE_KEY = "movie";
    public final static String WWW_KEY = "www";
    public final static int KOD = 2;
    private static final String TAG = MovieAdapter.class.getSimpleName();
    private List<Movie> movieList;
    private LayoutInflater layoutInflater;
    private Activity context;

    public MovieAdapter(Activity context) {
        this.movieList = null;
        this.context = context;
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
        Movie currentMovie = movieList.get(position);
        holder.rowTitle.setClickable(true);
        holder.rowTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(context, MovieDetails.class);
                Bundle details_content = new Bundle();
                details_content.putSerializable(MOVIE_KEY, currentMovie);
                //details_content.putString(WWW_KEY, holder.rowWWW.getText().toString());
                details.putExtras(details_content);
                context.startActivityForResult(details, KOD);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(movieList !=null) return movieList.size();
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public Object currentMovie;
        TextView rowId;
        TextView rowTitle;
        TextView rowWWW;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowId = itemView.findViewById(R.id.positionId);
            rowTitle = itemView.findViewById(R.id.positionTitle);
            rowWWW = itemView.findViewById(R.id.positionWWW);
        }

        public void bindToViewHolder(int position, boolean isSelected){
            String id = String.valueOf(movieList.get(position).getMovieID());
            rowId.setText(id);
            rowTitle.setText(movieList.get(position).getTitle());
            rowWWW.setText(movieList.get(position).getWww());

            itemView.setActivated(isSelected);
        }
    }

    public void setMovieList(List<Movie> newMovieList){
        movieList = newMovieList;
        notifyDataSetChanged();
    }
}
