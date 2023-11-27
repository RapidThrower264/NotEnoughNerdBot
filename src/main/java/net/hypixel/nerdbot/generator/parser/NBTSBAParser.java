package net.hypixel.nerdbot.generator.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.hypixel.nerdbot.util.JsonUtil;

import java.util.ArrayList;

import static net.hypixel.nerdbot.generator.util.GeneratorStrings.INVALID_ITEM_SKULL_DATA;
import static net.hypixel.nerdbot.generator.util.GeneratorStrings.MISSING_ITEM_NBT;
import static net.hypixel.nerdbot.generator.util.GeneratorStrings.MULTIPLE_ITEM_SKULL_DATA;

public class NBTSBAParser extends NBTParser {
    private String id;
    private SBATag tag;

    protected NBTSBAParser() {
        super();
    }

    @Override
    public String getMinecraftID() {
        return id;
    }

    @Override
    public String getItemName() {
        return tag == null ? null : tag.getItemName();
    }

    @Override
    public ArrayList<String> getItemLore() {
        return tag == null ? null : tag.getItemLore();
    }

    @Override
    public String getColor() {
        return tag == null ? null : tag.getColor();
    }

    @Override
    public boolean isEnchanted() {
        return tag != null && tag.isEnchanted();
    }

    @Override
    public boolean hasSkullID() {
        return tag != null && tag.hasSkullID();
    }

    @Override
    public String getSkullOwnerID() throws Exception {
        return tag == null ? null : tag.getSkullOwnerID();
    }

    private class SBATag {
        private DisplayTagSBA display;
        private SkullOwnerTagSBA SkullOwner;
        private JsonArray ench;

        private String getItemName() {
            return display == null ? null : display.getItemName();
        }

        private ArrayList<String> getItemLore() {
            return display == null ? null : display.getItemLore();
        }

        private String getColor() {
            return display == null ? null : display.getColor();
        }

        private boolean hasSkullID() {
            return SkullOwner != null && SkullOwner.hasSkullID();
        }

        private String getSkullOwnerID() throws Exception {
            return SkullOwner == null ? null : SkullOwner.getSkullID();
        }

        private boolean isEnchanted() {
            return ench != null;
        }
    }

    private class DisplayTagSBA {
        private String Name;
        private ArrayList<String> Lore;
        private String color;

        private String getItemName() {
            return this.Name;
        }

        private ArrayList<String> getItemLore() {
            return this.Lore;
        }

        private String getColor() {
            return this.color;
        }
    }

    private static class SkullOwnerTagSBA {
        private JsonObject Properties;

        public boolean hasSkullID() {
            return this.Properties != null;
        }

        public String getSkullID() throws Exception {
            // checking if there is a textures json object within properties
            JsonArray texturesJSON = JsonUtil.isJsonArray(Properties, "textures");
            if (texturesJSON == null) {
                throw new Exception(MISSING_ITEM_NBT.formatted("textures"));
            }

            // checking that there is only one json object in the array
            if (texturesJSON.size() != 1) {
                throw new Exception(MULTIPLE_ITEM_SKULL_DATA);
            } else if (!texturesJSON.get(0).isJsonObject()) {
                throw new Exception(INVALID_ITEM_SKULL_DATA);
            }

            // checking that there is a Base64 skin url string
            String base64String = JsonUtil.isJsonString(texturesJSON.get(0).getAsJsonObject(), "Value");
            if (base64String == null) {
                throw new Exception(INVALID_ITEM_SKULL_DATA);
            }

            return base64String;
        }
    }
}
