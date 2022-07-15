package hmz.question.quiz.Database.Class_Data;

public class Details
{
    private Integer Id;
    private Integer Id_Quiz;
    private Integer Id_Exam;

    public Details(Integer id, Integer id_Quiz, Integer id_Exam) {
        Id = id;
        Id_Quiz = id_Quiz;
        Id_Exam = id_Exam;
    }

    public Details(Integer id_Quiz, Integer id_Exam) {
        Id_Quiz = id_Quiz;
        Id_Exam = id_Exam;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getId_Quiz() {
        return Id_Quiz;
    }

    public void setId_Quiz(Integer id_Quiz) {
        Id_Quiz = id_Quiz;
    }

    public Integer getId_Exam() {
        return Id_Exam;
    }

    public void setId_Exam(Integer id_Exam) {
        Id_Exam = id_Exam;
    }
}
