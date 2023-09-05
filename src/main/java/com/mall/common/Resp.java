package com.mall.common;

/**
 * @author wy
 */
public class Resp<T> {
	private String respCode;
	private String respMsg;
	private T data;

	/**
	 * 成功时候的调用
	 * */
	public static <T> Resp<T> ok(T data){
		return new Resp<T>(data);
	}
	
	/**
	 * 失败时候的调用
	 * */
	public static <T> Resp<T> error(CodeMsg cm){
		return new Resp<T>(cm);
	}
	
	private Resp(T data) {
		this.respCode = "200";
		this.respMsg = "success";
		this.data = data;
	}
	
	private Resp(CodeMsg cm) {
		if(cm == null) {
			return;
		}
		this.respCode = cm.getCode();
		this.respMsg = cm.getMsg();
	}

	public String getRespCode() {
		return respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public T getData() {
		return data;
	}
}
