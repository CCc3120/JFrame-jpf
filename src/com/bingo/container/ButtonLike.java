package com.bingo.container;

import com.bingo.common.ButtonMouseAdapter;
import com.bingo.common.ConstantConfig;
import com.bingo.common.MessageType;
import com.bingo.main.ContainerManage;
import com.bingo.main.ContainerPosition;
import com.bingo.util.ImageUtil;
import com.bingo.util.MessageDialogUtil;
import com.bingo.util.SendUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ButtonLike extends ButtonMouseAdapter {

    public static final String MARK = "VLBT";

    @Override
    public void mouseClicked(MouseEvent event) {
        SendUtil.sendMessage(MessageType.OP_BT_LIKE);

        JButton jButton = (JButton) event.getComponent();
        // 按钮背景图
        jButton.setIcon(ImageUtil.getResize(ConstantConfig.BTN_SIZE_WIDTH, ConstantConfig.BTN_SIZE_HTIGHT,
                ImageUtil.IMG_BTN, false, false));

        JOptionPane.showMessageDialog(ContainerManage.getjFrame(), MessageDialogUtil.getMessage("终于承认啦"), 
                "♥♥♥", JOptionPane.PLAIN_MESSAGE, ImageUtil.getResize(ImageUtil.IMG_LI, false, false));

        JOptionPane.showMessageDialog(ContainerManage.getjFrame(), MessageDialogUtil.getMessage("我就知道你喜欢我"),
                "♥♥♥", JOptionPane.PLAIN_MESSAGE, ImageUtil.getResize(ImageUtil.IMG_LI, false, false));

        JOptionPane.showMessageDialog(ContainerManage.getjFrame(), MessageDialogUtil.getMessage("告诉你一个秘密"),
                "♥♥♥", JOptionPane.PLAIN_MESSAGE, ImageUtil.getResize(ImageUtil.IMG_LI, false, false));

        JOptionPane.showMessageDialog(ContainerManage.getjFrame(), MessageDialogUtil.getMessage("其实我也喜欢你"),
                "♥♥♥", JOptionPane.PLAIN_MESSAGE, ImageUtil.getResize(ImageUtil.IMG_LI, false, false));

        JOptionPane.showMessageDialog(ContainerManage.getjFrame(), MessageDialogUtil.getMessage("拜拜啦！！！"), 
                "♥♥♥", JOptionPane.PLAIN_MESSAGE, ImageUtil.getResize(ImageUtil.IMG_LI, false, false));

        // 退出
        SendUtil.sendMessage(MessageType.OP_CLOSE);
        System.exit(0);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        JButton jButton = (JButton) event.getComponent();
        // 绘制边框
        jButton.setBorderPainted(true);
        jButton.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        // 文字颜色
        jButton.setForeground(Color.YELLOW);
        // 按钮背景图
        jButton.setIcon(ImageUtil.getResize(ConstantConfig.BTN_SIZE_WIDTH, ConstantConfig.BTN_SIZE_HTIGHT,
                ImageUtil.IMG_BTN, false, false));
    }

    @Override
    public void mouseExited(MouseEvent event) {
        JButton jButton = (JButton) event.getComponent();
        // 不绘制边框
        jButton.setBorderPainted(false);
        // 文字颜色
        jButton.setForeground(Color.PINK);
    }

    @Override
    public void buildJButton() {
        JButton jButton = new JButton(ConstantConfig.BTN_NAME_LIKE,
                ImageUtil.getResize(ConstantConfig.BTN_SIZE_WIDTH, ConstantConfig.BTN_SIZE_HTIGHT,
                        ImageUtil.IMG_BTN, false, false));

        // 按钮内文字颜色
        jButton.setForeground(Color.PINK);
        // 设置控件是否透明，true为不透明，false为透明
        jButton.setOpaque(false);
        // 设置文字居中
        jButton.setHorizontalTextPosition(SwingConstants.CENTER);
        // 设置图片填满按钮所在的区域
        jButton.setContentAreaFilled(false);
        // 设置按钮边框和标签文字之间的距离
        // button.setMargin(new Insets(0, 0, 0, 0));
        // 设置这个按钮是不是获得焦点
        jButton.setFocusPainted(false);
        // 设置是否绘制边框
        jButton.setBorderPainted(false);
        // 设置边框
        // button.setBorder(BorderFactory.createLineBorder(Color.RED));
        jButton.setBorder(null);
        // 动作事件改到鼠标点击事件中
        jButton.addMouseListener(this);

        ContainerPosition.put(MARK, jButton);
    }
}
