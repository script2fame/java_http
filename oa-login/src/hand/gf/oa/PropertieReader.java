package hand.gf.oa;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertieReader {

	private static String LoginUrl;

	public static String getLoginUrl() {
		if(LoginUrl == null)
		{
			setProperties();
			return LoginUrl;
		}
		return LoginUrl;
	}

	private static void setProperties() {
		Properties prop = new Properties();
		try {
			// 读取属性文件a.properties
		      InputStream in = new BufferedInputStream((Thread.currentThread().getContextClassLoader().getResourceAsStream("hls-oa-login-properties.properties")));
			// 加载属性
			prop.load(in);
		
			LoginUrl = prop.getProperty("login.url");
			in.close();
		} catch (Exception e) {
			System.out.println("读取配置文件异常" + e);
		}
	}
}
