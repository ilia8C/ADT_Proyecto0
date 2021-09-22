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
public class Account implements Serializable{

    public Account() {
    }
    private long id;
    private String description;
    private float balance;
    private float creditLine;
    private float beginBalance;
    private LocalDateTime beginBalanceTimestamp;
    private AccountType type;

    
    //Getters and Setters
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

    /**
     * @return the creditLine
     */
    public float getCreditLine() {
        return creditLine;
    }

    /**
     * @param creditLine the creditLine to set
     */
    public void setCreditLine(float creditLine) {
        this.creditLine = creditLine;
    }

    /**
     * @return the beginBalance
     */
    public float getBeginBalance() {
        return beginBalance;
    }

    /**
     * @param beginBalance the beginBalance to set
     */
    public void setBeginBalance(float beginBalance) {
        this.beginBalance = beginBalance;
    }

    /**
     * @return the beginBalanceTimestamp
     */
    public LocalDateTime getBeginBalanceTimestamp() {
        return beginBalanceTimestamp;
    }

    /**
     * @param beginBalanceTimestamp the beginBalanceTimestamp to set
     */
    public void setBeginBalanceTimestamp(LocalDateTime beginBalanceTimestamp) {
        this.beginBalanceTimestamp = beginBalanceTimestamp;
    }

    /**
     * @return the type
     */
    public AccountType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(AccountType type) {
        this.type = type;
    }


   
}
