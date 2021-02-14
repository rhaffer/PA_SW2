package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    private final ObservableList<Customer> customers = FXCollections.observableArrayList();

    //Retrieve Customer from DB by Customer_Name
    public Customer get(Connection conn, String searchCustomer) throws SQLException {
        String selectStatement = "SELECT * FROM customers WHERE Customer_Name = ?";
        DBQuery.setPreparedStatement(conn, selectStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, searchCustomer);

        try {
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()) {
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                String createDate = rs.getString("Create_Date");
                String createdBy = rs.getString("Created_By");
                String lastUpdate = rs.getTimestamp("Last_Update").toString();
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionID = rs.getInt("Division_ID");
                return new Customer(customerID, customerName, address, postalCode, phone, createDate, createdBy,
                        lastUpdate, lastUpdatedBy, divisionID);
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex.getMessage());
            return null;
        }
    }

    //Gets all Customers from DB
    public ObservableList<Customer> getAll(Connection conn) throws SQLException {
        String selectAllStatement = "SELECT * FROM customers";
        DBQuery.setPreparedStatement(conn, selectAllStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.execute();
        ResultSet rs = statement.getResultSet();
        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getTimestamp("Last_Update").toString();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int divisionID = rs.getInt("Division_ID");
            customers.add(new Customer(customerID, customerName, address, postalCode, phone, createDate, createdBy,
                    lastUpdate, lastUpdatedBy, divisionID));
        }
        return customers;
    }

    public Boolean save(Connection conn, Customer customer) throws SQLException {
        String insertStatement = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, " +
                "Created_By, Last_Update, Last_Updated_By, Division_ID) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        DBQuery.setPreparedStatement(conn, insertStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setString(1, customer.getCustomerName());
        statement.setString(2, customer.getAddress());
        statement.setString(3, customer.getPostalCode());
        statement.setString(4, customer.getPhoneNumber());
        statement.setString(5, customer.getCreateDate());
        statement.setString(6, customer.getCreatedBy());
        statement.setString(7, customer.getLastUpdate());
        statement.setString(8, customer.getLastUpdatedBy());
        statement.setInt(9, customer.getDivisionID());
        try {
            statement.execute();
            return true;
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.out.println("Duplicate Key Error.");
            }
            return false;
        }
    }

    // TODO Make update functionality
    public void update(Customer customer, String[] params) {
        customers.add(customer);
    }

    public Boolean delete(Connection conn, int customerID) throws SQLException {
        String delStatement = "DELETE FROM customers WHERE Customer_ID = ?";
        DBQuery.setPreparedStatement(conn, delStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, customerID);
        try{
            statement.execute();
            return true;
        }catch (SQLException ex){
            System.out.println("SQL Exception: " + ex.getMessage());
            return false;
        }
    }

    public ObservableList<String> getCustomerDivisionCountry(Connection conn, Customer customer) throws SQLException {
        ObservableList<String> customerInfo = FXCollections.observableArrayList();
        String sqlStatement = "SELECT customers.Customer_ID, customers.Customer_Name, customers.Address, " +
                "customers.Postal_Code, customers.Phone, first_level_divisions.Division, countries.Country " +
                "FROM first_level_divisions " +
                "INNER JOIN customers ON (customers.Division_ID=first_level_divisions.Division_ID) " +
                "INNER JOIN countries ON (countries.Country_ID=first_level_divisions.COUNTRY_ID) " +
                "WHERE customers.Customer_ID = ?";
        DBQuery.setPreparedStatement(conn, sqlStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, customer.getCustomerID());
        try{
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if(rs.next()){
                String customerID = String.valueOf(rs.getInt("Customer_ID"));
                String customerName = rs.getString("Customer_Name");
                String customerAddress = rs.getString("Address");
                String customerPostalCode = rs.getString("Postal_Code");
                String customerPhone = rs.getString("Phone");
                String divison = rs.getString("Division");
                String country = rs.getString("Country");
                customerInfo.addAll(customerID, customerName, customerAddress, customerPostalCode, customerPhone, divison, country);
            }
        }
        catch (SQLException ex){
            System.out.println("SQL Exception: " + ex.getMessage());

        }
        return customerInfo;
    }
}