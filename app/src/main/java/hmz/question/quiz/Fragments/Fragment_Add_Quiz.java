package hmz.question.quiz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hmz.question.quiz.Database.Class_Dao.Category_Dao;
import hmz.question.quiz.Database.Class_Dao.Quiz_Doa;
import hmz.question.quiz.Database.Class_Data.Category;
import hmz.question.quiz.Database.Class_Data.Quiz;
import hmz.question.quiz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Add_Quiz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Add_Quiz extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Add_Quiz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Add_Quiz.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Add_Quiz newInstance(String param1, String param2) {
        Fragment_Add_Quiz fragment = new Fragment_Add_Quiz();
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

    public FragmentManager manager;
    private FragmentTransaction transaction;
    private Quiz_Doa quiz_dao;
    private Quiz quiz;
    private Button btn_add_question,btn_cancel_question;
    private EditText txt_question,txt_answer,txt_wrng_answer_1,txt_wrng_answer_2,txt_note;

    public Category category;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__add__quiz, container, false);

        //
        txt_question = (EditText) v.findViewById(R.id.edit_text_question);
        txt_answer = (EditText) v.findViewById(R.id.edit_text_answer);
        txt_wrng_answer_1 = (EditText) v.findViewById(R.id.edit_text_wrng_answer_1);
        txt_wrng_answer_2 = (EditText) v.findViewById(R.id.edit_text_wrng_answer_2);
        txt_note = (EditText) v.findViewById(R.id.edit_text_note_question);
        btn_add_question = (Button) v.findViewById(R.id.btn_add_question);
        btn_cancel_question = (Button) v.findViewById(R.id.btn_cancel_question);

        quiz_dao = new Quiz_Doa(getContext());
        //evint
        btn_cancel_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear data
                transaction = manager.beginTransaction();
                Fragment_Quiz_Category fragment_quiz_category = new Fragment_Quiz_Category();
                fragment_quiz_category.manager = manager;
                fragment_quiz_category.category = category;
                transaction.replace(R.id.container_fragments_admin,fragment_quiz_category).commit();
            }
        });

        //btn add
        btn_add_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz = new Quiz(category.getId(),txt_question.getText().toString(),txt_answer.getText().toString(),txt_wrng_answer_1.getText().toString(),txt_wrng_answer_2.getText().toString(),Integer.parseInt(txt_note.getText().toString()));
                quiz_dao.add_Quiz(quiz);
                Toast.makeText(getContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                clear_widget();
            }
        });
        return v;
    }//fin on create
    private void clear_widget()
    {
        txt_question.setText("");
        txt_answer.setText("");
        txt_wrng_answer_1.setText("");
        txt_wrng_answer_2.setText("");
        txt_note.setText("");
    }
}