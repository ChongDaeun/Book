package base;

import java.util.HashMap;
import java.util.Map;

// 이용자 초기화
public class UserInitializer {
    public static Map<String, User> initializeUsers() {
        Map<String, User> users = new HashMap<>();
        
        // 이용자 초기화
        User user1 = new User("정다은", "1000", 0);
        User user2 = new User("오진욱", "1001", 18);
        User user3 = new User("장희재", "1002", 27);
        User user4 = new User("추선주", "1003", 40);

        // 사용자를 Map에 추가
        users.put(user1.getUserNumber(), user1);
        users.put(user2.getUserNumber(), user2);
        users.put(user3.getUserNumber(), user3);
        users.put(user4.getUserNumber(), user4);
        
        return users;
    }
}