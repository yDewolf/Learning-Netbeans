/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemalocacaobikes;

import sistemalocacaobikes.dao.ConnectionFactory;
import view.TelaClientes;


public class SistemaLocacaoBikes {

    public static void main(String[] args) {
        try {
            System.out.println(ConnectionFactory.getConnection());
        } catch (Exception ex) {
            System.getLogger(SistemaLocacaoBikes.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
