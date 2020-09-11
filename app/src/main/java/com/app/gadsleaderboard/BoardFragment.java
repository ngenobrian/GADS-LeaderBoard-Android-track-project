package com.app.gadsleaderboard;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_COUNT = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
    private Integer counter;
    private RecyclerView mRecyclerView;

    public BoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment BoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BoardFragment newInstance(Integer counter) {
        BoardFragment fragment = new BoardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvLeader);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        if(counter == 0){
            URL url = ApiUtil.buildUrl("hours");
            new  BooksQuery().execute(url);
        }
        else if(counter == 1){
            URL url = ApiUtil.buildUrl("skilliq");
            new  BooksQuery().execute(url);
        }




    }

    public class BooksQuery extends AsyncTask<URL, Void, String>{


        @Override
        protected String doInBackground(URL... urls) {
            URL hoursUrl = urls[0];
            String result = null;
            try {
                result = ApiUtil.getJson(hoursUrl);

            }
            catch (IOException e){
                Log.d("Error", e.getMessage());


            }
            return  result;
        }

//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            mLoadingProgress.setVisibility(View.VISIBLE);
//        }

        @Override
        protected void onPostExecute(String result) {

            ArrayList<LeaderBoard> hrs = LeaderBoard.getDataFromJson(result);
            LeaderBoardAdapter adapter = new LeaderBoardAdapter(hrs);
            mRecyclerView.setAdapter(adapter);







        }
    }


}
