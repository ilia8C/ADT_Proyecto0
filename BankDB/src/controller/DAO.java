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
    public Customer consultCustomer(Integer id);
    public ArrayList<Account> counsultCustomerAccounts(Integer id);
    public void createAccount(CustomerAccount customerAccount);
    public void addCustomerToAccount(Integer id, Customer customer);
    public Account consultAccount(Integer id);
    public void makeMovement(Integer id);
    public ArrayList<Movement> consultMovements(Integer account_id);
}
