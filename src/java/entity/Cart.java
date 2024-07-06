package entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import entity.Preferential;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static repository1.CartRepository.removeCartItemFromDatabase;
import static repository1.CartRepository.updateCartItemQuantity;
import repository1.OrderRepository;
import repository1.ProductRepository;
public class Cart {
    private String userId;
    private String discountCode; 
    private double discountPercent =0f;
    private List<Items> cart;
    private List<Items> selectedItems;  
    DecimalFormat formatter = new DecimalFormat("#,###,###");
    private String orderedId;
    private String orderStatus;
    private String date;
    private int paymentType = 0; //0 la COD , 1 la CK
    private Map<String, List<Items>> orderItemsMap; 
    
    private boolean canCancel;
     public boolean isCanCancel() {
        return canCancel;
    }

    public void setCanCancel(boolean canCancel) {
        this.canCancel = canCancel;
    }
     public List<Items> getItems() {
        return cart;
    }

    public void setItems(List<Items> items) {
        this.cart = items;
    }

    
    private Map<String, Double> orderTotalPriceMap; // Map lưu trữ tổng giá trị của từng đơn hàng

    public void removeAll(){
        cart.clear();
    }
    public String getOrderedId() {
        return orderedId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    

    public void setOrderedId(String orderedId) {
        this.orderedId = orderedId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    public void setSelectedItems(List<Items> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public List<Items> getSelectedItems() {
        return selectedItems;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setOrderItemsMap(Map<String, List<Items>> orderItemsMap) {
        this.orderItemsMap = orderItemsMap;
    }
    public Map<String, List<Items>> getOrderItemsMap() {
    return orderItemsMap;
}

    public Cart() {
    cart = new ArrayList<>();
    selectedItems = new ArrayList<>();
    orderTotalPriceMap = new HashMap<>();
}
    public void addOrderItems(String orderId, List<Items> items) {
        if (orderItemsMap == null) {
            orderItemsMap = new HashMap<>(); // Khởi tạo lại nếu bị null
        }
        orderItemsMap.put(orderId, items);
    }
    
    public void setCart(List<Items> cart) {
        this.cart.addAll(cart);
    }
   

    public String addItems(Items item) {
  
            cart.add(item);
            return "=========>CART : add Thanh Cong<==========";
        
    }
    public double getThanhTien() {
        double tong = 0;
        for (Items item : cart) {
            tong += item.getPrice();
        }
        return tong ;
    }  
    public double getThanhTienByShip() {
        double tong = 0;
        for (Items item : cart) {
            tong += item.getPrice();
        }
        return tong + getThanhTienShip();
    } 
    
    public double getThanhTienShip() {
    double tong = 0;
    Set<String> ctvIds = new HashSet<>();
    
    for (Items item : cart) {
        Product product = item.getProduct();
        if (product != null && product.getCTVID() != null && !product.getCTVID().isEmpty()) {
            if (!ctvIds.contains(product.getCTVID())) {
                ctvIds.add(product.getCTVID());
                tong += 30000;
            }
        }
    }
    return tong;
    }
    
    public double getThanhTienDiscount() {
        double tong = 0;
        for (Items item : cart) {
            tong += item.getPrice();
        }
        return (tong - (tong  * discountPercent) + getThanhTienShip());
    } 
    
    public String getThanhTienDiscountString() {
        if (cart.isEmpty()) {
            return "0";
        }
        return formatter.format(getThanhTienDiscount());

    }
    public double tongGiamGia() {
        double tong = 0;
        for (Items item : cart) {
            tong += item.getPrice();
        }
        return (tong  * discountPercent) ;
    }  
    public String tongGiamGiaString() {
        if (cart.isEmpty()) {
            return "0";
        }
        return formatter.format(tongGiamGia());

    }
//    public double getThanhTienAfterPurchase(double phatsinh) {
//        double tong = 0;
//        for (Items item : cart) {
//            tong += item.getPriceAfterPurchase(orderedId);
//        }
//        return tong + phatsinh;
//    }
//    public double getThanhTienAfterPurchaseDiscount(double phatsinh) {
//        double tong = 0;
//        for (Items item : cart) {
//            tong += item.getPriceAfterPurchase(orderedId);
//        }
//         Preferential pr = new Preferential();
//        double discountPercent = OrderRepository.getDiscountPercent(pr.get());
//        return (tong + phatsinh) - (tong*discountPercent);
//    }
    public String getThanhTienString() {
        if (cart.isEmpty()) {
            return "0";
        }
        return formatter.format(getThanhTien());

    }
    public String getThanhTienByShipString() {
        if (cart.isEmpty()) {
            return "0";
        }
        return formatter.format(getThanhTienByShip());

    }
    public String getThanhTienShipString() {
        if (cart.isEmpty()) {
            return "0";
        }
        return formatter.format(getThanhTienShip());

    }
    
        public void setOrderItems(String orderId, List<Items> items) {
            orderItemsMap.put(orderId, items);
        }

       public List<Items> getOrderItems(String orderId) {
        if (orderItemsMap == null || !orderItemsMap.containsKey(orderId)) {
            return new ArrayList<>(); // Trả về danh sách rỗng hoặc xử lý phù hợp
        }
        return orderItemsMap.get(orderId);
    }

  public double calculateTotalPrice(String orderId, double additionalFees) {
    if (orderItemsMap != null && orderItemsMap.containsKey(orderId)) {
        List<Items> items = orderItemsMap.get(orderId);
        if (items != null && !items.isEmpty()) {
            double totalPrice = 0.0;
            for (Items item : items) {
                double itemPrice = item.getPriceAfterPurchase(orderId);
                System.out.println("Item price for item " + item.getOrderID()+ ": " + itemPrice);
                totalPrice += itemPrice;
            }
            System.out.println("Total price before additional fees: " + totalPrice);
            totalPrice += additionalFees;
            System.out.println("Total price after additional fees: " + totalPrice);
            return totalPrice;
        } else {
            System.out.println("Danh sách sản phẩm trống hoặc null");
        }
    } else {
        System.out.println("orderItemsMap là null hoặc không chứa orderId");
    }
    return 0.0;
}


    

    public String calculateTotalPriceString(String orderId, double additionalFees) {
        double totalPrice = calculateTotalPrice(orderId, additionalFees);
        return formatter.format(totalPrice);
    }

    public String calculateTotalPriceStringWithDiscount(String orderId, double shippingFee) {
        double totalPrice = calculateTotalPrice(orderId, shippingFee);
        double discount = totalPrice * discountPercent;
        return formatter.format(totalPrice - discount);
    }

    

    public List<Items> getCart() {
        return cart;
    }

    //    public void setCart(List<Items> cart) {
//        this.cart = cart;
//    }
        public String increaseAmount(String id) {
        if (cart.isEmpty()) {
            return "=========>Không tồn tại sản phẩm increaseAmount(String id)<==========";
        } else {
            for (Items item : cart) {
                if (item.getProduct().getProductId().equals(id)) {
                    if (item.getAmount()>= item.getProduct().getProductAmount()) {
                        return "=========>CART: Số lượng sản phẩm đã đạt mức tối đa<==========";
                    }
                    item.setAmount(item.getAmount() + 1);
                    updateCartItemQuantity(userId, id, item.getAmount()); // Update the database
                    return "=========>CART: Tăng số lượng thành công<==========";
                }
            }
            return "=========>CART: Sản phẩm không tồn tại trong giỏ hàng<==========";
        }
    }

    public String decreaseAmount(String id) {
        if (cart.isEmpty()) {
            return "=========>Không tồn tại sản phẩm decreaseAmount(String id)<==========";
        } else {
            for (Items item : cart) {
                if (item.getProduct().getProductId().equals(id)) {
                    if (item.getAmount() <= 1) {
                        return "=========>CART: Không thể giảm, số lượng tối thiểu là 1<==========";
                    }
                    item.setAmount(item.getAmount() - 1);
                    updateCartItemQuantity(userId, id, item.getAmount()); // Update the database
                    return "=========>CART: Giảm số lượng thành công<==========";
                }
            }
            return "=========>CART: Sản phẩm không tồn tại trong giỏ hàng<==========";
        }
    }

    public String removeItem(String id) {
        if (cart.isEmpty()) {
            return "=========>Khong ton tai san pham decreaseAmmount(String id) <==========";
        } else {
            for (Items items : cart) {
                if (items.getProduct().getProductId().equals(id)) {
                    cart.remove(cart.indexOf(items));
                    removeCartItemFromDatabase(userId, id);
                    return "=========>CART : remove Thanh Cong<==========";
                }
            }
            return "=========>CART : remove Thanh Cong removeItem(String id)<==========";
        }
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
    public void removeDiscountCode() {
        this.discountCode = null;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public DecimalFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(DecimalFormat formatter) {
        this.formatter = formatter;
    }

    public Map<String, List<Items>> splitByCTV() {
        Map<String, List<Items>> ctvProducts = new HashMap<>();
        for (Items item : cart) {
            String ctvId = item.getProduct().getCTVID();
            if (!ctvProducts.containsKey(ctvId)) {
                ctvProducts.put(ctvId, new ArrayList<>());
            }
            ctvProducts.get(ctvId).add(item);
        }
        return ctvProducts;
    }
    
    public static void main(String[] args) {
        // Tạo một đối tượng Cart và orderItemsMap giả định
        Cart cart = new Cart();
        Map<String, List<Items>> orderItemsMap = new HashMap<>();
        
        Product p1 = ProductRepository.getProductById("P4143");
        Product p2 = ProductRepository.getProductById("P9620");
        // Tạo một danh sách các mục đơn hàng cho orderId = "1"
        List<Items> itemsList = new ArrayList<>();
        itemsList.add(new Items(p1, 1));
        itemsList.add(new Items(p2, 1));
        orderItemsMap.put("3b7H7lUfPT", itemsList);

        // Gán orderItemsMap cho đối tượng Cart
        cart.setOrderItemsMap(orderItemsMap);

        // Gọi phương thức calculateTotalPrice và in kết quả
        double totalPrice = cart.calculateTotalPrice("3b7H7lUfPT", 30);
        System.out.println("Total price for order 1: " + totalPrice);
        
        
       
    }

    
   

}
