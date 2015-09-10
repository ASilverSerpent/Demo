package web.filter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 压缩过滤器
 * @author zhang
 *
 */
public class CompressionFilter implements Filter{
	
	private ServletContext ctx;
	private FilterConfig cfg;
	
	public void init(FilterConfig cfg) throws ServletException {
		this.cfg = cfg;
		ctx = cfg.getServletContext();
		ctx.log(cfg.getFilterName() + " initialized.");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		String valid_encodings = request.getHeader("Accept-Encoding");//客户端是否接受GZIP压缩
		if (valid_encodings.indexOf("gzip") > -1) {
			CompressionResponseWrapper wrappedResp = new CompressionResponseWrapper(response);
			wrappedResp.setHeader("Content-Encoding","gzip");//声明响应内容用GZIP格式编码
			fc.doFilter(request, wrappedResp);
			GZIPOutputStream gzos = wrappedResp.getGZIPOutputStream();
			gzos.finish();
			
			ctx.log(cfg.getFilterName() + ":finished the request.");
		} else {
			ctx.log(cfg.getFilterName() + ":no encoding performed.");
			fc.doFilter(request, response);
		}
	}

	
	public void destroy() {
		cfg = null;
		ctx = null;
	}
}
