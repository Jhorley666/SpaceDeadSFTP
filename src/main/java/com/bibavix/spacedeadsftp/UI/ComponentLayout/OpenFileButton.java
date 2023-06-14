package com.bibavix.spacedeadsftp.UI.ComponentLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OpenFileButton extends JButton implements ActionListener {
    JFileChooser fileChooser;
    private JTextArea pathAreaText;

    private JTextField objectKey;
    public OpenFileButton(JTextArea pathAreaText, JTextField objectKey){
        this.setSize(40, 16);
        this.setText("Open File");
        fileChooser = new JFileChooser();
        addActionListener(this);
        this.pathAreaText = pathAreaText;
        this.objectKey = objectKey;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            String path = fileChooser.getSelectedFile().getPath();
            String fileName = fileChooser.getSelectedFile().getName();
            JOptionPane.showMessageDialog(this, "Path:" + path);
            pathAreaText.setText(path);
            objectKey.setText(fileName);
        } else {
            JOptionPane.showMessageDialog(this, "Not file selected.");
            pathAreaText.setText("");
        }
    }
}