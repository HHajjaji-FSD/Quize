package hmz.question.quiz.Database.Class_Data;

public class Quiz {
    private Integer Id;
    private Integer Id_Category;
    private String Question;
    private String Answer;
    private String Wrong_Answer_A;
    private String Wrong_Answer_B;
    private Integer Note;

    public Quiz(Integer id, Integer id_Category, String question, String answer, String wrong_Answer_A, String wrong_Answer_B, Integer note) {
        Id = id;
        Id_Category = id_Category;
        Question = question;
        Answer = answer;
        Wrong_Answer_A = wrong_Answer_A;
        Wrong_Answer_B = wrong_Answer_B;
        Note = note;
    }

    public Quiz(Integer id_Category, String question, String answer, String wrong_Answer_A, String wrong_Answer_B, Integer note) {
        Id_Category = id_Category;
        Question = question;
        Answer = answer;
        Wrong_Answer_A = wrong_Answer_A;
        Wrong_Answer_B = wrong_Answer_B;
        Note = note;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getId_Category() {
        return Id_Category;
    }

    public void setId_Category(Integer id_Category) {
        Id_Category = id_Category;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getWrong_Answer_A() {
        return Wrong_Answer_A;
    }

    public void setWrong_Answer_A(String wrong_Answer_A) {
        Wrong_Answer_A = wrong_Answer_A;
    }

    public String getWrong_Answer_B() {
        return Wrong_Answer_B;
    }

    public void setWrong_Answer_B(String wrong_Answer_B) {
        Wrong_Answer_B = wrong_Answer_B;
    }

    public Integer getNote() {
        return Note;
    }

    public void setNote(Integer note) {
        Note = note;
    }

    @Override
    public String toString() {
        return "Question : " + Question +
                "\nAnswer : " + Answer +
                "\nWrong Answer A : " + Wrong_Answer_A +
                "\nWrong Answer B : " + Wrong_Answer_B +
                "\nNote : " + Note ;
    }
}
