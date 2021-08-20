package library;

public class Book {
    String title,author;

    Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }
}
