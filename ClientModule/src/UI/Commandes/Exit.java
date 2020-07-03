package UI.Commandes;

import UI.AbstractCommand;

public class Exit extends AbstractCommand {

    @Override
    public boolean receive() {
        return true;
    }

    @Override
    public Object getResult() {
        return null;
    }

}
