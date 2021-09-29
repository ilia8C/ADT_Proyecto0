/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.DAO;
import model.Customer;
import recursos.Util;

/**
 *
 * @author 2dam
 */
public class Console {

    /**
     *
     * @param datos
     */
    public void gestionarMenu(DAO datos) {
        char opc;

        do {
            opc = menu();
            switch (opc) {
                case '1':
                    customerCreation(datos);
                    break;
                case '2':

                    break;
                case '3':

                    break;
                case '4':

                    break;
                case '5':

                    break;
                case '6':

                    break;
                case '7':

                    break;
                case '8':

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
                + "\n3. CONSULT ACCOUNT OF A CLIENT."
                + "\n4. CREATE CUSTOMER ACCOUNT."
                + "\n5. ADD CUSTOMER TO ACCOUNT."
                + "\n6. MAKE MOVEMENTS ON AN ACCOUNT."
                + "\n8. CONSULT MOVEMENTS OF AN ACCOUNT."
                + "\n9. SALIR.");
        opc = Util.leerChar("Elige una opción:");
        return opc;
    }

    /**
     *
     * @param datos
     */
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

        datos.createCustomer(customer);

    }
}
