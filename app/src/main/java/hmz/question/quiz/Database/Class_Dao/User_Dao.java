package hmz.question.quiz.Database.Class_Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import hmz.question.quiz.Database.Base_Dao;
import hmz.question.quiz.Database.Class_Data.User;

public class User_Dao extends Base_Dao {

    public static final String Table_Name = "User";
    public static final String Column_Id = "Id_user";
    public static final String Column_Username = "Username";
    public static final String Column_Email = "Email";
    public static final String Column_Type = "Type";
    public static final String Column_Password = "Password";
    public static final String Create_Table = "CREATE TABLE " +Table_Name+
            "("+Column_Id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Column_Username+" TEXT NOT NULL," +
            Column_Type+" TEXT CHECK("+Column_Type+" IN ('ADM','USR'))," +
            Column_Email+" TEXT NOT NULL UNIQUE," +
            Column_Password+" TEXT NOT NULL);";

    public User_Dao(Context context) {
        super(context);
    }

    /// method
        //add
        public void add_User(User user)
        {
            try {
                ContentValues values = new ContentValues();
                values.put(Column_Username,user.getUsername());
                values.put(Column_Type,user.getType());
                values.put(Column_Email,user.getEmail());
                values.put(Column_Password,user.getPassword());
                this.database.insert(Table_Name,null,values);
            }
            catch (Exception exception)
            {
                throw exception;
            }
        }

        //select all
        @SuppressLint("Range")
        public ArrayList<User> list_Users()
        {
            ArrayList<User> list = new ArrayList<User>();
            String Query = "SELECT * FROM "+this.Table_Name +" WHERE "+Column_Type+"='USR'";
            Cursor cursor = this.database.rawQuery(Query,null);
            if (cursor.moveToFirst())
            {
                do {
                    Integer id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(this.Column_Id)));
                    String username = cursor.getString(cursor.getColumnIndex(this.Column_Username));
                    String type = cursor.getString(cursor.getColumnIndex(this.Column_Type));
                    String email = cursor.getString(cursor.getColumnIndex(this.Column_Email));
                    String password = cursor.getString(cursor.getColumnIndex(this.Column_Password));
                    User user = new User(id,username,type,email,password);
                    list.add(user);
                }while (cursor.moveToNext());
            }
            return list;
        }

        //login
        @SuppressLint("Range")
        public User login_User(String email,String password)
        {
            User user = null;
            Cursor cursor = this.database.rawQuery("SELECT * FROM "+Table_Name+" WHERE "+Column_Email+ "='"+email +"' AND "+Column_Password+" = '"+password+"'",null);
            if (cursor.moveToFirst())
            {
                Integer id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Column_Id)));
                String username = cursor.getString(cursor.getColumnIndex(Column_Username));
                String type = cursor.getString(cursor.getColumnIndex(Column_Type));
                String email_ = cursor.getString(cursor.getColumnIndex(Column_Email));
                String password_ = cursor.getString(cursor.getColumnIndex(Column_Password));
                user = new User(id,username,type,email_,password_);
            }
            return user;
        }
        @SuppressLint("Range")
        public User get_User(Integer id_user)
        {
            User user = null;
            Cursor cursor = this.database.rawQuery("SELECT * FROM "+Table_Name+" WHERE "+Column_Id+ "="+id_user,null);
            if (cursor.moveToFirst())
            {
                Integer id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(Column_Id)));
                String username = cursor.getString(cursor.getColumnIndex(Column_Username));
                String type = cursor.getString(cursor.getColumnIndex(Column_Type));
                String email_ = cursor.getString(cursor.getColumnIndex(Column_Email));
                String password_ = cursor.getString(cursor.getColumnIndex(Column_Password));
                user = new User(id,username,type,email_,password_);
            }
            return user;
        }

}
