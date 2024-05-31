package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import base.Grade;
import base.Library;
import base.User;
import base.book.Book;
import base.book.BookInitializer;

public class LibraryTest {
    private static Library library = Library.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;
        int consoleWidth = 58;  // 콘솔 화면 폭
        String libraryTestText = "★ 덤앤더머 도서관 입니다 ★";
        int libraryTestTextLength = libraryTestText.length();
        int padding = (consoleWidth - libraryTestTextLength) / 2;
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.println(libraryTestText);
        
        while (isRunning) {
            System.out.println("--------------------------  MAIN  --------------------------------");
            System.out.println("    1. 내 정보 조회   2. 도서 검색 및 목록 조회   3. 대출 및 반납   4. 종료");
            System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
            System.out.print("    원하는 항목의 숫자를 선택해주세요. 👉 ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                
                	case 1:
                		// 내 정보 조회
                        displayMyInformation();
                        break;
                    case 2:
                        displayBookList();
                        break;
                    case 3:
                        borrowOrReturnBook();
                        break;
                    case 4:
                        isRunning = false;
                        System.out.println("    프로그램을 종료합니다. 다음에 또 이용 해 주세요.");
                        break;
                    default:
                        System.out.println("    잘못된 입력입니다. 다시 시도하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("    잘못된 입력입니다. 숫자를 입력하세요.");
            }
        }
    }

    private static void displayMyInformation() {
    	System.out.println("    ---------------------------------------------------------");
    	int consoleWidth = 61;  // 콘솔 화면 폭
    	String myPageText = "😀 My Page 😀";
        int myPageTextLength = myPageText.length();
        int padding = (consoleWidth - myPageTextLength) / 2;
        System.out.print("    이름을 입력하세요: ");
        String userName = scanner.nextLine();
     // My Page 중앙 정렬
        System.out.println("___________________________________________________________________");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.println(myPageText);
        
        User user = library.findUserByName(userName);
        if (user != null) {
            System.out.println("     사용자 이름: " + user.getUserName() 
            					+ "\n     회원 번호: " + user.getUserNumber()
            					+ "\n     회원 등급: " + Grade.getGrade(user) 	
            					+ "\n     누적 대출 도서 수: " + user.getCumBookNum() 	
            					+ "\n     등급별 대출 한도: " + Grade.getAddBookCount(user)	
            					+ "\n     대출 가능 잔여 수: " + user.remainingBooks(user)
            					+ "\n     대출 중인 도서 수: " + user.getIsBorrowedNum());
            System.out.println("     [ 대출 중인 도서 목록 ]");
            for (int i = 0; i < user.getBookList().size(); i++) {
                Book book = user.getBookList().get(i);
                System.out.println("     " + (i + 1) + ". 도서 번호: " + book.getISBN() + " | 도서 제목: " + book.getTitle() 
                + " | 저자: " + book.getAuthor() + " | 출판사: " + book.getPublisher());
            }
            System.out.println("___________________________________________________________________\n\n");
        } else {
            System.out.println("\n                일치하는 사용자 정보를 찾을 수 없습니다.");
            System.out.println("___________________________________________________________________\n\n");
        }
    }
    
