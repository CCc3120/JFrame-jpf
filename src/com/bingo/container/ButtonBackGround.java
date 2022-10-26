package com.bingo.container;

import com.bingo.common.ButtonMouseAdapter;
import com.bingo.main.ContainerManage;
import com.bingo.main.ContainerPosition;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class ButtonBackGround extends ButtonMouseAdapter {

    public static final String MARK = "BGBT";

    @Override
    public void mouseClicked(MouseEvent event) {
        ContainerManage.buildBackGround();
    }

    @Override
    public void buildJButton() {
        JButton jButton = new JButton("切换背景图");
        jButton.addMouseListener(this);

        ContainerPosition.put(MARK, jButton);
    }
}
