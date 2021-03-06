package com.yologamer123415.fightforsalvation.object.movables;

import com.yologamer123415.fightforsalvation.helpers.Vector;
import com.yologamer123415.fightforsalvation.object.Damageable;
import com.yologamer123415.fightforsalvation.usables.weapons.Weapon;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

import java.util.List;

public class Arrow extends Movable {
	private static final int HP = 8;

	/**
	 * Construct a new Arrow.
	 *
	 * @param path The path of the arrow.
	 * @param shooter The shooter of this arrow (NEVER the bow).
	 */
	public Arrow(Vector path, GameObject shooter) {
		super( new Sprite("src/main/resources/usables/weapons/ranged/arrow.png"), path, shooter );
	}

	@Override
	public void collidedWithGameObjects(List<GameObject> gameObjects) {
		final int damage = Weapon.getCorrectedDamage(this.shooter, HP);

		for (GameObject go : gameObjects) {
			if ( !go.equals(this.shooter) && go instanceof Damageable ) {
				((Damageable) go).damage(damage);
			}
		}
	}
}
