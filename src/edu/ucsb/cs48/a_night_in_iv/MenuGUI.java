package edu.ucsb.cs48.a_night_in_iv;

/**
 * Represents the entire GUI of all Menus
 * Modifies the frame of RunGame statically depending on button input
 * @see StartMenu
 * @see LevelMenu
 * @see RunGame
 *
 * Created by Matthew R (solipsism648) on 5/14/2017.
 */
public class MenuGUI {
    public static StartMenu sMenu;
    public static LevelMenu lvlMenu;
    public static EndLevelMenu eMenu;

    public MenuGUI() {
        sMenu = new StartMenu();
        lvlMenu = new LevelMenu();
        eMenu = new EndLevelMenu();
    }

    public static void openLevelSelectMenu(){
        RunGame.fullFrame.remove(sMenu);
        RunGame.fullFrame.add(lvlMenu);
        RunGame.fullFrame.revalidate();
        RunGame.fullFrame.repaint();
    }

    public static void backToStart() {
        RunGame.fullFrame.remove(lvlMenu);
        RunGame.fullFrame.add(sMenu);
        RunGame.fullFrame.revalidate();
        RunGame.fullFrame.repaint();
    }

    public static void exitMenus(String sceneName) {
        RunGame.sceneName = sceneName;
        RunGame.fullFrame.getContentPane().removeAll();
        RunGame.fullFrame.revalidate();
        RunGame.fullFrame.repaint();
        RunGame.startGameGUI = true;
    }
}
