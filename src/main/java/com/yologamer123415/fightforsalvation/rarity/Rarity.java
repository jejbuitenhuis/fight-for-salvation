package com.yologamer123415.fightforsalvation.api;

public enum Rarity {
	COMMON(50),
	NORMAL(35),
	EPIC(15);

	/**
	 * The chance to get this rarity
	 */
	private final int chance;

	Rarity(int rarity) {
		this.chance = rarity;
	}

	public static Rarity getRarity() {
		Rarity[] values = Rarity.values();
		int random = (int) (Math.random() * 100);

		System.out.println(random);

		for (int i = 0; i < values.length; i++) {
			int boundry = 0;

			for (int j = 0; j <= i; j++) boundry += values[j].chance;

			if (random <= boundry) return values[i];
		}

		return Rarity.COMMON;
	}
}
