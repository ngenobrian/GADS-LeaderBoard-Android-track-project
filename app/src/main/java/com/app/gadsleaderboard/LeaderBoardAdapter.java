package com.app.gadsleaderboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.BoardViewHolder> {
    ArrayList<LeaderBoard> lbData;
    public LeaderBoardAdapter(ArrayList<LeaderBoard> lbData){
        this.lbData = lbData;
    }
    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.leaderboard_item_list, parent, false);
        return new BoardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        LeaderBoard gadsdata = lbData.get(position);
        holder.bind(gadsdata);

    }

    @Override
    public int getItemCount() {
        return lbData.size();
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView country;
        TextView hours_skills;
        TextView imageUrl;
        public BoardViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
            hours_skills = (TextView) itemView.findViewById(R.id.tvLearningHrs);
            itemView.setOnClickListener(this);
        }

        public  void bind(LeaderBoard lbData){
            name.setText(lbData.name);
            hours_skills.setText(lbData.hours_skilliq + ", " + lbData.country);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            LeaderBoard selected = lbData.get(position);
            Intent intent = new Intent(view.getContext(), Test.class);
            view.getContext().startActivity(intent);

        }
    }
}
