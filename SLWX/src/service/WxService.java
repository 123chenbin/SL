package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import dao.GetCosDao;
import dao.getIntegralByName;
import dao.subscribeDao;
import entity.AccessToken;
import entity.BaseMessage;
import entity.Cos;
import entity.TextMessage;
import entity.login;
import net.sf.json.JSONObject;
import sun.util.logging.resources.logging;
import unilt.Util;

public class WxService {
	private static final String TOKEN = "abc";
	private static final String GET_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//微信公众号
//	private static final String APPID="wx25c4c697eaf4d540";
	private static final String APPID="wxc813250c6a99d0ca";
//	private static final String APPSECRET="4b9a08830f942cccf2558d1559f6bc87";
	private static final String APPSECRET="730a02d052b215baa8c65be791b7863d";
	//用于存储token
	private static AccessToken at;
	
	/**
	 * 获取token
	 * by 罗召勇 Q群193557337
	 */
	private static void getToken() {
		String url = GET_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		String tokenStr = Util.get(url);
		JSONObject jsonObject = JSONObject.fromObject(tokenStr);
		String token = jsonObject.getString("access_token");
		String expireIn = jsonObject.getString("expires_in");
		//创建token对象,并存起来。
		at = new AccessToken(token, expireIn);
	}
	
	/**
	 * 向处暴露的获取token的方法
	 * @return
	 * by 罗召勇 Q群193557337
	 */
	public static String getAccessToken() {
		if(at==null||at.isExpired()) {
			getToken();
		}
		return at.getAccessToken();
	}
	
	/**
	 * 验证签名
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @return
	 * by 罗召勇 Q群193557337
	 */
	public static boolean check(String timestamp, String nonce, String signature) {
		 //1）将token、timestamp、nonce三个参数进行字典序排序 
		String[] strs = new String[] {TOKEN,timestamp,nonce};
		Arrays.sort(strs);
		 //2）将三个参数字符串拼接成一个字符串进行sha1加密 
		String str = strs[0]+strs[1]+strs[2];
		String mysig = sha1(str);
		 //3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		return mysig.equalsIgnoreCase(signature);
	}
	
