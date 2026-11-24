package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.systems.modules.Module;

/**
 * Storage visualization module. Rendering options will be introduced in the
 * next iteration after the initial 1.21.11 addon build is verified.
 */
public class StorageESP extends Module {
    public StorageESP() {
        super(BinMeteor.CATEGORY, "storage-esp", "Storage visualization for containers visible to the client.");
    }
}
