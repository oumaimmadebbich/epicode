/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;

/**
 *
 * @author trabe
 */

public class HyperlinkTableCell<S> extends TableCell<S, String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            File file = new File(item);
            String fileName = file.getName();
            Hyperlink hyperlink = new Hyperlink(fileName);
            hyperlink.setOnAction(event -> {
                try {
                    Desktop.getDesktop().browse(file.toURI());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            setGraphic(hyperlink);
        }
    }
}
