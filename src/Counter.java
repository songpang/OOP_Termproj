import java.util.*;

public class Counter {
    private static final List<Seat> reservedSeats = new ArrayList<>(); // 예약된 좌석을 저장하는 리스트
    private static final Map<String, Integer> menu = new HashMap<>();

    {
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

    public static int reserve(int seatNumber, String[] food, String time) {
        int totalPrice = 0;
        for (Seat seat : reservedSeats) {
            // 예약하고자 하는 시간대의 좌석이 예약되어 있는지 체크.
            if(seat.getSeatNumber() == seatNumber && seat.getTime().equals(time)) {
                System.out.println("예약할 수 없습니다.");
                return -1;
            }
        }

        totalPrice = calculatePrice(food);
        reservedSeats.add(new Seat(seatNumber, food, time, totalPrice));
        return totalPrice;
    }

    public static int calculatePrice(String[] food) {
        int totalPrice = 0;
        for (String s : food) {
            totalPrice += menu.get(s);
        }
        return totalPrice;
    }

    // 음식 추가 주문하는 메서드
    public static int orderAdditionalFood(Seat seat, String[] additionalFood) {
        int totalPrice = seat.getTotalPrice() + calculatePrice(additionalFood);
        String[] originalFood = seat.getFood();
        String[] newOrderedFood = new String[originalFood.length + additionalFood.length];

        // 기존 주문과 새로운 주문을 새로운 Array 에 복사.
        System.arraycopy(originalFood, 0, newOrderedFood, 0, originalFood.length);
        System.arraycopy(additionalFood, 0, newOrderedFood, originalFood.length, additionalFood.length);

        Seat modifiedSeat = new Seat(seat.getSeatNumber(), newOrderedFood, seat.getTime(), totalPrice);

        reservedSeats.remove(seat);
        reservedSeats.add(modifiedSeat);

        return totalPrice;
    }

    public static void checkAvailableSeat(int seatNumber) {
        for (Seat reservedSeat : reservedSeats) {
            if(reservedSeat.getSeatNumber() == seatNumber) {
                System.out.println("해당 좌석은 예약이 있습니다. ");
                System.out.println(reservedSeat.getTime() + "30분 이후부터 예약이 가능합니다.");
                return;
            }
        }

        System.out.println("해당 좌석은 예약이 없습니다.");
    }

    public static List<Seat> getReservedSeats() {
        return reservedSeats;
    }

    public static Map<String, Integer> getMenu() {
        return menu;
    }

    public static void removeSeat(Seat seat) {
        reservedSeats.remove(seat);
    }
}
