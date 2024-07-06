package entity;

import java.text.DecimalFormat;
import repository1.OrderRepository;

public class Items {
    private Product product;
    private int amount;
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    private boolean selected;
    private String OrderId;
    private boolean commentExists;
    private String brandName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    
    
    
    public boolean isCommentExists() {
        return commentExists;
    }

    public void setCommentExists(boolean commentExists) {
        this.commentExists = commentExists;
    }

    public String getOrderID() {
        return OrderId;
    }

    public void setOrderID(String OrderId) {
        this.OrderId = OrderId;
    }

    public Items() {
    }

    public Items(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public double getPrice() {
        return product.getProductPrice() * amount;
    }

    public double getPriceAfterPurchase(String OrderId) {
        return OrderRepository.getPriceOrdered(OrderId, product.getProductId());
    }

    public String getPriceString() {
        return formatter.format(getPrice());
    }

    public boolean equals(Items item) {
        return this.product.productId.compareToIgnoreCase(item.product.productId) == 0;
    }

    @Override
    public String toString() {
        return "Items{" +
                "product=" + product +
                ", amount=" + amount +
                ", formatter=" + formatter +
                '}';
    }
}
