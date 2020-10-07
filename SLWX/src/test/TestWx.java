package test;

import java.util.HashMap;

import java.util.Map;

import org.junit.Test;

import com.baidu.aip.ocr.AipOcr;
import com.thoughtworks.xstream.XStream;
import entity.TextMessage;
import service.WxService;

public class TestWx {
	//设置APPID/AK/SK
	public static final String APP_ID = "11519092";
	public static final String API_KEY = "q3TlGWWqEBG9uGvlFIBtpvY5";
	public static final String SECRET_KEY = "A14W5VRNG8my1GXYYAyNND0RjzBwxI8A";
	
	@Test
	public void testGetUserInfo() {
		String user="oqLxYv-iXv0TodN52qoHz5s1HlRw";
		String info = WxService.getUserInfo(user);
		System.out.println(info);
	}


	
	@Test
	public void test() {
		System.out.println(WxService.getAccessToken());
		//15_9DHAkyQuqkpLhvqAN5SAWsamv1ifJhoRBaofIMbavU4Q7qFwjjgTd-a1b13drz2ANkCaJsAXuvSvB3Z7BwJ-giQTCBwOI1ifDdtqccarOQUwWvgt2XYACAdqD3yT3PvZYlBiAMnwfWaxNQfILERcABAOHU
	}
	

	
	@Test
	public void testPic() {
		// 初始化一个AipOcr
		AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
		//client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
		//client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理

		// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
		// 也可以直接通过jvm启动参数设置此环境变量
		//System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

		// 调用接口
		String path = "/Users／Richard/Documents/2.png";
		org.json.JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
		System.out.println(res.toString(2));
	}

	
	@Test
	public void testToken() {
		System.out.println(WxService.getAccessToken());
		System.out.println(WxService.getAccessToken());
	}

	@Test
	public void testMsg() {
		Map<String, String> map = new HashMap<>();
		map.put("ToUserName", "to");
		map.put("FromUserName", "from");
		map.put("MsgType", "type");
		TextMessage tm = new TextMessage(map, "还好");
		XStream stream = new XStream();
		// 设置需要处理XStreamAlias("xml")注释的类
		stream.processAnnotations(TextMessage.class);
		String xml = stream.toXML(tm);
		System.out.println(xml);

	}

}
