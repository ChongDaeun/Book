package base;

public class Grade extends User {
 
   private static String grade;				// 이용자 등급
   
   public Grade() {
       super(); // 부모 클래스의 생성자 호출
   }
   
   // 누척 대출 권수에 따른 등급 설정
   public static String getGrade(User user) {
      if(user.getCumBookNum() > 40) {
         grade = "VIP";
         return grade;
      }else if(user.getCumBookNum() > 20) {
         grade = "GOLD";
         return grade;
      }else {
         grade = "SILVER";
         return grade;
      }
   }
   
   // 등급에 따른 추가 대출 가능 권수 반환
   public static int getAddBookCount(User user) {
	   switch(grade) {
           case "VIP":
               return Library.baseBookLimit + 10; // VIP 등급 추가 대출 가능 권수
           case "GOLD":
               return Library.baseBookLimit + 5;  // GOLD 등급 추가 대출 가능 권수
           default:
               return Library.baseBookLimit;      // SILVER 등급은 기본 대출 가능 권수 반환
       }
   }


}