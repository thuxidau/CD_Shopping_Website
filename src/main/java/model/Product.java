package model;

public class Product {
    private int id;
    private String name;
    private float price;
    private int quantity;
    private String description, image;
    private boolean status;
    private Category category;
    private Country country;
    private int qtysold;

    public Product() {
    }

    public Product(int id, String name, float price, int quantity, String description, String image, boolean status, Category category, Country country, int qtysold) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.image = image;
        this.status = status;
        this.category = category;
        this.country = country;
        this.qtysold = qtysold;
    }

    public int getQtysold() {
        return qtysold;
    }

    public void setQtysold(int qtysold) {
        this.qtysold = qtysold;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + ", description=" + description + ", image=" + image + ", status=" + status + ", category=" + category + ", country=" + country + '}';
    }
    
}