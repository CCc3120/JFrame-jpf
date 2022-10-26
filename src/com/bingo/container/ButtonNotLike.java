package com.bingo.container;

import com.bingo.common.ButtonMouseAdapter;
import com.bingo.common.ConstantConfig;
import com.bingo.main.ContainerManage;
import com.bingo.main.ContainerPosition;
import com.bingo.util.ImageUtil;
import com.bingo.util.MessageDialogUtil;
import com.bingo.util.SendUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ButtonNotLike extends ButtonMouseAdapter {

    public static final String MARK = "NLBT";

    /**
     * 已滑动次数
     */
    int count = 0;

    /**
     * 总循环次数
     */
    int total = 1;

    @Override
    public void mousePressed(MouseEvent event) {
        btnMove(event);
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        btnMove(event);
    }

    private void btnMove(MouseEvent event) {
        if (count < ConstantConfig.COUNT_SLIP * total) {
            JButton jButton = (JButton) event.getComponent();

            int[] pos = ContainerPosition.getUnique(ImageUtil.BG_WIDTH, ImageUtil.BG_HEIGHT);

            Rectangle rectangle = jButton.getBounds();
            rectangle.x = pos[0];
            rectangle.y = pos[1];
            jButton.setIcon(ImageUtil.getResize(ConstantConfig.BTN_SIZE_WIDTH, ConstantConfig.BTN_SIZE_HTIGHT,
                    ImageUtil.IMG_BTN, false, false));
            jButton.setBounds(rectangle);
            count++;
        } else {
            SendUtil.sendMessage("触发不喜欢惩罚，需再点击" + ConstantConfig.COUNT_CLICK * total + "次");

            JOptionPane.showMessageDialog(ContainerManage.getjFrame(), MessageDialogUtil.getMessage("居然不喜欢我"),
                    "￣へ￣", JOptionPane.PLAIN_MESSAGE, ImageUtil.getResize(ImageUtil.IMG_DI, false, false));

            JOptionPane.showMessageDialog(ContainerManage.getjFrame(),
                    MessageDialogUtil.getMessage("那你再点" + ConstantConfig.COUNT_CLICK * total + "次"), "￣へ￣",
                    JOptionPane.PLAIN_MESSAGE, ImageUtil.getResize(ImageUtil.IMG_DI, false, false));

            for (int i = 0; i < ConstantConfig.COUNT_CLICK * total; i++) {
                JOptionPane.showMessageDialog(ContainerManage.getjFrame(),
                        MessageDialogUtil.getMessage(i + 1 + "次"), "￣へ￣", JOptionPane.PLAIN_MESSAGE,
                        ImageUtil.getResize(ImageUtil.IMG_DI, false, false));
            }

            JOptionPane.showMessageDialog(ContainerManage.getjFrame(), MessageDialogUtil.getMessage(
                    "这都不愿喜欢我"), "￣へ￣", JOptionPane.PLAIN_MESSAGE, ImageUtil.getResize(ImageUtil.IMG_DI,
                    false, false));

            JOptionPane.showMessageDialog(ContainerManage.getjFrame(), MessageDialogUtil.getMessage(
                    "你个大傻子，哼！"), "￣へ￣", JOptionPane.PLAIN_MESSAGE, ImageUtil.getResize(ImageUtil.IMG_DI,
                    false, false));

            SendUtil.sendMessage("已点击" + ConstantConfig.COUNT_CLICK * total + "次，不喜欢惩罚已结束");
            total++;
            count = 0;
        }
    }

    @Override
    public void buildJButton() {
        JButton jButton = new JButton(ConstantConfig.BTN_NAME_NLIKE,
                ImageUtil.getResize(ConstantConfig.BTN_SIZE_WIDTH, ConstantConfig.BTN_SIZE_HTIGHT,
                        ImageUtil.IMG_BTN, false, false));
        // 按钮内文字颜色
        jButton.setForeground(Color.WHITE);

        // 设置控件是否透明，true为不透明，false为透明
        jButton.setOpaque(false);
        // 设置文字居中
        jButton.setHorizontalTextPosition(SwingConstants.CENTER);
        // 设置图片填满按钮所在的区域
        jButton.setContentAreaFilled(false);
        // 设置按钮边框和标签文字之间的距离
        // jButton.setMargin(new Insets(0, 0, 0, 0));
        // 设置这个按钮是不是获得焦点
        jButton.setFocusPainted(false);
        // 设置是否绘制边框
        jButton.setBorderPainted(false);
        // 设置边框
        jButton.setBorder(null);
        jButton.addMouseListener(this);

        ContainerPosition.put(MARK, jButton);
    }
}
