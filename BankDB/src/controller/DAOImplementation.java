/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Customer;
import model.CustomerAccount;
import model.Movement;
import java.sql.ResultSet;
import model.AccountType;

/**
 *
 * @author 2dam
 */
public class DAOImplementation implements DAO{

    //Atributes
    private Connection con;
    private PreparedStatement stmt;
    private ConnectionOpenClose connection = new ConnectionOpenClose();
    

    //Sentences SQL
    final String ADDCUSTOMER = "INSERT INTO CUSTOMER (id, city, email, firstName, lastName, middleIntial, phone, state, street, zip) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String CONSULTCUSTOMER = "SELECT * FROM CUSTOMER WHERE id = ?";
    final String CONSULTCUSTOMERACCOUNT = "SELECT * FROM ACCOUNT WHERE id IN (SELECT accounts_id FROM CUSTOMER_ACCOUNT WHERE customers_id = ?)";
    final String CREATEACCOUNT = "INSERT INTO ACCOUNT (id, balance, beginBalance, beginBalanceTimestamp, creditLine, description, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String INSERTACCOUNTCUSTOMER = "INSERT INTO CUSTOMER_ACCOUNT (customers_id, accounts_id) VALUES (?, ?)";
    final String CONSULTACCOUNT = "SELECT * FROM ACCOUNT WHERE id = ?";
    final String MAKEMOVEMENT = "INSERT INTO MOVEMENT (id, amount, balance, description, timestamp, account_id) VALUES (?, ?, ?, ?, ?, ?)";
    final String CONSULTMOVEMENTS = "SELECT * FROM MOVEMENT WHERE account_id = ?";
    
    
    @Override
    public void createCustomer(Customer customer) {
        try {
            con = connection.openConnection();
            
            stmt = con.prepareStatement(ADDCUSTOMER);
            stmt.setLong(1, customer.getId());
            stmt.setString(2, customer.getCity());
            stmt.setString(3, customer.getEmail());
            stmt.setString(4, customer.getFirstName());
            stmt.setString(5, customer.getLastName());
            stmt.setString(6, customer.getMiddleIntial());
            stmt.setInt(7, customer.getPhone());
            stmt.setString(8, customer.getState());
            stmt.setString(9, customer.getStreet());
            stmt.setInt(10, customer.getZip());
            
            stmt.executeUpdate();
            
            connection.closeConnection(stmt, con);
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    

    @Override
    public void createAccount(CustomerAccount customerAccount) {
        
    }

    

    @Override
    public ArrayList<Movement> consultMovements(long idAccount) {
        return null;
        
    }

   


    @Override
    public ArrayList<Account> counsultCustomerAccounts(long idCustomer) {
       
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            // final String CONSULTCUSTOMERACCOUNT = "SELECT * FROM ACCOUNT WHERE id IN (SELECT accounts_id FROM CUSTOMER_ACCOUNT WHERE customers_id = ?)";
           
            ResultSet rs = null;
            con =connection.openConnection();
            
            stmt = con.prepareStatement(CONSULTCUSTOMERACCOUNT);
            stmt.setLong(1, idCustomer);
                     
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Account account = new Account();
                account.setId(rs.getLong("id"));
                account.setDescription(rs.getString("description"));
                account.setBalance(rs.getFloat("balance"));
                account.setBeginBalance(rs.getFloat("beginBalance"));
                account.setCreditLine(rs.getFloat("creditLine"));
                account.setBeginBalanceTimestamp(rs.getTimestamp("beginBalanceTimestamp").toLocalDateTime());
                int auxType = rs.getInt("type");
                account.setType(AccountType.values()[auxType]);
                accounts.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return accounts;
    }

    @Override
    public Customer consultCustomer(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCustomerToAccount(long id, Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account consultAccount(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeMovement(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
