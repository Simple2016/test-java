package lqw.test.test_jmeter.http;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName 类名：HttpResponse
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2016-2-25
 * @author 创建人： liupeng
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2016-2-25 songdl 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */

public class HttpResponse {

	private int responseStatus;

	private String contentType;

	private long contentLength;

	private String context;

	private InputStream responseContent;

	private String errorMessage;

	private Map<String, List<String>> headerFields;

	public int getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public InputStream getResponseContent() {
		return responseContent;
	}

	public void setResponseContent(InputStream responseContent) {
		this.responseContent = responseContent;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setHeaderFields(Map<String, List<String>> headerFields) {
		this.headerFields = headerFields;

	}

	public Map<String, List<String>> getHeaderFields() {
		return headerFields;

	}

}
