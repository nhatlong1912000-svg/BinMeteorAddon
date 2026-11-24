package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.Utils;
import meteordevelopment.meteorclient.utils.render.color.Color;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.block.entity.*;

public class StorageESP extends Module {
    private final Color line = new Color(255, 45, 45, 255);
    private final Color side = new Color(255, 45, 45, 35);
    private int count;

    public StorageESP() {
        super(BinMeteor.CATEGORY, "storage-esp", "Highlights storage blocks in chunks currently loaded by your client.");
    }

    @EventHandler
    private void onRender(Render3DEvent event) {
        count = 0;
        if (mc.world == null || mc.player == null) return;

        for (BlockEntity entity : Utils.blockEntities()) {
            if (!isStorage(entity)) continue;
            var pos = entity.getPos();
            event.renderer.box(pos.getX(), pos.getY(), pos.getZ(),
                pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1,
                side, line, ShapeMode.Both, 0);
            count++;
        }
    }

    private boolean isStorage(BlockEntity entity) {
        return entity instanceof ChestBlockEntity
            || entity instanceof BarrelBlockEntity
            || entity instanceof ShulkerBoxBlockEntity
            || entity instanceof EnderChestBlockEntity
            || entity instanceof HopperBlockEntity
            || entity instanceof DispenserBlockEntity
            || entity instanceof AbstractFurnaceBlockEntity;
    }

    @Override
    public String getInfoString() {
        return Integer.toString(count);
    }
}
