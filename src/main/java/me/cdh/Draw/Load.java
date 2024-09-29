package me.cdh.Draw;

import javax.imageio.ImageIO;

import me.cdh.Main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public record Load() {
    public static BufferedImage loadImage(String RelativePath) {
        File ImageFile = new File(Main.RESOURCE_PATH + RelativePath);
        //从路劲获取文件对象
        try {
            //加载图片
            return ImageIO.read(ImageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
            //抛出异常
        }
    }
}
