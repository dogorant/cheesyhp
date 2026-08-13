package net.lynn.cheesyhp.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lynn.cheesyhp.CheesyHP;
import net.lynn.cheesyhp.item.custom.MushroomItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item MAGIC_MUSHROOM = registerItem("magic_mushroom", new MushroomItem(new Item.Settings()));
    public static final Item SPORE = registerItem("spore", new Item(new Item.Settings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(CheesyHP.MOD_ID, name), item);
    }

    public static void registerModItems(){
        CheesyHP.LOGGER.info("registering mod items for " + CheesyHP.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(MAGIC_MUSHROOM);
            entries.add(SPORE);
        });
    }
}
