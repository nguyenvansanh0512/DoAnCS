/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author sanh6
 */
public class Bill {
//    private int id;
//    private String name;
//    private String table;
//    private String drinkName;
//    private Timestamp day;
//    private int quantity;
//    private double price;
//    private double total;
//
//    public Bill(int id, String name, String table, String drinkName, Timestamp day, int quantity, double price, double total) {
//        this.id = id;
//        this.name = name;
//        this.table = table;
//        this.drinkName = drinkName;
//        this.day = day;
//        this.quantity = quantity;
//        this.price = price;
//        this.total = total;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getTable() {
//        return table;
//    }
//
//    public void setTable(String table) {
//        this.table = table;
//    }
//
//    public String getDrinkName() {
//        return drinkName;
//    }
//
//    public void setDrinkName(String drinkName) {
//        this.drinkName = drinkName;
//    }
//
//    public Timestamp getDay() {
//        return day;
//    }
//
//    public void setDay(Timestamp day) {
//        this.day = day;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public double getTotal() {
//        return total;
//    }
//
//    public void setTotal(double total) {
//        this.total = total;
//    }
private int id;
    private String name;
    private String table;
    private Timestamp day;
    private double total;
    private List<DrinkItem> drinkItems;

    public Bill(int id, String name, String table, Timestamp day, double total, List<DrinkItem> drinkItems) {
        this.id = id;
        this.name = name;
        this.table = table;
        this.day = day;
        this.total = total;
        this.drinkItems = drinkItems;
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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Timestamp getDay() {
        return day;
    }

    public void setDay(Timestamp day) {
        this.day = day;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<DrinkItem> getDrinkItems() {
        return drinkItems;
    }

    public void setDrinkItems(List<DrinkItem> drinkItems) {
        this.drinkItems = drinkItems;
    }

    
}
