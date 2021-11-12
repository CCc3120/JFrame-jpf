package com.bingo.container;

import java.awt.event.MouseEvent;

import javax.swing.*;

import com.bingo.common.ButtonMouseAdapter;
import com.bingo.main.ContainerManage;
import com.bingo.main.ContainerPosition;

public class ButtonBackGround extends ButtonMouseAdapter {

    public static final String MARK = "BGBT";

    @Override
    public void mouseClicked(MouseEvent event) {
        ContainerManage.buildBackGround();
    }

    @Override
    public JButton buildJButton() {
        JButton jButton = new JButton("切换背景图");
        jButton.addMouseListener(this);

        ContainerPosition.put(MARK, jButton);
        return jButton;
    }
}
