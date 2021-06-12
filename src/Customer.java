public class Customer extends Member{
    private int totalAmount;

    public Customer(String position, String name, String id, String password, String phoneNumber) {
        super(position, name, id, password, phoneNumber);
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    //마일리지를 위해 계산 금액 누적.
    public void increaseAmount(int amount) {
        this.totalAmount += amount;
    }
}
