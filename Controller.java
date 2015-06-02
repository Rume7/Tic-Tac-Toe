package mygame;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.control.*;

import javax.swing.*;
import java.util.Iterator;

public class Controller {

    private boolean isFirstPlayer = true;
    private FadeTransition ft;

    public void buttonClickHandler(ActionEvent evt)
    {
        Button clickedButton = (Button) evt.getTarget();
        String buttonLabel = clickedButton.getText();

        if ("".equals(buttonLabel) && isFirstPlayer)
        {
            clickedButton.setText("X");
            isFirstPlayer = false;
            find3InARow();
        }
        else if ("".equals(buttonLabel) && !isFirstPlayer)
        {
            clickedButton.setText("O");
            isFirstPlayer = true;
            find3InARow();
        }
    }

    @FXML Button b1;
    @FXML Button b2;
    @FXML Button b3;
    @FXML Button b4;
    @FXML Button b5;
    @FXML Button b6;
    @FXML Button b7;
    @FXML Button b8;
    @FXML Button b9;

    @FXML GridPane gameBoard;

    private boolean find3InARow()
    {
        // Row 1
        if ("" != b1.getText() && b1.getText() == b2.getText() && b2.getText() == b3.getText())
        {
            highlightWinningCombo(b1, b2, b3);
            return true;
        }
        // Row 2
        if ("" != b4.getText() && b4.getText() == b5.getText() && b5.getText() == b6.getText())
        {
            highlightWinningCombo(b4, b5, b6);
            return true;
        }

        // Row 3
        if ("" != b7.getText() && b7.getText() == b8.getText() && b8.getText() == b9.getText())
        {
            highlightWinningCombo(b7, b8, b9);
            return true;
        }

        // Column 1
        if ("" != b1.getText() && b1.getText() == b4.getText() && b4.getText() == b7.getText())
        {
            highlightWinningCombo(b1, b4, b7);
            return true;
        }
        // Column 2
        if ("" != b2.getText() && b2.getText() == b5.getText() && b5.getText() == b8.getText())
        {
            highlightWinningCombo(b2, b5, b8);
            return true;
        }
        // Column 3
        if ("" != b3.getText() && b3.getText() == b6.getText() && b6.getText() == b9.getText())
        {
            highlightWinningCombo(b3, b6, b9);
            return true;
        }
        // Diagonal 1
        if ("" != b1.getText() && b1.getText() == b5.getText() && b5.getText() == b9.getText())
        {
            highlightWinningCombo(b1, b5, b9);
            return true;
        }
        // Diagonal 2
        if ("" != b3.getText() && b3.getText() == b5.getText() && b5.getText() == b7.getText())
        {
            highlightWinningCombo(b3, b5, b7);
            return true;
        }
        return false;
    }

    private void highlightWinningCombo(Button... Buttons)// first, Button second, Button third)
    {
        for (Button btn : Buttons)
        {
            btn.getStyleClass().add("winning-square");
            applyFadeTransition(btn);
        }
    }

    private void applyFadeTransition(Button winningButton)
    {
        ft = new FadeTransition(Duration.millis(700), winningButton);

        ft.setFromValue(1.0);
        ft.setToValue(0.1);
        ft.setCycleCount(6);
        ft.setAutoReverse(true);
        ft.play();
    }

    public void menuClickHandler(ActionEvent evt)
    {
        MenuItem clickedMenu = (MenuItem) evt.getTarget();
        String menuLabel = clickedMenu.getText();

        if ("Play".equals(menuLabel)){
            ObservableList<Node> buttons = gameBoard.getChildren();

            Iterator<Node> iterate = buttons.iterator();

            while(iterate.hasNext())
            {
                Button btn = (Button)iterate.next();
                btn.setText("");
                btn.getStyleClass().remove("winning-square");
            }
            isFirstPlayer = true;
        }

        if ("Quit".equals(menuLabel))
        {
            System.exit(0);
        }

        if("About".equals(menuLabel))
        {
            class Box extends JFrame
            {
                Box()
                {
                    JOptionPane.showMessageDialog(null, "This is the famous Tic Tac-Toe game for two players",
                                                                "About", JFrame.EXIT_ON_CLOSE);
                }
            }
            JFrame box = new Box();
            box.setVisible(true);
        }

        if ("How to Play".equals(menuLabel))
        {
            System.out.println("Players play X and O alternately");
        }
    }
}
