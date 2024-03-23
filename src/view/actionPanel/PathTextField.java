package view.actionPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/****************************************
 *  PathTextField Class
 *  - Custom JTextField class
 *  - Used to display the current path
 ****************************************/
public class PathTextField extends JTextField {
    private String path;

    public PathTextField(String path) {
        this.path = path;
        this.setText(path);
        this.setEditable(false);
    }
    public PathTextField() {
//        setPreferredSize(new Dimension(200, 35));
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
