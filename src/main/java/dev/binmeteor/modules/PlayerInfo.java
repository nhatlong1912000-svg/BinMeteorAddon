package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.systems.modules.Module;
import net.minecraft.util.math.BlockPos;

public class PlayerInfo extends Module {
    public PlayerInfo() {
        super(BinMeteor.CATEGORY, "player-info", "Displays your own position and world information when activated.");
    }

    @Override
    public void onActivate() {
        if (mc.player == null || mc.world == null) {
            warning("Join a world before enabling this module.");
            toggle();
            return;
        }

        BlockPos pos = mc.player.getBlockPos();
        info("Position: %d, %d, %d".formatted(pos.getX(), pos.getY(), pos.getZ()));
        info("Dimension: %s".formatted(mc.world.getRegistryKey().getValue()));
        info("Facing: %s".formatted(mc.player.getHorizontalFacing().getName()));
        toggle();
    }
}
