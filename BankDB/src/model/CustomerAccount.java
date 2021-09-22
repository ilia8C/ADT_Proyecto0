/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author 2dam
 */
public class CustomerAccount implements Serializable{

    public CustomerAccount() {
    }
    private long idAccount;
    private long idCustomer;

    
    //Getters and Setters
    
    /**
     * @return the idAccount
     */
    public long getIdAccount() {
        return idAccount;
    }

    /**
     * @param idAccount the idAccount to set
     */
    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    /**
     * @return the idCustomer
     */
    public long getIdCustomer() {
        return idCustomer;
    }

    /**
     * @param idCustomer the idCustomer to set
     */
    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }
}
