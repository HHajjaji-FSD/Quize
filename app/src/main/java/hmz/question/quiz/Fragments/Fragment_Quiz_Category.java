package hmz.question.quiz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import hmz.question.quiz.Database.Class_Dao.Category_Dao;
import hmz.question.quiz.Database.Class_Dao.Quiz_Doa;
import hmz.question.quiz.Database.Class_Data.Category;
import hmz.question.quiz.Database.Class_Data.Quiz;
import hmz.question.quiz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Quiz_Category#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Quiz_Category extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Quiz_Category() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Quiz_Category.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Quiz_Category newInstance(String param1, String param2) {
        Fragment_Quiz_Category fragment = new Fragment_Quiz_Category();
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

    public Category category;
    private Quiz_Doa quiz_doa;
    private Button btn_new,btn_back;
    private ListView listView;
    private TextView textView;
    public FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__quiz__category, container, false);

        btn_back = (Button) v.findViewById(R.id.btn_back);
        btn_new = (Button) v.findViewById(R.id.btn_new_quiz);
        listView = (ListView) v.findViewById(R.id.list_view_quiz_category);
        textView = (TextView) v.findViewById(R.id.text_view_title_category);
        textView.setText(category.getTitle());

        quiz_doa = new Quiz_Doa(getContext());
        ArrayAdapter<Quiz> adapter = new ArrayAdapter<Quiz>(getContext(), android.R.layout.simple_list_item_1,quiz_doa.list_quiz(category.getId()));
        listView.setAdapter(adapter);

        //btn back
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = manager.beginTransaction();
                Fragment_Category fragment_category = new Fragment_Category();
                fragment_category.manager = manager;
                transaction.replace(R.id.container_fragments_admin,fragment_category).commit();
            }
        });

        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = manager.beginTransaction();
                Fragment_Add_Quiz fragment_add_quiz = new Fragment_Add_Quiz();
                Bundle bundle = new Bundle();
                fragment_add_quiz.manager = manager;
                fragment_add_quiz.category = category;
                transaction.replace(R.id.container_fragments_admin,fragment_add_quiz).commit();
            }
        });
        return v;
    }
}