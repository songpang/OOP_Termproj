package domain;

public class Seat {
    private final int seatNumber;
    private final String food;
    private final String memberCount;
    private String time;
    private int totalPrice;

    public Seat(int seatNumber,
                String food,
                String memberCount,
                String time,
                int totalPrice) {
        this.seatNumber = seatNumber;
        this.food = food;
        this.time = time;
        this.totalPrice = totalPrice;
        this.memberCount = memberCount;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getFood() {
        return food;
    }

    public String getTime() {
        return time;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getMemberCount() {
        return memberCount;
    }

    public String[] getInfo() {
        String[] temp = new String[4];
        temp[0] = String.valueOf(seatNumber);
        temp[1] = memberCount;
        temp[2] = food;
        temp[3] = time;

        return temp;
    }
}
