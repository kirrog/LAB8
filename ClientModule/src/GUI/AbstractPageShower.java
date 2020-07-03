package GUI;

import javax.swing.*;

public abstract class AbstractPageShower {

    JMainFrame jmf;
    JPanel jp;

    public abstract void show(boolean show);

    public abstract void translate();

}
