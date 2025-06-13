package tiktactoe;

import javax.swing.SwingUtilities;

import tiktactoe.view.GuiMenu;

/**
 * Main driver class that runs the application
 */
public class TikTakToeDriver {

    /**
     * The main method that runs the application
     * @param args User arguments
     */
    public static void main(String[] args) {
        GuiMenu guiMenu = new GuiMenu();
        SwingUtilities.invokeLater(guiMenu::getUserInfo);
    }
}
