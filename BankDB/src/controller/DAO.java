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
    public void createCustomer(Customer customer);
    public Customer consultCustomer(long id);
    public ArrayList<Account> counsultCustomerAccounts(long id);
    public void createAccount(CustomerAccount customerAccount, Account account);
    public void addCustomerToAccount(long id, Customer customer);
    public Account consultAccount(long id);
    public void makeMovement(long id);
    public ArrayList<Movement> consultMovements(long account_id);
}
