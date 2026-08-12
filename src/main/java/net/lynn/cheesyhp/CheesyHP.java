package net.lynn.cheesyhp;

import net.fabricmc.api.ModInitializer;

import net.lynn.cheesyhp.item.ModItems;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheesyHP implements ModInitializer {
	public static final String MOD_ID = "cheesyhp";

	// This logger is used to write text to the console and the log file
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.

		ModItems.registerModItems();
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
