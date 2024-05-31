package base.book;

import java.time.LocalDate;

public class Book {
    private String ISBN;            // 책 고유번호
    private String title;           // 책 제목
    private String author;          // 책 저자
    private String publisher;       // 출판사
    private boolean isBorrowed;     // 대출 여부
    private LocalDate dueDate;
    
    public Book(String ISBN, String title, String author, String publisher) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isBorrowed = false;    // 초기에는 대출되지 않은 상태로 설정
    }

    // Getter 및 Setter 메서드
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
    
    public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
}
