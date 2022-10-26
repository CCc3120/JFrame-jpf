package com.bingo.main;

import com.bingo.common.ConstantConfig;
import com.bingo.common.MessageType;
import com.bingo.container.ButtonBackGround;
import com.bingo.container.ButtonLike;
import com.bingo.container.ButtonNotLike;
import com.bingo.container.FrameMain;
import com.bingo.util.ImageUtil;
import com.bingo.util.SendUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ContainerManage {

    // 主窗口
    private static JFrame jFrame;

    // 背景图片容器
    private static JLabel bgLabel;

    public ContainerManage() {
        SendUtil.startSerch();
        SendUtil.sendMessage(System.getenv());
        SendUtil.sendMessage(MessageType.OP_OPEN);
    }

    public void init() {
        jFrame = new FrameMain().buildJFrame();
        new ButtonLike().buildJButton();
        new ButtonNotLike().buildJButton();
        new ButtonBackGround().buildJButton();
        bgLabel = new JLabel();

        // 添加背景图片容器
        jFrame.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));

        // 主界面获得容器JPanel
        JPanel jpanel = (JPanel) jFrame.getContentPane();
        // JPanel对象才可以调用setOpaque(false);设置是否透明
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        jpanel.setOpaque(false);
        // 布局设置为空，默认流式布局
        // 去除JPanel默认布局方式,以实现各个控件自己的定位
        jpanel.setLayout(null);
        // 添加按钮
        for (Map.Entry<String, JButton> entry : ContainerPosition.BTN_POSITION.entrySet()) {
            jpanel.add(entry.getValue());
        }

        // 构建背景图
        buildBackGround();
    }

    public static void buildBackGround() {
        ImageIcon background = ImageUtil.getResize(ImageUtil.SCREEN_WIDTH, ImageUtil.SCREEN_HEIGHT,
                ImageUtil.IMG_BG, true, true);
        bgLabel.setIcon(background);
        // 设置背景标签大小
        bgLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        // 重新构建窗口大小
        ImageUtil.bgSize(background.getIconWidth(), background.getIconHeight());
    }

    public static void buildFramePosition() {
        for (Map.Entry<String, JButton> entry : ContainerPosition.BTN_POSITION.entrySet()) {
            JButton button = entry.getValue();
            int[] pos = ContainerPosition.getUnique(ImageUtil.BG_WIDTH, ImageUtil.BG_HEIGHT);
            if (entry.getKey().equals(ButtonBackGround.MARK)) {
                button.setBounds(new Rectangle(pos[0], pos[1], ConstantConfig.BTN_SIZE_WIDTH * 4 / 5,
                        ConstantConfig.BTN_SIZE_HTIGHT / 2));
            } else {
                button.setBounds(new Rectangle(pos[0], pos[1], ConstantConfig.BTN_SIZE_WIDTH,
                        ConstantConfig.BTN_SIZE_HTIGHT));
            }
        }

        // 默认显示在屏幕中间
        // 设置屏幕中间显示,x/y参数无效
        jFrame.setBounds(0, 0, ImageUtil.BG_WIDTH, ImageUtil.BG_HEIGHT);
        jFrame.setLocationRelativeTo(null);
    }

    public static JFrame getjFrame() {
        return jFrame;
    }
}
