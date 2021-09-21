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
            stmt.setInt(1, customer.getId());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public ArrayList<Account> counsultCustomerAccounts(Integer idCustomer) {
        return null;
        
    }

    @Override
    public void createAccount(CustomerAccount customerAccount) {
        
    }

    @Override
    public void addCustomerToAccount(Integer idAccount, Customer customer) {
        
    }

    @Override
    public Account consultAccount(Integer idAccount) {
        return null;
       
    }

    @Override
    public void makeMovement(Integer idAccount) {
        
    }

    @Override
    public ArrayList<Movement> consultMovements(Integer idAccount) {
        return null;
        
    }

    @Override
    public Customer consultCustomer(Integer idCustomer) {
        return null;
        
    }
}
