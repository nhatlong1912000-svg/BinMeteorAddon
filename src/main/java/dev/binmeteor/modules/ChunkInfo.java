package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.systems.modules.Module;
import net.minecraft.util.math.ChunkPos;

public class ChunkInfo extends Module {
    public ChunkInfo() {
        super(BinMeteor.CATEGORY, "chunk-info", "Shows the chunk coordinates you are currently standing in.");
    }

    @Override
    public void onActivate() {
        if (mc.player == null) return;
        ChunkPos pos = mc.player.getChunkPos();
        info("Current chunk: X %d, Z %d".formatted(pos.x, pos.z));
        info("Region: X %d, Z %d".formatted(pos.x >> 5, pos.z >> 5));
        toggle();
    }
}
