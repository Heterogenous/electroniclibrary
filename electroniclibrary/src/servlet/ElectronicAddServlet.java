package servlet;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImpl.BookDaoImpl;
import domain.Book;
import utils.FileUploadUtils;
import utils.UUIDUtils;

/**
 * 处理添加电子书的操作
 */
@WebServlet("/ElectronicAddServlet")
public class ElectronicAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置表外参数
		Book book=null;
		String bid="";
		Date uploadTime=null;
		//为bid赋值
		bid=UUIDUtils.getActiveCode();
		//为上传时间赋值
		uploadTime=new Date();
		//处理上传照片
		book=FileUploadUtils.upload(request);//成功就返回上传的文件名
		if(book==null) {
			request.getSession().setAttribute("AddBook", "添加电子书失败!原因:照片名称已存在!");
			response.sendRedirect(request.getContextPath()+"/admin/admin-electronicbook.jsp");
		}else {
			//设置数据
			book.setBid(bid);
			book.setUploadTime(uploadTime);
			//调用DAO层添加电子书
			boolean b=new BookDaoImpl().insertBook(book);
			if(b) {
				request.getSession().setAttribute("AddBook", "添加电子书成功!");
			}else {
				request.getSession().setAttribute("AddBook", "添加电子书失败!");
			}
			response.sendRedirect(request.getContextPath()+"/admin/admin-electronicbook.jsp");
		}
	}

}
