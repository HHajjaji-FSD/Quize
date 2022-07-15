package hmz.question.quiz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import hmz.question.quiz.Database.Class_Dao.Category_Dao;
import hmz.question.quiz.Database.Class_Data.Category;
import hmz.question.quiz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_Category#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Category extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_Category() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_Category.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Category newInstance(String param1, String param2) {
        Fragment_Category fragment = new Fragment_Category();
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

    private Button btn_new ;
    private ListView listView;
    public Category_Dao category_dao = null;
    public FragmentManager manager;
    private FragmentTransaction transaction;
    private Category category;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__category, container, false);

        btn_new = (Button) v.findViewById(R.id.btn_new_category);
        listView = (ListView) v.findViewById(R.id.list_view_category);
        if (category_dao == null)
        {
            category_dao = new Category_Dao(getContext());
        }

        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(getContext(), android.R.layout.simple_list_item_1,category_dao.list_Category());
        listView.setAdapter(adapter);
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = manager.beginTransaction();
                Fragment_New_Category fragment_new_category = new Fragment_New_Category();
                fragment_new_category.manager = manager;
                fragment_new_category.category_dao = category_dao;
                transaction.replace(R.id.container_fragments_admin,fragment_new_category);
                transaction.commit();
            }
        });

        // event list view

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                category = adapter.getItem(i);
                transaction = manager.beginTransaction();
                Fragment_Quiz_Category fragment_quiz_category = new Fragment_Quiz_Category();
                fragment_quiz_category.category = category;
                fragment_quiz_category.manager = manager;
                transaction.replace(R.id.container_fragments_admin,fragment_quiz_category).commit();
            }
        });
        return v;
    }
}