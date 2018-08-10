package filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) res;
		//拦截所有的请求,解决全站乱码问题
		//指定request和response的编码
		request.setCharacterEncoding("UTF-8");//只对消息体有效,即当使用POST方式时
		response.setContentType("text/html;charset=utf-8");
		//对request进行包装
		CharacterRequest characterRequest=new CharacterRequest(request);
		chain.doFilter(characterRequest, response);
		
	}
	
	public void desstroy() {
		
	}

}

class CharacterRequest extends HttpServletRequestWrapper {

	public CharacterRequest(HttpServletRequest request) {
		super(request);
	}
	//子类继承父类一定会覆写一些方法,此处用于重写getParameter()方法
	public String getParameter(String name) {
		//调用被包装对象的getParamter()方法,获得请求参数
		String value=super.getParameter(name);
		if(value==null)return null;
		//判断请求方式
		String method=super.getMethod();
		if("get".equalsIgnoreCase(method)) {
			try {
				value=new String(value.getBytes("ISO-8859-1"),"utf-8");
			}catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		//解决乱码后返回结果
		return value;
	}
}
