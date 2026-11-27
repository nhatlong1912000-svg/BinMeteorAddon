package dev.binmeteor.modules;

import dev.binmeteor.BinMeteor;
import meteordevelopment.meteorclient.systems.modules.Module;

public class Hub extends Module {
    public Hub() {
        super(BinMeteor.CATEGORY, "hub", "Shows Bin Meteor addon information.");
    }

    @Override
    public void onActivate() {
        info("Bin Meteor | Red / Black");
        info("Minecraft 1.21.11 | Fabric | Meteor");
        info("Utility and visual modules are available in the Bin Meteor category.");
        toggle();
    }
}
