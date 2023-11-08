import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class View {
    private JTextArea text;
    private JLabel fg, bg, size, status;
    private ButtonGroup groupF, groupB;
    private JMenuItem exit, praca, dom, szkola, open, save, saveAs, left, right;
    private JFrame frame;
    private JFileChooser fc;
    private File selectedFile;

    public JMenuItem getLeft() {
        return left;
    }

    public JMenuItem getRight() {
        return right;
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile;
    }

    public JFileChooser getFc() {
        return fc;
    }

    public JLabel getStatus() {
        return status;
    }

    public JMenuItem getOpen() {
        return open;
    }

    public JMenuItem getSave() {
        return save;
    }

    public JMenuItem getSaveAs() {
        return saveAs;
    }

    public JTextArea getText() {
        return text;
    }

    public JMenuItem getPraca() {
        return praca;
    }

    public JMenuItem getDom() {
        return dom;
    }

    public JMenuItem getSzkola() {
        return szkola;
    }

    public JMenuItem getExit() {
        return exit;
    }

    public JFrame getFrame() {
        return frame;
    }

    View(){
        frame = new JFrame();
        text = new JTextArea();
        JScrollPane scroll = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        frame.setLayout(new BorderLayout());

        frame.setTitle("prosty edytor - bez tytulu");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(200,200);
        frame.setPreferredSize(new Dimension(500, 500));
        frame.add(scroll, BorderLayout.CENTER);

        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("file");
        open = new JMenuItem("open", KeyEvent.VK_O);
        save = new JMenuItem("save", KeyEvent.VK_S);
        saveAs = new JMenuItem("save as", KeyEvent.VK_A);
        exit = new JMenuItem("exit", KeyEvent.VK_X);
        open.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        saveAs.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK, true));
        exit.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK, true));
        fc = new JFileChooser();
        JMenuItem[] fileOptions = {open, save, saveAs};
        for (JMenuItem i :
                fileOptions) {
            file.add(i);
        }
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.red);
        file.add(separator);
        file.add(exit);

        JMenu edit = new JMenu("edit");
        JMenu adresy = new JMenu("adresy");
        praca = new JMenuItem("praca", KeyEvent.VK_P);
        dom = new JMenuItem("dom", KeyEvent.VK_D);
        szkola = new JMenuItem("szkola", KeyEvent.VK_S);
        praca.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        dom.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        szkola.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        JMenuItem[] adresyOptions = {praca, dom, szkola};
        for (JMenuItem i :
                adresyOptions) {
            adresy.add(i);
        }
        edit.add(adresy);

        JMenu options = new JMenu("options");
        JMenu foreground = new JMenu("foreground");
        JMenu background = new JMenu("background");
        JMenu font = new JMenu("font size");
        String[] names = {"black", "white", "red", "green", "blue", "yellow", "orange"};
        Color[] colors = {Color.black, Color.white, Color.red, Color.green, Color.blue, Color.yellow, Color.orange};
        groupF = new ButtonGroup();
        groupB = new ButtonGroup();
        for (int i = 0; i < colors.length; i++) {
            foreground.add(newMenuItem(names[i], colors[i],groupF));
            background.add(newMenuItem(names[i], colors[i],groupB));
        }
        for (int i = 8; i <= 24; i+=2) {
            font.add(size(i));
        }
        options.add(foreground);
        options.add(background);
        options.add(font);

        JMenu style = new JMenu("style");
        JMenuItem[] array = {
                left = new JMenuItem("left", KeyEvent.VK_L),
                right = new JMenuItem("right", KeyEvent.VK_R)};

        left.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        right.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));

        for (JMenuItem i :
                array) {
            style.add(i);
        }

        JMenu[] menu = {file, edit, options, style};
        for (JMenu m :
                menu) {
            bar.add(m);
        }
        frame.setJMenuBar(bar);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel left = new JPanel();
        left.setLayout(new FlowLayout());
        fg = new JLabel("fg");
        bg = new JLabel("bg");
        size = new JLabel("size");
        left.add(fg);
        left.add(bg);
        left.add(size);
        panel.add(left, BorderLayout.LINE_START);
        status = new JLabel("new");
        panel.add(status, BorderLayout.LINE_END);
        frame.add(panel, BorderLayout.PAGE_END);

        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);
    }
    JMenuItem newMenuItem(String name, Color color, ButtonGroup group) {
        JRadioButtonMenuItem c = new JRadioButtonMenuItem(name, new Kolo(8, 8, color));
        c.setForeground(color);
        group.add(c);
        if (group.equals(groupF)) {
            c.addActionListener(e ->
                    {
                        if (c.isSelected()) {
                            fg.setIcon(new Kolo(8, 8, text.getForeground()));
                            text.setForeground(color);
                        }
                    }
            );
        } else if (group.equals(groupB)) {
            c.addActionListener(e -> {
                if (c.isSelected()) {
                    bg.setIcon(new Kolo(8, 8, text.getBackground()));
                    text.setBackground(color);
                }
            });
        }
        return c;
    }
    JMenuItem size(int fontSize){
        String name = fontSize + " pts";
        JMenuItem c = new JMenuItem(name);
        c.setFont(new Font(null, Font.PLAIN, fontSize));
        c.addActionListener(e -> {
            size.setText("" + fontSize);
            text.setFont(c.getFont());
        });
        return c;
    }
}
