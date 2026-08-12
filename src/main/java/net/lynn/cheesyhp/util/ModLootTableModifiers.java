package net.lynn.cheesyhp.util;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.lynn.cheesyhp.item.ModItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;

import java.util.List;

public class ModLootTableModifiers {
    private static final List<RegistryKey<LootTable>> CHESTS = List.of(
            LootTables.END_CITY_TREASURE_CHEST,
            LootTables.ANCIENT_CITY_CHEST,
            LootTables.ANCIENT_CITY_ICE_BOX_CHEST,
            LootTables.TRIAL_CHAMBERS_CORRIDOR_CHEST,
            LootTables.TRIAL_CHAMBERS_ENTRANCE_CHEST,
            LootTables.TRIAL_CHAMBERS_INTERSECTION_CHEST,
            LootTables.TRIAL_CHAMBERS_REWARD_CHEST,
            LootTables.TRIAL_CHAMBERS_REWARD_COMMON_CHEST,
            LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_CHEST,
            LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON_CHEST,
            LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_RARE_CHEST,
            LootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE_CHEST,
            LootTables.TRIAL_CHAMBERS_REWARD_RARE_CHEST,
            LootTables.TRIAL_CHAMBERS_REWARD_UNIQUE_CHEST,
            LootTables.TRIAL_CHAMBERS_SUPPLY_CHEST,
            LootTables.TRIAL_CHAMBERS_INTERSECTION_CHEST,
            LootTables.SIMPLE_DUNGEON_CHEST,
            LootTables.IGLOO_CHEST_CHEST,
            LootTables.ABANDONED_MINESHAFT_CHEST,
            LootTables.BASTION_TREASURE_CHEST,
            LootTables.BASTION_OTHER_CHEST,
            LootTables.BASTION_BRIDGE_CHEST,
            LootTables.BURIED_TREASURE_CHEST,
            LootTables.DESERT_PYRAMID_CHEST,
            LootTables.END_CITY_TREASURE_CHEST,
            LootTables.JUNGLE_TEMPLE_CHEST,
            LootTables.JUNGLE_TEMPLE_DISPENSER_CHEST,
            LootTables.NETHER_BRIDGE_CHEST,
            LootTables.PILLAGER_OUTPOST_CHEST,
            LootTables.RUINED_PORTAL_CHEST,
            LootTables.SHIPWRECK_SUPPLY_CHEST,
            LootTables.SHIPWRECK_TREASURE_CHEST,
            LootTables.SHIPWRECK_MAP_CHEST,
            LootTables.SIMPLE_DUNGEON_CHEST,
            LootTables.STRONGHOLD_CORRIDOR_CHEST,
            LootTables.STRONGHOLD_CROSSING_CHEST,
            LootTables.STRONGHOLD_LIBRARY_CHEST,
            LootTables.UNDERWATER_RUIN_BIG_CHEST,
            LootTables.UNDERWATER_RUIN_SMALL_CHEST,
            LootTables.VILLAGE_ARMORER_CHEST,
            LootTables.VILLAGE_BUTCHER_CHEST,
            LootTables.VILLAGE_CARTOGRAPHER_CHEST,
            LootTables.VILLAGE_FISHER_CHEST,
            LootTables.VILLAGE_FLETCHER_CHEST,
            LootTables.VILLAGE_MASON_CHEST,
            LootTables.VILLAGE_TANNERY_CHEST,
            LootTables.VILLAGE_TEMPLE_CHEST,
            LootTables.VILLAGE_TOOLSMITH_CHEST,
            LootTables.VILLAGE_WEAPONSMITH_CHEST,
            LootTables.VILLAGE_TAIGA_HOUSE_CHEST,
            LootTables.VILLAGE_SNOWY_HOUSE_CHEST,
            LootTables.VILLAGE_SAVANNA_HOUSE_CHEST,
            LootTables.WOODLAND_MANSION_CHEST
    );

    public static void modifyLootTables(){
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {


            if(CHESTS.contains(key)){
                addItemToChest(tableBuilder);
            }
        });
    }

    public static void addItemToChest(LootTable.Builder tableBuilder){
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(1.0f))
                    .with(ItemEntry.builder(ModItems.SPORE))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)).build());

            tableBuilder.pool(poolBuilder.build());
    }
}
