package hmz.question.quiz.Fragments.Fragment_Users;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import hmz.question.quiz.Database.Class_Dao.Exam_Doa;
import hmz.question.quiz.Database.Class_Dao.User_Dao;
import hmz.question.quiz.Fragments.Fragment_Exam.Adapter_list_view_exam;
import hmz.question.quiz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_List_Exam_Passed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_List_Exam_Passed extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_List_Exam_Passed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_List_Exam_Passed.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_List_Exam_Passed newInstance(String param1, String param2) {
        Fragment_List_Exam_Passed fragment = new Fragment_List_Exam_Passed();
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

    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v =inflater.inflate(R.layout.fragment__list__exam__passed, container, false);
         listView = (ListView) v.findViewById(R.id.list_view_exam_passed_user);
        Adapter_list_view_exam adapter = new Adapter_list_view_exam(getContext());
        Integer id_user = getArguments().getInt(User_Dao.Column_Id);
        adapter.list = adapter.exam_doa.list_Exam_User(id_user);
        listView.setAdapter(adapter);
        return v;
    }
}