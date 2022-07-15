package hmz.question.quiz.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import hmz.question.quiz.Database.Class_Dao.Category_Dao;
import hmz.question.quiz.Database.Class_Data.Category;
import hmz.question.quiz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_New_Category#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_New_Category extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_New_Category() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_New_Category.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_New_Category newInstance(String param1, String param2) {
        Fragment_New_Category fragment = new Fragment_New_Category();
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

    private Button btn_add,btn_cancel;
    private EditText txt_title;
    public Category_Dao category_dao = null;
    public FragmentManager manager;
    private FragmentTransaction transaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment__new__category, container, false);
        btn_add = (Button) v.findViewById(R.id.btn_add_category);
        btn_cancel = (Button) v.findViewById(R.id.btn_cancel_category_admin);
        txt_title = (EditText) v.findViewById(R.id.edit_text_title_category);
        //btn_cancel
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = manager.beginTransaction();
                Fragment_Category fragment_category = new Fragment_Category();
                fragment_category.manager = manager;
                fragment_category.category_dao = category_dao;
                transaction.replace(R.id.container_fragments_admin,fragment_category).commit();
            }
        });

        //btn add
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category_dao.add_Category(new Category(txt_title.getText().toString()));
                txt_title.setText("");
            }
        });
        return v;
    }
}