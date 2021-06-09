public class Seat {
    private String name;
    private String food;
    private boolean available;
    private int totalPrice;

    public Seat(String name, String food) {
        this.name = name;
        this.food = food;
        this.available = false;
    }
}
