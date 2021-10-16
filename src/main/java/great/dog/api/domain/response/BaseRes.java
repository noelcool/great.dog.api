package great.dog.api.domain.response;

import great.dog.api.util.Status;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseRes {

	private int resCode;
	private String resMsg;

	public BaseRes() {
		this.resCode = Status.StatusCode.OK;
		this.resMsg = Status.ResponseMessage.RES_SUCCESS;
	}
	
	public BaseRes(int resCode, String resMsg) {
		this.resCode = resCode;
		this.resMsg = resMsg;
	}
}
