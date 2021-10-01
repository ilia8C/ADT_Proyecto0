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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import model.AccountType;

/**
 *
 * @author 2dam
 */
public class DAOImplementation implements DAO {

    //Atributes
    private Connection con;
    private PreparedStatement stmt;
    private ConnectionOpenClose connection = new ConnectionOpenClose();

    //Sentences SQL
    final String ADDCUSTOMER = "INSERT INTO CUSTOMER (id, city, email, firstName, lastName, middleInitial, phone, state, street, zip) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    final String CONSULTCUSTOMER = "SELECT * FROM CUSTOMER WHERE id = ?";
    final String CONSULTCUSTOMERACCOUNT = "SELECT * FROM ACCOUNT WHERE id IN (SELECT accounts_id FROM CUSTOMER_ACCOUNT WHERE customers_id = ?)";
    final String CREATEACCOUNT = "INSERT INTO ACCOUNT (id, balance, beginBalance, beginBalanceTimestamp, creditLine, description, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
    final String INSERTACCOUNTCUSTOMER = "INSERT INTO CUSTOMER_ACCOUNT (customers_id, accounts_id) VALUES (?, ?)";
    final String CONSULTACCOUNT = "SELECT * FROM ACCOUNT WHERE id = ?";
    final String MAKEMOVEMENT = "INSERT INTO MOVEMENT (id, amount, balance, description, timestamp, account_id) VALUES (null, ?, ?, ?, ?, ?)";
    final String CONSULTMOVEMENTS = "SELECT * FROM MOVEMENT WHERE account_id = ?";

    @Override
    public void createCustomer(Customer customer) throws Exception {
        con = connection.openConnection();

        stmt = con.prepareStatement(ADDCUSTOMER);
        stmt.setLong(1, customer.getId());
        stmt.setString(2, customer.getCity());
        stmt.setString(3, customer.getEmail());
        stmt.setString(4, customer.getFirstName());
        stmt.setString(5, customer.getLastName());
        stmt.setString(6, customer.getMiddleInitial());
        stmt.setLong(7, customer.getPhone());
        stmt.setString(8, customer.getState());
        stmt.setString(9, customer.getStreet());
        stmt.setInt(10, customer.getZip());

        stmt.executeUpdate();

        connection.closeConnection(stmt, con);

    }

    @Override
    public void createAccount(Customer customer, Account account) throws Exception {

        con = connection.openConnection();
        stmt = con.prepareStatement(CREATEACCOUNT);
        stmt.setLong(1, account.getId());
        stmt.setDouble(2, account.getBalance());
        stmt.setDouble(3, account.getBeginBalance());
        stmt.setTimestamp(4, Timestamp.valueOf(account.getBeginBalanceTimestamp()));
        stmt.setDouble(5, account.getCreditLine());
        stmt.setString(6, account.getDescription());
        stmt.setInt(7, account.getType().ordinal());

        stmt.executeUpdate();

        stmt = con.prepareStatement(INSERTACCOUNTCUSTOMER);

        stmt.setLong(1, customer.getId());
        stmt.setLong(2, account.getId());
        stmt.executeUpdate();

        connection.closeConnection(stmt, con);

    }

