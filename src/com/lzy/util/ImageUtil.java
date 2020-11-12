package com.lzy.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.lzy.model.pojo.User;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;

// 拼接海报
public class ImageUtil {

	public  static void addimg(String fileUrl, User user) {
		// 将二维码和头像添加到海报上去
		try {
			// 获取背景图片的路径
			String path = new Object() {
				public String getPath() {
					return this.getClass().getResource("/img/wx.jpg").getPath();
				}
			}.getPath().substring(1);
			// 获取背景图片
			BufferedImage img = ImageIO.read(new File(path));
			// 获取二维码图片
			BufferedImage er = ImageIO.read(new URL(fileUrl));
			// 获取用户头像图片
			BufferedImage headimg = ImageIO.read(new URL(user.getHeadimgurl()));
			// 绘图
			Graphics g = img.getGraphics();
			Graphics g2 = er.getGraphics();
			g.drawImage(ImgUtil.cut(headimg.getScaledInstance(80, 80, Image.SCALE_DEFAULT), 0, 0, 30), 10, 10, null);
			g.setFont(new Font("黑体",Font.PLAIN,25));
//			System.out.println(g.getFont());
			g.drawString("邀你一起参与领奖", 80, 50);
			g2.drawImage(headimg.getScaledInstance(100, 100, Image.SCALE_DEFAULT), 155, 155,null);
			g.drawImage(er.getScaledInstance(204, 204, Image.SCALE_DEFAULT), 490, 803,null);
			g.dispose();
			String imgPath = path + "/img/"+ user.getOpenid()+".jpg";
			ImageIO.write(img, "jpg",  new File(imgPath));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
