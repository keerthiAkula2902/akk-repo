package POJO;

import java.util.List;

public class Courses {

	private List<WebAutomation> webAutomation;
	private List<POJO.api> api;
	private List<mobile> mobile;

	public List<WebAutomation> getWebAutomation() {
		return webAutomation;
	}

	public void setWebAutomation(List<WebAutomation> webAutomation) {
		this.webAutomation = webAutomation;
	}

	public List<POJO.api> getApi() {
		return api;
	}

	public void setApi(List<POJO.api> api) {
		this.api = api;
	}

	public List<POJO.mobile> getMobile() {
		return mobile;
	}

	public void setMobile(List<POJO.mobile> mobile) {
		this.mobile = mobile;
	}

}
