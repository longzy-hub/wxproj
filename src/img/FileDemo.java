package img;

import java.io.File;
import java.io.IOException;

//获取所有图片地址
public class FileDemo {
	public static void main(String[] args) throws IOException {
		String path = "../wxproj/web/view/PicturePage/activity_img/";
		FileDemo file = new FileDemo();
		// 获取所有图片文件夹地址
		String[] folderPathUrl = file.getFolderPathUrl(path);
		// 声明
		String[] pathUrl = null;
		// 遍历所有图片文件夹地址
		for (int i = 0; i < folderPathUrl.length; i++) {
			// 获取图片文件夹地址
			pathUrl = file.getFolderPathUrl(folderPathUrl[i]);
			for (int j = 0; j < pathUrl.length; j++) {
				// 截取字符并拼接
				pathUrl[j] = ".." + pathUrl[j].substring(18);
				System.out.println(pathUrl[j]);
			}
		}
	}

	public String[] getPathUrl(String path) throws IOException {
		// 获取所有图片文件夹地址
		String[] folderPathUrl = getFolderPathUrl(path);
		// 声明
		String[] pathUrl = null;
		// 遍历所有图片文件夹地址
		for (int i = 0; i < folderPathUrl.length; i++) {
			// 获取图片文件夹地址
			pathUrl = getFolderPathUrl(folderPathUrl[i]);
			for (int j = 0; j < pathUrl.length; j++) {
				// 截取字符并拼接
				pathUrl[j] = ".." + pathUrl[j].substring(18);
				System.out.println(pathUrl[j]);
			}
		}
		return pathUrl;
	}

	// 获取图片文件夹地址
	@SuppressWarnings("unused")
	public String[] getFolderPathUrl(String path) throws IOException {
		File file = new File(path);
		// 判断地址是否正确
		if (!file.isDirectory()) {
			throw new IOException("文件目录不存在");
		}
		if (null == file) {
			System.out.println("文件目录不正确");
		}
		File[] listFile = file.listFiles();
		// 存储--图片文件夹地址
		String[] folderPathUrl = new String[listFile.length];
		// 遍历
		for (int i = 0; i < listFile.length; i++) {
			folderPathUrl[i] = listFile[i].getAbsolutePath().substring(38);
		}
		return folderPathUrl;
	}
}
