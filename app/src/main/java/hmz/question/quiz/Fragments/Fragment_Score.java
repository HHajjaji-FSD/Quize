package hmz.question.quiz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hmz.question.quiz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Score#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Score extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Score() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Score.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Score newInstance(String param1, String param2) {
        Fragment_Score fragment = new Fragment_Score();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private TextView txt_score;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__score, container, false);
        txt_score = (TextView) v.findViewById(R.id.text_view_score_final);
        Integer SC,AV;
        SC = getArguments().getInt("Score_Finish");
        AV = getArguments().getInt("AVG_");
        txt_score.setText(SC+" / "+AV);
        return v;
    }
}