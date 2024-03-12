import java.util.ArrayList;
public class ItemOrder {
    Item item;
    int quantity;

    public ItemOrder(){}
    public ItemOrder(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    //toString is used to format the information
    public String toString() {
        return item.getName() + " - Quantity: " + quantity + " - Total: $" + getTotalPrice();
    }
}
