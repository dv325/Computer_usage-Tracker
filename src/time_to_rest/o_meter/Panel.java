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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import sun.audio.AudioPlayer;

/**
 *
 * @author Deepti
 */
public class Panel {


    //inside is used for the counter in the dialog dialogkillf which gives user 30 seconds before browser is closed
    volatile int inside = 30;
//to check if firefox is open
    Boolean fireopen = false;
    long finalf = 0;
    //has the pid of the browser process which is to be killed
    int pf = -1;
    
    int iitab;//to keep track of which tab is open
    //these 3 integeres used to get the value from the slider in rest tab
    int value;
    int value1;
    int value2;
    
    //these 3 integeres used to get the value from the slider in browser tab
    int valueb;
    int value1b;
    int value2b;
    //if the user choose not to change the settings these 3 values are used to get the old values
    int repeat;
    int repeat1;
    int repeat2;
    
    int first = 0;
    volatile Integer check = 1;
    String str = "0";
    String str1 = "0";
    String str2 = "0";
    String strb = "0";
    String str1b = "0";
    String str2b = "0";
    int sethr;
    int setmin;
    int setsc;
    Brow b = new Brow();
    Rest r = new Rest();
    JPanel main = new JPanel();
    
    Time_to_restO_meter ob = new Time_to_restO_meter();
    final JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
    static Semaphore mutex = new Semaphore(0);
   
    JDialog sleepdialog = new JDialog();//dialog to set alarm
    SleepDialog s = new SleepDialog();//object of sleepdialog class
    //settings components
    JDialog dialog = new JDialog();
    JLabel text = new JLabel();
    JButton yes = new JButton("Yes");
    JButton no = new JButton("No");
    JPanel j = new JPanel();
//dialog to tell the user in how much time their browser will close and its components
    final JDialog dialogkillf = new JDialog();
    JButton snoozekillf = new JButton("Snooze");
    JLabel text1 = new JLabel();//dialog killf
    JLabel time = new JLabel();//dialog killf
    //exercise dialog components
    JDialog exer = new JDialog();
    JLabel elabel = new JLabel();
    JButton ecancel = new JButton("Cancel");
    JLabel labelback = new JLabel();
    JButton ebutton = new JButton();
    int where = 1;

    public JPanel main() {

//this is the main panel
        main.setLayout(new GridLayout(1, 1));
        main.add(tabs);
        main.setBackground(Color.WHITE);
        main.setSize(400, 275);


//creating tabs
        tabs.addTab("Browser", b.main());
        tabs.addTab("Break", r.main());
        tabs.setBackground(new Color(0x086A87));
        tabs.setSize(400, 275);

//dialog to set up alarm
        sleepdialog.setTitle("Go to sleep");
        sleepdialog.add(s.main());
        sleepdialog.setSize(new Dimension(300, 300));
        sleepdialog.setLocationRelativeTo(null);
        sleepdialog.getContentPane().setBackground(new Color(0x086A87));
        sleepdialog.setSize(650, 400);

 //settings dialog for rest       
        dialog.setTitle("Settings");
        text.setText("<html>Do you want to change the settings?</html>");
        text.setFont(new Font("Andale Mono", Font.BOLD, 40));
        text.setForeground(new Color(0xD7DF01));
        j.setLayout(new FlowLayout());
        j.add(yes);
        j.add(no);
        dialog.add(text, BorderLayout.CENTER);
        dialog.add(j, BorderLayout.SOUTH);
        dialog.getContentPane().setBackground(new Color(0x086A87));
        dialog.setSize(new Dimension(500, 300));
        dialog.setLocationRelativeTo(null);

//dialog to tell the user in how much time their browser will close
        dialogkillf.setTitle("Going to close");
        text1.setText("Your Firefox will close in");
        text1.setHorizontalAlignment(0);
        time.setHorizontalAlignment(0);
        text1.setFont(new Font("Andale Mono", Font.BOLD, 20));
        time.setFont(new Font("Andale Mono", Font.BOLD, 60));
        time.setForeground(new Color(0xECF6CE));
        text1.setForeground(Color.ORANGE);
        dialogkillf.getContentPane().setBackground(new Color(0x086A87));
        dialogkillf.add(text1, BorderLayout.NORTH);
        dialogkillf.add(time, BorderLayout.CENTER);
        dialogkillf.add(snoozekillf, BorderLayout.SOUTH);
        dialogkillf.setSize(new Dimension(325, 300));
        dialog.setLocationRelativeTo(null);

//dialog when exercise is selected 
        elabel.setText("Time to exercise");
        elabel.setFont(new Font("Andale Mono", Font.BOLD, 60));
        elabel.setHorizontalAlignment(0);
        exer.setTitle("Exercise");
        ebutton.setIcon(new ImageIcon("1.png"));
        labelback.setLayout(new BorderLayout());
        labelback.add(ebutton, BorderLayout.EAST);
        labelback.add(elabel, BorderLayout.CENTER);
        labelback.add(ecancel, BorderLayout.SOUTH);
        labelback.setIcon(new ImageIcon("1.jpg"));
        exer.setLayout(new GridLayout(1, 1));
        exer.add(labelback);
        exer.setSize(new Dimension(1024, 700));
        exer.setResizable(false);
        exer.setLocationRelativeTo(null);
        
        return main;
    }

