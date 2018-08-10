package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import daoImpl.BookDaoImpl;
import domain.Book;

public class FileUploadUtils {
	public static Book upload(HttpServletRequest request) {
		Book book=new Book();
		try {
		//创建DiskFileItemFactory对象
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//设置文件缓存目录,如果该目录不存在则新建一个
		File f=new File("D:\\bookUrl");
		if(!f.exists()) {
			f.mkdirs();
		}
		//设置文件的缓存路径
		factory.setRepository(f);
		//创建ServeltFileUpload对象
		ServletFileUpload fileUpload=new ServletFileUpload(factory);
		//设置字符编码
		fileUpload.setHeaderEncoding("UTF-8");
		//解析request,得到上传文件的FileItem对象
		List<FileItem> fileItems=fileUpload.parseRequest(request);
		//遍历集合
		for(FileItem fileitem:fileItems) {
			//判断是否为普通字段
			if(!fileitem.isFormField()) {
				//获取上传文件的文件名
				String filename=fileitem.getName();
				//先判断是否存在相同名称的照片
				boolean b=new BookDaoImpl().isImgUrl(filename);
				if(b)return null;//存在则返回null
				//不存在相同的名称
				book.setImgUrl(filename);//获取上传的文件名
				//处理上传文件
				if(filename!=null&&!filename.equals("")) {
					//截取出文件名
					filename=filename.substring(filename.lastIndexOf("\\")+1);
					//文件名需要唯一
					//filename=UUIDUtils.getActiveCode()+"_"+filename;
					//在服务器创建同名文件
					String  filepath=request.getServletContext().getRealPath("/bookUrl/"+filename);
					//创建文件
					File file=new File(filepath);
					file.getParentFile().mkdirs();
					file.createNewFile();
					//获得上传文件流
					InputStream in =fileitem.getInputStream();
					//使用FileOutputStream打开服务器端的上传文件
					FileOutputStream out =new FileOutputStream(file);
					//流的对拷
					byte[] buffer=new byte[1024];//每次读取1个字节
					int len;
					//开始读取上传文件的字节,并将其输出到服务端的上传文件输出流中
					while((len=in.read(buffer))>0)out.write(buffer, 0, len);
					//关闭流
					in.close();
					out.close();
					//删除临时文件
					fileitem.delete();
					//上传成功
				}
			}else {
				//获取字段名和字段值
				String bookName=fileitem.getFieldName();
				String author=fileitem.getFieldName();
				String introduce=fileitem.getFieldName();
				String publisher=fileitem.getFieldName();
				String type=fileitem.getFieldName();
				if(bookName.equals("bookName")) {
					//如果文件不为空,将其保存在value中
					if(!fileitem.getString().equals("")) {
						book.setBookName(fileitem.getString("utf-8"));
					}
				}
				if(author.equals("author")) {
					//如果文件不为空,将其保存在value中
					if(!fileitem.getString().equals("")) {
						book.setAuthor(fileitem.getString("utf-8"));
					}
				}
				if(introduce.equals("introduce")) {
					//如果文件不为空,将其保存在value中
					if(!fileitem.getString().equals("")) {
						book.setIntroduce(fileitem.getString("utf-8"));
					}else {
						book.setIntroduce("");
					}
				}
				if(publisher.equals("publisher")) {
					//如果文件不为空,将其保存在value中
					if(!fileitem.getString().equals("")) {
						book.setPublisher(fileitem.getString("utf-8"));
					}else {
						book.setPublisher("");
					}
				}
				if(type.equals("type")) {
					//如果文件不为空,将其保存在value中
					if(!fileitem.getString().equals("")) {
						book.setType(fileitem.getString("utf-8"));
					}
				}
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return book;
	}
}
