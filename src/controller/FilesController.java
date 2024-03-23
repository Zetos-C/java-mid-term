package controller;

import model.FileItem;
import model.FilesTreeModel;
import model.action.ActionItems;
import view.FilesView;
import view.renderer.FileTreeCellRenderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilesController implements ActionListener {
    private FilesView view;
    private FileItem file;
    private FilesTreeModel rootTree;
    private ActionItems actionItems;

    public FilesController(FilesView view, FilesTreeModel tree, ActionItems actionItems) {
        this.view = view;
        this.rootTree = tree;
    }
    private void setKeyBindings() {
        view.getTree().getInputMap().put(KeyStroke.getKeyStroke("F2"), "rename");
//        view.getTree().getActionMap().put("rename", file.getRenameAction());
        view.getTree().getInputMap().put(KeyStroke.getKeyStroke("DELETE"), "delete");
//        view.getTree().getActionMap().put("delete", actionItems.deleteFile());
        Action copyAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call your copy method here
                System.out.println("Copy");
            }
        };
        Action pasteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call your paste method here
                System.out.println("Paste");
            }
        };

        KeyStroke copyKeyStroke = KeyStroke.getKeyStroke("control C");
        KeyStroke pasteKeyStroke = KeyStroke.getKeyStroke("control V");

        view.getFileTable().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(copyKeyStroke, "Copy");
        view.getFileTable().getRootPane().getActionMap().put("Copy", copyAction);

        view.getFileTable().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(pasteKeyStroke, "Paste");
        view.getFileTable().getActionMap().put("Paste", pasteAction);
    }

    public void setTreeModel() {
        rootTree = new FilesTreeModel();
        rootTree.setRootTree();
        view.getTree().setModel(rootTree);
        view.getTree().setCellRenderer(new FileTreeCellRenderer());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Open")) {
            actionItems.openFile();
        } else if (e.getActionCommand().equals("Copy")) {
//            actionItems.copyFile(file.getPath());
        } else if (e.getActionCommand().equals("Cut")) {
            actionItems.cutFile(file.getPath());
        } else if (e.getActionCommand().equals("Paste")) {
            actionItems.pasteFile();
        } else if (e.getActionCommand().equals("Delete")) {
            actionItems.deleteFile();
        } else if (e.getActionCommand().equals("Rename")) {
            actionItems.renameFile();
        } else if (e.getActionCommand().equals("Properties")) {
            actionItems.propertiesFile();
        }
    }
}
