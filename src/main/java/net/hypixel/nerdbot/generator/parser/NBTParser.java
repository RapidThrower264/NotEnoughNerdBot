package net.hypixel.nerdbot.generator.parser;

import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class NBTParser {
    protected NBTParser() {
    }

    public abstract String getMinecraftID();
    public abstract String getItemName();
    public abstract ArrayList<String> getItemLore();
    public abstract String getColor();
    public abstract boolean isEnchanted();
    public abstract boolean hasSkullID();
    public abstract String getSkullOwnerID() throws Exception;
}
