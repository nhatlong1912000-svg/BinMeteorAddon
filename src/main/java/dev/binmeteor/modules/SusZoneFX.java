package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.render.color.Color;
import meteordevelopment.orbit.EventHandler;

public class SusZoneFX extends Module {
    private final Setting<Integer> opacity = settings.getDefaultGroup().add(new IntSetting.Builder()
        .name("fx-opacity").description("Opacity of the decorative current-chunk effect.")
        .defaultValue(12).range(0, 100).sliderRange(0, 100).build());

    public SusZoneFX() {
        super(BinMeteor.CATEGORY, "sus-zone-fx", "Decorative red effect on your current chunk; does not detect or score locations.");
    }

    @EventHandler
    private void onRender(Render3DEvent event) {
        if (mc.player == null || mc.world == null) return;
        var pos = mc.player.getChunkPos();
        int x = pos.getStartX(), z = pos.getStartZ(), y = mc.player.getBlockY();
        Color line = new Color(255, 20, 35, 170);
        Color fill = new Color(255, 20, 35, opacity.get() * 255 / 100);
        event.renderer.box(x, y, z, x + 16, y + 2, z + 16, fill, line, ShapeMode.Both, 0);
    }
}
