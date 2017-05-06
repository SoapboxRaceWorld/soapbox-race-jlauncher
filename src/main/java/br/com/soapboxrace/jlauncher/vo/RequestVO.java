package br.com.soapboxrace.jlauncher.vo;

public class RequestVO {

	private int responseCode;
	private String response;

	public RequestVO(int responseCode, String response) {
		this.responseCode = responseCode;
		this.response = response;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public String getResponse() {
		return response;
	}

}
