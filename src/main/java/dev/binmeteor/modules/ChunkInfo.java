package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.systems.modules.Module;
import net.minecraft.util.math.ChunkPos;

public class ChunkInfo extends Module {
    private final Setting<Boolean> showChunk = settings.getDefaultGroup().add(new BoolSetting.Builder()
        .name("show-chunk-coordinates")
        .description("Show the current chunk coordinates.")
        .defaultValue(true)
        .build()
    );

    private final Setting<Boolean> showRegion = settings.getDefaultGroup().add(new BoolSetting.Builder()
        .name("show-region-coordinates")
        .description("Show the current region coordinates.")
        .defaultValue(true)
        .build()
    );

    public ChunkInfo() {
        super(BinMeteor.CATEGORY, "chunk-info", "Shows configurable information about your current chunk.");
    }

    @Override
    public void onActivate() {
        if (mc.player == null) {
            warning("Join a world before enabling this module.");
            return;
        }

        ChunkPos pos = mc.player.getChunkPos();
        if (showChunk.get()) info("Current chunk: X %d, Z %d".formatted(pos.x, pos.z));
        if (showRegion.get()) info("Region: X %d, Z %d".formatted(pos.x >> 5, pos.z >> 5));

        if (!showChunk.get() && !showRegion.get()) info("Both Chunk Info outputs are disabled in Settings.");
    }
}
