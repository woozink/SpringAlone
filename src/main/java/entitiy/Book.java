package entitiy;

public class Book {
    public static Book book;
    private long id;
    private String name;
    private String author;
    private String page;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void remove(int i) {
    }

    public int size() {
        return 0;
    }
}
