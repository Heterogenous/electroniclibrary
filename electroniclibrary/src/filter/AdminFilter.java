package filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import domain.Book;
import domain.BookType;
import domain.Borrow;
import domain.User;
import service.BookService;
import service.UserService;
import serviceImpl.BookServiceImpl;
import serviceImpl.UserServiceImpl;

public class AdminFilter implements Filter {
	
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			//拦截所有访问Admin里的内容
			HttpServletRequest request=(HttpServletRequest)req;
			//调用业务层
			UserService userService=new UserServiceImpl();
			BookService bookService=new BookServiceImpl();
			//获取所有用户
			List<User> normalUsers=userService.selectUsersByRole("普通用户");
			List<User> superUsers=userService.selectUsersByRole("超级用户");
			//获取所有电子书
			List<Book> books=bookService.selectAllBook();
			//获取所有借阅情况
			List<Borrow> borrows=bookService.selectAllBorrowBook();
			//获取书籍类型
			List<BookType> typelist=bookService.selectBookType();
			//分别将获取到的列表存入request里
			request.setAttribute("normalUsers",normalUsers);
			request.setAttribute("superUsers", superUsers);
			request.setAttribute("books", books);
			request.setAttribute("borrows", borrows);
			request.setAttribute("typelist", typelist);
			//放行
			chain.doFilter(request, response);
	}
	
	public void desstroy() {
		
	}

}
