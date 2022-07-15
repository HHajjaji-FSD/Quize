package hmz.question.quiz.Database.Class_Data;

import java.util.Date;

public class Exam {
    private Integer Id;
    private Integer Id_User;
    private Integer Id_Category;
    private Integer Score;
    private Date Date_Exam;

    public Exam(Integer id, Integer id_User, Integer id_Category, Integer score, Date date_Exam) {
        Id = id;
        Id_User = id_User;
        Id_Category = id_Category;
        Score = score;
        Date_Exam = date_Exam;
    }

    public Exam(Integer id_User, Integer id_Category, Integer score, Date date_Exam) {
        Id_User = id_User;
        Id_Category = id_Category;
        Score = score;
        Date_Exam = date_Exam;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getId_User() {
        return Id_User;
    }

    public void setId_User(Integer id_User) {
        Id_User = id_User;
    }

    public Integer getId_Category() {
        return Id_Category;
    }

    public void setId_Category(Integer id_Category) {
        Id_Category = id_Category;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public Date getDate_Exam() {
        return Date_Exam;
    }

    public void setDate_Exam(Date date_Exam) {
        Date_Exam = date_Exam;
    }


}
