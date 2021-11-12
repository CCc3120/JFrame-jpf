package com.bingo.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.bingo.common.ConstantConfig;
import com.bingo.main.ContainerManage;

public class ImageUtil {

    // 获取屏幕分辨率
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    // 背景大小
    public static int BG_WIDTH;
    public static int BG_HEIGHT;

    public static Random random = new Random();

    // 背景图图片数组
    public static List<String> IMG_BG = new ArrayList<>();
    // 按钮图图片数组
    public static List<String> IMG_BTN = new ArrayList<>();
    // 对话框图图片数组
    public static List<String> IMG_DI = new ArrayList<>();
    // 喜欢对话框图片数组
    public static List<String> IMG_LI = new ArrayList<>();

    static {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        SCREEN_WIDTH = (int) screensize.getWidth();
        SCREEN_HEIGHT = (int) screensize.getHeight();

        initImg();
    }

    private static void initImg() {
        File bgFile = new File(ConstantConfig.PATH_IMG + File.separator + ConstantConfig.PATH_BGIMG);
        if (bgFile.isDirectory()) {
            File[] files = bgFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (!files[i].isDirectory()) {
                    IMG_BG.add(files[i].getPath());
                }
            }
        }

        File otherFile = new File(ConstantConfig.PATH_IMG + File.separator + ConstantConfig.PATH_OTHERIMG);
        eachImage(otherFile);
    }

    private static void eachImage(File file) {
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                eachImage(files[i]);
                continue;
            }
            // 按钮
            if (files[i].getName().contains("bt")) {
                IMG_BTN.add(files[i].getPath());
            }
            // 对话框
            if (files[i].getName().contains("di")) {
                IMG_DI.add(files[i].getPath());
            }
            // 喜欢
            if (files[i].getName().contains("li")) {
                IMG_LI.add(files[i].getPath());
            }
        }
    }

    public static void bgSize(int width, int height) {
        if (width != BG_WIDTH || height != BG_HEIGHT) {
            BG_WIDTH = width;
            BG_HEIGHT = height;
            ContainerManage.buildFramePosition();
        }
    }

    public static String getAnyOneImg(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        return list.get(random.nextInt(list.size()));
    }

    public static ImageIcon getResize(int width, int height, List<String> imgs, boolean proportion,
            boolean isBackGround) {
        try {
            File tem = File.createTempFile("tem", null);
            ImageUtil.resizePng(new File(getAnyOneImg(imgs)), tem, width, height, proportion, isBackGround);
            return new ImageIcon(tem.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ImageIcon getResize(List<String> imgs, boolean proportion, boolean isBackGround) {
        return getResize(ConstantConfig.DIALOG_SIZE, ConstantConfig.DIALOG_SIZE, imgs, proportion,
                isBackGround);
    }


    /**
     * 裁剪PNG图片工具类
     *
     * @param fromFile     源文件
     * @param toFile       裁剪后的文件
     * @param outputWidth  裁剪宽度
     * @param outputHeight 裁剪高度
     * @param proportion   是否是等比缩放
     * @param isBackGround 是否背景图
     */
    public static void resizePng(File fromFile, File toFile, int outputWidth, int outputHeight,
            boolean proportion, boolean isBackGround) {
        try {
            BufferedImage bi2 = ImageIO.read(fromFile);
            int newWidth;
            int newHeight;
            if (isBackGround) {
                int min_width = ConstantConfig.BTN_SIZE_WIDTH * 2 + 30;
                double mid_width = ((double) outputWidth) * 0.75;
                double mid_height = ((double) outputHeight) * 0.75;
                double rate1 = ((double) bi2.getWidth()) / mid_width;
                double rate2 = ((double) bi2.getHeight()) / mid_height;
                // 根据缩放比率大的进行缩放控制 > 小 < 大
                double rate = rate1 > rate2 ? rate1 : rate2;
                newWidth = (int) ((bi2.getWidth()) / rate);
                newHeight = (int) ((bi2.getHeight()) / rate);
                if (newWidth < min_width) {
                    newWidth = min_width;
                    newHeight = bi2.getHeight() * newWidth / bi2.getWidth();
                }
                if (newHeight > outputHeight * 2) {
                    newHeight = (outputHeight - 50) * 2;
                }
            } else {
                // 判断是否是等比缩放
                if (proportion) {
                    double rate1 = ((double) bi2.getWidth()) / outputWidth;
                    double rate2 = ((double) bi2.getHeight()) / outputHeight;
                    // 根据缩放比率大的进行缩放控制 > 小 < 大
                    double rate = rate1 > rate2 ? rate1 : rate2;
                    newWidth = (int) ((bi2.getWidth()) / rate);
                    newHeight = (int) ((bi2.getHeight()) / rate);
                } else {
                    newWidth = outputWidth; // 输出的图片宽度
                    newHeight = outputHeight; // 输出的图片高度
                }
            }
            BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight,
                    Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = to.createGraphics();
            Image from = bi2.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();
            ImageIO.write(to, "png", toFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
