package model;

public class Order {
    private int id;
    private String date;
    private Users user;
    private float totalMoney;
    private boolean status;

    public Order() {
    }

    public Order(int id, String date, Users user, float totalMoney, boolean status) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.totalMoney = totalMoney;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", date=" + date + ", user=" + user + ", totalMoney=" + totalMoney + ", status=" + status + '}';
    }
    
}