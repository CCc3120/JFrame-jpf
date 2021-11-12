package com.bingo.container;

import java.awt.*;
import java.awt.event.WindowEvent;

import javax.swing.*;

import com.bingo.common.ConstantConfig;
import com.bingo.common.JFrameAdapter;
import com.bingo.common.MessageType;
import com.bingo.util.ImageUtil;
import com.bingo.util.MessageDialogUtil;
import com.bingo.util.SendUtil;

public class FrameMain extends JFrameAdapter {

    @Override
    public void windowClosing(WindowEvent event) {
        SendUtil.sendMessage(MessageType.OP_BT_CLOSE);

        JOptionPane.showMessageDialog(event.getComponent(), MessageDialogUtil.getMessage("你个大傻子，关不掉的！"), 
                "￣へ￣", JOptionPane.PLAIN_MESSAGE, ImageUtil.getResize(ImageUtil.IMG_DI, false, false));

        JOptionPane.showMessageDialog(event.getComponent(), MessageDialogUtil.getMessage(
                "温馨提示，有一个按钮可以关闭哦！！！"), "(*^▽^*)", JOptionPane.INFORMATION_MESSAGE,
                ImageUtil.getResize(ImageUtil.IMG_DI, false, false));

        SendUtil.sendMessage(MessageType.OP_CLOSE_FAIL);
    }

    @Override
    public JFrame buildJFrame() {
        initUI();

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        JFrame jFrame = new JFrame(ConstantConfig.FRAME_TITLE);
        // 设置左上角图标
        jFrame.setIconImage(toolkit.getImage(ImageUtil.getAnyOneImg(ImageUtil.IMG_BTN)));
        // 窗口大小不可变
        jFrame.setResizable(false);
        // 设置可见
        jFrame.setVisible(true);
        // 取消右上角关闭按钮
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // 监听窗口事件
        jFrame.addWindowListener(this);
        return jFrame;
    }

    private void initUI() {
        try {
            // 设置当前系统默认风格
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("OptionPane.minimumSize", new Dimension(320, 108));
        } catch (ClassNotFoundException
                | UnsupportedLookAndFeelException
                | InstantiationException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
