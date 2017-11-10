package com.hand.oa.synuser;

import javax.ws.rs.core.MediaType;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class SynUserFromOa {

	private String AuthServiceUrl = PropertieReader.getAuthServiceUrl();
	private String ApplicationID = PropertieReader.getApplicationID();// 调用API的标识
	private String Password = PropertieReader.getPassword();// 调用API的密码
	private String CompanyId = PropertieReader.getCompanyId();

	public String doMain() {
		try {
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(AuthServiceUrl);
			ClientResponse response = service.path("usersbydeptid")
					.path(ApplicationID).path(Password).path(CompanyId)
					.type(MediaType.APPLICATION_XML_TYPE)
					.accept(MediaType.APPLICATION_XML)
					.get(ClientResponse.class);
//			GfUserDataListRestfulResult erprs = response
//					.getEntity(GfUserDataListRestfulResult.class);
//			System.out.println("CALL Result:" + erprs.isSuccess());
//			System.out.println("CALL Result Msg:" + erprs.getMsg());
//			if (erprs.getRet() != null) {
//				for (GfUser o : erprs.getRet()) {
//					System.out.println(o.getLoginid());
//				}
//			}
			//将结果转换成String
			String result = response.getEntity(String.class);
			XMLSerializer xmlSerializer = new XMLSerializer();
			JSON json = xmlSerializer.read(result);
			return json.toString(1);
		} catch (Exception e) {
			System.out.println(e);
			return "";
		}
	}
}