    public void Actions() {
        //this checks which tab is open
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                iitab = tabs.getSelectedIndex();//getting which of browser or rest tab is selected
            }
        });
//getting the values from the sliders and setting the Jlabel t1 to that value in rest tab
        r.slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                value = r.slider.getValue();
                repeat = r.slider.getValue();
                str = Integer.toString(value);
                r.t1.setText((str + ":" + str1 + ":" + str2));
            }
        });

        r.slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                value1 = r.slider1.getValue();
                repeat1 = r.slider1.getValue();
                str1 = Integer.toString(value1);
                r.t1.setText((str + ":" + str1 + ":" + str2));
            }
        });

        r.slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                value2 = r.slider2.getValue();
                repeat2 = r.slider2.getValue();
                str2 = Integer.toString(value2);
                r.t1.setText((str + ":" + str1 + ":" + str2));
            }
        });
//getting the values from the sliders and setting the Jlabel t1 to that value in browser tab


        b.slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                valueb = b.slider.getValue();
                strb = Integer.toString(valueb);
                b.t1.setText((strb + ":" + str1b + ":" + str2b));
                sethr = Integer.parseInt(strb);
            }
        });

        b.slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                value1b = b.slider1.getValue();
                str1b = Integer.toString(value1b);
                b.t1.setText((strb + ":" + str1b + ":" + str2b));
                setmin = Integer.parseInt(str1b);

            }
        });
        b.slider2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent ce) {
                value2b = b.slider2.getValue();
                str2b = Integer.toString(value2b);
                b.t1.setText((strb + ":" + str1b + ":" + str2b));
                setsc = Integer.parseInt(str2b);
            }
        });
        rdone();//timer for rest tab
        bdone();//timer for browser tab
    }

    public void rdone() {
        r.done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                new Thread(new Runnable() {
                    public void run() {
                        if (iitab == 1) {
                            //countdown for rest tab
                            r.slider.setEnabled(false);
                            r.slider1.setEnabled(false);
                            r.slider2.setEnabled(false);
                            r.done.setEnabled(false);

                            if (value1 == 0 && value == 0) {

                                int value2copy = value2;
                                for (int i = (value2copy); i >= 0; i--) {
                                    String str22 = Integer.toString(i);
                                    r.t1.setText((str + ":" + str1 + ":" + str22));
                                    value2--;
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException ex) {
                                    }
                                }
                            } else {
                                int value2copy = value2;

                                for (int i = (value2copy); i > 0; i--) {
                                    String str22 = Integer.toString(i);
                                    r.t1.setText((str + ":" + str1 + ":" + str22));
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException ex) {
                                    }
                                    value2--;
                                }
                            }
                            int r1 = value1 - 1;
                            value1 = value1 - 1;
                            for (int j = r1; j >= 0; j--) {
                                for (int z = 59; z > 0; z--) {
                                    r.t1.setText((str + ":" + j + ":" + z));

                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException ex) {
                                    }

                                    value1--;
                                }
                            }

                            int s = value - 1;
                            value = value - 1;

                            for (int j = s; j >= 0; j--) {
                                for (int z = 59; z > 0; z--) {
                                    for (int s1 = 59; s1 > 0; s1--) {
                                        r.t1.setText((j + ":" + z + ":" + s1));
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException ex) {
                                        }
                                    }
                                }
                                value--;
                            }

                        }
