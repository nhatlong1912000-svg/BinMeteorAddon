package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.systems.modules.Module;

/**
 * Chunk exploration module. The first iteration exposes a safe module entry;
 * scan and render controls will be added after API compatibility is verified.
 */
public class ChunkFinder extends Module {
    public ChunkFinder() {
        super(BinMeteor.CATEGORY, "chunk-finder", "Chunk exploration tools for areas already loaded by your client.");
    }
}
