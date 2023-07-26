package cs.hku.wetrade;

import android.graphics.Bitmap;

public class HomeItem {
    private int id, stock;
    private float price;
    private String itemname, category, seller;
    private Bitmap itemimage;

    public HomeItem(int id, String itemname, Bitmap itemimage, int stock, float price, String category, String seller){
        this.id = id;
        this.itemname = itemname;
        this.itemimage = itemimage;
        this.stock = stock;
        this.price = price;
        this.category = category;
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return itemname;
    }

    public Bitmap getImage() {
        return itemimage;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getSeller() {
        return seller;
    }

}
