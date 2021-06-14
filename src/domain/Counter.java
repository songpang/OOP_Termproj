package domain;

import java.util.*;

public class Counter {
    public static final List<Seat> reservedSeats = new ArrayList<>(); // 예약된 좌석을 저장하는 리스트
    public static final Map<String, Integer> menu = new HashMap<>();

    public Counter() {
        createMenu();
    }

    //메뉴 미리 생성후 삽입.
    private void createMenu() {
        menu.put("김치찌개", 8000);
        menu.put("된장찌개", 8000);
        menu.put("물냉면", 8000);
        menu.put("비빔냉면", 8000);
        menu.put("삼겹살", 12000);
        menu.put("오겹살", 8000);
        menu.put("꽃등심", 24000);
        menu.put("항정살", 12000);
        menu.put("갈매기살", 12000);
        menu.put("부속고기", 12000);
    }

    //자리를 예약하는 메서드
    public static int reserve(int seatNumber, String memberCount, String food, String time) {
        int totalPrice = 0;
        for (Seat seat : reservedSeats) {
            // 예약하고자 하는 시간대의 좌석이 예약되어 있는지 체크.
            if(seat.getSeatNumber() == seatNumber && seat.getTime().equals(time)) {
//                System.out.println("예약할 수 없습니다.");
                return -1;
            }
        }

        totalPrice = calculatePrice(food);
        reservedSeats.add(new Seat(seatNumber, food, memberCount, time, totalPrice));
        return totalPrice;
    }

    //음식의 총 값을 계산하는 메서드
    public static int calculatePrice(String food) {
        int totalPrice = 0;
        String[] foods = food.split(" ");

        for (String s : foods) {
            totalPrice += menu.get(s);
        }

        return totalPrice;
    }

    // 음식 추가 주문하는 메서드
    public static int orderAdditionalFood(Seat seat, String additionalFood) {
        int totalPrice = seat.getTotalPrice() + calculatePrice(additionalFood);
        String originalFood = seat.getFood();
        String newOrderedFood = originalFood + " " + additionalFood;
        Seat modifiedSeat = new Seat(seat.getSeatNumber(), newOrderedFood, seat.getMemberCount(), seat.getTime(), totalPrice);

        reservedSeats.remove(seat);
        reservedSeats.add(modifiedSeat);
        return totalPrice;
    }

    //정산된 자리를 치우는 메서드
    public static void removeSeat(Seat seat) {
        reservedSeats.remove(seat);
    }
}