    @Override
    public ArrayList<Movement> consultMovements(long idAccount) throws Exception {
        ArrayList<Movement> movements = new ArrayList<>();

        ResultSet rs = null;
        con = connection.openConnection();

        stmt = con.prepareStatement(CONSULTMOVEMENTS);
        stmt.setLong(1, idAccount);

        rs = stmt.executeQuery();

        while (rs.next()) {
            Movement movement = new Movement();
            movement.setId(rs.getLong("id"));
            movement.setAmount(rs.getDouble("amount"));
            movement.setBalance(rs.getDouble("balance"));
            movement.setDescription(rs.getString("description"));
            movement.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime());
            movement.setAccount_id(rs.getLong("account_id"));
            movements.add(movement);
            
        }
        rs.close();
        connection.closeConnection(stmt, con);
        return movements;

    }

    @Override
    public ArrayList<Account> consultCustomerAccounts(long idCustomer) throws Exception {

        ArrayList<Account> accounts = new ArrayList<>();

        ResultSet rs = null;
        con = connection.openConnection();

        stmt = con.prepareStatement(CONSULTCUSTOMERACCOUNT);
        stmt.setLong(1, idCustomer);

        rs = stmt.executeQuery();

        while (rs.next()) {
            Account account = new Account();
            account.setId(rs.getLong("id"));
            account.setBalance(rs.getFloat("balance"));
            account.setBeginBalance(rs.getFloat("beginBalance"));
            account.setBeginBalanceTimestamp(rs.getTimestamp("beginBalanceTimestamp").toLocalDateTime());
            account.setCreditLine(rs.getFloat("creditLine"));
            account.setDescription(rs.getString("description"));
            int auxType = rs.getInt("type");
            account.setType(AccountType.values()[auxType]);
            accounts.add(account);

        }
        rs.close();
        connection.closeConnection(stmt, con);
        return accounts;
    }

    @Override
    public Customer consultCustomer(long id) throws Exception {
        Customer customer = null;
        try {
            ResultSet rs = null;
            con = connection.openConnection();
            stmt = con.prepareStatement(CONSULTCUSTOMER);
            stmt.setLong(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                customer = new Customer();
                customer.setId(id);
                customer.setCity(rs.getString("city"));
                customer.setEmail(rs.getString("email"));
                customer.setFirstName(rs.getString("firstName"));
                customer.setLastName(rs.getString("lastName"));
                customer.setMiddleInitial(rs.getString("middleInitial"));
                customer.setPhone(rs.getLong("phone"));
                customer.setState(rs.getString("state"));
                customer.setStreet(rs.getString("street"));
                customer.setZip(rs.getInt("zip"));

            }
            rs.close();
            connection.closeConnection(stmt, con);
        } catch (SQLException ex) {
            Logger.getLogger(DAOImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    @Override
    public void addCustomerToAccount(long account_id, long customer_id) throws Exception {

        con = connection.openConnection();
        stmt = con.prepareStatement(INSERTACCOUNTCUSTOMER);
        stmt.setLong(1, customer_id);
        stmt.setLong(2, account_id);

        stmt.executeUpdate();
        connection.closeConnection(stmt, con);

    }

    @Override
    public Account consultAccount(long id) throws Exception {
        Account account = null;
        //final String CONSULTACCOUNT = "SELECT * FROM ACCOUNT WHERE id = ?";
        ResultSet rs = null;
        con = connection.openConnection();
        stmt = con.prepareStatement(CONSULTACCOUNT);
        stmt.setLong(1, id);

        rs = stmt.executeQuery();
        while (rs.next()) {
            account = new Account();
            account.setId(id);
            account.setBalance(rs.getDouble("balance"));
            account.setBeginBalance(rs.getDouble("beginBalance"));
            account.setBeginBalanceTimestamp(rs.getTimestamp("beginBalanceTimestamp").toLocalDateTime());
            account.setCreditLine(rs.getDouble("creditLine"));
            account.setDescription(rs.getString("description"));
            int auxType = rs.getInt("type");
            account.setType(AccountType.values()[auxType]);

        }
        rs.close();
        connection.closeConnection(stmt, con);

        return account;
    }

    @Override
    public void makeMovement(Movement movement) throws Exception {

        con = connection.openConnection();
        stmt = con.prepareStatement(MAKEMOVEMENT);

        stmt.setDouble(1, movement.getAmount());
        stmt.setDouble(2, movement.getBalance());
        stmt.setString(3, movement.getDescription());
        stmt.setTimestamp(4, Timestamp.valueOf(movement.getTimestamp()));
        stmt.setLong(5, movement.getAccount_id());
        stmt.executeUpdate();
        connection.closeConnection(stmt, con);

    }

}
