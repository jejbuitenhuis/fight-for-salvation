package com.yologamer123415.fightforsalvation.usables.weapons.normal;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class Knife extends Weapon {
	public Knife(GameObject holder) {
		super("Knife", new Sprite("src/main/resources/usables/weapons/normal/Knife.png"), holder);
	}

	@Override
	public void use(Vector mousePos) {

	}
}
