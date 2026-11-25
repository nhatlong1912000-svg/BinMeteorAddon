package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.render.color.Color;
import meteordevelopment.orbit.EventHandler;

public class ChunkOverlayRenderer extends Module {
    private final Setting<Integer> opacity = settings.getDefaultGroup().add(new IntSetting.Builder()
        .name("fill-opacity").description("Opacity of the current chunk overlay.")
        .defaultValue(25).range(0, 100).sliderRange(0, 100).build());
    private final Setting<Integer> height = settings.getDefaultGroup().add(new IntSetting.Builder()
        .name("height").description("Overlay height above your current block.")
        .defaultValue(1).range(1, 8).sliderRange(1, 8).build());

    public ChunkOverlayRenderer() {
        super(BinMeteor.CATEGORY, "chunk-overlay-renderer", "Draws a simple overlay on your current chunk only.");
    }

    @EventHandler
    private void onRender(Render3DEvent event) {
        if (mc.player == null || mc.world == null) return;
        var pos = mc.player.getChunkPos();
        int x = pos.getStartX(), z = pos.getStartZ();
        Color line = new Color(235, 40, 50, 220);
        Color fill = new Color(235, 40, 50, opacity.get() * 255 / 100);
        event.renderer.box(x, mc.player.getBlockY(), z, x + 16, mc.player.getBlockY() + height.get(), z + 16,
            fill, line, ShapeMode.Both, 0);
    }
}
