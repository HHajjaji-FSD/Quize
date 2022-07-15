package hmz.question.quiz.Fragments.Fragment_Exam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hmz.question.quiz.Database.Class_Dao.Category_Dao;
import hmz.question.quiz.Database.Class_Dao.Exam_Doa;
import hmz.question.quiz.Database.Class_Dao.User_Dao;
import hmz.question.quiz.Database.Class_Data.Exam;
import hmz.question.quiz.R;

public class Adapter_list_view_exam extends BaseAdapter {

    Context context;
    public ArrayList<Exam> list = new ArrayList<Exam>();

    User_Dao user_dao ;
    Category_Dao category_dao;
    public Exam_Doa exam_doa;
    public Adapter_list_view_exam(Context cntx) {
        this.context = cntx;
        user_dao = new User_Dao(context);
        category_dao = new Category_Dao(context);
        exam_doa = new Exam_Doa(context);
        list = exam_doa.list_Exam();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Exam getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    private TextView txt_username,txt_category,txt_date,txt_score;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       try {
           if (view == null)
           {
               view = LayoutInflater.from(context).inflate(R.layout.disgn_list_view_exam_admin,null);
           }
           txt_username = (TextView) view.findViewById(R.id.text_view_username_user);
           txt_category = (TextView) view.findViewById(R.id.text_view_category_exam);
           txt_date = (TextView) view.findViewById(R.id.text_view_date_exam);
           txt_score = (TextView) view.findViewById(R.id.text_view_note);

           Exam exam = getItem(i);
           Log.d("mmm",exam.getScore().toString());
           txt_username.setText(user_dao.get_User(exam.getId_User()).getUsername());
           txt_category.setText(category_dao.get_Category(exam.getId_User()).getTitle());
           txt_date.setText(exam.getDate_Exam().toString());
           txt_score.setText(exam.getScore().toString());

       }
       catch (Exception ex)
       {
           Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
       }
        return view;
    }
}
