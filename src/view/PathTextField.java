package view;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PathTextField extends JTextField {
    private String path;

    public PathTextField(String path) {
        this.path = path;
        this.setText(path);
        this.setEditable(false);
    }
    public PathTextField() {
        setPreferredSize(new Dimension(200, 35));
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setEditable(false);
        this.path = "Path: ";
        this.setText(path);
    }
    public void setPath(String path) {
        this.path = path;
        this.setText("Path: " + path);
    }
    public String getPath() {
        return path;
    }
}
