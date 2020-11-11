package com.lzy.test;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

// 测试拼接图片
public   class   Test2{          
	public static void main(String[] args) {
		try {
			String path = new Object() {
		        public String getPath() {
		            return this.getClass().getResource("/img/wx.jpg").getPath();
		        }
		}.getPath().substring(1);
		System.out.println();
			BufferedImage img = ImageIO.read(new File(path));
			BufferedImage er = ImageIO.read(new File("D:\\qrcode.jpg"));
			BufferedImage headimg = ImageIO.read(new URL("http://thirdwx.qlogo.cn/mmopen/EUGyxFcqibXddUQqwkQiazmSAnhKX7nKXsLxeaQYabcJ3QhW8aeE97VJ42eXXkuoVfLXs4kicWE3cbkFiatJ2hrXFic8kAp29pXj2/132"));
			Graphics g = img.getGraphics();
			Graphics g2 = er.getGraphics();
			g.setFont(new Font("黑体",Font.PLAIN,25));
//			System.out.println(g.getFont());
			g.drawString("邀你一起参与领奖", 80, 50);
			g.drawImage(ImgUtil.cut(headimg, 0, 0, 30), 10, 10, null);
			g2.drawImage(headimg.getScaledInstance(100, 100, Image.SCALE_DEFAULT), 155, 155,null);
			g.drawImage(er.getScaledInstance(204, 204, Image.SCALE_DEFAULT), 490, 803,null);
			g.dispose();
			ImageIO.write(img, "jpg",  FileUtil.file("d:\\poster.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}  