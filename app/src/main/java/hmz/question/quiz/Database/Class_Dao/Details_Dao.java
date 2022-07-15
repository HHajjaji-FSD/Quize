package hmz.question.quiz.Database.Class_Dao;

import android.content.ContentValues;
import android.content.Context;

import hmz.question.quiz.Database.Base_Dao;
import hmz.question.quiz.Database.Class_Data.Details;

public class Details_Dao extends Base_Dao {

    public static final String Table_Name = "Details";
    public static final String Column_Id = "Id_dt";
    public static final String Column_Id_Exam = "Id_Exam";
    public static final String Column_Id_Quiz = "Id_Quiz";
    public static final String Create_Table = "CREATE TABLE " +Table_Name+
            "("+Column_Id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Column_Id_Exam+" INTEGER REFERENCES "+ Exam_Doa.Table_Name+"("+Exam_Doa.Column_Id+"),"+
            Column_Id_Quiz+" INTEGER REFERENCES "+ Quiz_Doa.Table_Name+"("+Quiz_Doa.Column_Id+"));";

    public Details_Dao(Context context) {
        super(context);
    }
    //method
        //add
    public void add_Details(Details details)
    {
        ContentValues values = new ContentValues();
        values.put(Column_Id_Exam,details.getId_Exam());
        values.put(Column_Id_Quiz,details.getId_Quiz());
        this.database.insert(Table_Name,null,values);
    }

}
