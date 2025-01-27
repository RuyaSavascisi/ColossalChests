package org.cyclops.colossalchests.network.packet;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.cyclops.colossalchests.Reference;
import org.cyclops.cyclopscore.network.CodecField;
import org.cyclops.cyclopscore.network.PacketCodec;

/**
 * Packet for setting slots with id's larger than max short size (65535).
 * {@link ClientboundContainerSetSlotPacket}.
 * @author rubensworks
 *
 */
public class ClientboundContainerSetSlotPacketLarge extends PacketCodec<ClientboundContainerSetSlotPacketLarge> {

	public static final Type<ClientboundContainerSetSlotPacketLarge> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "clientbound_container_set_slot_packet_large"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ClientboundContainerSetSlotPacketLarge> CODEC = getCodec(ClientboundContainerSetSlotPacketLarge::new);

	@CodecField
	private int windowId;
	@CodecField
	private int stateId;
	@CodecField
	private int slot;
	@CodecField
	private ItemStack itemStack;

    public ClientboundContainerSetSlotPacketLarge() {
		super(TYPE);
    }

    public ClientboundContainerSetSlotPacketLarge(int windowId, int stateId, int slot, ItemStack itemStack) {
		super(TYPE);
		this.windowId = windowId;
		this.stateId = stateId;
		this.slot = slot;
		this.itemStack = itemStack;
    }

	@Override
	public boolean isAsync() {
		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void actionClient(Level world, Player player) {
		// Modified code from NetHandlerPlayClient#handleSetSlot
		if (windowId == player.containerMenu.containerId) {
			player.containerMenu.setItem(slot, stateId, itemStack);
		}
	}

	@Override
	public void actionServer(Level world, ServerPlayer player) {

	}

}
