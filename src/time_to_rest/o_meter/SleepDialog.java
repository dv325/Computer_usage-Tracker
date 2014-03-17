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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Deepti
 */
public class SleepDialog {

    String song = "/Frank.wav";//song has the alarm tone which will be selected by user.Default is frank.wav
    
    JPanel sliders = new JPanel();//these sliders are to set the alarm
    JSlider slider;
    JSlider slider1;
    JSlider slider2;
   
    JPanel upper = new JPanel();
    JLabel t1 = new JLabel();
    JLabel set = new JLabel("Set The Alarm");
    JLabel tone = new JLabel("Set The Tone");
    JButton changetone = new JButton("change alarm tone");
    JButton done = new JButton("done");
    //these three integers have the value given by sliders and are used for the countdown 
    int valuebrow;
    int value1brow;
    int value2brow;
    //these three strings are used to show the time.
    String strb = "0";
    String str1b = "0";
    String str2b = "0";
    
    JRadioButton sleepb = new JRadioButton("Sleep");
    JPanel bottom = new JPanel();
    JButton okay = new JButton("okay");
    JDialog alarm = new JDialog();//this jdialog plays the alarm
    AudioStream as;

    public JPanel main() {

        JPanel sleepD = new JPanel();
        sleepD.setBackground(new Color(0x086A87));

        bottom.setLayout(new FlowLayout());
        sleepD.setLayout(new BorderLayout());
        slider = new JSlider(JSlider.HORIZONTAL, 0, 24, 1);
        slider.setMajorTickSpacing(8);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider1 = new JSlider(JSlider.HORIZONTAL, 0, 60, 1);
        slider1.setMajorTickSpacing(10);
        slider1.setMinorTickSpacing(1);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider2 = new JSlider(JSlider.HORIZONTAL, 0, 60, 1);
        slider2.setMajorTickSpacing(10);
        slider2.setMinorTickSpacing(1);
        slider2.setPaintTicks(true);
        slider2.setPaintLabels(true);

        t1 = new JLabel();
        t1.setText("0:0:0");
        t1.setHorizontalAlignment(0);
        set.setFont(new Font("Andale Mono", Font.BOLD, 60));
        t1.setFont(new Font("Andale Mono", Font.PLAIN, 60));
        t1.setForeground(new Color(0xECF6CE));
        set.setForeground(new Color(0xD7DF01));

        sliders.setBackground(new Color(0x086A87));
        sliders.setLayout(new GridLayout(3, 1));
        sliders.add(slider);
        sliders.add(slider1);
        sliders.add(slider2);


        upper.setLayout(new GridLayout(1, 1));
        upper.add(sliders);
        upper.add(t1);
        upper.setBackground(new Color(0x086A87));

        bottom.add(done);
        bottom.add(changetone);
        bottom.setBackground(new Color(0x086A87));

        sleepD.add(set, BorderLayout.NORTH);
        sleepD.add(upper, BorderLayout.CENTER);
        sleepD.add(bottom, BorderLayout.SOUTH);
        sleepD.setSize(650, 400);
        
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                valuebrow = slider.getValue();
                strb = Integer.toString(valuebrow);
                t1.setText((strb + ":" + str1b + ":" + str2b));
            }
        });

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                value1brow = slider1.getValue();
                str1b = Integer.toString(value1brow);
                t1.setText((strb + ":" + str1b + ":" + str2b));
            }
        });
        slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                value2brow = slider2.getValue();
                str2b = Integer.toString(value2brow);
                t1.setText((strb + ":" + str1b + ":" + str2b));


            }
        });

        return sleepD;
    }

    public void Action() {

        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                new Thread(new Runnable() {
                    public void run() {
                        slider.setEnabled(false);
                        slider1.setEnabled(false);
                        slider2.setEnabled(false);
                        done.setEnabled(false);
                        if (value1brow == 0 && valuebrow == 0) {

                            for (int i = (value2brow); i >= 0; i--) {
                                String str22 = Integer.toString(i);
                                t1.setText((strb + ":" + str1b + ":" + str22));

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                }
                            }
                        } else {

                            for (int i = (value2brow); i > 0; i--) {
                                String str22 = Integer.toString(i);
                                t1.setText((strb + ":" + str1b + ":" + str22));
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                }
                            }
                        }
                        int r1 = value1brow - 1;
                        for (int j = r1; j >= 0; j--) {
                            for (int z = 59; z > 0; z--) {
                                t1.setText((strb + ":" + j + ":" + z));

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                }
                            }
                        }
                        int s = valuebrow - 1;
                        for (int j = s; j >= 0; j--) {
                            for (int z = 59; z > 0; z--) {
                                for (int s1 = 59; s1 > 0; s1--) {
                                    t1.setText((j + ":" + z + ":" + s1));
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException ex) {
                                    }
                                }
                            }
                        }
                        try {
                            //after the countdown reaches zero alarm has to be played therefore closeAlarm function is called

                            closeAlarm();
                        } catch (IOException ex) {
                            Logger.getLogger(SleepDialog.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }).start();
            }
        });
        //if the user clicks on changetone button a new dialog pops up which is in whichtone function
        changetone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whichtone();
            }
        });

    }

    public void closeAlarm() throws IOException {
        
        String s = "resources" + song;
        as = new AudioStream(SleepDialog.class.getResourceAsStream(s));//getting the alarm tone
        
        alarm.setVisible(true);
        alarm.setLayout(new BorderLayout());
        
        JLabel wake = new JLabel("Alarm");
        wake.setHorizontalAlignment(0);
        
        alarm.getContentPane().setBackground(new Color(0x086A87));
        wake.setFont(new Font("Andale Mono", Font.PLAIN, 30));
        wake.setForeground(new Color(0xECF6CE));
        
        alarm.add(wake, BorderLayout.CENTER);
        alarm.add(okay, BorderLayout.SOUTH);
        alarm.setSize(300, 300);
        
        AudioPlayer.player.start(as);//playing the alarm tone

    }

    public void whichtone() {
        
        final JDialog dialog = new JDialog();//this dialog is used to select the alarm tone
        
        final JRadioButton s1 = new JRadioButton("The way you look tonight");
        final JRadioButton s2 = new JRadioButton("Bring it to me");
        
        ButtonGroup b = new ButtonGroup();
        b.add(s1);
        b.add(s2);
        s1.setSelected(true);
        
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton okay = new JButton("okay");
        buttons.setBackground(new Color(0x086A87));

        dialog.setTitle("Set tone");
        dialog.setVisible(true);
        buttons.add(s1);
        buttons.add(s2);
        
        dialog.setLayout(new BorderLayout());
        tone.setFont(new Font("Andale Mono", Font.PLAIN, 60));
        tone.setForeground(new Color(0xD7DF01));


        dialog.add(tone, BorderLayout.NORTH);
        dialog.add(buttons, BorderLayout.CENTER);
        dialog.add(okay, BorderLayout.SOUTH);
        dialog.setSize(new Dimension(400, 400));
        dialog.getContentPane().setBackground(new Color(0x086A87));
//when u click on okay it saves which tone was selected by user.
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (s1.isSelected() == true) {
                    song = "/Frank.wav";
                } else if (s2.isSelected() == true) {
                    song = "/Sam.wav";
                }
                dialog.dispose();
            }
        });

    }
}
