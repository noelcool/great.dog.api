package great.dog.api.domain.response;

import great.dog.api.util.Message;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseRes {

	private String resCode;
	private String resMsg;
	private int resResult;
	
	public BaseRes() {
		this.resCode = Message.codeSuccess;
		this.resMsg = Message.msgSuccess;
		this.resResult = 0;
	}
	
	public BaseRes(String resCode, String resMsg, int resResult) {
		this.resCode = resCode;
		this.resMsg = resMsg;
		this.resResult = resResult;
	}
}
