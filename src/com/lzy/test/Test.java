package com.lzy.test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;

// 测试拼接图片
public   class   Test{          
	public static void main(String[] args) {
		try {
			BufferedImage img = ImageIO.read(new File("src/wx.jpg"));
			BufferedImage er = ImageIO.read(new File("D:\\qrcode.jpg"));
			BufferedImage logo = ImageIO.read(new URL("http://thirdwx.qlogo.cn/mmopen/EUGyxFcqibXddUQqwkQiazmSAnhKX7nKXsLxeaQYabcJ3QhW8aeE97VJ42eXXkuoVfLXs4kicWE3cbkFiatJ2hrXFic8kAp29pXj2/132"));
			Graphics g = img.getGraphics();
			Graphics g2 = er.getGraphics();
			g2.drawImage(logo.getScaledInstance(100, 100, Image.SCALE_DEFAULT), 150, 150,null);
			g.drawImage(er.getScaledInstance(204, 204, Image.SCALE_DEFAULT), 490, 803,null);
			g.dispose();
			ImageIO.write(img, "jpg", new File("d:\\poster.jpg"));
	        
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}  