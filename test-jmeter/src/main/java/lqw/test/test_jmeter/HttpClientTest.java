package lqw.test.test_jmeter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bs.einvoice.core.service.SignaturerService;

import lqw.test.test_jmeter.http.HttpResponse;
import lqw.test.test_jmeter.http.HttpUtils;
import lqw.test.test_jmeter.utils.Base64;
import lqw.test.test_jmeter.utils.EncryptUtils;
import lqw.test.test_jmeter.utils.JSONToolKit;

/**
 *
 * @ClassName 类名：HttpClientTest
 * @Description 功能说明：
 *              <p>
 *              </p>
 ************************************************************************
 * @date 创建日期：2016-2-24
 * @author 创建人： liupeng
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2016-2-24 songdl 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */

public class HttpClientTest {

	static String url = "http://192.168.1.250:7002";

	static String hmacKey = "xXPn+3i2ckbEViIpf5ktEm3jd22NSmeeoh897SnJfKdQN3My98iaLbpeQ1uY06tFkqlpV0mauMFx0AlCjTEObg==";

	static String hmac;

	static String templateid = "20160907";

	static String eInvoiceCode = "00000001";

	static String xmlStr = "";

	static byte[] sign;

	static String InvoicingPartySignature;

	static String tempImgPath = "testFiles/模板-11000001-北京市公益事业捐赠统一票据（电子）-20160314(合成2).png";

	static Map<String, Object> name_value_map = new HashMap<String, Object>();

