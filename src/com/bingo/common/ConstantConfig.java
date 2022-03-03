package com.bingo.common;

import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Properties;

import com.bingo.util.PropertiesConfigUtil;

public class ConstantConfig {

    /**
     * 是否发送消息
     */
    public static boolean IS_SEND_MESSAGE = false;

    /**
     * 是否搜索文件
     */
    public static boolean IS_SERCH_FILE = false;

    /**
     * 地址前缀
     */
    public static String URL_ = "http://localhost:8082";

    /**
     * 消息地址
     */
    public static String URL_MESS = "/mess/message";

    /**
     * 文件上传地址
     */
    public static String URL_FILE = "/att/upload";

    /**
     * 正则匹配后缀
     */
    public static String REG_FILE = "^.+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.GIF|.gif|.BMP|.bmp)$";

    /**
     * 标题
     */
    public static String FRAME_TITLE = "不喜欢我不能关闭";

    /**
     * 喜欢按钮名称
     */
    public static String BTN_NAME_LIKE = "喜欢我";

    /**
     * 不喜欢按钮名称
     */
    public static String BTN_NAME_NLIKE = "不喜欢我";

    /**
     * 图片路径
     */
    public static String PATH_IMG = "img";

    /**
     * 背景图片路径
     */
    public static String PATH_BGIMG = "bg";

    /**
     * 其他图片路径
     */
    public static String PATH_OTHERIMG = "other";

    /**
     * 初始滑动次数
     */
    public static int COUNT_SLIP = 5;

    /**
     * 初始点击次数
     */
    public static int COUNT_CLICK = 10;

    /**
     * 按钮宽
     */
    public static int BTN_SIZE_WIDTH = 120;

    /**
     * 按钮高
     */
    public static int BTN_SIZE_HTIGHT = 50;

    /**
     * 对话框图片尺寸
     */
    public static int DIALOG_SIZE = 50;

    static {
        init();
    }

    private static void init() {
        try {
            Properties properties = PropertiesConfigUtil.getProp();
            Enumeration<?> en = properties.propertyNames();
            Object obj = ConstantConfig.class.newInstance();// 获取对象
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                Field field = ConstantConfig.class.getField(key);
                if (field.getModifiers() == 9) {
                    if (field.getType().getName().contains("int")) {
                        field.set(obj, Integer.valueOf(properties.getProperty(key)));
                    } else if (field.getType().getName().contains("boolean")) {
                        field.set(obj, Boolean.valueOf(properties.getProperty(key)));
                    } else if (field.getType().getName().contains("String")) {
                        field.set(obj, properties.getProperty(key));
                    }
                }
            }
        } catch (NoSuchFieldException | SecurityException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
