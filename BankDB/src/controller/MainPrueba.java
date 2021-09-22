/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Account;

/**
 *
 * @author 2dam
 */
public class MainPrueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        
        DAOImplementation daoImplementation = new DAOImplementation();
        accounts = daoImplementation.counsultCustomerAccounts(102263301);
        
        System.out.println(accounts.size());
        for(Account a: accounts ){
            System.out.println(a.getType());
        }
    }
    
}
