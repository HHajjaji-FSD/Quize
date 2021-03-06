package hmz.question.quiz.Database.Class_Data;

public class Category {
    private Integer Id;
    private String Title;

    public Category(Integer id, String title) {
        Id = id;
        Title = title;
    }

    public Category(String title) {
        Title = title;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public String toString() {
        return Title;
    }
}
