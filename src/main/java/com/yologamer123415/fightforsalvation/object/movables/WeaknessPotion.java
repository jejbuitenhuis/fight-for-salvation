package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class WeaknessPotion extends Potion {
	/**
	 * Construct a new Weakness Potion.
	 *
	 * @param path The path of the Weakness potion.
	 * @param shooter The shooter of the Weakness potion.
	 */
	public WeaknessPotion(Vector path, GameObject shooter) {
		super( new Sprite("src/main/resources/usables/abilities/ranged/weakness_potion.png"), path, shooter );
	}

	@Override
	public void collidedWithGameObjects(List<GameObject> gameObjects) {

	}
}
