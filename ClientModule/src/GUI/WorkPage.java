package GUI;

//Contains template to exit and links to table and graphic

import GUI.Commands.CommandsPage;
import GUI.Graphic.Painter;
import GUI.Localization.LanguagesProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkPage extends AbstractPageShower {

    private TablePage tp;
    private GraphicPage gp;
    JPanel jpTemplate;
    JPanel mainJP;

    CardLayout cardLayout;

    private static JLabel labelCollectionInfo;
    private JLabel labelUserInfo;
    private static JLabel currentTime;
    private JButton jButtonTable;
    private JButton jButtonGraphic;
    private JButton jButtonCommands;
    private JButton jButtonExit;
    private JComboBox<String> locations = LanguagesProvider.locations;

    private JScrollPane jspInfo;

    public static void setTime(String  str){
        labelCollectionInfo.setText(LanguagesProvider.adaptPhrase(CommandFormer.info()));
        currentTime.setText(str);
    }

    public Painter getPainter(){
        return gp.getPainter();
    }

    public WorkPage(JMainFrame jMainFrame) {
        jmf = jMainFrame;
        jp = new JPanel(new GridBagLayout());
        mainJP = new JPanel(new GridBagLayout());

        jpTemplate = new JPanel();
        cardLayout = new CardLayout();
        jpTemplate.setLayout(cardLayout);

        labelCollectionInfo = new JLabel(getInfoAboutCollection());
        GridBagConstraints bagC1 = new GridBagConstraints();
        bagC1.insets = new Insets(0, 10, 10, 10);
        bagC1.weightx = 1;
        bagC1.weighty = 0;
        bagC1.gridx = 6;
        bagC1.gridy = 0;
        bagC1.gridheight = 2;
        bagC1.gridwidth = 4;
        bagC1.fill = GridBagConstraints.HORIZONTAL;

        jp.add(labelCollectionInfo, bagC1);

        labelUserInfo = new JLabel();
        GridBagConstraints bagC2 = new GridBagConstraints();
        bagC2.insets = new Insets(0, 10, 10, 0);
        bagC2.weightx = 0;
        bagC2.weighty = 0;
        bagC2.gridx = 12;
        bagC2.gridy = 0;
        bagC2.gridheight = 1;
        bagC2.gridwidth = 1;
        bagC2.fill = GridBagConstraints.HORIZONTAL;
        jspInfo = new JScrollPane(labelUserInfo);
        jp.add(jspInfo, bagC2);

        jButtonTable = new JButton("Table");
        jButtonTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Table clicked");
                show(true);
            }
        });
        GridBagConstraints bagC3 = new GridBagConstraints();
        bagC3.insets = new Insets(0, 0, 10, 0);
        bagC3.weightx = 0;
        bagC3.weighty = 0;
        bagC3.gridx = 0;
        bagC3.gridy = 0;
        bagC3.gridheight = 1;
        bagC3.gridwidth = 3;
        bagC3.fill = GridBagConstraints.HORIZONTAL;
        jp.add(jButtonTable, bagC3);

        jButtonGraphic = new JButton("Graphic");
        jButtonGraphic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Graphic clicked");
                show(false);
            }
        });
        GridBagConstraints bagC4 = new GridBagConstraints();
        bagC4.insets = new Insets(0, 0, 10, 10);
        bagC4.weightx = 0;
        bagC4.weighty = 0;
        bagC4.gridx = 3;
        bagC4.gridy = 0;
        bagC4.gridheight = 1;
        bagC4.gridwidth = 3;
        bagC4.fill = GridBagConstraints.HORIZONTAL;
        jp.add(jButtonGraphic, bagC4);

        jButtonCommands = new JButton("Commands");
        jButtonCommands.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Commands clicked");
                startFormingCommands();
            }
        });
        GridBagConstraints bagC10 = new GridBagConstraints();
        bagC10.insets = new Insets(10, 0, 10, 10);
        bagC10.weightx = 0;
        bagC10.weighty = 0;
        bagC10.gridx = 5;
        bagC10.gridy = 1;
        bagC10.gridheight = 1;
        bagC10.gridwidth = 1;
        jp.add(jButtonCommands, bagC10);

        jButtonExit = new JButton("Exit");
        jButtonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit clicked");
                jmf.showLoginPage();
            }
        });
        GridBagConstraints bagC11 = new GridBagConstraints();
        bagC11.insets = new Insets(10, 10, 10, 0);
        bagC11.weightx = 0;
        bagC11.weighty = 0;
        bagC11.gridx = 12;
        bagC11.gridy = 1;
        bagC11.gridheight = 1;
        bagC11.gridwidth = 1;
        jp.add(jButtonExit, bagC11);

        currentTime = new JLabel();
        GridBagConstraints bagC13 = new GridBagConstraints();
        bagC13.insets = new Insets(0, 10, 10, 10);
        bagC13.weightx = 0;
        bagC13.weighty = 0;
        bagC13.gridx = 10;
        bagC13.gridy = 1;
        bagC13.gridheight = 1;
        bagC13.gridwidth = 2;
        jp.add(currentTime, bagC13);

        GridBagConstraints bagC14 = new GridBagConstraints();
        bagC14.insets = new Insets(10, 10, 10, 0);
        bagC14.weightx = 0;
        bagC14.weighty = 0;
        bagC14.gridx = 10;
        bagC14.gridy = 0;
        bagC14.gridheight = 1;
        bagC14.gridwidth = 2;
        jp.add(locations, bagC14);

        GridBagConstraints bagC15 = new GridBagConstraints();
        bagC15.insets = new Insets(0, 0, 0, 0);
        bagC15.weightx = 0;
        bagC15.weighty = 0;
        bagC15.gridx = 0;
        bagC15.gridy = 0;
        bagC15.gridheight = 2;
        bagC15.gridwidth = 13;
        bagC15.fill = GridBagConstraints.HORIZONTAL;
        mainJP.add(jp, bagC15);

        GridBagConstraints bagC16 = new GridBagConstraints();
        bagC16.insets = new Insets(0, 0, 0, 0);
        bagC16.weightx = 1;
        bagC16.weighty = 1;
        bagC16.gridx = 0;
        bagC16.gridy = 2;
        bagC16.gridheight = 1;
        bagC16.gridwidth = 13;
        bagC16.fill = GridBagConstraints.BOTH;
        mainJP.add(jpTemplate, bagC16);

        mainJP.setBackground(Color.darkGray);

        tp = new TablePage(this);
        gp = new GraphicPage(this);
    }

    @Override
    public void show(boolean show) {
        jmf.frame.setVisible(false);
        if (show) {
            labelUserInfo.setText(getInfoAboutUser());
            gp.show(false);
            tp.show(true);
        } else {
            tp.show(false);
            gp.show(true);
        }
        jmf.frame.getContentPane().remove(0);
        jmf.frame.getContentPane().add(mainJP, 0);
        jmf.frame.setMinimumSize(new Dimension(600, 400));
        jmf.frame.setResizable(true);
        jmf.frame.pack();
        jmf.frame.setVisible(true);

    }

    private String[] names = {"Collection info", "User info", "Table", "Graphic", "Commands", "Exit"};

    @Override
    public void translate() {
        names[0] = labelCollectionInfo.getText();
        labelCollectionInfo.setText(LanguagesProvider.adaptPhrase(names[0]));
        names[1] = labelUserInfo.getText();
        labelUserInfo.setText(LanguagesProvider.adaptPhrase(names[1]));
        jButtonTable.setText(LanguagesProvider.adaptPhrase(names[2]));
        jButtonGraphic.setText(LanguagesProvider.adaptPhrase(names[3]));
        jButtonCommands.setText(LanguagesProvider.adaptPhrase(names[4]));
        jButtonExit.setText(LanguagesProvider.adaptPhrase(names[5]));
        gp.translate();
        tp.translate();
        if(comPage != null){
            comPage.translate();
        }
    }

    private String getInfoAboutCollection() {
        return CommandFormer.info();
    }

    private String getInfoAboutUser() {
        return "<html>" + CommandFormer.ticketOwner.getName() + "<br>" + CommandFormer.ticketOwner.getMail() + "</html>";
    }

    private CommandsPage comPage;

    private void startFormingCommands() {
        comPage = new CommandsPage();
        comPage.translate();
    }
}
