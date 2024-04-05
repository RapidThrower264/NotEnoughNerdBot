package net.hypixel.nerdbot.api.badge;

import com.vdurmont.emoji.EmojiManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.hypixel.nerdbot.cache.EmojiCache;

@AllArgsConstructor
@Getter
@ToString
public class Badge {

    private final String id;
    private final String name;
    private final String emoji;

    public Emoji getEmoji() {
        if (EmojiManager.isEmoji(emoji)) {
            return Emoji.fromUnicode(emoji);
        }

        return EmojiCache.getEmojiById(emoji).orElse(Emoji.fromUnicode("❓"));
    }

    public String getFormattedName() {
        return getEmoji().getFormatted() + " " + name;
    }
}