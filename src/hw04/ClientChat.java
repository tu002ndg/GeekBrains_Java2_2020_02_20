package hw04;

import javax.swing.*;

public class ClientChat {
    private JPanel pnlMain;
    private JTextField txtMessage;
    private JButton btnSend;
    private JList lstContacts;
    private JTextArea txtMessageArea;

    private final DefaultListModel<String> listModel
            = new DefaultListModel<>();
    private final String[] contacts = {
            "Alex",
            "Gary",
            "Jane",
            "John",
            "Mike",
            "Patric"
    };

    public ClientChat() {
        lstContacts.setModel(listModel);
        for (String s:contacts
                ) {
            listModel.addElement(s);
        }
        //lstContacts.setSelectedIndex(0);
        btnSend.addActionListener(e ->doAction());
        txtMessage.addActionListener(e ->doAction());

    }

    private void doAction() {
        sendMessage("Me", txtMessage.getText());
        txtMessage.setText("");
        if (!lstContacts.isSelectionEmpty())
        sendMessage(
                lstContacts.getSelectedValue().toString(),
                "Hello!");
    }

    private void sendMessage(String sender, String msg) {
        if (!msg.trim().isEmpty())
        txtMessageArea.append(String.format("%s> %s%n",sender,msg));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Chat ");
        frame.setBounds(300, 100, 400, 500);
        frame.setContentPane(new ClientChat().pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
