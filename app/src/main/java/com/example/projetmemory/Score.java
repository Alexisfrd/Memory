package com.example.projetmemory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projetmemory.databinding.FragmentScoreBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Score#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Score extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private FragmentScoreBinding binding;
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
            mParamScore = getArguments().getString(ARG_SCORE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScoreBinding.inflate(inflater, container, false);
        binding.textNom.setText(mParamNamePlayer);
        binding.textScore.setText(mParamScore);
        return binding.getRoot();
    }
}