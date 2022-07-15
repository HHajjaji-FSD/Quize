package hmz.question.quiz.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Base_Dao {
    protected static final String Database_Name = "Quizzes";
    protected static final Integer Database_Version = 1;
    protected SQLite_Open_Helper helper = null;
    protected SQLiteDatabase database=null;
    public Base_Dao (Context context)
    {
        helper = new SQLite_Open_Helper(context,Database_Name,null,Database_Version);
        database = helper.getWritableDatabase();
    }
}
