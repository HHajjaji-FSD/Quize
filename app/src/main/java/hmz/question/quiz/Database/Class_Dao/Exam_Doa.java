package hmz.question.quiz.Database.Class_Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.wifi.WifiEnterpriseConfig;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import hmz.question.quiz.Database.Base_Dao;
import hmz.question.quiz.Database.Class_Data.Exam;

public class Exam_Doa extends Base_Dao {

    public static final String Table_Name = "Exam";
    public static final String Column_Id = "Id_exam";
    public static final String Column_Id_Category = "Id_Category";
    public static final String Column_Id_User = "Id_User";
    public static final String Column_Date = "Date_Exam";
    public static final String Column_Score = "Score";
    public static final String Create_Table = "CREATE TABLE " +Table_Name+
            "("+Column_Id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Column_Id_Category+" INTEGER REFERENCES "+ Category_Dao.Table_Name+"("+Category_Dao.Column_Id+") ON DELETE CASCADE,"+
            Column_Id_User+" INTEGER REFERENCES "+ User_Dao.Table_Name+"("+User_Dao.Column_Id+") ON DELETE CASCADE,"+
            Column_Date+" DATE NOT NULL," +
            Column_Score+" INTEGER NOT NULL);" ;

    public Exam_Doa(Context context) {
        super(context);
    }

    //method
        //add
        public void add_Exam(Exam exam)
        {
            try
            {
                ContentValues values = new ContentValues();
                values.put(Column_Id_Category,exam.getId_Category());
                values.put(Column_Id_User,exam.getId_User());
                values.put(Column_Date,exam.getDate_Exam().toString());
                values.put(Column_Score,exam.getScore());
                this.database.insert(Table_Name,null,values);
                //Log.d("hhh",String.valueOf(x));
            }
            catch (Exception exception)
            {
                throw exception;
            }
        }
        //select all
        @SuppressLint("Range")
        public ArrayList<Exam> list_Exam()
        {
            ArrayList<Exam> list = new ArrayList<Exam>();
            String Query = "SELECT * FROM "+this.Table_Name;
            Cursor cursor = this.database.rawQuery(Query,null);
            if (cursor.moveToFirst())
            {
                do {
                    Integer id_exam = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Id)));
                    Integer id_category = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Id_Category)));
                    Integer id_user = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Id_User)));
                    Integer score = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Score)));
                    Date date = new Date(cursor.getString(cursor.getColumnIndex(this.Column_Date)));
                    Exam exam = new Exam(id_exam,id_user,id_category,score,date);
                    list.add(exam);
                }while (cursor.moveToNext());
            }
            return list;
        }

        // select exam the user
        @SuppressLint("Range")
        public ArrayList<Exam> list_Exam_User(Integer id_user)
        {
            ArrayList<Exam> list = new ArrayList<Exam>();
            String Query = "SELECT * FROM "+this.Table_Name+" WHERE "+this.Column_Id_User+"="+id_user;
            Cursor cursor = this.database.rawQuery(Query,null);
            if (cursor.moveToFirst())
            {
                do {
                    Integer id_exam = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Id)));
                    Integer id_category = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Id_Category)));
                    Integer score = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Score)));
                    Date date = new Date(cursor.getString(cursor.getColumnIndex(this.Column_Date)));
                    Exam exam = new Exam(id_exam,id_user,id_category,score,date);
                    list.add(exam);
                }while (cursor.moveToNext());
            }
            return list;
        }
}
