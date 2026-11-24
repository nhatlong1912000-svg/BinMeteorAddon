package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.systems.modules.Module;

/**
 * Highlights potentially interesting chunk patterns in client-loaded terrain.
 * It does not access server files or data outside the game client's view.
 */
public class SusChunkFinder extends Module {
    public SusChunkFinder() {
        super(BinMeteor.CATEGORY, "sus-chunk-finder", "Experimental indicators for unusual patterns in loaded chunks.");
    }
}
