package com.bingo.common;

import java.awt.event.WindowAdapter;

import javax.swing.*;

public abstract class JFrameAdapter extends WindowAdapter {

    public abstract JFrame buildJFrame();
    
}
