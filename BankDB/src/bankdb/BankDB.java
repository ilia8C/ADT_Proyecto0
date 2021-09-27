/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankdb;

import controller.DAO;
import controller.DAOImplementation;
import view.Console;


/**
 *
 * @author 2dam
 */
public class BankDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       DAO data = new DAOImplementation();
       
       Console console = new Console();
       console.gestionarMenu(data);
    }

    
}
