package model;

public class TopSpending {
    private String username,image, fullname, phoneNumber;
    private double total;

    public TopSpending() {
    }

    public TopSpending(String username, String image, String fullname, String phoneNumber, double total) {
        this.username = username;
        this.image = image;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.total = total;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}