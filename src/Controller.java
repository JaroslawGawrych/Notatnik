import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.*;

public class Controller {
    private View view;
    private Model model;
    Controller(Model model, View view){
        this.model = model;
        this.view = view;
    }
    public void initView(){

    }
    public void initController(){
        view.getLeft().addActionListener(e -> {
            view.getText().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        });
        view.getRight().addActionListener(e -> {
            view.getText().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        });
        view.getExit().addActionListener(e -> {
            System.exit(0);
        });
        view.getPraca().addActionListener(e -> {
            if(model.getPraca() == null) {
                String text = JOptionPane.showInputDialog("podaj adres pracy");
                model.setPraca(text);
            }
            view.getText().insert(model.getPraca(), view.getText().getCaretPosition());
        });
        view.getDom().addActionListener(e -> {
            if(model.getDom() == null) {
                String text = JOptionPane.showInputDialog("podaj adres domu");
                model.setDom(text);
            }
            view.getText().insert(model.getDom(), view.getText().getCaretPosition());
        });
        view.getSzkola().addActionListener(e -> {
            if(model.getSzkola() == null) {
                String text = JOptionPane.showInputDialog("podaj adres szkoly");
                model.setSzkola(text);
            }
            view.getText().insert(model.getSzkola(), view.getText().getCaretPosition());
        });
        view.getOpen().addActionListener(e -> {
            int returnVal = view.getFc().showOpenDialog(view.getFrame());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    view.setSelectedFile(new File(view.getFc().getSelectedFile().getAbsolutePath()));
                    view.getFc().setCurrentDirectory(view.getSelectedFile());
                    FileReader fr = new FileReader(view.getSelectedFile());
                    BufferedReader br = new BufferedReader(fr);
                    String line = "", newText = "";
                    newText = br.readLine();
                    while((line = br.readLine())!=null){
                        newText += "\n" + line;
                    }
                    view.getFrame().setTitle("prosty edytor - " + view.getSelectedFile());
                    view.getText().setText(newText);
                    view.getStatus().setText(model.getStatus()[2]);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view.getFrame(), "wrong path");
                }
            }
        });
        view.getSave().addActionListener(e -> {
            if (view.getSelectedFile() != null) {
                try {
                    FileWriter wr = new FileWriter(view.getSelectedFile());
                    BufferedWriter bw = new BufferedWriter(wr);
                    bw.write(view.getText().getText());
                    bw.close();
                    view.getStatus().setText(model.getStatus()[2]);
                    return;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            saveAs();
        });
        view.getSaveAs().addActionListener(e -> {
            saveAs();
        });
        view.getText().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                view.getStatus().setText(model.getStatus()[1]);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                view.getStatus().setText(model.getStatus()[1]);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                view.getStatus().setText(model.getStatus()[1]);
            }
        });
    }

    private void saveAs() {
        try {
            view.getFc().showOpenDialog(view.getFrame());
            FileWriter wr = new FileWriter(view.getFc().getSelectedFile().getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(wr);
            bw.write(view.getText().getText());
            bw.close();
            view.getStatus().setText(model.getStatus()[2]);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
