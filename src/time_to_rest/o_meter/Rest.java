/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package time_to_rest.o_meter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Deepti
 */
public class Rest {
//all the components of the rest tab
    JLabel t1 = new JLabel();
    
    JPanel rest = new JPanel();
    
    JPanel sliders = new JPanel();
    JPanel timer = new JPanel();
    JPanel sleep = new JPanel();
    JPanel exercise = new JPanel();
    JPanel preference = new JPanel();
    
    JSlider slider;
    JSlider slider1;
    JSlider slider2;
    
    JTextField sleept = new JTextField();
    JTextField exerciset = new JTextField();
    
    JPanel upper = new JPanel();
    JButton done = new JButton("done");
    JPanel lower = new JPanel();
    JRadioButton sleepb = new JRadioButton("TAKE A BREAK");
    JRadioButton exerciseb = new JRadioButton("EXERCISE");
    ButtonGroup b = new ButtonGroup();
    
    public JPanel main() {

        sleepb.setSelected(true);
        
        slider = new JSlider(JSlider.HORIZONTAL, 0, 23, 1);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        slider1 = new JSlider(JSlider.HORIZONTAL, 0, 59, 1);
        slider1.setMajorTickSpacing(10);
        slider1.setMinorTickSpacing(1);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        
        slider2 = new JSlider(JSlider.HORIZONTAL, 0, 59, 1);
        slider2.setMajorTickSpacing(10);
        slider2.setMinorTickSpacing(1);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);


        t1 = new JLabel();
        t1.setText("0:0:0");
        t1.setHorizontalAlignment(0);
        t1.setFont(new Font("Andale Mono", Font.PLAIN, 60));
        t1.setForeground(new Color(0xECF6CE));

        sliders.setLayout(new GridLayout(3, 1));
        sliders.add(slider);
        sliders.add(slider1);
        sliders.add(slider2);
        sliders.setBackground(new Color(0x086A87));

        upper.setLayout(new GridLayout(1, 1));
        upper.add(sliders);
        upper.add(t1);
        upper.setBackground(new Color(0x086A87));

        sleepb.setForeground(new Color(0xD7DF01));
        exerciseb.setForeground(new Color(0xF781F3));

        sleepb.setFont(new Font("Andale Mono", Font.BOLD, 20));
        exerciseb.setFont(new Font("Andale Mono", Font.BOLD, 20));

        b.add(sleepb);
        b.add(exerciseb);
        
        preference.setLayout(new BoxLayout(preference, BoxLayout.Y_AXIS));
        preference.add(sleepb);
        preference.add(exerciseb);
        preference.setBackground(new Color(0x086A87));

        lower.setLayout(new BoxLayout(lower, BoxLayout.Y_AXIS));

        lower.add(preference);
        lower.add(done);
        lower.setBackground(new Color(0x086A87));

        rest.setLayout(new BorderLayout());
        rest.add(upper, BorderLayout.CENTER);
        rest.add(lower, BorderLayout.SOUTH);
        rest.setBackground(new Color(0x086A87));

        return rest;
    }
}
