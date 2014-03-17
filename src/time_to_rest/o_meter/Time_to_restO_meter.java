/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package time_to_rest.o_meter;

import javax.swing.JFrame;

/**
 *
 * @author Deepti
 */
public class Time_to_restO_meter {

    /**
     * @param args the command line arguments
     */
    static JFrame f1 = new JFrame("COMPUTER_USAGE_TRACKER");

    public static void main(String[] args) {

        Panel p = new Panel();
        f1.add(p.main());
        p.Actions();
        f1.setSize(650,400);
        f1.setLocationRelativeTo(null);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // TODO code application logic here
    }

    public static void mini() {
        f1.setState(f1.ICONIFIED);
    }

    public static void max() {
        f1.setState(f1.NORMAL);
    }
}




