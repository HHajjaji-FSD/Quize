package hmz.question.quiz.Database.Class_Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import hmz.question.quiz.Database.Base_Dao;
import hmz.question.quiz.Database.Class_Data.Category;
import hmz.question.quiz.Database.Class_Data.User;

public class Category_Dao extends Base_Dao {

    public static final String Table_Name = "Category";
    public static final String Column_Id = "Id_Category";
    public static final String Column_Title = "Title";
    public static final String Create_Table = "CREATE TABLE " +Table_Name+
            "("+Column_Id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Column_Title+" TEXT NOT NULL);";

    public Category_Dao(Context context) {
        super(context);
    }

    //method
        //add
        public void add_Category(Category category) {
            try {
                ContentValues values = new ContentValues();
                values.put(Column_Title, category.getTitle());
                this.database.insert(Table_Name, null, values);
            } catch (Exception exception) {
                throw exception;
            }
        }
        //select all
        @SuppressLint("Range")
        public ArrayList<Category> list_Category()
        {
            ArrayList<Category> list = new ArrayList<Category>();
            String Query = "SELECT * FROM "+this.Table_Name;
            Cursor cursor = this.database.rawQuery(Query,null);
            if (cursor.moveToFirst())
            {
                do {
                     Integer id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Id)));
                    String title = cursor.getString(cursor.getColumnIndex(this.Column_Title));

                    Category category = new Category(id,title);
                    list.add(category);
                }while (cursor.moveToNext());
            }
            return list;
        }
    @SuppressLint("Range")
    public Category get_Category(Integer id_category)
    {
        Category category = null;
        Cursor cursor = this.database.rawQuery("SELECT * FROM "+Table_Name+" WHERE "+Column_Id+ "="+id_category,null);
        if (cursor.moveToFirst())
        {
            Integer id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Id)));
            String title = cursor.getString(cursor.getColumnIndex(this.Column_Title));
            category = new Category(id,title);
        }
        return category;
    }

}