//once countdown reaches 0 it checks whether sleep or exercise option is selected and opens the corresponding dialog
                        if (r.sleepb.isSelected() == true) {
                            ob.mini();
                            sleepdialog();
                        }
                        if (r.exerciseb.isSelected() == true) {
                            try {
                                where = 1;
                                exercisedialog();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }).start();
            }
        });


    }

    public void sleepdialog() {
        sleepdialog.setVisible(true);
        s.Action();
        s.okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AudioPlayer.player.stop(s.as);
                s.slider.setEnabled(true);
                s.slider1.setEnabled(true);
                s.slider2.setEnabled(true);
                s.done.setEnabled(true);
                s.alarm.dispose();
                sleepdialog.dispose();
                changesettings();//once the alarm is closed it asks user if they want to change the settings
            }
        });

    }

    public void exercisedialog() throws InterruptedException {
        where = 1;
        //ebutton is used to skip a step
        //the listen checks which step the user is on and when the button is clicked next step comes up.
        ebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (where == 5) {
                    elabel.setText("<html> Done!! </html>");
                    where = where + 1;


                } else if (where == 4) {
                    elabel.setText("<html>Step 4: Time 3 min <br> brace your hands on the edge your desk, each about a shoulder width away from your body. Twist your hands in so they point toward your body and lean forward, hunching your shoulders. </html>");
                    where = where + 1;

                } else if (where == 3) {
                    elabel.setText("<html>Step 3: Time: 1 min <br> Roll the wrists 10 times clockwise, then 10 times counterclockwise.Repeat.</html>");

                    where = where + 1;

                } else if (where == 2) {
                    elabel.setText("<html>Step 2: Time: 2 min <br> Roll your shoulders forward around 10 times, then backward.Repeat.</html>");
                    where = where + 1;

                } else if (where == 1) {
                    elabel.setText("<html>Step 1: Time: 1 min <br> slowly flex your head forward and backward, <br> side to side and look right and left</html>");
                    where = where + 1;

                }




            }
        });

//to cancel the exercise
        ecancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                exer.dispose();
                changesettings(); //once the exercise dialog is closed it asks user if they want to change the settings

            }
        });

        exer.setVisible(true);

        if (where == 1) {
            elabel.setText("Time to exercise");

            Thread.sleep(10 * 1000);
            where = where + 1;
        }
        if (where == 2) {
            elabel.setText("<html>Step 1: Time: 1 min <br> slowly flex your head forward and backward, <br> side to side and look right and left</html>");
            Thread.sleep(60 * 60 * 1000);
            where = where + 1;
        }
        if (where == 3) {
            elabel.setText("<html>Step 2: Time: 2 min <br> Roll your shoulders forward around 10 times, then backward.Repeat.</html>");
            Thread.sleep(2 * 60 * 60 * 1000);
            where = where + 1;
        }
        if (where == 4) {
            elabel.setText("<html>Step 3: Time: 1 min <br> Roll the wrists 10 times clockwise, then 10 times counterclockwise.Repeat.</html>");
            Thread.sleep(1 * 60 * 60 * 1000);
            where = where + 1;
        }
        if (where == 5) {
            elabel.setText("<html>Step 4: Time 3 min <br> brace your hands on the edge your desk, each about a shoulder width away from your body. Twist your hands in so they point toward your body and lean forward, hunching your shoulders. ></html>");
            Thread.sleep(3 * 60 * 60 * 1000);
            where = where + 1;
        }
        if (where == 6) {
            elabel.setText("<html> Done!! </html>");
            Thread.sleep(2 * 1000);
        }
        exer.dispose();
        changesettings();     //once the exercise is closed it asks user if they want to change the settings

    }

    public void changesettings() {

        dialog.setVisible(true);
        //if the user wants to change the settings then the main frame comes up and the user can use the sliders to set new time and select new checkbox
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                where = 1;
                dialog.dispose();
                r.slider.setEnabled(true);
                r.slider1.setEnabled(true);
                r.slider2.setEnabled(true);
                r.done.setEnabled(true);
                ob.max();
                dialog.dispose();
            }
        });
