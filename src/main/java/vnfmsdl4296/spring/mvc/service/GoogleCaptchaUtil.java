package vnfmsdl4296.spring.mvc.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component("gcutil")
public class GoogleCaptchaUtil {

    // 구글 recaptcha 확인 여부
    public boolean checkCaptcha(String gCaptcha) {
        boolean isChecked = false;
        String secretKey = "6LfalQAVAAAAALw38KjxdNIVLZYAMfuyhGTYdPhq";
        String verifyURL = "https://www.google.com/recaptcha/api/siteverify";
        String params = "secret=" + secretKey + "&response=" + gCaptcha;

        try {
            String jsonData = "";


            URL url = new URL(verifyURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(params);
            dos.flush();
            dos.close();

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, "UTF-8") );

            StringBuffer sb = new StringBuffer();
            while ((jsonData = br.readLine()) != null) {
                sb.append(jsonData);
            }

            JSONParser parser = new JSONParser();
            Object json = parser.parse(sb.toString());
            JSONObject jsonObj = (JSONObject) json;

            isChecked = (boolean) jsonObj.get("success");
            System.out.println("recaptcha 결과 : " + isChecked);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return isChecked;

    }
}
