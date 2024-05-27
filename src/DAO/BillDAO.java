package DAO;

import DTO.Bill;
import DTO.DrinkItem;
import DTO.Invoices;
import Utilities.DBUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author sanh6
 */
public class BillDAO {

    private static BillDAO instance;

    public BillDAO() {
    }

    public static BillDAO getInstance() {
        if (instance == null) {
            instance = new BillDAO();
        }
        return instance;
    }

//    public List<Bill> GetListInvoicesByStatus(int id) {
//        List<Bill> list = new ArrayList<Bill>();
//        Connection con = DBUtility.openConnection();
//        try {
//            PreparedStatement pstmt = con.prepareStatement("SELECT `invoice`.`ID`, `account`.`name`, `tables`.`table_name`, `drinks`.`name`, `invoice`.`invoice_date`, `orders`.`count`, `drinks`.`price`, `invoice`.`total_price` FROM `invoice`, `tables`, `account`, `drinks`, `orders`  WHERE `invoice`.`tables_id` = `tables`.`ID` AND `invoice`.`account_ID` = `account`.`ID`AND `invoice`.`ID` = `orders`.`invoice_ID`AND `orders`.`drinks_ID` = `drinks`.`ID` AND `invoice`.`ID` = ?");
//        pstmt.setInt(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                int billId = rs.getInt(1);
//                String sellerName = rs.getString(2);
//                String tableName = rs.getString(3);
//                Timestamp invoiceDate = rs.getTimestamp(5);
//                List<DrinkItem> drinkItems = new ArrayList<>();
//                DrinkItem drinkItem = new DrinkItem(rs.getString(4), rs.getInt(6), rs.getDouble(7), rs.getDouble(8));
//                drinkItems.add(drinkItem);
//                Bill bill = new Bill(billid, sellerName, tableName, invoiceDate, drinkItems));
//                list.add(bill);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    
    public List<Bill> getListInvoicesByStatus(int id) {
        List<Bill> list = new ArrayList<>();
        Connection con = DBUtility.openConnection();

        if (con == null) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, "Connection to database failed");
            return list;
        }

        String query = "SELECT `invoice`.`ID`, `account`.`name`, `tables`.`table_name`, `invoice`.`invoice_date`, " +
                       "`orders`.`count`, `drinks`.`name`, `drinks`.`price`, `invoice`.`total_price` " +
                       "FROM `invoice` " +
                       "JOIN `tables` ON `invoice`.`tables_id` = `tables`.`ID` " +
                       "JOIN `account` ON `invoice`.`account_ID` = `account`.`ID` " +
                       "JOIN `orders` ON `invoice`.`ID` = `orders`.`invoice_ID` " +
                       "JOIN `drinks` ON `orders`.`drinks_ID` = `drinks`.`ID` " +
                       "WHERE `invoice`.`ID` = ?";

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            Map<Integer, Bill> billMap = new HashMap<>();
            while (rs.next()) {
                int billId = rs.getInt("ID");
                String sellerName = rs.getString("name");
                String tableName = rs.getString("table_name");
                Timestamp invoiceDate = rs.getTimestamp("invoice_date");
                String drinkName = rs.getString(6);
                int count = rs.getInt("count");
                double price = rs.getDouble("price");
                double totalPrice = rs.getDouble("total_price");

                DrinkItem drinkItem = new DrinkItem(drinkName, count, price);

                if (billMap.containsKey(billId)) {
                    billMap.get(billId).getDrinkItems().add(drinkItem);
                } else {
                    List<DrinkItem> drinkItems = new ArrayList<>();
                    drinkItems.add(drinkItem);
                    Bill bill = new Bill(billId, sellerName, tableName, invoiceDate, totalPrice, drinkItems);
                    billMap.put(billId, bill);
                }
            }

            list.addAll(billMap.values());
        } catch (SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return list;
    }
}
