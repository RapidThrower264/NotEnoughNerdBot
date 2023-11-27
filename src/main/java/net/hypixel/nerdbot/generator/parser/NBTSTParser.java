package net.hypixel.nerdbot.generator.parser;

import com.google.gson.JsonArray;

import java.util.ArrayList;

public class NBTSTParser extends NBTParser {
    private JsonArray ench;
    private DisplayTagST display;

    @Override
    public String getMinecraftID() {
        return null;
    }

    @Override
    public String getItemName() {
        return display == null ? null : display.getItemName();
    }

    @Override
    public ArrayList<String> getItemLore() {
        return display == null ? null : display.getItemLore();
    }

    @Override
    public String getColor() {
        return display == null ? null : display.getColor();
    }

    @Override
    public boolean isEnchanted() {
        return ench != null;
    }

    @Override
    public boolean hasSkullID() {
        return false;
    }

    @Override
    public String getSkullOwnerID() throws Exception {
        return null;
    }

    private class DisplayTagST {
        private String Name;
        private ArrayList<String> Lore;
        private String color;

        private String getItemName() {
            return display == null ? null : display.getItemName();
        }

        private ArrayList<String> getItemLore() {
            return display == null ? null : display.getItemLore();
        }

        private String getColor() {
            return this.color;
        }
    }
}
