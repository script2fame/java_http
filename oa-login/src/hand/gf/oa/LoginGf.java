package hand.gf.oa;
/**
 * java使用post方式实现用户登录
 * created by hungteshun 
 * 
 **/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

public class LoginGf {
	
	private String loginUrl = PropertieReader.getLoginUrl();
	
	public String login(String  username,String password){
		PrintWriter out = null;
        BufferedReader in = null;
        String callback= "cb";
        String result = "";
        String param = MessageFormat.format(
				"username={0}&password={1}&callback={2}", username, password,
				callback);
        try {
        	System.out.println(loginUrl);
            URL realUrl = new URL(loginUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应 
            int a = conn.getInputStream().available();
            byte[] b = new byte[a];
            conn.getInputStream().read(b);    
            result = new String(b , "utf-8");
            return result;
        } catch (Exception e) {
            System.out.println("POST请求异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return null;
	}
}
