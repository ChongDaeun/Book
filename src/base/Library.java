package base;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import base.book.Book;
import base.book.BookInitializer;
import base.book.Fiction;
import base.book.Literature;
import base.book.NonFiction;

public class Library {
    public static int baseBookLimit = 5; // 기본 대출 권수
    private List<Book> books;    // 도서 목록
    private Map<String, User> users; // 사용자 목록을 저장할 Map
    private Map<Book, User> borrowedBooks;  // 대출 중인 도서 목록 (도서, 이용자)
    private static Library instance = new Library();

    private Library() {
        books = new ArrayList<>();
        borrowedBooks = new HashMap<>();
        users = UserInitializer.initializeUsers(); // 사용자 초기화 메서드 호출
        initializeBooks();	// 도서 초기화
    }

    public static Library getInstance() {
    	if(instance ==  null) {
    		instance = new Library();
    	}
    	return instance;
    }

    private void initializeBooks() {
        books = BookInitializer.initializeBooks(); // 도서 초기화 메서드 호출
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    // 이용자 이름 존재 유무
    public User findUserByName(String name) {
        for (User user : users.values()) {
            if (user.getUserName().equals(name)) {
                return user;
            }
        }
        return null; // 해당하는 사용자를 찾지 못한 경우 null 반환
    }
    
    // 입력 값과 보유 도서 비교
    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null; // 해당 제목의 도서를 찾지 못한 경우 null 반환
    }
    
    // 대출 가능 여부 확인
    private boolean canBorrowBook(User user) {
    	Grade.getGrade(user);
        return user.getIsBorrowedNum() < Grade.getAddBookCount(user);
    }
    
    // 대출 메서드
    public void borrowBook(User user, Book book) {
        if (!canBorrowBook(user)) {
            System.out.println("    대출 가능한 도서의 한도를 초과하였습니다.");
            return;
        }
        else if (book.isBorrowed()) {
            System.out.println("    이미 대출 중인 도서입니다.");
        } else {
            borrowedBooks.put(book, user);  // 대출 목록에 추가
            book.setBorrowed(true); 		// 책의 대출 여부 업데이트
            user.addCumBookNum();  	// 이용자의 누적 대출 권수 증가
            user.addIsBorrowedNum();		// 대출 중인 권수 증가
            user.addBookList(book);
            
            System.out.println("    [ " + book.getTitle() + " ]을/를 대출하였습니다.");
            System.out.println("    ★ 도서 대출이 완료되었습니다 ★");
            System.out.println("___________________________________________________________________\n");
        }
    }

    // 반납 메서드
    public void returnBook(User user, Book book) {
        if (book.isBorrowed() && borrowedBooks.get(book).equals(user)) {  // 해당 도서를 대출한 사용자인지 확인
            borrowedBooks.remove(book);  // 대출 목록에서 책만 제거
            book.setBorrowed(false); // 책의 대출 여부 업데이트
            user.deIsBorrowedNum();
            user.getBookList().remove(book);
            System.out.println("    [ " + book.getTitle() + " ]을/를 반납하였습니다.");
            System.out.println("    ★ 반납이 완료되었습니다 ★");
        } else {
            System.out.println("    대출 중인 도서 목록에 해당 도서가 없거나, 다른 사용자가 대출한 도서입니다.");
        }
    }

    // 14일 후의 반납 날짜를 계산하는 메서드
    public LocalDate calculateDueDate(LocalDate borrowDate) {
        return borrowDate.plusDays(14);
    }
    
  	// 연체 연체 이새끼야
	public double calculateLateDays(Book book) {
		LocalDate dueDate = book.getDueDate();
	        if (dueDate.isBefore(LocalDate.now())) {
	            long daysLate = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
	            return daysLate;
	        }
	        return 0; 
	}
}
