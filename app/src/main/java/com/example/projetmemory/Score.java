package com.example.projetmemory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Score#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Score extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAMEPLAYER = "namePlayer";
    private static final String ARG_SCORE = "Score";

    // TODO: Rename and change types of parameters
    private String mParamNamePlayer;
    private String mParamScore;

    public Score() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param paramNamePlayer Parameter 1.
     * @param paramScore Parameter 2.
     * @return A new instance of fragment Score.
     */
    // TODO: Rename and change types and number of parameters
    public static Score newInstance(String paramNamePlayer, String paramScore) {
        Score fragment = new Score();
        Bundle args = new Bundle();
        args.putString(ARG_NAMEPLAYER, paramNamePlayer);
        args.putString(ARG_SCORE, paramScore);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamNamePlayer = getArguments().getString(ARG_NAMEPLAYER);
            mParamNamePlayer = getArguments().getString(ARG_SCORE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }
}