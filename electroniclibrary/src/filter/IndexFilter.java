package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import domain.Book;
import service.BookService;
import serviceImpl.BookServiceImpl;

public class IndexFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			//拦截访问首页的请求,将页面所空缺的内容填充
			//接收查询的结果
			List<Book> hotBookList=new ArrayList<Book>();
			List<Book> newBookList=new ArrayList<Book>();
			//创建业务层
			BookService bookService=new BookServiceImpl();
			//调用业务层查找热门的前10本书
			hotBookList=bookService.selectHotBook();
			//调用业务层查找最新上架的10本书
			newBookList=bookService.selectNewUploadBook();
			//获取所有电子书
			List<Book> bookList=bookService.selectAllBook();
			//将查找到的内容存入请求里面
			request.setAttribute("hotBookList", hotBookList);
			request.setAttribute("newBookList", newBookList);
			request.setAttribute("booklist", bookList);
			//System.out.println(".............................................");
			chain.doFilter(request, response);
	}
	
	public void desstroy() {
		
	}

}
