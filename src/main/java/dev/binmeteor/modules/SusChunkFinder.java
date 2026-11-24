package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.Utils;
import meteordevelopment.meteorclient.utils.render.color.Color;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.block.entity.*;
import net.minecraft.world.chunk.Chunk;

public class SusChunkFinder extends Module {
    private final Setting<Integer> minimumStorage = settings.getDefaultGroup().add(new IntSetting.Builder()
        .name("minimum-storage")
        .description("Minimum number of storage blocks in a loaded chunk before it is marked.")
        .defaultValue(4)
        .min(2)
        .sliderRange(2, 16)
        .build()
    );

    private final Color line = new Color(255, 35, 35, 255);
    private final Color side = new Color(255, 35, 35, 28);
    private int count;

    public SusChunkFinder() {
        super(BinMeteor.CATEGORY, "sus-chunk-finder", "Marks loaded chunks containing many storage blocks. It cannot inspect unloaded chunks.");
    }

    @EventHandler
    private void onRender(Render3DEvent event) {
        count = 0;
        if (mc.world == null || mc.player == null) return;

        int y = mc.player.getBlockY();
        for (Chunk chunk : Utils.chunks()) {
            int storage = 0;
            for (BlockEntity entity : chunk.getBlockEntities().values()) {
                if (isStorage(entity)) storage++;
            }

            if (storage < minimumStorage.get()) continue;

            var pos = chunk.getPos();
            event.renderer.box(pos.getStartX(), y, pos.getStartZ(),
                pos.getStartX() + 16, y + 2, pos.getStartZ() + 16,
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
