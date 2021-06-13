package domain;

public class Management {
    public static Customer[] members = new Customer[100];

    public static void register(String position, String[] userData) {
        Customer newMember;

        if(position.equals("admin")) {
//            newMember = new domain.Customer();
        } else {
//            newMember = new domain.Customer();
        }
    }

    public static void login(String id, String password) {
        for (User member : members) {
            if(member.getId().equals(id) && member.getPassword().equals(password)) {
                System.out.println("로그인 성공");
            } else {
                System.out.println("로그인 실패");
            }
        }
    }


}
