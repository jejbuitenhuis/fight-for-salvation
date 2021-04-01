package com.yologamer123415.fightforsalvation.usables.weapons.ranged;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.helpers.Cooldown;
import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.movables.Arrow;
import com.yologamer123415.fightforsalvation.object.movables.Movable;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class BowAndArrow extends Weapon implements ICollidableWithGameObjects {
	private static final float COOLDOWN = 1.5F;
	private final Cooldown cooldown;

	public BowAndArrow(GameObject holder) {
		super("Bow and Arrow", new Sprite("src/main/resources/usables/weapons/ranged/crossbow_01.png"), holder);
		this.cooldown = new Cooldown(COOLDOWN);
	}

	@Override
	public void use(Vector mousePos) {
		if (this.cooldown.isInCooldown()) return;

		Movable arrowMovable = new Arrow(mousePos, this.holder);
		FightForSalvation.getInstance().getGameObjectItems().add(arrowMovable);
		arrowMovable.startMoving();

		this.cooldown.start();
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> list) {

	}
}
