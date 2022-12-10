package com.lpbigfish.wob;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;

public class ColorSystem {

    private final WOB plugin;

    public ColorSystem(WOB plugin) {
        this.plugin = plugin;
    }

    public TextComponent color(String text) {

        String item = plugin.getConfig().getString("DisplayName");

        assert item != null;
        if (item.contains("#")) {
            //get index of every # in the string
            int[] index = new int[item.length()];
            int count = 0;
            for (int i = 0; i < item.length() - 1; i++) {

                if (item.charAt(i) == '#') {
                    index[count] = i;
                    count++;
                }
            }
            char[] colorcodes = new char[count];
            for (int i = 0; i < count; i++) {
                colorcodes[i] = item.charAt(index[i] + 1);
            }

            //create substring from first # to next #
            String[] substrings = new String[count + 1];
            for (int i = 0; i < count; i++) {
                substrings[i] = item.substring(index[i] + 1, index[i + 1]);
            }

        } else {
            return Component.text(item);
        }

        return null;
    }

}
