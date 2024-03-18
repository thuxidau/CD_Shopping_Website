package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    private Item getItemById(int id){
        for (Item item : items) {
            if(item.getProduct().getId() == id){
                return item;
            }
        }
        return null;
    }
    
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    
    public void addItem(Item item){
//        already added to cart
        if(getItemById(item.getProduct().getId()) != null){
            Item i = getItemById(item.getProduct().getId());
            i.setQuantity(i.getQuantity() + item.getQuantity());
        } 
//        not added to cart yet
        else {
            items.add(item);
        }
    }
    
    public void removeItem(int id){
        if(getItemById(id) != null){
            items.remove(getItemById(id));
        }
    }
    
    public float getTotalMoney(){
        float t = 0;
        for (Item item : items) {
            t += (item.getQuantity() * item.getPrice());
        }
        return t;
    }
}