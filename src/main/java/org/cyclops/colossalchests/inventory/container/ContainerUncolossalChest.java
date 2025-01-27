package org.cyclops.colossalchests.inventory.container;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import invtweaks.api.container.ChestContainer;
import invtweaks.api.container.ContainerSection;
import invtweaks.api.container.ContainerSectionCallback;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;
import org.cyclops.colossalchests.RegistryEntries;
import org.cyclops.cyclopscore.inventory.container.InventoryContainer;

import java.util.List;
import java.util.Map;

/**
 * @author rubensworks
 */
@ChestContainer(rowSize = 5)
public class ContainerUncolossalChest extends InventoryContainer {

    public ContainerUncolossalChest(int id, Inventory playerInventory) {
        this(id, playerInventory, new SimpleContainer(5));
    }

    public ContainerUncolossalChest(int id, Inventory playerInventory, Container inventory) {
        super(RegistryEntries.CONTAINER_UNCOLOSSAL_CHEST.get(), id, playerInventory, inventory);

        this.addInventory(inventory, 0, 44, 20, 1, getSizeInventory());
        this.addPlayerInventory(playerInventory, 8, 51);
    }

    @Override
    protected int getSizeInventory() {
        return 5;
    }

    /**
     * @return Container selection options for inventory tweaks.
     */
    @ContainerSectionCallback
    public Map<ContainerSection, List<Slot>> getContainerSelection() {
        Map<ContainerSection, List<Slot>> selection = Maps.newHashMap();
        List<Slot> chest = Lists.newArrayList();
        List<Slot> playerInventory = Lists.newArrayList();
        for(int i = 0; i < 5; i++) {
            chest.add(this.getSlot(i));
        }
        for(int i = 5; i < 5 + 4 * 9; i++) {
            playerInventory.add(this.getSlot(i));
        }
        selection.put(ContainerSection.CHEST, chest);
        selection.put(ContainerSection.INVENTORY, playerInventory);
        return selection;
    }

}
