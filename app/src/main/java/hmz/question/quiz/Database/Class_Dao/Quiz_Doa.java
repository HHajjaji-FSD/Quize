package hmz.question.quiz.Database.Class_Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import hmz.question.quiz.Database.Base_Dao;
import hmz.question.quiz.Database.Class_Data.Quiz;

public class Quiz_Doa extends Base_Dao {

    public static final String Table_Name = "Quiz";
    public static final String Column_Id = "Id_Quiz";
    public static final String Column_Id_Category= "Id_Category";
    public static final String Column_Question = "Question";
    public static final String Column_Answer = "Answer";
    public static final String Column_WRNG_Answer_A = "Wrong_Answer_A";
    public static final String Column_WRNG_Answer_B = "Wrong_Answer_B";
    public static final String Column_Note = "Note";
    public static final String Create_Table = "CREATE TABLE " +Table_Name+
            "("+Column_Id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Column_Id_Category+" INTEGER REFERENCES "+ Category_Dao.Table_Name+"("+Category_Dao.Column_Id+") ON DELETE CASCADE,"+
            Column_Question+" TEXT NOT NULL ," +
            Column_Answer+" TEXT NOT NULL," +
            Column_WRNG_Answer_A+" TEXT NOT NULL," +
            Column_WRNG_Answer_B+" TEXT NOT NULL ," +
            Column_Note+" INTEGER NOT NULL);";

    public Quiz_Doa(Context context) {
        super(context);
    }

    //method
        //add
        public void add_Quiz(Quiz quiz)
        {
            try
            {
                ContentValues values = new ContentValues();
                values.put(Column_Id_Category,quiz.getId_Category());
                values.put(Column_Question,quiz.getQuestion());
                values.put(Column_Answer,quiz.getAnswer());
                values.put(Column_WRNG_Answer_A,quiz.getWrong_Answer_A());
                values.put(Column_WRNG_Answer_B,quiz.getWrong_Answer_B());
                values.put(Column_Note,quiz.getNote().toString());
                this.database.insert(Table_Name,null,values);
            }
            catch (Exception exception)
            {
                throw exception;
            }
        }
        //select all
        @SuppressLint("Range")
        public ArrayList<Quiz> list_quiz()
        {
            ArrayList<Quiz> list = new ArrayList<Quiz>();
            String Query = "SELECT * FROM "+this.Table_Name;
            Cursor cursor = this.database.rawQuery(Query,null);
            if (cursor.moveToFirst())
            {
                do {
                    Integer id_quiz = Integer.valueOf(cursor.getString(cursor.getColumnIndex(this.Column_Id)));
                    Integer id_category = Integer.valueOf(cursor.getString(cursor.getColumnIndex(this.Column_Id_Category)));
                    String question = cursor.getString(cursor.getColumnIndex(this.Column_Question));
                    String answer = cursor.getString(cursor.getColumnIndex(this.Column_Answer));
                    String wrng_A = cursor.getString(cursor.getColumnIndex(this.Column_WRNG_Answer_A));
                    String wrng_B = cursor.getString(cursor.getColumnIndex(this.Column_WRNG_Answer_B));
                    Integer note = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Note)));
                    Quiz quiz = new Quiz(id_quiz,id_category,question,answer,wrng_A,wrng_B,note);
                    list.add(quiz);
                }while (cursor.moveToNext());
            }
            return list;
        }
        //select quiz the category
        @SuppressLint("Range")
        public ArrayList<Quiz> list_quiz(Integer id_ctg)
        {
            ArrayList<Quiz> list = new ArrayList<Quiz>();
            String Query = "SELECT * FROM "+this.Table_Name+" WHERE "+Column_Id_Category+"="+id_ctg;
            Cursor cursor = this.database.rawQuery(Query,null);
            if (cursor.moveToFirst())
            {
                do {
                    Integer id_quiz = Integer.valueOf(cursor.getString(cursor.getColumnIndex(this.Column_Id)));
                    Integer id_category = Integer.valueOf(cursor.getString(cursor.getColumnIndex(this.Column_Id_Category)));
                    String question = cursor.getString(cursor.getColumnIndex(this.Column_Question));
                    String answer = cursor.getString(cursor.getColumnIndex(this.Column_Answer));
                    String wrng_A = cursor.getString(cursor.getColumnIndex(this.Column_WRNG_Answer_A));
                    String wrng_B = cursor.getString(cursor.getColumnIndex(this.Column_WRNG_Answer_B));
                    Integer note = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Note)));
                    Quiz quiz = new Quiz(id_quiz,id_category,question,answer,wrng_A,wrng_B,note);
                    list.add(quiz);
                }while (cursor.moveToNext());
            }
            return list;
        }
}