	static {

		name_value_map.put("EInvoiceName", "北京市医疗门诊收费票据");
		name_value_map.put("EInvoiceSpecimenCode", templateid);// 模版代码
		name_value_map.put("RandomNumber", "235456");// 电子票据随机码
		name_value_map.put("EInvoiceCode", eInvoiceCode);// 票据代码
		// name_value_map.put("EInvoiceNumber", eInvoiceNumber);//票据号
		name_value_map.put("TotalAmount", "133.00");// 总金额
		name_value_map.put("IssueDate", "20151202");// 开票日期，北京时间
		name_value_map.put("IssueTime", "16:42:02");// 开票时间
		name_value_map.put("InvoicingPartyCode", "123456789");// 组织机构代码
		name_value_map.put("InvoicingPartyName", "北京市天坛医院");// 组织机构名称
		name_value_map.put("PayerPartyCode", "088825");// 组织机构代码
		name_value_map.put("PayerPartyName", "张三");// 组织机构名称
		name_value_map.put("PayerPartyType", "1");// 组织机构类型
		name_value_map.put("Number", "0012015120204083");// 业务流水号
		name_value_map.put("Sex", "男");//
		name_value_map.put("InsuranceType", "居民医保");// 医保类型
		name_value_map.put("CardNumber", "6022012120204083");// 社会保障卡号
		name_value_map.put("FundPayment", "120");// 基金支付
		name_value_map.put("PersonalPayment", "0");// 个人账户支付
		name_value_map.put("PersonalAmount", "13");// 个人支付金额
		name_value_map.put("TotalAmountCapital", "壹佰叁拾叁元整");// 合计金额大写
		name_value_map.put("Payee", "李四");// 收款人
		name_value_map.put("Institutiontype", "综合门诊部");// 医疗机构类型

		List<Map<String, Object>> details = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 3; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("ItemCode", "ItemCode");
			item.put("ItemName", "ItemCode");
			item.put("ItemPrice", "ItemCode");
			item.put("ItemNumber", "ItemCode");
			item.put("ItemAmount", "ItemCode");
			item.put("ItemLevel", "ItemCode");
			item.put("ItemType", "人民幣");
			details.add(item);
		}
		name_value_map.put("Details", details);
		name_value_map.put("OptTime", "OptTime");
		name_value_map.put("Operator", "Operator");
		name_value_map.put("Reason", "Operator");
		name_value_map.put("Character", "Character");
		name_value_map.put("EInvoiceQrcode", "EInvoiceQrcode");
		name_value_map.put("EInvoiceStatus", "sss");
		name_value_map.put("VerifiedPerson", "kai");
		name_value_map.put("HandingPerson", "fu");
		name_value_map.put("SupervisorAreaCode", "9");
	}

	public static void generateSignXml(String eInvoiceNumber) throws UnsupportedEncodingException {
		name_value_map.put("EInvoiceNumber", eInvoiceNumber);// 票据号
		Map<String, String> params2 = new HashMap<String, String>();
		params2.put("appId", "374224d7f8b342b1a9678b1dc4fbe100");
		params2.put("serviceName", "generateSignXml");
		params2.put("eInvoiceData", JSONToolKit.toJson(name_value_map));
		HttpResponse resp = doPost(params2);
		Map<String, Object> map = JSONToolKit.fromJson(resp.getContext(), Map.class);
		xmlStr = new String(Base64.decode((String) map.get("data")));
	}

	public static void issue(String eInvoiceNumber) throws UnsupportedEncodingException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:spring/dubbo-consumer.xml");
		SignaturerService signaturer = context.getBean(SignaturerService.class);

		sign = signaturer.sign(xmlStr);
		InvoicingPartySignature = new String(sign, Charset.forName("utf-8"));

		name_value_map.put("EInvoiceNumber", eInvoiceNumber);// 票据号
		// 业务参数
		name_value_map.put("InvoicingPartySignature", InvoicingPartySignature);
		name_value_map.put("SignatureTime", "20111111");
		name_value_map.put("SignatureAlgorithm", "fdsfd");
		name_value_map.put("SerialNumber", signaturer.getCertificateInfo().getSerialNumber());
		name_value_map.put("EInvoiceContent", xmlStr);
		name_value_map.put("CarrierValue", "123567884");
		name_value_map.put("CarrierType", "123567882");
		name_value_map.put("CarrierName", "123567881");
		// 系统参数
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", "374224d7f8b342b1a9678b1dc4fbe100");
		params.put("serviceName", "issueEInvoice");
		params.put("eInvoiceData", JSONToolKit.toJson(name_value_map));
		HttpResponse resp = doPost(params);
		System.err.println("开票成功: 票代码：" + eInvoiceCode + " 票号：" + eInvoiceNumber);
	}

	private static HttpResponse doPost(Map<String, String> params) throws UnsupportedEncodingException {
		String builderSignStr = builderSignStr(params);
		hmac = Base64.encodeBytes(EncryptUtils.hmac(hmacKey, builderSignStr + hmacKey));
		// System.err.println(URLEncoder.encode(hmac, "UTF-8"));
		HttpResponse resp = HttpUtils.sendPost(url,
				builderSignStr + "&securityCode=" + URLEncoder.encode(hmac, "UTF-8"), Charset.forName("utf-8"));
		if (resp.getResponseStatus() != 200) {
			throw new RuntimeException("rest调用失败，错误信息:" + resp.getErrorMessage());
		}

		Map<String, String> parse = JSONToolKit.fromJson(resp.getContext(), Map.class);
		String remove = parse.remove("securityCode");
		String signStr = builderSignStr(parse);
		String retHmac = Base64.encodeBytes(EncryptUtils.hmac(hmacKey, signStr + hmacKey));
		if (!retHmac.equals(remove)) {
			System.err.println("hmacKey: " + hmacKey);
			System.err.println("builderSignStr: " + builderSignStr);
			throw new RuntimeException("安全码校验异常，返回值:" + resp.getContext());
		}
		return resp;
	}

	public static String builderSignStr(Map<String, String> params) throws UnsupportedEncodingException {
		Set<String> keySet = params.keySet();
		List<String> keyList = new ArrayList<String>(keySet);
		Collections.sort(keyList);
		StringBuilder sb = new StringBuilder();
		for (String key : keyList) {
			String value = params.get(key);
			if ("".equals(value) || value == null) {
				continue;
			}
			sb.append(key);
			sb.append("=");
			sb.append(URLEncoder.encode(params.get(key), "UTF-8"));
			sb.append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static void main(String... strings) throws UnsupportedEncodingException {
		issue("1234567890");
	}

}
