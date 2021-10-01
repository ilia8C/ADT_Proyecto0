/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DAO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.AccountType;
import model.Customer;
import model.Movement;
import recursos.Util;

/**
 *
 * @author 2dam
 */
public class Console {

    public void gestionarMenu(DAO datos) {
        char opc;

        do {
            opc = menu();
            switch (opc) {
                case '1':
                    customerCreation(datos);
                    break;
                case '2':
                    consultCustomer(datos);

                    break;
                case '3':
                    consultCustomerAccounts(datos);
                    break;
                case '4':
                    accountCreation(datos);
                    break;
                case '5':
                    addCustomerToAccount(datos);
                    break;
                case '6':
                    consultAccount(datos);
                    break;
                case '7':
                    makeMovement(datos);
                    break;
                case '8':
                    consultMovement(datos);
                    break;
                case '9':
                    System.out.println("End of the program.");
                    break;
                default:
                    System.out.println("Incorrect selection.");
                    break;
            }
        } while (opc != '9');
    }

    private char menu() {
        char opc;
        System.out.println("\n\tMENÚ:"
                + "\n1. CREATE CUSTOMER."
                + "\n2. CONSULT CUSTOMER DATA."
                + "\n3. CONSULT ACCOUNTS OF A CLIENT."
                + "\n4. CREATE CUSTOMER ACCOUNT."
                + "\n5. ADD CUSTOMER TO ACCOUNT."
                + "\n6. CONSULT DETAILS OF AN ACCOUNT."
                + "\n7. MAKE MOVEMENTS ON AN ACCOUNT."
                + "\n8. CONSULT MOVEMENTS OF AN ACCOUNT."
                + "\n9. SALIR.");
        opc = Util.leerChar("Elige una opción:");
        return opc;
    }

    public void customerCreation(DAO datos) {
        Customer customer = new Customer();

        System.out.println("Introduce the customer's id: ");
        customer.setId(Util.leerLong());
        System.out.println("Introduce the customer's first name: ");
        customer.setFirstName(Util.introducirCadena());
        System.out.println("Introduce the customer's last name: ");
        customer.setLastName(Util.introducirCadena());
        System.out.println("Introduce the customer's middle initial: ");
        customer.setMiddleInitial(Util.introducirCadena());
        System.out.println("Introduce the customer's street: ");
        customer.setStreet(Util.introducirCadena());
        System.out.println("Introduce the customer's city: ");
        customer.setCity(Util.introducirCadena());
        System.out.println("Introduce the customer's state: ");
        customer.setState(Util.introducirCadena());
        System.out.println("Introduce the customer's zip: ");
        customer.setZip(Util.leerInt());
        System.out.println("Introduce the customer's phone: ");
        customer.setPhone(Util.leerInt());
        System.out.println("Introduce the customer's email: ");
        customer.setEmail(Util.introducirCadena());

        try {
            datos.createCustomer(customer);
        } catch (Exception ex) {
            System.out.println("Error creating the customer");;
        }

    }

    private void accountCreation(DAO datos) {
        Account account = new Account();
        Customer customer=null;
        
        
        System.out.println("Introduce the account balance: ");
        account.setBalance(Util.leerDouble());
        System.out.println("Introduce the account begin balance: ");
        account.setBeginBalance(Util.leerDouble());
        account.setBeginBalanceTimestamp(LocalDateTime.now());
        System.out.println("Introduce the account credit line: ");
        account.setCreditLine(Util.leerDouble());
        System.out.println("Introduce the account description: ");
        account.setDescription(Util.introducirCadena());
        System.out.println("Introduce the account type: ");
        int auxType = Util.leerInt();
        account.setType(AccountType.values()[auxType]);
        System.out.println("Introduce the id of the client to create the account");
        try {
            customer = datos.consultCustomer(Util.leerLong());
           
        } catch (Exception ex) {
            System.out.println("There´s no customer with that id");;
        } 
        try {
            datos.createAccount(customer, account);
        } catch (Exception ex) {
            System.out.println("Cant create the account");
        }

    }

    private void consultCustomer(DAO datos) {
        System.out.println("Introduce the customer id: ");
        long id = Util.leerLong();
        try {
            Customer customer = datos.consultCustomer(id);
            System.out.println(customer.toString());
        } catch (Exception ex) {
            System.out.println("There´s no customer with that id");;
        }
    }

    private void consultCustomerAccounts(DAO datos) {
        System.out.println("Introduce the customer id: ");
        long id = Util.leerLong();
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            accounts = datos.consultCustomerAccounts(id);
            System.out.println("The client has " + accounts.size() + " accounts");
            for (int i = 0; i < accounts.size(); i++) {
                System.out.println(accounts.get(i).getId());
            }
        } catch (Exception ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addCustomerToAccount(DAO datos) {
        System.out.println("Introduce the customer id: ");
        long id_customer = Util.leerLong();
        try {
            datos.consultCustomer(id_customer);
        } catch (Exception ex) {
            System.out.println("Create the customer firstly");
        }
        System.out.println("Introduce the account id: ");
        long id_account = Util.leerLong();
        try {
            datos.consultAccount(id_account);
        } catch (Exception ex) {
            System.out.println("Create the account firstly");
        }
        try {
            datos.addCustomerToAccount(id_account, id_customer);
        } catch (Exception ex) {
            System.out.println("Error adding the customer to the account, they already are connected");
        }

    }

    private void consultAccount(DAO datos) {
        System.out.println("Introduce the account id: ");
        long id_account = Util.leerLong();
        try {
            Account account = datos.consultAccount(id_account);
            System.out.println(account.toString());
        } catch (Exception ex) {
            System.out.println("There is no account with that id");
        }
    }

    private void makeMovement(DAO datos) {
        Movement movement= new Movement();
        System.out.println("Introduce the account id to make the movement");
        Long account_id=Util.leerLong();
        try {
            datos.consultAccount(account_id);
        } catch (Exception ex) {
            System.out.println("There is no account with that id");
        }
        System.out.println("Introduce the amount");
        movement.setAmount(Util.leerDouble());
        System.out.println("Introduce the balance");
        movement.setBalance(Util.leerDouble());
        System.out.println("Introduce the description");
        movement.setDescription(Util.introducirCadena());
        movement.setTimestamp(LocalDateTime.now());
        movement.setAccount_id(account_id);
        try {
            datos.makeMovement(movement);
        } catch (Exception ex) {
            System.out.println("Error creating the movement");
        }
    }

    private void consultMovement(DAO datos) {
        ArrayList <Movement> movements = new ArrayList <>();
        System.out.println("Introduce the account id to consult the movement");
        Long account_id=Util.leerLong();
        try {
            datos.consultAccount(account_id);
        } catch (Exception ex) {
            System.out.println("There is no account with that id");
        }
        try {
            movements=datos.consultMovements(account_id);
            for(int i=0;i<movements.size();i++){
                System.out.println(movements.get(i).toString());
            }
        } catch (Exception ex) {
            System.out.println("There are no movements");
        }
    }
}
