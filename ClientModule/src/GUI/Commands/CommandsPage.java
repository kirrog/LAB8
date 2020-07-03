package GUI.Commands;

import Collection.Ticket;
import Collection.Venue;
import GUI.AbstractPageShower;
import GUI.CommandFormer;
import GUI.Localization.LanguagesProvider;
import GUI.Tables.BaseTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CommandsPage extends AbstractPageShower {

    private static String[] commandsEn = new String[]{
            //"Help",
            "Insert",
            "Update",
            "Remove Key",
            "Clear",
            "Execute Script",
            "Remove Lower",
            "Replace If Lower",
            "Remove Greater Key",
            "Filter By Venue"};

    private JFrame commandFrame = new JFrame("Commands");
    private JComboBox comboBox = new JComboBox(commandsEn);
    private JPanel workPanel = (JPanel) commandFrame.getContentPane();


    public CommandsPage() {
        commandFrame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        commandFrame.setMinimumSize(new Dimension(500, 300));
        comboBox.setEditable(false);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox) e.getSource();
                int command = box.getSelectedIndex();
                if(command < 0){
                    return;
                }
                showCommand(commandsEn[command]);
            }
        });
        workPanel.setLayout(new GridBagLayout());

        GridBagConstraints bagC1 = new GridBagConstraints();
        bagC1.insets = new Insets(10, 0, 10, 0);
        bagC1.weightx = 0;
        bagC1.weighty = 0;
        bagC1.gridx = 0;
        bagC1.gridy = 0;
        bagC1.gridheight = 1;
        bagC1.gridwidth = 2;
        bagC1.fill = GridBagConstraints.HORIZONTAL;
        workPanel.add(comboBox, bagC1);

        GridBagConstraints bagC2 = new GridBagConstraints();
        bagC2.insets = new Insets(10, 0, 10, 0);
        bagC2.weightx = 0.33;
        bagC2.weighty = 0;
        bagC2.gridx = 4;
        bagC2.gridy = 3;
        bagC2.gridheight = 1;
        bagC2.gridwidth = 2;
        workPanel.add(senderButton, bagC2);
        senderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                workCommand.execute();
            }
        });

        GridBagConstraints bagC3 = new GridBagConstraints();
        bagC3.insets = new Insets(10, 10, 10, 0);
        bagC3.weightx = 0;
        bagC3.weighty = 0;
        bagC3.gridx = 4;
        bagC3.gridy = 0;
        bagC3.gridheight = 1;
        bagC3.gridwidth = 2;
        bagC3.fill = GridBagConstraints.HORIZONTAL;
        workPanel.add(keyTextArea, bagC3);

        comboBox.removeAllItems();
        for (int i = 0; i < commandsEn.length; i++) {
            comboBox.insertItemAt(LanguagesProvider.adaptPhrase(commandsEn[i]), i);
        }

        tickGet = new TicketGetter(workPanel);
        keyTextArea.setVisible(false);
        commandFrame.setVisible(true);
        workPanel.setVisible(true);

    }

    private void showCommand(String command) {
        switch (command) {
            case "Insert":
                makeInsert();
                break;
            case "Update":
                makeUpdate(-1, null);
                break;
            case "Remove Key":
                makeRemoveKey(null);
                break;
            case "Clear":
                makeClear();
                break;
            case "Execute Script":
                makeExecuteScript(null);
                break;
            case "Remove Lower":
                makeRemoveLower();
                break;
            case "Replace If Lower":
                makeReplaceIfLower();
                break;
            case "Remove Greater Key":
                makeRemoveGreaterKey();
                break;
            case "Filter By Venue":
                makeFilterByVenue();
                break;
            default:
                break;
        }
    }

    @Override
    public void show(boolean show) {
        commandFrame.setVisible(show);
        workPanel.setVisible(show);
    }

    private String[] names = {"Commands", "Execute command"};

    @Override
    public void translate() {
        commandFrame.setTitle(LanguagesProvider.adaptPhrase(names[0]));
        senderButton.setText(LanguagesProvider.adaptPhrase(names[1]));
        comboBox.removeAllItems();
        for (int i = 0; i < commandsEn.length; i++) {
            comboBox.insertItemAt(LanguagesProvider.adaptPhrase(commandsEn[i]), i);
        }
        tickGet.translate();
    }

    private JButton senderButton = new JButton("Execute command");

    private JTextField keyTextArea = new JTextField();

    public TicketGetter tickGet;

    private InsertingParts insert = new InsertingParts();
    private UpdatingParts update = new UpdatingParts();
    private RemoveKeyParts removeKey = new RemoveKeyParts();
    private ClearParts clear = new ClearParts();
    private ExecuteScriptParts executeScript = new ExecuteScriptParts();
    private RemoveLowerParts removeLower = new RemoveLowerParts();
    private ReplaceIfLowerParts replaceIfLower = new ReplaceIfLowerParts();
    private RemoveGreaterKeyParts removeGreaterKey = new RemoveGreaterKeyParts();
    private FilterByVenueParts filterByVenue = new FilterByVenueParts();

    private ComParts workCommand = insert;

    public void makeInsert() {
        workCommand.deShow();
        workCommand = insert;
        insert.show();
    }

    public void makeUpdate(int id, Ticket ticket) {
        workCommand.deShow();
        update.setArgs(id, ticket);
        workCommand = update;
        update.show();
    }

    public void makeRemoveKey(String key) {
        workCommand.deShow();
        if(key == null){
            removeKey.setArgs(tickGet.getCurrentKey());
        }else {
            removeKey.setArgs(key);
        }

        workCommand = removeKey;
        removeKey.show();
    }

    public void makeClear() {
        workCommand.deShow();
        workCommand = clear;
        clear.show();
    }

    public void makeExecuteScript(String fileName) {
        workCommand.deShow();
        executeScript.setArgs(fileName);
        workCommand = executeScript;
        executeScript.show();
    }

    public void makeRemoveLower() {
        workCommand.deShow();
        workCommand = removeLower;
        removeLower.show();
    }

    public void makeReplaceIfLower() {
        workCommand.deShow();
        workCommand = replaceIfLower;
        replaceIfLower.show();
    }

    public void makeRemoveGreaterKey() {
        workCommand.deShow();
        workCommand = removeGreaterKey;
        removeGreaterKey.show();
    }

    public void makeFilterByVenue() {
        workCommand.deShow();
        workCommand = filterByVenue;
        filterByVenue.show();
    }

    private interface ComParts {
        void show();

        void deShow();

        void execute();
    }

    private class FilterByVenueParts implements ComParts {

        @Override
        public void show() {
            keyTextArea.setVisible(true);
            tickGet.setTicketOrVenueGetter(false);
            tickGet.show(true);
            senderButton.setVisible(true);
        }

        @Override
        public void deShow() {
            keyTextArea.setVisible(false);
            tickGet.show(false);
            senderButton.setVisible(false);
        }

        @Override
        public void execute() {
            if (tickGet.isVenueReady()) {
                Venue v = tickGet.getVenue();
                ArrayList<Ticket> result = CommandFormer.filterByVenue(v);
                BaseTableModel.fillTheTable(result);
                if (result.size() > -1) {
                    tickGet.printText("Filtered: "+ result.size() +" \n" + CommandFormer.answer);
                } else {
                    tickGet.printText("Something goes wrong\n" + CommandFormer.answer);
                }
            }else {
                tickGet.printText("Venue unready\n" + tickGet.getPrinterText());
            }
        }
    }

    private class RemoveGreaterKeyParts implements ComParts {

        @Override
        public void show() {
            tickGet.setPrinterVisible(true);
            keyTextArea.setVisible(true);
            senderButton.setVisible(true);
        }

        @Override
        public void deShow() {
            tickGet.setPrinterVisible(false);
            keyTextArea.setVisible(false);
            senderButton.setVisible(false);
        }

        @Override
        public void execute() {
            if (keyTextArea.getText() != null && !keyTextArea.getText().equals("")) {
                String key = keyTextArea.getText();
                int result = CommandFormer.removeGraterKey(key);
                if (result > -1) {
                    tickGet.printText("Tickets removed: " + result + " \n" + CommandFormer.answer);
                } else {
                    tickGet.printText("Something goes wrong\n" + CommandFormer.answer);
                }
            }
        }
    }

    private class ReplaceIfLowerParts implements ComParts {

        @Override
        public void show() {
            keyTextArea.setVisible(true);
            tickGet.setTicketOrVenueGetter(true);
            tickGet.show(true);
            senderButton.setVisible(true);
        }

        @Override
        public void deShow() {
            keyTextArea.setVisible(false);
            tickGet.setTicketOrVenueGetter(false);
            tickGet.show(false);
            senderButton.setVisible(false);
        }

        @Override
        public void execute() {
            if (tickGet.isReady()) {
                Ticket t = tickGet.getTicket();
                boolean result = CommandFormer.replaceIfLower(t.getKey(), t);
                if (result) {
                    tickGet.printText("Result of replacing: \n" + CommandFormer.answer);
                } else {
                    tickGet.printText("Something goes wrong\n" + CommandFormer.answer);
                }
            } else {
                tickGet.printText("Ticket unready\n" + tickGet.getPrinterText());
            }
        }
    }

    private class RemoveLowerParts implements ComParts {

        @Override
        public void show() {
            tickGet.setPrinterVisible(true);
            tickGet.setTicketOrVenueGetter(true);
            tickGet.show(true);
            senderButton.setVisible(true);
        }

        @Override
        public void deShow() {
            tickGet.setPrinterVisible(false);
            tickGet.setTicketOrVenueGetter(false);
            tickGet.show(false);
            senderButton.setVisible(false);
        }

        @Override
        public void execute() {
            if (tickGet.isReady()) {
                Ticket t = tickGet.getTicket();
                int result = CommandFormer.removeLower(t);
                if (result > -1) {
                    tickGet.printText("Removed tickets: " + result + "\n" + CommandFormer.answer);
                } else {
                    tickGet.printText("Something goes wrong\n" + CommandFormer.answer);
                }
            } else {
                tickGet.printText("Ticket unready\n" + tickGet.getPrinterText());
            }
        }
    }

    private class ExecuteScriptParts implements ComParts {

        String fileName = null;

        public void setArgs(String k) {
            fileName = k;
        }

        @Override
        public void show() {
            tickGet.setPrinterVisible(true);
            keyTextArea.setVisible(true);
            senderButton.setVisible(true);
        }

        @Override
        public void deShow() {
            tickGet.setPrinterVisible(false);
            keyTextArea.setVisible(false);
            senderButton.setVisible(false);
        }

        @Override
        public void execute() {
            if (fileName != null || (keyTextArea.getText() != null && !keyTextArea.getText().equals(""))) {
                fileName = keyTextArea.getText();
                String result = CommandFormer.executeScript(fileName);
                tickGet.printText(result);
            } else {
                tickGet.printText("File name can't be null");
            }

        }
    }

    private class ClearParts implements ComParts {

        @Override
        public void show() {
            tickGet.setPrinterVisible(true);
            senderButton.setVisible(true);
        }

        @Override
        public void deShow() {
            tickGet.setPrinterVisible(false);
            senderButton.setVisible(false);
        }

        @Override
        public void execute() {
            if (CommandFormer.clear()) {
                tickGet.printText("Cleared tickets\n" + CommandFormer.answer);
            } else {
                tickGet.printText("Something goes wrong: \n" + CommandFormer.answer);
            }
        }
    }

    private class RemoveKeyParts implements ComParts {

        String key = null;

        public void setArgs(String k) {
            key = k;
        }

        @Override
        public void show() {
            System.out.println("Showing remove key");
            tickGet.setPrinterVisible(true);
            keyTextArea.setVisible(true);
            keyTextArea.setText(key);
            senderButton.setVisible(true);
        }

        @Override
        public void deShow() {
            tickGet.setPrinterVisible(false);
            keyTextArea.setVisible(false);
            senderButton.setVisible(false);
        }

        @Override
        public void execute() {
            if (keyTextArea.getText() != null && !keyTextArea.getText().equals("")) {
                key = keyTextArea.getText();
                boolean result = CommandFormer.removeKey(key);
                if (result) {
                    tickGet.printText("Ticket removed\n" + CommandFormer.answer);
                } else {
                    tickGet.printText("Something goes wrong\n" + CommandFormer.answer);
                }
            } else {
                keyTextArea.setBackground(Color.RED);
                tickGet.printText("Key can't be null");
            }

        }
    }

    private class InsertingParts implements ComParts {

        @Override
        public void show() {
            tickGet.setTicketOrVenueGetter(true);
            tickGet.show(true);
            senderButton.setVisible(true);
        }

        @Override
        public void deShow() {
            tickGet.show(false);
            senderButton.setVisible(false);
        }

        @Override
        public void execute() {
            if (tickGet.isReady()) {
                Ticket t = tickGet.getTicket();
                boolean result = CommandFormer.insertKey(t.getKey(), t);
                if (result) {
                    tickGet.printText("Ticket inserted\n" + CommandFormer.answer);
                } else {
                    tickGet.printText("Something goes wrong\n" + CommandFormer.answer);
                }
            } else {
                tickGet.printText("Ticket unready\n" + tickGet.getPrinterText());
            }
        }
    }

    private class UpdatingParts implements ComParts {

        Ticket tick = null;
        int idOfTicket = -1;

        public void setArgs(int id, Ticket ticket) {
            tick = ticket;
            idOfTicket = id;
        }

        @Override
        public void show() {
            tickGet.setTicketOrVenueGetter(true);
            if (tick != null) {
                tickGet.setTicket(tick);
                keyTextArea.setText(String.valueOf(tick.getId()));
            }else {
                keyTextArea.setText(String.valueOf(idOfTicket));
            }
            keyTextArea.setVisible(true);

            tickGet.show(true);
            senderButton.setVisible(true);
        }

        @Override
        public void deShow() {
            keyTextArea.setVisible(false);
            tickGet.show(false);
            senderButton.setVisible(false);
        }

        @Override
        public void execute() {
            if (tickGet.isReady()) {
                Ticket t = tickGet.getTicket();

                int idOfTicket;
                try{
                    String num = keyTextArea.getText();
                    idOfTicket = Integer.parseInt(num);
                }catch (Exception e){
                    keyTextArea.setBackground(Color.RED);
                    tickGet.printText("Wrong id entering");
                    return;
                }

                if(! BaseTableModel.checkUserByTicketId(idOfTicket, CommandFormer.getTicketOwner())){
                    if(BaseTableModel.findTicketById(idOfTicket)){
                        keyTextArea.setBackground(Color.RED);
                        tickGet.printText("It's not your Ticket");
                    }else {
                        keyTextArea.setBackground(Color.RED);
                        tickGet.printText("Ticket with this Id doesn't exists");
                    }
                    return;
                }
                boolean result = CommandFormer.update(idOfTicket, t);
                if (result) {
                    tickGet.printText("Ticket updated\n" + CommandFormer.answer);
                } else {
                    tickGet.printText("<html>Something goes wrong\n" + CommandFormer.answer);
                }
            } else {
                tickGet.printText("Ticket unready\n" + tickGet.getPrinterText());
            }
        }
    }
}
