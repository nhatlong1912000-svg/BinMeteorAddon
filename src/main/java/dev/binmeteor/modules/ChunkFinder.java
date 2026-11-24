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
import net.minecraft.world.chunk.Chunk;

public class ChunkFinder extends Module {
    private final Setting<Integer> maxDistance = settings.getDefaultGroup().add(new IntSetting.Builder()
        .name("max-distance")
        .description("Maximum distance from you to draw loaded chunk outlines.")
        .defaultValue(96)
        .range(16, 256)
        .sliderRange(16, 256)
        .build()
    );

    private final Color line = new Color(235, 35, 45, 220);
    private final Color side = new Color(235, 35, 45, 18);
    private int count;

    public ChunkFinder() {
        super(BinMeteor.CATEGORY, "chunk-finder", "Draws outlines around chunks already loaded by your client.");
    }

    @EventHandler
    private void onRender(Render3DEvent event) {
        count = 0;
        if (mc.world == null || mc.player == null) return;

        double limit = maxDistance.get() * maxDistance.get();
        int y = mc.player.getBlockY();

        for (Chunk chunk : Utils.chunks()) {
            var pos = chunk.getPos();
            double x = pos.getStartX() + 8.0;
            double z = pos.getStartZ() + 8.0;
            double dx = x - mc.player.getX();
            double dz = z - mc.player.getZ();
            if (dx * dx + dz * dz > limit) continue;

            int x1 = pos.getStartX();
            int z1 = pos.getStartZ();
            event.renderer.box(x1, y, z1, x1 + 16, y + 1, z1 + 16,
                side, line, ShapeMode.Lines, 0);
            count++;
        }
    }

    @Override
    public String getInfoString() {
        return Integer.toString(count);
    }
}
