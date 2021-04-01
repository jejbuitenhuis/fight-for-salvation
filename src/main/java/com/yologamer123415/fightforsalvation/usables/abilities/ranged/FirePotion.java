package com.yologamer123415.fightforsalvation.usables.abilities.ranged;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class FirePotion extends Potion {
	public FirePotion(GameObject holder) {
		super("Fire Potion", new Sprite("src/main/resources/usables/abilities/ranged/rood.png"), holder);
	}

	@Override
	public Movable getMovable(Vector mousePos) {
		return new com.yologamer123415.fightforsalvation.object.movables.FirePotion(mousePos, holder);
	}

	@Override
	public void update() {

	}
}
