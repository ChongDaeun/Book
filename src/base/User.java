package base;

import java.util.ArrayList;

import base.book.Book;

public class User {
    private String userName;					// 이름
    private String userNumber;					// 회원 번호
    private int cumBookNum;						// 누적 대출 도서 수 - 이용자의 등급을 나누는 기준
    private int isBorrowedNum;					// 대출 중인 도서 수
    
    private ArrayList<Book> borrowBookList = new ArrayList<>();  // 대출 중인 도서 목록

    // 생성자
    public User() {}							// Grade 클래스가 User 클래스 상속 받기 때문
    
    public User(String userName, String userNumber, int cumBookNum) {
        this.userName = userName;
        this.userNumber = userNumber;
        this.cumBookNum = cumBookNum;
    }

    // Getter 메서드
    public String getUserName() {
        return userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public int getCumBookNum() {
        return cumBookNum;
    }
    
    public int getIsBorrowedNum() {
    	return isBorrowedNum;
   }
    
    public ArrayList<Book> getBookList() {		// 대출 중인 도서 목록
    	return borrowBookList;
    }
    
    // 메서드
    public ArrayList<Book> addBookList(Book book) {
    	borrowBookList.add(book);				// 대출 중인 도서 목록에 도서 추가
    	return borrowBookList;					// 업데이트 된 도서 목록 반환
    }
    
    public void addCumBookNum() {				// 대출한 책의 누적 권수를 증가시키는 메서드
        cumBookNum++;
    }

    public void addIsBorrowedNum() {			// 대출 중인 도서 수 증가
    	isBorrowedNum++;
    }
   
    public void deIsBorrowedNum() {				// 대출 중인 도서 수 감소
    	isBorrowedNum--;
    }
   
    public int remainingBooks(User user) {		// 대출 가능 잔여 수
    	Grade.getGrade(user);					// 이용자 등급 확인
    	return Grade.getAddBookCount(user) - getIsBorrowedNum();
   }

   
   
}