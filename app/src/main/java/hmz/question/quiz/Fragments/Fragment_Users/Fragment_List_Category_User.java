package hmz.question.quiz.Fragments.Fragment_Users;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import hmz.question.quiz.Database.Class_Dao.Category_Dao;
import hmz.question.quiz.Database.Class_Dao.User_Dao;
import hmz.question.quiz.Database.Class_Data.Category;
import hmz.question.quiz.Fragments.Fragment_Exam.Fragment_Exam;
import hmz.question.quiz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_List_Category_User#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_List_Category_User extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_List_Category_User() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_List_Category_User.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_List_Category_User newInstance(String param1, String param2) {
        Fragment_List_Category_User fragment = new Fragment_List_Category_User();
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
    private Category_Dao category_dao;
    private Category category = null;
    private Integer id_User;
    public FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__list__category__user, container, false);
        id_User = getArguments().getInt(User_Dao.Column_Id);
        listView = (ListView) v.findViewById(R.id.list_view_category_user);
        category_dao = new Category_Dao(getContext());
        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(getContext(), android.R.layout.simple_list_item_1,category_dao.list_Category());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                category = adapter.getItem(i);
                Fragment_Exam fragment_exam = new Fragment_Exam();
                fragment_exam.manager = manager;
                fragment_exam.category = category;
                category = null;
                Bundle bdl = new Bundle();
                bdl.putInt(User_Dao.Column_Id,id_User);
                fragment_exam.setArguments(bdl);
                transaction = manager.beginTransaction();
                transaction.replace(R.id.container_fragments_user,fragment_exam).commit();
            }
        });
        return v;
    }
}