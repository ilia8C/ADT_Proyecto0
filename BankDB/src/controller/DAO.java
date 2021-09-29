/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Account;
import model.Customer;
import model.CustomerAccount;
import model.Movement;

/**
 *
 * @author 2dam
 */
public interface DAO {
    public void createCustomer(Customer customer) throws Exception;
    public Customer consultCustomer(long id) throws Exception;
    public ArrayList<Account> counsultCustomerAccounts(long id) throws Exception;
    public void createAccount(Customer customer, Account account) throws Exception;
    public void addCustomerToAccount(long account_id, long customer_id) throws Exception;
    public Account consultAccount(long id) throws Exception;
    public void makeMovement(long account_id,Movement movement) throws Exception;
    public ArrayList<Movement> consultMovements(long account_id) throws Exception;
}
