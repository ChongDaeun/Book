package base;

import java.util.ArrayList;
import java.util.List;

import base.book.Book;
import base.book.Fiction;
import base.book.Literature;
import base.book.NonFiction;

public class BookInitializer {
    public static List<Book> initializeBooks() {
        List<Book> books = new ArrayList<>();
        // 소설 추가
        books.add(new Fiction("F1809", "랑과 나의 사막", "천선란", "현대문학"));
        books.add(new Fiction("F1811", "지구 끝의 온실", "김초엽", "자이언트 북스"));
        books.add(new Fiction("F1813", "모순", "양귀자", "쓰다"));
        books.add(new Fiction("F1815", "불편한 편의점", "김호연", "나무옆의자"));

        // 비소설 추가
        books.add(new NonFiction("N1822", "긴 작별 인사", "오수영", "고어라운드"));
        books.add(new NonFiction("N1824", "좋은 일이 오려나 보다", "박여름", "도서출판 히읏"));
        books.add(new NonFiction("N1826", "모든 것은 기본에서 시작한다", "손웅정", "수오서재"));
        books.add(new NonFiction("N1828", "살고 싶다는 농담", "허지웅", "고어라운드"));

        // 문학 추가
        books.add(new Literature("L1831", "꽃을 보듯 너를 본다", "나태주", "지혜"));
        books.add(new Literature("L1833", "동물농장", "조지오웰", "비꽃"));
        books.add(new Literature("L1835", "윤동주시집", "윤동주", "범우사"));

        return books;
    }

    // 모든 책 출력
    public static void showInfoAllBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // 소설만 출력
    public static void showInfoFictionBooks(List<Book> books) {
        for (Book book : books) {
            if (book instanceof Fiction) {
                System.out.println(book);
            }
        }
    }

    // 비소설만 출력
    public static void showInfoNonFictionBooks(List<Book> books) {
        for (Book book : books) {
            if (book instanceof NonFiction) {
                System.out.println(book);
            }
        }
    }

    // 문학만 출력
    public static void showInfoLiteratureBooks(List<Book> books) {
        for (Book book : books) {
            if (book instanceof Literature) {
                System.out.println(book);
            }
        }
    }
}
