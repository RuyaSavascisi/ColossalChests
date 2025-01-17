package org.cyclops.colossalchests.modcompat;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.cyclops.colossalchests.Reference;
import org.cyclops.colossalchests.block.ChestMaterial;
import org.cyclops.colossalchests.client.render.blockentity.RenderTileEntityColossalChest;
import org.cyclops.cyclopscore.helper.MinecraftHelpers;
import org.cyclops.cyclopscore.modcompat.ICompatInitializer;
import org.cyclops.cyclopscore.modcompat.IModCompat;

/**
 * Mod compat for the Iron Chest mod.
 * @author rubensworks
 *
 */
public class IronChestModCompat implements IModCompat {

	@OnlyIn(Dist.CLIENT)
	private void overrideTextures() {
		String prefix = "model/";
		RenderTileEntityColossalChest.TEXTURES_CHEST.put(ChestMaterial.COPPER,
				ResourceLocation.fromNamespaceAndPath(Reference.MOD_IRONCHEST, prefix + "copper_chest"));
		RenderTileEntityColossalChest.TEXTURES_CHEST.put(ChestMaterial.IRON,
				ResourceLocation.fromNamespaceAndPath(Reference.MOD_IRONCHEST, prefix + "iron_chest"));
		RenderTileEntityColossalChest.TEXTURES_CHEST.put(ChestMaterial.SILVER,
				ResourceLocation.fromNamespaceAndPath(Reference.MOD_IRONCHEST, prefix + "silver_chest"));
		RenderTileEntityColossalChest.TEXTURES_CHEST.put(ChestMaterial.GOLD,
				ResourceLocation.fromNamespaceAndPath(Reference.MOD_IRONCHEST, prefix + "gold_chest"));
		RenderTileEntityColossalChest.TEXTURES_CHEST.put(ChestMaterial.DIAMOND,
				ResourceLocation.fromNamespaceAndPath(Reference.MOD_IRONCHEST, prefix + "diamond_chest"));
		RenderTileEntityColossalChest.TEXTURES_CHEST.put(ChestMaterial.OBSIDIAN,
				ResourceLocation.fromNamespaceAndPath(Reference.MOD_IRONCHEST, prefix + "obsidian_chest"));
	}

	@Override
	public String getId() {
		return Reference.MOD_IRONCHEST;
	}

	@Override
	public boolean isEnabledDefault() {
		return true;
	}

	@Override
	public String getComment() {
		return "If the non-wood variants should use the textures of the Iron Chest mod.";
	}

	@Override
	public ICompatInitializer createInitializer() {
		return () -> {
			if(MinecraftHelpers.isClientSide()) {
				overrideTextures();
			}
		};
	}

}
