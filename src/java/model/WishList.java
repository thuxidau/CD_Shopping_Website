package model;

import java.util.ArrayList;
import java.util.List;

public class WishList {
    private List<Item> items;

    public WishList() {
        items = new ArrayList<>();
    }

    public WishList(List<Item> items) {
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
    public void addItem(Item item){
        if(getItemById(item.getProduct().getId()) == null){
            items.add(item);
        }
    }
    public void removeItem(int id){
        if(getItemById(id) != null){
            items.remove(getItemById(id));
        }
    }
}