//if the user does not choose to change the settings the old settings are obtained;
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                where = 1;
                dialog.dispose();
                value1 = repeat1;
                value2 = repeat2;
                value = repeat;
                r.done.setEnabled(true);
                r.done.doClick();
                dialog.dispose();
            }
        });
    }

    public void bdone() {

        b.done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Thread(new Runnable() {
                    public void run() {
                        if (iitab == 0) {
                            long donetime = System.currentTimeMillis();
                            while (check == 1) {
                                check = 0;
                                fireopen = false;
                                int pfire = -1;
                                long ftimeopen = 0;
                                long finalmillif = 0;
                                //getting all the current running processes.
                                Process myProcess = null;
                                try {
                                    myProcess = Runtime.getRuntime().exec("ps aux");
                                } catch (IOException ex) {
                                }
                                
                                //getting the process id for the firefox if it is running
                                InputStream inputStream = myProcess.getInputStream();
                                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                                String line;
                                try {
                                    while ((line = in.readLine()) != null) {
                                        //checking if the firebox checkbox was selected.If so then it is checked it is open
                                        if (b.fire.isSelected()) {
                                            if (line.contains("Firefox")) {
                                                
                                                fireopen = true;
                                                String arrget[] = new String[11];
                                                arrget = line.split("\\s+");
                                                pfire = Integer.parseInt(arrget[1]);
                                                String z = arrget[8];
                                                //if the firefox was  opened before  when the  application was started then the 
                                                //application assumes it to be opened at the same time when application was started
                                             
                                                //if the firefox was  opened a day before  when the  application was opened it's time will contain a day
                                                if (z.contains("Mon") || z.contains("Tue") || z.contains("Wed") || z.contains("Thu") || z.contains("Fri") || z.contains("Sat") || z.contains("Sun")) {
                                                    ftimeopen = System.currentTimeMillis();
                                                } 
                                                
                                                else {
                                                    //getting the time when browser was opened.
                                                    String time[] = new String[2];
                                                    if (z.contains("PM")) {
                                                        time = z.split("P");
                                                        String time1[] = new String[2];
                                                        time1 = time[0].split(":");
                                                        long millis = (Integer.parseInt(time1[0]) + 12) * 60 * 60 * 1000 + Integer.parseInt(time1[1]) * 60 * 1000;

                                                        if (millis < donetime) {
                                                            ftimeopen = System.currentTimeMillis();

                                                        } else {
                                                            ftimeopen = millis;
                                                        }
                                                    } else {
                                                        time = z.split("A");
                                                        String time1[] = new String[2];
                                                        time1 = time[0].split(":");
                                                        long millis = (Integer.parseInt(time1[0])) * 60 * 60 * 1000 + Integer.parseInt(time1[1]) * 60 * 1000;

                                                        if (millis < donetime) {
                                                            ftimeopen = System.currentTimeMillis();

                                                        } else {
                                                            ftimeopen = millis;
                                                        }
                                                    }
                                                }
                                            }
                                            //ftime open is the time browser was opened.to that we add the time obtained from the sliders.
                                            finalmillif = sethr * 60 * 60 * 1000 + setmin * 60 * 1000 + setsc * 1000 + ftimeopen;
                                        }
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                if (fireopen) {
                                    //finalmillif has the time when the browser should be opened .
                                    //As soon as this time becomes gretaer than the current time it is closed
                                    while ((finalmillif) > System.currentTimeMillis()) {
                                    }
                                    finalf = finalmillif;
                                    pf = pfire;
                                    killfiredialog();
                                    try {
                                        mutex.acquire();
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                check = 1;
                            }
                        }
                    }
                }).start();
            }
        });

    }

    public void killfiredialog() {
        //here we give the user 30 seconds before closing the browser
        //user can click on user on snooze and 10 seconds will be added to the timer.
        //That's why these two things are ran in different threads parallely
        dialogkillf.setVisible(true);
        inside = 30;
        Thread loop = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; inside >= 0; inside--) {
                    if (inside >= 10 && inside <= 59) {
                        time.setText(("0 : " + inside));
                    }
                    if (inside < 10) {
                        time.setText(("0 : 0" + inside));

                    }
                    if (inside > 59) {
                        int x = inside - 60;

                        time.setText(("1 : " + x));

                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                }
                String s1 = "kill " + pf;
                try {
                    Runtime.getRuntime().exec(s1);
                } catch (IOException ex) {
                    Logger.getLogger(Panel.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialogkillf.dispose();
                pf = -1;
                finalf = 0;
                mutex.release();
            }
        });

        snoozekillf.addActionListener(new ActionListener() {
//hardcode snooze for 20 sec 
            @Override
            public void actionPerformed(ActionEvent e) {

                Thread snoo = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        inside = inside + 20;
                    }
                });

                snoo.start();
            }
        });
        loop.start();
    }
}
