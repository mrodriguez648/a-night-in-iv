package edu.ucsb.cs48.a_night_in_iv;

import javax.swing.*;

/**
 * Main class that of game
 * Constructs all necessary components for the entire game in specific order.
 * 1st: Game model is made. 2nd: Game component is made. 3rd: Game model is added to game component.
 *
 * Resources folder contains all sprites and data used.
 *
 * Built upon by authors below
 *
 * @author Karl Wang, Matthew Rodriguez, Armin Mahini, Cristobal Caballero, Adrian Olguin , Greg Whitier (CS48)
 * @author Alex Wood (for CS56, W12, UCSB, 2/16/2012)
 * @author Danielle Dodd and George Lieu (CS56)
 * @author Lisa Liao and Patrick Vidican (CS56)
 * @version CS48
 * @see GameComponent
 * @see GameModel
 */


public class GameGUI {
    static final String resourcesDir = "/resources/";
    GameModel game;
    GameComponent component;

    MoveAction moveActionDown, moveActionUp, moveActionLeft, moveActionRight;

    public GameGUI() {
        component = new GameComponent();
        component.setOpaque(true);
        addPlayerMovementBindings();
    }

    public GameModel loadGame(String sceneName){
        game = generateGameModel(sceneName);
        component.setGame(game);
        updateBindings(game.getPlayer());
        return game;
    }

    public GameModel generateGameModel(String sceneName){
        GameModel newGame = new GameModel(sceneName);
        newGame.setPlayer(new Player( 3, 3, 16, 8, "player", newGame , newGame.getCurrentMap() ));
        generateGenericItem(6, newGame, 0, 1, "COKE", -5);
        generateGenericItem(4, newGame, 0, 0, "BEER", 20);
        generateGenericItem(4, newGame, 0, 2, "HD", -10);
        generateGenericItem(3, newGame, 0, 2, "BEER", 20);
        generateGenericItem(4, newGame, 0, 3, "PZ", -10);
        generateGenericItem(7, newGame, 0, 4, "BEER", 20);
        generateGenericItem(3, newGame, 0, 5, "BN", -15);
        generateGenericItem(5, newGame, 0, 5, "BEER", 20);
        generateGenericItem(5, newGame, 0, 6, "BEER", 20);
        generateGenericItem(4, newGame, 0, 7, "COKE", -5);
        generateWinItem(newGame, 0, 7, "WIN");
        generateRingItem(newGame, 0, 3, "R");
        return newGame;
    }

    public void updateBindings(Player player) {
        moveActionDown.setPlayer(player);
        moveActionUp.setPlayer(player);
        moveActionLeft.setPlayer(player);
        moveActionRight.setPlayer(player);
    }

    /**
     * creates multiple items spawned randomly within the bounds of the map at specified Y and X coordinate within section[][]
     * @param howMany the number of genericItems to create
     * @param game current game class running
     * @param sceneY corresponding Y coordinate for map
     * @param sceneX corresponding X coordinate for map
     * @param textureID the sprite ID # associated with the item
     * @param boM the amount that affects the players blackout bar
     * @see MapSection for how section[][] is constructed
     */
    static public void generateGenericItem(int howMany, GameModel game, int sceneY, int sceneX, String textureID, int boM) {
        MapSection itemMapSect = game.getMapInDirection(sceneY, sceneX);
        for (int i = 0; i < howMany; i++) {
            int xTile, yTile;
            do {
                xTile = (int) (Math.random() * game.mapWidth);
                yTile = (int) (Math.random() * game.mapHeight);
            }while ( itemMapSect.getSprite(yTile, xTile) != null );
            itemMapSect.setSprite(new GenericItem(game.getTexture(textureID), boM, itemMapSect), yTile, xTile);
            System.out.println("Item " + textureID + " at section " + itemMapSect.name + " (" + yTile + "," + xTile + ")");
        }
    }

    private void generateWinItem(GameModel game, int sceneY, int sceneX, String textureID){
        MapSection itemMapSect = game.getMapInDirection(sceneY, sceneX);
        int xTile, yTile;
        xTile = 22;//(int)(.5 * game.mapWidth);
        for(int i = 3; i < 15; i++) {
            yTile = i;
            itemMapSect.setSprite(new WinItem(game.getTexture(textureID), itemMapSect), yTile, xTile);
            System.out.println("Item " + textureID + " at section " + itemMapSect.name + " (" + yTile + "," + xTile + ")");
        }
    }

    private void generateRingItem(GameModel game, int sceneY, int sceneX, String textureID){
        MapSection itemMapSect = game.getMapInDirection(sceneY, sceneX);
        int xTile, yTile;
        xTile = 13;//(int)(.5 * game.mapWidth);
        yTile = 0; //(int)(.5 * game.mapHeight);
        itemMapSect.setSprite(new RingItem(game.getTexture(textureID), itemMapSect), yTile, xTile);
        System.out.println("Item " + textureID + " at section " + itemMapSect.name + " (" + yTile + "," + xTile + ")");
    }

    /**
     * adds keyboard binds to player based on X/Y tile increments using keyboard arrow keys
     * @see MoveAction
     */
    private void addPlayerMovementBindings() {
        moveActionDown   = new MoveAction(0, 1);
        moveActionUp     = new MoveAction(0, -1);
        moveActionLeft   = new MoveAction(-1, 0);
        moveActionRight  = new MoveAction(1, 0);

        // https://docs.oracle.com/javase/7/docs/api/javax/swing/KeyStroke.html
        component.registerKeyboardAction(moveActionDown,    KeyStroke.getKeyStroke("DOWN"),     JComponent.WHEN_IN_FOCUSED_WINDOW);
        component.registerKeyboardAction(moveActionUp,      KeyStroke.getKeyStroke("UP"),       JComponent.WHEN_IN_FOCUSED_WINDOW);
        component.registerKeyboardAction(moveActionLeft,    KeyStroke.getKeyStroke("LEFT"),     JComponent.WHEN_IN_FOCUSED_WINDOW);
        component.registerKeyboardAction(moveActionRight,   KeyStroke.getKeyStroke("RIGHT"),    JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
}
