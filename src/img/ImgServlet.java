package img;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImgServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = "dslaj";
		request.getParameter(name);
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

		String[] str = request.getParameterValues("img-responsive");
		System.out.println(str);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
