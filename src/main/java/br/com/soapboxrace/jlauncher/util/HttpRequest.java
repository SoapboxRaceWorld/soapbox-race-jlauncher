package br.com.soapboxrace.jlauncher.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import br.com.soapboxrace.jlauncher.vo.RequestVO;
import br.com.soapboxrace.jlauncher.vo.UserVO;

public class HttpRequest {

	private String serverUrl;

	public HttpRequest(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getLoginURL(UserVO userVO) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(serverUrl);
		stringBuilder.append("/soapbox-race-core/Engine.svc/User/authenticateUser?email=");
		stringBuilder.append(userVO.getEmail());
		stringBuilder.append("&password=");
		stringBuilder.append(userVO.getShaPassword());
		return stringBuilder.toString();
	}

	public String getCreateURL(UserVO userVO) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(serverUrl);
		stringBuilder.append("/soapbox-race-core/Engine.svc/User/createUser?email=");
		stringBuilder.append(userVO.getEmail());
		stringBuilder.append("&password=");
		stringBuilder.append(userVO.getShaPassword());
		stringBuilder.append("&inviteTicket=");
		stringBuilder.append(userVO.getInviteTicket());
		return stringBuilder.toString();
	}

	public RequestVO doRequest(String url) {
		RequestVO requestVO = null;
		try {
			URL serverAuth = new URL(url);
			HttpURLConnection serverCon = (HttpURLConnection) serverAuth.openConnection();
			serverCon.setRequestMethod("GET");
			InputStream inputStream = null;
			int responseCode = serverCon.getResponseCode();
			if (responseCode == 200) {
				inputStream = serverCon.getInputStream();
			} else {
				inputStream = serverCon.getErrorStream();
			}
			GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
			InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = bufferedReader.readLine()) != null) {
				response.append(inputLine);
			}
			requestVO = new RequestVO(responseCode, response.toString());
			bufferedReader.close();
		} catch (Exception e) {
			String message = e.getMessage();
			message = message.replace(";", " ");
			message = message.replace("&", "&amp;");
			requestVO = new RequestVO(500, "<EngineExceptionTrans><Description>Launcher error [" + message + "]</Description></EngineExceptionTrans>");
		}
		return requestVO;
	}

}
