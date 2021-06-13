package domain;

public class User {
    private String position;
    private String name;
    private String id;
    private String password;
    private String phoneNumber;

    public User(String position, String name, String id, String password, String phoneNumber) {
        this.position = position;
        this.name = name;
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
