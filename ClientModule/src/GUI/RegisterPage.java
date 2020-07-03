package GUI;

import GUI.Localization.LanguagesProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegisterPage extends AbstractPageShower {


    private JLabel labelRegistration;
    private JLabel labelLogin;
    private JLabel labelPassword;
    private JLabel labelRPassword;
    private JLabel labelMail;
    private JTextField jTextAreaLogin;
    private JPasswordField jTextAreaPassword;
    private JPasswordField jTextAreaRPassword;
    private JTextField jTextAreaMail;
    private JButton jButtonRegister;
    private JButton jButtonReRegister;
    private JButton jButtonExit;
    private JButton jButtonChange;
    private JComboBox<String> locations = LanguagesProvider.locationsR;

    public RegisterPage(JMainFrame jMainFrame) {

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

        labelRegistration = new JLabel("Registration");
        GridBagConstraints bagC1 = new GridBagConstraints();
        bagC1.insets = new Insets(0, 10, 10, 0);
        bagC1.weightx = 0;
        bagC1.weighty = 0;
        bagC1.gridx = 2;
        bagC1.gridy = 0;
        bagC1.gridheight = 1;
        bagC1.gridwidth = 2;
        jp.add(labelRegistration, bagC1);

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

        labelRPassword = new JLabel("Repeat Password");
        GridBagConstraints bagC4 = new GridBagConstraints();
        bagC4.insets = new Insets(10, 0, 10, 10);
        bagC4.weightx = 0;
        bagC4.weighty = 0;
        bagC4.gridx = 0;
        bagC4.gridy = 3;
        bagC4.gridheight = 1;
        bagC4.gridwidth = 2;
        bagC4.fill = GridBagConstraints.HORIZONTAL;
        jp.add(labelRPassword, bagC4);

        labelMail = new JLabel("Mail");
        GridBagConstraints bagC5 = new GridBagConstraints();
        bagC5.insets = new Insets(10, 0, 10, 10);
        bagC5.weightx = 0;
        bagC5.weighty = 0;
        bagC5.gridx = 0;
        bagC5.gridy = 4;
        bagC5.gridheight = 1;
        bagC5.gridwidth = 2;
        bagC5.fill = GridBagConstraints.HORIZONTAL;
        jp.add(labelMail, bagC5);

        jTextAreaLogin = new JTextField();
        GridBagConstraints bagC6 = new GridBagConstraints();
        bagC6.insets = new Insets(10, 10, 10, 0);
        bagC6.weightx = 0;
        bagC6.weighty = 0;
        bagC6.gridx = 2;
        bagC6.gridy = 1;
        bagC6.gridheight = 1;
        bagC6.gridwidth = 2;
        bagC6.fill = GridBagConstraints.HORIZONTAL;
        jp.add(jTextAreaLogin, bagC6);

        jTextAreaPassword = new JPasswordField();
        GridBagConstraints bagC7 = new GridBagConstraints();
        bagC7.insets = new Insets(10, 10, 10, 0);
        bagC7.weightx = 0;
        bagC7.weighty = 0;
        bagC7.gridx = 2;
        bagC7.gridy = 2;
        bagC7.gridheight = 1;
        bagC7.gridwidth = 2;
        bagC7.fill = GridBagConstraints.HORIZONTAL;
        jp.add(jTextAreaPassword, bagC7);

        jTextAreaRPassword = new JPasswordField();
        GridBagConstraints bagC8 = new GridBagConstraints();
        bagC8.insets = new Insets(10, 10, 10, 0);
        bagC8.weightx = 0;
        bagC8.weighty = 0;
        bagC8.gridx = 2;
        bagC8.gridy = 3;
        bagC8.gridheight = 1;
        bagC8.gridwidth = 2;
        bagC8.fill = GridBagConstraints.HORIZONTAL;
        jp.add(jTextAreaRPassword, bagC8);

        jTextAreaMail = new JTextField();
        GridBagConstraints bagC9 = new GridBagConstraints();
        bagC9.insets = new Insets(10, 10, 10, 0);
        bagC9.weightx = 0;
        bagC9.weighty = 0;
        bagC9.gridx = 2;
        bagC9.gridy = 4;
        bagC9.gridheight = 1;
        bagC9.gridwidth = 2;
        bagC9.fill = GridBagConstraints.HORIZONTAL;
        jp.add(jTextAreaMail, bagC9);

        jButtonRegister = new JButton("Register");
        jButtonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = jTextAreaLogin.getText();
                char[] password = jTextAreaPassword.getPassword();

                char[] rPassword = jTextAreaRPassword.getPassword();

                String mail = jTextAreaMail.getText();
                if((mail.length()) > 0 && (!mail.contains("@"))){
                    jTextAreaMail.setBackground(Color.RED);
                    return;
                }
                if(Arrays.equals(password,rPassword)){
                    int i = CommandFormer.register(login, password, mail);
                    if(i == 0){
                        jmf.showLoginPage();
                    }else {
                        jTextAreaLogin.setBackground(Color.RED);
                    }
                }else{
                    jTextAreaRPassword.setBackground(Color.RED);
                }

            }
        });
        GridBagConstraints bagC10 = new GridBagConstraints();
        bagC10.insets = new Insets(10, 0, 0, 10);
        bagC10.weightx = 0;
        bagC10.weighty = 0;
        bagC10.gridx = 2;
        bagC10.gridy = 5;
        bagC10.gridheight = 1;
        bagC10.gridwidth = 1;
        jp.add(jButtonRegister, bagC10);

        jButtonExit = new JButton("Exit");
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit clicked");
                jmf.showLoginPage();
            }
        });
        GridBagConstraints bagC11 = new GridBagConstraints();
        bagC11.insets = new Insets(10, 0, 0, 0);
        bagC11.weightx = 0;
        bagC11.weighty = 0;
        bagC11.gridx = 3;
        bagC11.gridy = 5;
        bagC11.gridheight = 1;
        bagC11.gridwidth = 1;
        jp.add(jButtonExit, bagC11);

        jButtonReRegister = new JButton("ReRegister");
        jButtonReRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = jTextAreaLogin.getText();
                char[] password = jTextAreaPassword.getPassword();
                char[] rPassword = jTextAreaRPassword.getPassword();
                String mail = jTextAreaMail.getText();
                if(password.equals(rPassword)){
                    int i = CommandFormer.reRegister(login, password, mail);
                    if(i == 0){
                        jmf.showLoginPage();
                    }else if(i == -1){
                        jTextAreaLogin.setBackground(Color.RED);
                    }else if(i == -2){
                        jTextAreaPassword.setBackground(Color.RED);
                    }else {
                        jTextAreaLogin.setBackground(Color.RED);
                        jTextAreaPassword.setBackground(Color.RED);
                        jTextAreaRPassword.setBackground(Color.RED);
                        jTextAreaMail.setBackground(Color.RED);
                    }
                }else{
                    jTextAreaRPassword.setBackground(Color.RED);
                }
            }
        });
        GridBagConstraints bagC12 = new GridBagConstraints();
        bagC12.insets = new Insets(10, 0, 0, 10);
        bagC12.weightx = 0;
        bagC12.weighty = 0;
        bagC12.gridx = 0;
        bagC12.gridy = 5;
        bagC12.gridheight = 1;
        bagC12.gridwidth = 1;
        jp.add(jButtonReRegister, bagC12);
    }

    @Override
    public void show(boolean show) {
        jmf.frame.setVisible(false);
        jmf.frame.getContentPane().remove(0);
        jmf.frame.getContentPane().add(jp, 0);
        jmf.frame.setMinimumSize(new Dimension(500, 300));
        jmf.frame.setResizable(true);
        jmf.frame.pack();
        jmf.frame.setVisible(true);
    }

    private String[] names = {"Registration", "Login", "Password", "Repeat Password", "Mail", "Register", "Exit"};

    @Override
    public void translate() {
        labelRegistration.setText(LanguagesProvider.adaptPhrase(names[0]));
        labelLogin.setText(LanguagesProvider.adaptPhrase(names[1]));
        labelPassword.setText(LanguagesProvider.adaptPhrase(names[2]));
        labelRPassword.setText(LanguagesProvider.adaptPhrase(names[3]));
        labelMail.setText(LanguagesProvider.adaptPhrase(names[4]));
        jButtonRegister.setText(LanguagesProvider.adaptPhrase(names[5]));
        jButtonExit.setText(LanguagesProvider.adaptPhrase(names[6]));
    }

}
