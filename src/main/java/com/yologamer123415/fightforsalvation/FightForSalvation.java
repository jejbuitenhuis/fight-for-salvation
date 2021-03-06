package com.yologamer123415.fightforsalvation;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.player.Player;
import com.yologamer123415.fightforsalvation.screens.EndScreen;
import com.yologamer123415.fightforsalvation.screens.StartScreen;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.userinput.IMouseInput;
import nl.han.ica.oopg.view.View;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.Iterator;

public class FightForSalvation extends GameEngine {
	private static FightForSalvation instance;

	public static final int FONT_SIZE = 16;
	public static final int SCREEN_WIDTH = MapGenerator.TILESIZE * 20;
	public static final int SCREEN_HEIGHT = MapGenerator.TILESIZE * 12;

	private StartScreen startScreen;

	private TextObject essenceText;
	private int level = 0;
	private Player player;
	private int monstersAlive = 0;

	/**
	 * Start the game.
	 * (Called by JVM)
	 *
	 * @param args The args of the start command.
	 */
	public static void main(String[] args) {
		instance = new FightForSalvation();
		runSketch(new String[]{"Fight for Salvation"}, instance);
	}

	/**
	 * Get the instance of the game.
	 *
	 * @return The instance.
	 */
	public static FightForSalvation getInstance() {
		return instance;
	}

	/**
	 * Get the player.
	 *
	 * @return The player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Set the player.
	 *
	 * @param player The player.
	 */
	public void setPlayer(Player player) {
		if (this.player == null) this.player = player;
	}

	/**
	 * Get the amount of monsters still alive.
	 *
	 * @return The amount of monsters.
	 */
	public int getMonstersAlive() {
		return this.monstersAlive;
	}

	/**
	 * Set the amount of monsters still alive.
	 *
	 * @param monstersAlive The amount of monsters alive.
	 */
	public void setMonstersAlive(int monstersAlive) {
		this.monstersAlive = monstersAlive;
	}

	/**
	 * Decrease the amount of monsters alive with one.
	 */
	public void decreaseMonstersAlive() {
		if (this.monstersAlive > 0) this.monstersAlive--;
	}

	/**
	 * Setup the View.
	 */
	private void setupView() {
		View view = new View(SCREEN_WIDTH, SCREEN_HEIGHT);
		view.setBackground( this.loadImage("src/main/resources/background.jpg") );

		this.setView(view);
		this.size(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	/**
	 * Setup the TextObjects.
	 */
	private void setupTextObjects() {
		this.essenceText = new TextObject("Aantal essence: X", FONT_SIZE);
		this.addGameObject(this.essenceText, 0, 0);
	}

	public void startGame() {
		this.setupTextObjects();
		this.startScreen.setVisible(false);
	}

	public void endGame(boolean died) {
		this.deleteAllGameOBjects();

		EndScreen endScreen = new EndScreen(died);
		this.addDashboard(endScreen);
	}

	/**
	 * Override of the mousePressed()
	 * We fixed clicking on Dashboards.
	 */
	@Override
	public void mousePressed() {
		super.mousePressed();

		//Implemented mouseclick for Dashboards
		PVector location = this.calculateRelativeMouseLocation(this.mouseX, this.mouseY);

		for ( Dashboard db : new ArrayList<>( this.getDashboards() ) ) {
			((IMouseInput) db).mousePressed((int) location.x, (int) location.y, this.mouseButton);
		}
	}

	@Override
	public void setupGame() {
		MapGenerator.load();

		this.startScreen = new StartScreen();
		this.addDashboard(this.startScreen);

		this.setupView();
		this.setTileMap( MapGenerator.generateTilemapFromFile(level) );
	}

	@Override
	public void update() {
		if (this.essenceText != null) this.essenceText.setText( "Aantal essence: " + this.getPlayer().getEssence() );
	}

	/**
	 * Called when the inventory is closed.
	 * Moves the game to the next level.
	 */
	public void closedInventory() {
		this.level++;

		Iterator<GameObject> iter = this.getGameObjectItems().iterator();
		while ( iter.hasNext() ) {
			GameObject go = iter.next();
			if (go instanceof Player || go instanceof TextObject) continue;
			iter.remove();
		}

		TileMap newTileMap;
		try {
			newTileMap = MapGenerator.generateTilemapFromFile(this.level);
		} catch (IllegalArgumentException ex) {
			this.endGame(false);
			return;
		}
		this.setTileMap(newTileMap);
	}
}
