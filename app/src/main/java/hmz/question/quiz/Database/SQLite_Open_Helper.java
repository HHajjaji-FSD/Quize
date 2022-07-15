package hmz.question.quiz.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import hmz.question.quiz.Database.Class_Dao.Category_Dao;
import hmz.question.quiz.Database.Class_Dao.Details_Dao;
import hmz.question.quiz.Database.Class_Dao.Exam_Doa;
import hmz.question.quiz.Database.Class_Dao.Quiz_Doa;
import hmz.question.quiz.Database.Class_Dao.User_Dao;

public class SQLite_Open_Helper extends SQLiteOpenHelper {

    public SQLite_Open_Helper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Category_Dao.Create_Table);
        sqLiteDatabase.execSQL(User_Dao.Create_Table);
        sqLiteDatabase.execSQL(Quiz_Doa.Create_Table);
        sqLiteDatabase.execSQL(Exam_Doa.Create_Table);
        sqLiteDatabase.execSQL(Details_Dao.Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
