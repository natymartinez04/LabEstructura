
package LabEstructuras;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

/**
 *
 * @author tllach, nmartinez, dkaty
 */
public class LabEstructura {

    
    public static void main(String[] args) {
        String proyecto;
        ListaEnlazada listaEntregables = new ListaEnlazada();
        proyecto = JOptionPane.showInputDialog("Ingresa el nombre del proyecto: ");
        JFrame display = new Display(1500, 1000, proyecto, listaEntregables);
        display.setDefaultCloseOperation(EXIT_ON_CLOSE);
        display.setSize(1500, 1000);
        display.setResizable(false);
        display.setVisible(true);
        display.setLocationRelativeTo(null);
    }
    
}
