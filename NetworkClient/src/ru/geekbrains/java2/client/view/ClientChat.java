package ru.geekbrains.java2.client.view;

import ru.geekbrains.java2.client.controller.ClientController;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientChat extends JFrame {
    private JPanel pnlMain;
    private JList<String> usersList;
    private JTextField txtMessage;
    private JButton btnSend;
    private JTextArea txtChatArea;

    private ClientController controller;

    public ClientChat(ClientController controller) {
        this.controller = controller;
        //setTitle(controller.getUsername());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setContentPane(pnlMain);
        addListeners();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                controller.shutdown();
            }
        });
    }

    private void addListeners() {
        btnSend.addActionListener(e->sendMessage());
        txtMessage.addActionListener(e->sendMessage());
    }

    private void sendMessage() {
        String message =
                txtMessage.getText().trim();
        if (message.isEmpty())
            return;
        appendOwnMessage(message);
        controller.sendMessage(message);
        txtMessage.setText(null);
    }

    public void appendMessage(String message) {
        SwingUtilities.invokeLater(()->{
            txtChatArea.append(message);
            txtChatArea.append(System.lineSeparator());
        });
    }

    private void appendOwnMessage(String message) {
        appendMessage("Я: "+message);
    }

}
