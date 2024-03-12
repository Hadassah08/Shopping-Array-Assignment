import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCart {
    ArrayList<ItemOrder> cart = new ArrayList<ItemOrder>();


    public double getTotalPrice() {
        double totalPrice = 0;
        for (ItemOrder itemOrder : cart) {
            totalPrice += itemOrder.getTotalPrice();
        }
        return totalPrice;
    }

    public void addItemOrder(ItemOrder item) {
        cart.add(item);
    }

    public void removeItemOrder(ItemOrder item){
        cart.remove(item);
    }

    public ItemOrder searchItemOrder(String item){
        for(ItemOrder i : this.cart){
            if(i.getItem().getName().equals(item)) return i;
        }
        return new ItemOrder();
    }


    //toString is used to format the information
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ItemOrder itemOrder : cart) {
            sb.append(itemOrder.toString()).append("\n");
        }
        sb.append("Total Price: $").append(String.format("%.2f", getTotalPrice()));
        return sb.toString();
    }
}