	private static void displayBookList() {
        List<Book> books = library.getBooks();
        System.out.println("___________________________________________________________________\n");

        boolean isRunning = true;
        while (isRunning) {
			
			int consoleWidth = 61;  // 콘솔 화면 폭
	    	String searchBookText = "📚 도서 검색 및 목록 조회 📚\n";
	        int searchBookTextLength = searchBookText.length();
	        int padding = (consoleWidth - searchBookTextLength) / 2;
	        for (int i = 0; i < padding; i++) {
	            System.out.print(" ");
	        }
	        System.out.println(searchBookText);
	        
			System.out.println("    1. 도서 검색   2. 전체 목록   3. 소설   4. 비소설   5. 문학   6. MAIN");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.print("    원하는 항목의 숫자를 선택해주세요. 👉 ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println("    ---------------------------------------------------------");
            switch (choice) {
            	case 1:
            		searchBook();
            		break;
                case 2:
                    BookInitializer.showInfoBooks(books);
                    break;
                    
                case 3:
                    BookInitializer.showInfoFictionBooks(books);
                    break;
                case 4:
                	BookInitializer.showInfoNonFictionBooks(books);
                	break;
                case 5:
                	BookInitializer.showInfoLiteratureBooks(books);
                	break;
                case 6:
                	isRunning = false;
                    break;
                default:
                    System.out.println("    잘못된 입력입니다. 다시 시도하세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("    잘못된 입력입니다. 숫자를 입력하세요.");
        }
        System.out.println("___________________________________________________________________\n");
    }
	}

    private static void borrowOrReturnBook() {
    	System.out.println("___________________________________________________________________\n");
    	
    	boolean isRunning = true;
    	while (isRunning) {

    		int consoleWidth = 61;  // 콘솔 화면 폭
    		String borrowReturnText = "⏳ 대출 및 반납 ⌛\n";
    		int borrowReturnTextLength = borrowReturnText.length();
    		int padding = (consoleWidth - borrowReturnTextLength) / 2;
    		for (int i = 0; i < padding; i++) {
    			System.out.print(" ");
    		}
    		System.out.println(borrowReturnText);
    		
    		System.out.println("    1. 대출   2. 반납   3. MAIN");
    		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
    		System.out.print("    원하는 항목의 숫자를 선택해주세요. 👉 ");
    		
    		try {
    			int choice = Integer.parseInt(scanner.nextLine());
    			System.out.println("    ---------------------------------------------------------");
    			switch (choice) {
    				case 1:
    					borrowBook();
    					break;
    				case 2:
    					returnBook();
    					break;
    				case 3:
    					isRunning = false;
    					break;
    				default:
    					System.out.println("    잘못된 입력입니다. 다시 시도하세요.");
    			}
    		} catch (NumberFormatException e) {
    			System.out.println("    잘못된 입력입니다. 숫자를 입력하세요.");
    		}
    		System.out.println("___________________________________________________________________\n");
    	}
    }

    private static void borrowBook() {
        try {
            System.out.print("    대출할 도서의 제목을 입력하세요: ");
            String title = scanner.nextLine();
            System.out.print("    대출할 사용자의 이름을 입력하세요: ");
            String userName = scanner.nextLine();

            Book book = library.findBookByTitle(title);
            User user = library.findUserByName(userName);
            
            if (book.isBorrowed() == true) {
               System.out.println("    이미 대출 중인 도서입니다.");
            }else if (book != null && user != null) {
               // 대출일 출력
                System.out.print("    대출 시작 날짜를 입력하세요 (예: 2024-05-30): ");
                String borrowDateString = scanner.nextLine();
                LocalDate borrowDate = LocalDate.parse(borrowDateString);

                LocalDate dueDate = library.calculateDueDate(borrowDate);   // 대출 날짜를 인자로 전달
                book.setDueDate(dueDate);
                System.out.println("    반납 날짜: " + dueDate);
                library.borrowBook(user, book);                        // Library 클래스의 메서드
            } else {
                System.out.println("    [ 도서 ] 또는 [ 사용자 ]를 찾을 수 없습니다.");
                System.out.println("___________________________________________________________________\n");
            }
        } catch (Exception e) {
            System.out.println("    대출 중 오류가 발생했습니다: " + e.getMessage());
            System.out.println("___________________________________________________________________\n");
        }
    }

    private static void returnBook() {
        try {
           System.out.print("    반납할 도서의 제목을 입력하세요: ");
            String title = scanner.nextLine();
            System.out.print("    반납할 사용자의 이름을 입력하세요: ");
            String userName = scanner.nextLine();

            Book book = library.findBookByTitle(title);
            User user = library.findUserByName(userName);

            if (book != null && user != null) {
                double lateDays = library.calculateLateDays(book); // 연체 일수 계산
                int lateFee = (int) (lateDays * 100); // 연체비 계산

                if (lateDays > 0) {
                    System.out.println("    도서 반납이 [ " + (int)lateDays + " ]일 연체되었습니다.");
                    System.out.println("    연체비: " + lateFee + "원");
                }
                library.returnBook(user, book);
                System.out.println("___________________________________________________________________\n");
            } else {
               System.out.println("    [ 도서 ] 또는 [ 사용자 ]를 찾을 수 없습니다.");
               System.out.println("___________________________________________________________________\n");
            }
        } catch (Exception e) {
            System.out.println("    반납 중 오류가 발생했습니다: " + e.getMessage());
            System.out.println("___________________________________________________________________\n");
        }
    }
    
    
    // MAIN 메뉴 2-1 도서 검색 기능
    private static void searchBook() {
        System.out.print("    찾으시는 도서의 제목을 입력해주세요(공백 구분): ");
        String findTitle = scanner.nextLine();
        
        Book book = library.findBookByTitle(findTitle);
        
        if (book != null) {
             System.out.println("    [ " + findTitle + " ]은/는 보유 중인 도서입니다.");
             if (book.isBorrowed() == true) {
            	 System.out.println("    [ " + findTitle + " ]은/는 대출 중 입니다.");
             }else {
            	 
            	 System.out.println("    [ " + findTitle + " ]은/는 대출 가능합니다.");
             }
         } else {
             System.out.println("    [ " + findTitle + " ]은/는 보유 중이지 않은 도서입니다.");
         }
     } 

}
