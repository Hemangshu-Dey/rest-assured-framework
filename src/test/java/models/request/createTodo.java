package models.request;

public class createTodo {

    private String title;
    private String description;
    private String deadline;
    private String todoCategoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTodoCategoryId() {
        return todoCategoryId;
    }

    public void setTodoCategoryId(String todoCategoryId) {
        this.todoCategoryId = todoCategoryId;
    }
}
