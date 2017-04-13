package lqw.test.test_jmeter.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lqw.test.test_jmeter.utils.IOUtils;

/**
 *
 * @ClassName 类名：HttpUtils
 * @Description 功能说明：
 *              <p>
 *              </p>
 ************************************************************************
 * @date 创建日期：2015-11-24
 * @author 创建人：liupeng E-mail:liupeng6251@163.com
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2015-11-24 Administrator 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */

public class HttpUtils {

	private static final Logger LOG = LoggerFactory.getLogger(HttpUtils.class);

	private static final int CONN_TIMEOUT = 30000;

	private static final int READ_TIMEOUT = 60000;

	/**
	 * 
	 * <p>
	 * 函数名称： sendPost
	 * </p>
	 * <p>
	 * 功能说明：post http请求非https
	 * </p>
	 * 
	 * @date 创建时间：2015-11-24
	 * @author 作者：liupeng E-mail:liupeng6251@163.com
	 *         <p>
	 *         参数说明：
	 *         </p>
	 * @param url
	 * @param param
	 * @param charset
	 * @return
	 */
	public static HttpResponse sendPost(String url, String param, Charset charset) {
		try {
			URL httpurl = new URL(url);
			HttpURLConnection httpConn = (HttpURLConnection) httpurl.openConnection();
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; "
					+ "Windows NT 5.1; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
			httpConn.setConnectTimeout(CONN_TIMEOUT);
			httpConn.setReadTimeout(READ_TIMEOUT);
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setUseCaches(false);
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset.name());
			httpConn.connect();
			OutputStream os = httpConn.getOutputStream();
			PrintWriter out = new PrintWriter(os);
			out.print(param);
			out.flush();
			out.close();
			os.close();
			HttpResponse response = new HttpResponse();
			response.setResponseStatus(httpConn.getResponseCode());
			response.setContentLength(httpConn.getContentLength());
			response.setContentType(httpConn.getContentType());
			response.setHeaderFields(httpConn.getHeaderFields());
			if (response.getResponseStatus() != 200 && httpConn.getErrorStream() != null) {
				response.setErrorMessage(new String(IOUtils.readToByte(httpConn.getErrorStream()), charset));
				return response;
			}
			response.setResponseContent(httpConn.getInputStream());
			if (response.getContentType() == null || response.getContentType().startsWith("text")) {
				response.setContext(new String(IOUtils.readToByte(response.getResponseContent()), charset));
			}
			return response;
		} catch (IOException e) {
			throw new RuntimeException(String.format("url:%s,param:%s,message:%s", url, param, e.getMessage()), e);
		}
	}

	public static void main(String[] args) {
		HttpResponse resp = sendPost("http://www.baidu.com", "", Charset.forName("utf-8"));
		// System.err.println(resp.getContext());
		LOG.error(resp.getContext());
		// System.err.println(resp.getErrorMessage());
		LOG.error(resp.getErrorMessage());
	}
}
