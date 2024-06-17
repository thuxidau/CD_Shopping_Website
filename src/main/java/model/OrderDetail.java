package model;

public class OrderDetail {
    private Product product;
    private Order order;
    private int quantity;
    private float price;

    public OrderDetail() {
    }

    public OrderDetail(Product product, Order order, int quantity, float price) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "product=" + product + ", order=" + order + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
}