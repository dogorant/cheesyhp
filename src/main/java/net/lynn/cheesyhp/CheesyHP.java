package net.lynn.cheesyhp;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.lynn.cheesyhp.item.ModItems;
import net.lynn.cheesyhp.item.custom.MushroomItem;
import net.lynn.cheesyhp.util.HeartData;
import net.lynn.cheesyhp.util.ModLootTableModifiers;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class CheesyHP implements ModInitializer {
	public static final String MOD_ID = "cheesyhp";

	// This logger is used to write text to the console and the log file
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.

		ModItems.registerModItems();
		ModLootTableModifiers.modifyLootTables();

		ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
			HeartData oldData = (HeartData) oldPlayer;
			HeartData newData = (HeartData) newPlayer;

			double currentExtra = oldData.getExtraHearts();
			double totalMaxHealthBeforeDeath = oldPlayer.getMaxHealth();

			if (totalMaxHealthBeforeDeath > 20.0){
				double updatedExtra = currentExtra - 2.0;

				if (updatedExtra < 0.0) {
					updatedExtra = 0.0;
				}

				newData.setExtraHearts(updatedExtra);
			} else {
				newData.setExtraHearts(currentExtra);
			}



		});

		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			HeartData data = (HeartData) newPlayer;
			double extraHealth = data.getExtraHearts();

			if (extraHealth > 0){
				EntityAttributeInstance attrInstance = newPlayer.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
				if (attrInstance != null){
					EntityAttributeModifier modifier = new EntityAttributeModifier(
							MushroomItem.MODIFIER_ID, extraHealth, EntityAttributeModifier.Operation.ADD_VALUE
					);
					attrInstance.addPersistentModifier(modifier);
					newPlayer.setHealth(newPlayer.getMaxHealth());
				}
			}
		});
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
