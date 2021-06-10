public class Seat {
    private final int seatNumber;
    private final String[] food;
    private String time;
    private int totalPrice;

    public Seat(int seatNumber, String[] food, String time, int totalPrice) {
        this.seatNumber = seatNumber;
        this.food = food;
        this.time = time;
        this.totalPrice = totalPrice;
    }

    public Seat(int seatNumber, String[] food) {
        this.seatNumber = seatNumber;
        this.food = food;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String[] getFood() {
        return food;
    }

    public String getTime() {
        return time;
    }


    public int getTotalPrice() {
        return totalPrice;
    }
}
