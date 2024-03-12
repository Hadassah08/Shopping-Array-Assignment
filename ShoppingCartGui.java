import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingCartGui extends JFrame implements ActionListener {

    private ShoppingCart cart;
    private JTextArea cartitems = new JTextArea(10, 30);
    private JLabel totalPriceLabel = new JLabel("Total: $0.00");
    private JButton addItemButton = new JButton("Add Item");
    private JButton removeItemButton = new JButton("Remove Item");

    private JComboBox<Item> shoppinglist; //dropdown for items
    private JTextField quantityField;

    public ShoppingCartGui() {
        //gui window settings
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cart = new ShoppingCart();

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        //list of items
        Item item1 = new Item("Tissue", 2.50);
        Item item2 = new Item("Water", 3.20);
        Item item3 = new Item("Soap", 5);
        Item item4 = new Item("Hand Sanitizer", 7);

        Item[] items = {item1, item2, item3, item4};

        //putting the items in a combo box
        shoppinglist = new JComboBox<>(items);
        buttons.add(shoppinglist);

        //setting the quantity box field
        quantityField = new JTextField(5); // 5 columns wide
        quantityField.setText("1"); // Default to 1
        buttons.add(quantityField);

        addItemButton.addActionListener(this);
        addItemButton.setBackground(Color.GREEN);
        addItemButton.setForeground(Color.GREEN);
        buttons.add(addItemButton);

        removeItemButton.addActionListener(this);
        removeItemButton.setBackground(Color.RED);
        removeItemButton.setForeground(Color.GREEN);
        buttons.add(removeItemButton);

        //ensures text area can't be manipulated
        cartitems.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartitems);

        // Panel for Total
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.add(totalPriceLabel, BorderLayout.EAST);

        JScrollPane cartScrollPane = new JScrollPane(cartitems);
        cartScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(buttons, BorderLayout.NORTH);
        add(cartScrollPane, BorderLayout.CENTER);
        add(totalPanel, BorderLayout.SOUTH);

        updateCart(); //update cart display

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addItemButton) {
            Item selectedItem = (Item) shoppinglist.getSelectedItem();
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                if (quantity <= 0) {
                    throw new NumberFormatException();
                }
                if (selectedItem != null) {
                    cart.addItemOrder(new ItemOrder(selectedItem, quantity));
                    updateCart();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid quantity (greater than 0).", "Invalid Quantity", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == removeItemButton) {
            Item selectedItem = (Item) shoppinglist.getSelectedItem();
            if (selectedItem != null) {
                ItemOrder itemToRemove = cart.searchItemOrder(selectedItem.getName());
                if (itemToRemove != null) {
                    cart.removeItemOrder(itemToRemove);
                    updateCart();
                } else {
                    JOptionPane.showMessageDialog(this, "Item not found in cart.", "Item Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void updateCart() {
        cartitems.setText(cart.toString());
        totalPriceLabel.setText("Total: $" + String.format("%.2f", cart.getTotalPrice()));
    }

    public static void main(String[] args) {
            new ShoppingCartGui();
    }
}
