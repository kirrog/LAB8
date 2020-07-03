package GUI;

//First page control sign in of user and have links to register and work area

import GUI.Localization.LanguagesProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends AbstractPageShower {

    private JLabel labelTickets;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JTextField jTextAreaLogin;
    private JPasswordField jTextAreaPassword;
    private JButton jButtonSignIn;
    private JButton jButtonRegister;
    private JComboBox<String> locations = LanguagesProvider.locationsL;

    public LoginPage(JMainFrame jMainFrame) {

        jmf = jMainFrame;
        jp = new JPanel(new GridBagLayout());


        GridBagConstraints bagC = new GridBagConstraints();
        bagC.insets = new Insets(0, 0, 10, 10);
        bagC.weightx = 0;
        bagC.weighty = 0;
        bagC.gridx = 0;
        bagC.gridy = 0;
        bagC.gridheight = 1;
        bagC.gridwidth = 2;
        jp.add(locations, bagC);

        labelTickets = new JLabel("Tickets");
        GridBagConstraints bagC1 = new GridBagConstraints();
        bagC1.insets = new Insets(0, 10, 10, 0);
        bagC1.weightx = 0;
        bagC1.weighty = 0;
        bagC1.gridx = 2;
        bagC1.gridy = 0;
        bagC1.gridheight = 1;
        bagC1.gridwidth = 2;
        jp.add(labelTickets, bagC1);

        labelLogin = new JLabel("Login");
        GridBagConstraints bagC2 = new GridBagConstraints();
        bagC2.insets = new Insets(10, 0, 10, 10);
        bagC2.weightx = 0;
        bagC2.weighty = 0;
        bagC2.gridx = 0;
        bagC2.gridy = 1;
        bagC2.gridheight = 1;
        bagC2.gridwidth = 2;
        bagC2.fill = GridBagConstraints.HORIZONTAL;
        jp.add(labelLogin, bagC2);

        labelPassword = new JLabel("Password");
        GridBagConstraints bagC3 = new GridBagConstraints();
        bagC3.insets = new Insets(10, 0, 10, 10);
        bagC3.weightx = 0;
        bagC3.weighty = 0;
        bagC3.gridx = 0;
        bagC3.gridy = 2;
        bagC3.gridheight = 1;
        bagC3.gridwidth = 2;
        bagC3.fill = GridBagConstraints.HORIZONTAL;
        jp.add(labelPassword, bagC3);

        jTextAreaLogin = new JTextField();
        GridBagConstraints bagC4 = new GridBagConstraints();
        bagC4.insets = new Insets(10, 10, 10, 0);
        bagC4.weightx = 0;
        bagC4.weighty = 0;
        bagC4.gridx = 2;
        bagC4.gridy = 1;
        bagC4.gridheight = 1;
        bagC4.gridwidth = 2;
        bagC4.fill = GridBagConstraints.HORIZONTAL;
        jp.add(jTextAreaLogin, bagC4);

        jTextAreaPassword = new JPasswordField();
        GridBagConstraints bagC5 = new GridBagConstraints();
        bagC5.insets = new Insets(10, 10, 10, 0);
        bagC5.weightx = 0;
        bagC5.weighty = 0;
        bagC5.gridx = 2;
        bagC5.gridy = 2;
        bagC5.gridheight = 1;
        bagC5.gridwidth = 2;
        bagC5.fill = GridBagConstraints.HORIZONTAL;
        jp.add(jTextAreaPassword, bagC5);

        jButtonSignIn = new JButton("SignIn");
        jButtonSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jTextAreaLogin.getText().length() < 1){
                    return;
                }
                if(jTextAreaPassword.getPassword().length < 1){
                    return;
                }
                int i = CommandFormer.login(jTextAreaLogin.getText(), jTextAreaPassword.getPassword(), null);
                if (i == 0) {
                    jmf.showWorkPage();
                } else {
                    labelPassword.setForeground(Color.RED);
                    if(i == -1){
                        labelLogin.setForeground(Color.RED);
                    }
                }
            }
        });
        GridBagConstraints bagC6 = new GridBagConstraints();
        bagC6.insets = new Insets(10, 10, 0, 10);
        bagC6.weightx = 0;
        bagC6.weighty = 0;
        bagC6.gridx = 2;
        bagC6.gridy = 3;
        bagC6.gridheight = 1;
        bagC6.gridwidth = 1;
        jp.add(jButtonSignIn, bagC6);

        jButtonRegister = new JButton("Register");
        jButtonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jmf.showRegisterPage();
            }
        });
        GridBagConstraints bagC7 = new GridBagConstraints();
        bagC7.insets = new Insets(10, 10, 0, 0);
        bagC7.weightx = 0;
        bagC7.weighty = 0;
        bagC7.gridx = 3;
        bagC7.gridy = 3;
        bagC7.gridheight = 1;
        bagC7.gridwidth = 1;
        jp.add(jButtonRegister, bagC7);

    }

    @Override
    public void show(boolean show) {
        jmf.frame.setVisible(false);
        if(jmf.frame.isActive()){
            jmf.frame.getContentPane().remove(0);
        }
        jmf.frame.getContentPane().add(jp, 0);
        jmf.frame.setMinimumSize(new Dimension(500, 300));
        jmf.frame.setResizable(true);
        jmf.frame.pack();
        jmf.frame.setVisible(true);

    }

    private String[] names = {"Tickets", "Login", "Password", "SignIn", "Register"};

    @Override
    public void translate() {
        labelTickets.setText(LanguagesProvider.adaptPhrase(names[0]));
        labelLogin.setText(LanguagesProvider.adaptPhrase(names[1]));
        labelPassword.setText(LanguagesProvider.adaptPhrase(names[2]));
        jButtonSignIn.setText(LanguagesProvider.adaptPhrase(names[3]));
        jButtonRegister.setText(LanguagesProvider.adaptPhrase(names[4]));
    }

}
