package dev.binmeteor;

import meteordevelopment.meteorclient.addons.GithubRepo;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinMeteor extends MeteorAddon {
    public static final Logger LOG = LoggerFactory.getLogger("Bin Meteor");
    public static final Category CATEGORY = new Category("Bin Meteor");

    @Override
    public void onInitialize() {
        LOG.info("Bin Meteor is loading.");
        Modules.get().add(new dev.binmeteor.modules.ChunkInfo());
        Modules.get().add(new dev.binmeteor.modules.PlayerInfo());
        Modules.get().add(new dev.binmeteor.modules.ChunkFinder());
        Modules.get().add(new dev.binmeteor.modules.SusChunkFinder());
        Modules.get().add(new dev.binmeteor.modules.StorageESP());
        Modules.get().add(new dev.binmeteor.modules.ChunkOverlayRenderer());
        Modules.get().add(new dev.binmeteor.modules.SusZoneFX());
        Modules.get().add(new dev.binmeteor.modules.Hub());
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "dev.binmeteor";
    }

    @Override
    public GithubRepo getRepo() {
        return new GithubRepo("nhatlong1912000-svg", "BinMeteorAddon");
    }
}
