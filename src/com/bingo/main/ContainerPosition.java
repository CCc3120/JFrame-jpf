package com.bingo.main;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import com.bingo.common.ConstantConfig;
import com.bingo.util.ImageUtil;

public class ContainerPosition {

    // 按钮
    public static Map<String, JButton> BTN_POSITION = new HashMap<>();

    public static int[] getUnique(int max_width, int max_height) {
        int pix;
        int piy;

        loopA:
        while (true) {
            // 减去按钮宽136
            pix = ImageUtil.random.nextInt(max_width - 136);
            // 右下角定位需减89
            piy = ImageUtil.random.nextInt(max_height - 89);

            boolean flag = true;

            loopB:
            for (Map.Entry<String, JButton> entry : BTN_POSITION.entrySet()) {
                Rectangle rectangle = entry.getValue().getBounds();

                if (rectangle.x - ConstantConfig.BTN_SIZE_WIDTH <= pix
                        && rectangle.x + ConstantConfig.BTN_SIZE_WIDTH >= pix
                        && rectangle.y - ConstantConfig.BTN_SIZE_HTIGHT <= piy
                        && rectangle.y + ConstantConfig.BTN_SIZE_HTIGHT >= piy) {
                    flag = false;
                    break loopB;
                }
            }
            if (flag)
                break loopA;

        }
        return new int[]{pix, piy};
    }

    public static void put(String key, JButton jButton) {
        BTN_POSITION.put(key, jButton);
    }

    public static void remove(String key) {
        BTN_POSITION.remove(key);
    }
}
