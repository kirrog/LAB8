package GUI;

//In this I write Frame and contain examplers of all other pages
//Created at start and provide opportunity to commands
//Call pages as need

import GUI.Graphic.Painter;
import GUI.Localization.LanguagesProvider;
import GUI.Localization.TimeAdapter;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class JMainFrame{

    JFrame frame = new JFrame("Client");
    private LoginPage lp;
    private RegisterPage rp;
    private WorkPage wp;


    public JMainFrame()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JPanel(), 0);
        lp = new LoginPage(this);
        rp = new RegisterPage(this);
        wp = new WorkPage(this);

        //Подключение слушателя окна
        frame.addWindowListener(new WindowListener() {
            public void windowActivated  (WindowEvent event) {
                System.out.println("Activated");
            }
            public void windowClosed     (WindowEvent event) {
                System.out.println("Closed");
            }
            public void windowDeactivated(WindowEvent event) {
                System.out.println("Deactivated");
            }
            public void windowDeiconified(WindowEvent event) {
                System.out.println("Deiconified");
            }
            public void windowIconified  (WindowEvent event) {
                System.out.println("Iconified");
            }
            public void windowOpened     (WindowEvent event) {
                System.out.println("Opened");
                lp.show(true);
            }
            public void windowClosing (WindowEvent event) {
                TimeAdapter.work = false;
                CommandFormer.exit();
                System.out.println("Attention, Closing!!");
            }
        });
        new TimeAdapter().start();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private String[] names = {"Client"};

    public void translate(){
        frame.setTitle(LanguagesProvider.adaptPhrase(names[0]));
        lp.translate();
        rp.translate();
        wp.translate();
    }

    public Painter getPainter(){
        return wp.getPainter();
    }

    public void showLoginPage(){
        lp.show(true);
    }

    public void showRegisterPage(){
        rp.show(true);
    }

    public void showWorkPage(){
        wp.show(true);
    }

    void repaint(){
        frame.repaint();
    }
}
