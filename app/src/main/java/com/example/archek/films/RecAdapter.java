package com.example.archek.films;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.archek.films.model.ObjectListResponse;
import com.example.archek.films.model.ObjectResponse;
import java.util.LinkedList;
import java.util.List;
import static android.view.View.GONE;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {
    private List<ObjectResponse> films = new LinkedList<>();
    private final Callback callback;

    public RecAdapter(Callback callback) { //constuctor with callback
        this.callback = callback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate( R.layout.item, parent, false);
        final ViewHolder holder = new ViewHolder( itemView );
        //setup on the click listener, click inflate a browser with corresponding url link
        itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectResponse film = films.get(holder.getAdapterPosition());
                callback.onFilmClick( film );
            }
        } );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //bind and load all the views to the holder
        ObjectResponse film = films.get(holder.getAdapterPosition());
        holder.tvFilmRuName.setText( film.getLocalizedName() );
        holder.tvFilmEngName.setText( film.getName() );
        holder.tvRating.setText( film.getRating() );
        holder.tvFilmDate.setText(film.getYear());
        //set invisible correspond years
        if(film.getOneYear()){
            holder.tvFilmDate.setVisibility(GONE);
        }
        else holder.tvFilmDate.setVisibility(View.VISIBLE);
}

    @Override
    //count all items
    public int getItemCount() {
        return films.size();
    }


    public void replaceAll(ObjectListResponse objectListResponse) {
        films.clear();
        films.addAll(objectListResponse.getFilms());
        for(int i = 0; i < films.size(); i++) {
            for (int j = 0; j < films.size(); j++) {
                try {
                    /*sorting the filmlist with films of Years and secondarily of ratings*/
                    if (isGreaterThan(films.get(j).getYear(), films.get(j + 1).getYear())
                            || films.get(j).getYear().equals(films.get(j + 1).getYear())
                            & isGreaterThan(films.get(j+1).getRating(), films.get(j).getRating())) {
                        ObjectResponse temp = films.get(j);
                        films.set(j, films.get(j + 1));
                        films.set(j + 1, temp);
                    }
                }
                catch (Exception notRating){}
            }
        }
        /*marking year-correspond films, if this year allready showed*/
        for(int i = 1; i < films.size(); i++) {
            if(films.get(i).getYear().equals(films.get(i - 1).getYear())){
                films.get(i).setOneYear(true);
            }
        }
        notifyDataSetChanged();
    }
    //method for sorting the films
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //standing views in ViewHolder
        private TextView tvFilmDate;
        private TextView tvFilmRuName;
        private TextView tvFilmEngName;
        private TextView tvRating;

        public ViewHolder(View itemView) {
            super(itemView);
            //initiate views
            tvFilmDate = itemView.findViewById(R.id.tvFilmDate);
            tvFilmRuName = itemView.findViewById(R.id.tvFilmRuName);
            tvFilmEngName = itemView.findViewById(R.id.tvFilmEngName);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }

    public interface Callback{
        void onFilmClick(ObjectResponse critic);
    }
}
