package com.nyfaria.nyfcore.init;

import com.nyfaria.nyfcore.NyfCore;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NyfCore.MODID);
}
