package dev.com.jongewaard.mundo_fruta.models;

/**
 * Created by german on 20-11-17.
 */

public class Fruit {

    private String name;
    private String descripcion;
    private int imgBackground;
    private int imgIcon;
    private int quantity;

    //Valores accesibles estaticamente.
    public static final int LIMIT_QUANTITY = 10;
    public static final int RESET_VALUE_QUANTITY = 0;

    public Fruit(){
    }

    public Fruit(String name, String descripcion, int imgBackground, int imgIcon, int quantity){

        this.name = name;
        this.descripcion = descripcion;
        this.imgBackground = imgBackground;
        this.imgIcon = imgIcon;
        this.quantity = quantity;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getImgBackground() { return imgBackground; }

    public void setImgBackground(int imgBackground) { this.imgBackground = imgBackground; }

    public int getImgIcon() { return imgIcon; }

    public void setImgIcon(int imgIcon) { this.imgIcon = imgIcon; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public static int getLimitQuantity() { return LIMIT_QUANTITY; }

    public static int getResetValueQuantity() { return RESET_VALUE_QUANTITY; }

    //Añadir cantidad
    public void addQuantity(int quantity){

        if(this.quantity < LIMIT_QUANTITY)  this.quantity += quantity;
    }

    //Reset cantidad
    public void resetQuantity(){ this.quantity = RESET_VALUE_QUANTITY; }
}
