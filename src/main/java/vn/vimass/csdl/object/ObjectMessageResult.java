package vn.vimass.csdl.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ObjectMessageResult {

	private Integer msgCode;
	private String msgContent;
	private Object result;

	public ObjectMessageResult() {
	}

	public ObjectMessageResult(Integer msgCode, String msgContent) {
		this.msgCode = msgCode;
		this.msgContent = msgContent;
	}

}
