package com.hand.oa.synuser;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertieReader {

	private static String AuthServiceUrl; // WebService调用的地址
	private static String ApplicationID;
	private static String Password;
	private static String CompanyId;
	// 获取webservice地址
	public static String getAuthServiceUrl() {
		if (AuthServiceUrl == null) {
			setProperties();
			return AuthServiceUrl;
		}
		return AuthServiceUrl;
	}

	// 获取应用系统名
	public static String getApplicationID() {
		if (ApplicationID == null) {
			setProperties();
			return ApplicationID;
		}
		return ApplicationID;
	}

	// 获取应用系统密码
	public static String getPassword() {
		if (Password == null) {
			setProperties();
			return Password;
		}
		return Password;
	}

	// 获取应用系统密码
		public static String getCompanyId() {
			if (CompanyId == null) {
				setProperties();
				return CompanyId;
			}
			return CompanyId;
		}
	private static void setProperties() {
		Properties prop = new Properties();
		try {
			// 读取属性文件a.properties
			InputStream in = new BufferedInputStream(
					(Thread.currentThread().getContextClassLoader()
							.getResourceAsStream("hls-oa-synuser-properties.properties")));
			// 加载属性
			prop.load(in);

			AuthServiceUrl = prop.getProperty("AuthServiceUrl");
			ApplicationID = prop.getProperty("ApplicationID");
			Password = prop.getProperty("Password");
			CompanyId = prop.getProperty("CompanyId");
			in.close();
		} catch (Exception e) {
			System.out.println("读取配置文件异常" + e);
		}
	}
}
