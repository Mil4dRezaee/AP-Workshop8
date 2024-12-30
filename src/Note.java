import java.io.Serializable;

public class Note implements Serializable {

    private String title;
    private String content;
    private String date;

    public void setFields(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setFields(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return this.title + "\n" + this.date + "\n" + this.content;
    }
}