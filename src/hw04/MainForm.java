package hw04;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {
    private JPanel pnlMain;
    private JTextField txtMessage;
    private JButton btnSend;
    private JList lstContacts;
    private JList lstMessages;

    private final DefaultListModel<String> listModel
            = new DefaultListModel<>();
    private final DefaultListModel<String> listModelMsg
            = new DefaultListModel<>();
    private final String[] contacts = {
            "Alex",
            "Gary",
            "Jane",
            "John",
            "Mike",
            "Patric"
    };

    private final StringBuilder sb = new StringBuilder();

    public MainForm() {
        lstContacts.setModel(listModel);
        for (String s:contacts
                ) {
            listModel.addElement(s);
        }
        lstContacts.setSelectedIndex(0);
        lstMessages.setModel(listModelMsg);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtMessage.getText().isEmpty())
                listModelMsg.addElement(
                        "Me> "+txtMessage.getText());
                txtMessage.setText("");
                listModelMsg.addElement(
                        lstContacts.getSelectedValue().toString()
                                +"> "+"Hello!");
            }
        });

    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat ");
        frame.setBounds(300, 100, 400, 500);
        frame.setContentPane(new MainForm().pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setVisible(true);
    }
}
