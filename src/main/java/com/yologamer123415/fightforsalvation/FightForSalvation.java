package com.yologamer123415.fightforsalvation;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;
import com.yologamer123415.fightforsalvation.inventory.Inventory;
import com.yologamer123415.fightforsalvation.player.Player;
import com.yologamer123415.fightforsalvation.usables.weapons.ranged.BowAndArrow;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;

public class FightForSalvation extends GameEngine {
	private static FightForSalvation instance;
	public static final int SCREEN_WIDTH = MapGenerator.TILESIZE * 20;
	public static final int SCREEN_HEIGHT = MapGenerator.TILESIZE * 12;

	private TextObject essenceText;
	private int level = 0;
	private boolean isInInventory;
	private Player player;
	private Inventory inventory;
	private int monstersAlive = 0;

	public static void main(String[] args) {
		instance = new FightForSalvation();
		runSketch(new String[]{"Fight for Salvation"}, instance);
	}

	public static FightForSalvation getInstance() {
		return instance;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		if (this.player == null) this.player = player;
	}

	public int getMonstersAlive() {
		return monstersAlive;
	}

	public void setMonstersAlive(int monstersAlive) {
		this.monstersAlive = monstersAlive;
	}

	public void decreaseMonstersAlive() {
		if (this.monstersAlive > 0) this.monstersAlive--;
	}

	/**
	 * Override of the addGameObject()
	 * We force it to add multiple instance of the same object
	 *
	 * @param gameObject The GameObject to add
	 */
	@Override
	public void addGameObject(GameObject gameObject) {
		this.getGameObjectItems().add(gameObject);
	}

	@Override
	public void setupGame() {
		MapGenerator.load();

		setTileMap(MapGenerator.generateTilemapFromFile(level));

		View view = new View(SCREEN_WIDTH, SCREEN_HEIGHT);
		view.setBackground(loadImage("src/main/resources/background.png"));

		setView(view);
		size(SCREEN_WIDTH, SCREEN_HEIGHT);

		this.essenceText = new TextObject("", 16);
		this.addGameObject(this.essenceText, 0, 0);

		this.inventory = new Inventory(800, 400, 200, 200);
		this.inventory.show();

		int index = this.inventory.addItem( new BowAndArrow(this.player) );
		this.inventory.setSelectedWeapon(index);
	}

	@Override
	public void update() {
		this.essenceText.setText( "Aantal essence: " + this.getPlayer().getTotalEssence() );
	}

	public void closedInventory() {

	}
}