	/**
	 * 进行sha1加密
	 * @param str
	 * @return
	 * by 罗召勇 Q群193557337
	 */
	private static String sha1(String src) {
		try {
			//获取一个加密对象
			MessageDigest md = MessageDigest.getInstance("sha1");
			//加密
			byte[] digest = md.digest(src.getBytes());
			char[] chars= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
			StringBuilder sb = new StringBuilder();
			//处理加密结果
			for(byte b:digest) {
				sb.append(chars[(b>>4)&15]);
				sb.append(chars[b&15]);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析xml数据包
	 * @param is
	 * @return
	 * by 罗召勇 Q群193557337
	 */
	public static Map<String, String> parseRequest(InputStream is) {
		Map<String, String> map = new HashMap<>();
		SAXReader reader = new SAXReader();
		try {
			//读取输入流，获取文档对象
			Document document = reader.read(is);
			//根据文档对象获取根节点
			Element root = document.getRootElement();
			//获取根节点的所有的子节点
			List<Element> elements = root.elements();
			for(Element e:elements) {
				map.put(e.getName(), e.getStringValue());
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 用于处理所有的事件和消息的回复
	 * @param requestMap
	 * @return 返回的是xml数据包
	 * by 罗召勇 Q群193557337
	 */
	public static String getRespose(Map<String, String> requestMap) {
		BaseMessage msg=null;
		String msgType = requestMap.get("MsgType");
		switch (msgType) {
			//处理文本消息
			case "text":
				msg=dealTextMessage(requestMap);
				break;
			case "event":
				msg = dealEvent(requestMap);
				break;
			default:
				break;
		}
		//把消息对象处理为xml数据包
		if(msg!=null) {
			return beanToXml(msg);
		}
		return null;
	}


	/**
	 * 处理事件推送
	 * @param requestMap
	 * @return
	 * by 罗召勇 Q群193557337
	 */
	private static BaseMessage dealEvent(Map<String, String> requestMap) {
		String event = requestMap.get("Event");
		switch (event) {
			case "subscribe":
				return dealSubscribe(requestMap);
			case "unsubscribe":
				return dealUnSubscribe(requestMap);
			default:
				break;
		}
		return null;
	}
	/**
	 * 处理关注/取消关注
	 * @param requestMap
	 * @return
	 * by 罗召勇 Q群193557337
	 */
	private static BaseMessage dealSubscribe(Map<String, String> requestMap) {
		String subscribe = requestMap.get("Event");
		String username = requestMap.get("FromUserName");
		if(subscribe.equals("subscribe")) {
			subscribeDao.SubscribeDao(username);
				return new TextMessage(requestMap, "感谢您关注晟隆公众号！\n如需查询积分:\n回复【积分】\n查询订单信息：\n回复系统单号或者指令单号\n（暂时不支持模糊查询请输入正确的格式如【FM20-1234567】，【1234567】）");
		}
		else {
		return null;
		}
	}
	private static BaseMessage dealUnSubscribe(Map<String, String> requestMap) {
		String subscribe = requestMap.get("Event");
		String username = requestMap.get("FromUserName");
		if(subscribe.equals("unsubscribe")) {
			subscribeDao.DelSubscribeDao(username);
				return new TextMessage(requestMap, "感谢您一直以来的支持！");
		}
		else {
		return null;
		}
	}


	/**
	 * 把消息对象处理为xml数据包
	 * @param msg
	 * @return
	 * by 罗召勇 Q群193557337
	 */
	private static String beanToXml(BaseMessage msg) {
		XStream stream = new XStream();
		//设置需要处理XStreamAlias("xml")注释的类
		stream.processAnnotations(TextMessage.class);
		String xml = stream.toXML(msg);
		return xml;
	}

	/**
	 * 处理文本消息
	 * @param requestMap
	 * @return
	 * by 罗召勇 Q群193557337
	 */
	private static BaseMessage dealTextMessage(Map<String, String> requestMap) {
		//调用方法返回聊天的内容

		String resp="如需查询积分:\n回复【积分】\n查询订单信息：\n回复系统单号或者指令单号\n（暂时不支持模糊查询请输入正确的格式如【FM20-1234567】，【1234567】）";
		TextMessage tm = new TextMessage(requestMap, resp);
		String msg = requestMap.get("Content");
		if(msg.equals("积分")) {
		String username = requestMap.get("FromUserName");
		 resp = chat (username);
		  tm = new TextMessage(requestMap, resp);
		}else if(!GetCosDao.CosByUrlOn(msg).isEmpty()) {
			resp = chat1 (msg);
			  tm = new TextMessage(requestMap, resp);
		}
		return tm;
		
	}

	/**
	 * 
	 * @param msg 	发送的消息
	 * @return
	 * by 罗召勇 Q群193557337
	 */
	private static String chat(String username) {
		login lo=new login();
   lo =  getIntegralByName.GetIntegralByName(username);
   if(lo==null) {
	   return "您当前积分为0";
   }
		return "您当前积分为"+lo.getIntegral();
	}
	
	private static String chat1(String msg) {

		String text = "";
		List<Cos> cos  = GetCosDao.CosByUrlOn(msg);
   if(cos==null) {
	   return "查询为空";
   }else {

	   for (Cos c : cos) {
			text+="工厂:"+c.getFty()+
				 "\n 系统单:"+c.getOrderNo()+
				 "\n Item:"+c.getItem()+
				 "\n 产品名称:"+c.getProduct_name()+
				 "\n FILA PO:"+c.getFILAPO()+
				 "\n STYLE NO:"+c.getStyleColor()+
				 "\n 指令单号:"+c.getFactoryPO()+
				 "\n 生产完成日期:"+c.getSlDate()+
				 "\n 数量:"+c.getQty()+
				 "\n 备注:"+c.getRemark()+
				 "\n 状态:"+c.getState()+"\n------------------\n";
		}
		return  text;
   }
	}
	
	
	/**
	 * 获取用户的基本信息
	 * @return
	 * by 罗召勇 Q群193557337
	 */
	public static String getUserInfo(String openid) {
		String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		url = url.replace("ACCESS_TOKEN", getAccessToken()).replace("OPENID", openid);
		String result = Util.get(url);
		return result;
	}

}
