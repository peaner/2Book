package cn.book.po;

public class Book {
    private Integer bookId;

    private String bookIcon;

    private String bookName;

    private String bookAuthor;

    private String bookPublish;

    private Float bookPrice;

    private String composeTime;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookIcon() {
        return bookIcon;
    }

    public void setBookIcon(String bookIcon) {
        this.bookIcon = bookIcon == null ? null : bookIcon.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public String getBookPublish() {
        return bookPublish;
    }

    public void setBookPublish(String bookPublish) {
        this.bookPublish = bookPublish == null ? null : bookPublish.trim();
    }

    public Float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getComposeTime() {
        return composeTime;
    }

    public void setComposeTime(String composeTime) {
        this.composeTime = composeTime == null ? null : composeTime.trim();
    }
}