/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package time_to_rest.o_meter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author Deepti
 */
public class Brow {

    // here are the components for the browser tab
    JPanel preference = new JPanel();
  //sliders to set the time for which the browser should be allowed to be open 
    JPanel sliders = new JPanel();
    JSlider slider;
    JSlider slider1;
    JSlider slider2;
    
    JPanel timer = new JPanel();
    JPanel upper = new JPanel();
    
    JLabel t1 = new JLabel();
    JButton done = new JButton("done");
    JCheckBox fire = new JCheckBox("FIREFOX");//checkbox to select the browser which should be tracked
    
    JPanel lower = new JPanel();

    public JPanel main() {
       
        JPanel brow = new JPanel();
        
        brow.setBackground(new Color(0x086A87));
        brow.setLayout(new BorderLayout());
        
        fire.setSelected(true);
        fire.setForeground(Color.ORANGE);
        fire.setFont(new Font("Andale Mono", Font.BOLD, 20));
        
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
        
        slider1.setForeground(new Color(0xECF6CE));
        slider.setForeground(new Color(0xECF6CE));
        slider2.setForeground(new Color(0xECF6CE));
        
        t1 = new JLabel();
        t1.setText("0:0:0");
        t1.setHorizontalAlignment(0);
        t1.setFont(new Font("Andale Mono", Font.PLAIN, 60));
        t1.setBackground(new Color(0x086A87));
        t1.setForeground(new Color(0xECF6CE));
        
        sliders.setLayout(new GridLayout(3, 1));
        sliders.add(slider);
        sliders.add(slider1);
        sliders.add(slider2);
        sliders.setBackground(new Color(0x086A87));
        
        preference.setLayout(new BoxLayout(preference, BoxLayout.Y_AXIS));
        preference.add(fire);
        preference.setBackground(new Color(0x086A87));
        
        upper.setLayout(new GridLayout(1, 1));
        upper.add(sliders);
        upper.add(t1);
        upper.setBackground(new Color(0x086A87));
        
        lower.setLayout(new BoxLayout(lower, BoxLayout.Y_AXIS));
        lower.add(preference);
        lower.add(done);
        lower.setBackground(new Color(0x086A87));
        
        brow.add(upper, BorderLayout.CENTER);
        brow.add(lower, BorderLayout.SOUTH);
        
        return brow;
    }
}
