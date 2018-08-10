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
import javax.servlet.http.HttpServletRequest;

import domain.Book;
import domain.Borrow;
import domain.User;
import service.BookService;
import serviceImpl.BookServiceImpl;

public class ClientFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
				//拦截所有访问client里的内容
				HttpServletRequest request=(HttpServletRequest)req;
				//定义个人信息列表
				User user=null;
				//定义借书详情列表
				List<Borrow> borrowBookList=null;
				//定义书本详情列表
				List<Book> readerBookList=new ArrayList<Book>();
				//获取用户状态user是否在session域中，存在则进行数据操作
				user=(User) request.getSession().getAttribute("user");
				//调用业务层
				BookService bookService=new BookServiceImpl();
				if(user!=null) {
					//查询借阅的课本
					borrowBookList=bookService.selectBorrowBookByUid(user.getUid());
					//查询出借阅的书的具体内容
					if(borrowBookList!=null) {
						for(Borrow b:borrowBookList) {
							readerBookList.add(bookService.selectBookByBid(b.getBid()));
						}
						//存入到request里
						request.setAttribute("borrowBookList", borrowBookList);
						request.setAttribute("readerBookList", readerBookList);
					}		
				}
				//放行
				chain.doFilter(request, response);
	}
	
	public void desstroy() {
		
	}

}
