package hmz.question.quiz.Fragments.Fragment_Exam;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import hmz.question.quiz.Activity_User;
import hmz.question.quiz.Database.Class_Dao.Exam_Doa;
import hmz.question.quiz.Database.Class_Dao.Quiz_Doa;
import hmz.question.quiz.Database.Class_Dao.User_Dao;
import hmz.question.quiz.Database.Class_Data.Category;
import hmz.question.quiz.Database.Class_Data.Exam;
import hmz.question.quiz.Database.Class_Data.Quiz;
import hmz.question.quiz.Fragments.Fragment_Category;
import hmz.question.quiz.Fragments.Fragment_Score;
import hmz.question.quiz.Fragments.Fragment_Users.Fragment_List_Category_User;
import hmz.question.quiz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Exam#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Exam extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Exam() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Exam.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Exam newInstance(String param1, String param2) {
        Fragment_Exam fragment = new Fragment_Exam();
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
    public Category category;
    private Integer id_user;
    private Quiz_Doa quiz_doa;
    private Quiz quiz;
    private Exam_Doa exam_doa;
    private Exam exam;
    private ArrayList<Quiz> list;
    private Integer position = 0;
    private Integer Score = 0;

    private Integer AVG = 0;

    private Button btn_back,btn_previous,btn_next,btn_finish;
    private TextView txt_title,txt_nbr_question,txt_question;
    private RadioGroup radioGroup;
    private RadioButton radioButton_1,radioButton_2,radioButton_3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__exam, container, false);

            ///reference object
            quiz_doa = new Quiz_Doa(getContext());
            list = quiz_doa.list_quiz(category.getId());
            exam_doa = new Exam_Doa(getContext());
            for (Quiz i : list)
            {
                AVG +=i.getNote();
            }


            // inflet widjet button
            btn_back = (Button) v.findViewById(R.id.btn_back_user);
            btn_previous = (Button) v.findViewById(R.id.btn_previous);
            btn_next = (Button) v.findViewById(R.id.btn_Next);
            btn_finish = (Button) v.findViewById(R.id.btn_finish);
            // inflet widjet text view
            txt_title = (TextView) v.findViewById(R.id.text_view_title_category_user);
            txt_nbr_question = (TextView) v.findViewById(R.id.text_view_nbr_question);
            txt_question = (TextView) v.findViewById(R.id.text_view_question);
            radioButton_1 = (RadioButton) v.findViewById(R.id.radio_1) ;
            radioButton_2 = (RadioButton) v.findViewById(R.id.radio_2) ;
            radioButton_3 = (RadioButton) v.findViewById(R.id.radio_3) ;

            radioGroup = (RadioGroup) v.findViewById(R.id.radio_group);

            /// tratment

            txt_nbr_question.setText(String.valueOf(position+"/"+list.size()));
            id_user = getArguments().getInt(User_Dao.Column_Id);
            txt_title.setText(category.getTitle());
            Attach_Quiz(position);

            btn_previous.setVisibility(View.INVISIBLE);
            btn_finish.setVisibility(View.INVISIBLE);
            //event
                //btn back
                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Sing Out").setMessage("Are you sue to finish exam?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        transaction = manager.beginTransaction();
                                        Fragment_List_Category_User fr = new Fragment_List_Category_User();
                                        fr.manager = manager;
                                        Bundle bundle = new Bundle();
                                        bundle.putInt(User_Dao.Column_Id,id_user);
                                        fr.setArguments(bundle);
                                        transaction.replace(R.id.container_fragments_user,fr).commit();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).show();
                    }
                });
                // btn next
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        position+=1;
                        txt_nbr_question.setText(String.valueOf(position+"/"+list.size()));
                        Attach_Quiz(position);
                        btn_previous.setVisibility(View.VISIBLE);
                    if (position == list.size()-1)
                        {
                            btn_finish.setVisibility(View.VISIBLE);
                            btn_next.setVisibility(View.INVISIBLE);
                        }

                        radioGroup.clearCheck();
                    }
                });
                //btn previous
                btn_previous.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        position-=1;
                        txt_nbr_question.setText(String.valueOf(position+"/"+list.size()));
                        Attach_Quiz(position);
                        if (position == 0)
                        {
                            btn_previous.setVisibility(View.INVISIBLE);
                        }
                        if (!btn_next.isActivated())
                        {
                            btn_next.setVisibility(View.VISIBLE);
                            btn_finish.setVisibility(View.INVISIBLE);
                        }

                    }
                });
                //radio group

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            if (radioButton_1.isChecked())
                            {
                                if (radioButton_1.getText().toString().equals(quiz.getAnswer()))
                                {
                                    Score+=quiz.getNote();
                                }
                            }
                            else if (radioButton_2.isChecked())
                            {
                                if (radioButton_2.getText().toString().equals(quiz.getAnswer()))
                                {
                                    Score+=quiz.getNote();
                                }
                            }
                            else if  (radioButton_2.isChecked())
                            {
                                if (radioButton_3.getText().toString().equals(quiz.getAnswer()))
                                {
                                    Score+=quiz.getNote();
                                }
                            }
                    }
                });

                //btn finish
                btn_finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        LocalDateTime now = LocalDateTime.now();
                        Date dt = new Date(dtf.format(now));
                        exam = new Exam(id_user,category.getId(),Score,dt);
                        exam_doa.add_Exam(exam);

                        Fragment_Score fr = new Fragment_Score();
                        Bundle bundle = new Bundle();
                        bundle.putInt("Score_Finish",Score);
                        bundle.putInt("AVG_",AVG);
                        fr.setArguments(bundle);
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.container_fragments_user,fr).commit();
                    }
                });


        return v;
    }// on crate

    private void Attach_Quiz(Integer p)
    {
        quiz =null;
        quiz =  list.get(p);
        txt_question.setText(quiz.getQuestion());
        radioButton_1.setText(quiz.getWrong_Answer_A());
        radioButton_2.setText(quiz.getAnswer());
        radioButton_3.setText(quiz.getWrong_Answer_B());

    }
}