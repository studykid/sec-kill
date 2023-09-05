package com.mall.common;

/**
 * @author wy
 */
public class CodeMsg {

	private String code;
	private String msg;
	public static CodeMsg REGISTER_SUCCESS = new CodeMsg("200", "注册成功");

	public static CodeMsg REGISTER_FAIL = new CodeMsg("500", "用户名或者密码不能为空");

	public static CodeMsg REGISTER_FAIL_01 = new CodeMsg("500", "请输入正确的用户名密码");

	public static CodeMsg RECHARGE_SUCCESS = new CodeMsg("200", "充值成功");
	public static CodeMsg WITHDRAW_SUCCESS = new CodeMsg("200", "提现成功");
	public static CodeMsg PUBLISH_SUCCESS = new CodeMsg("200", "发布成功");
	public static CodeMsg PUT_ON_SALE_SUCCESS = new CodeMsg("200", "商品上架成功");
	public static CodeMsg PULL_OFF_SHELF_SUCCESS = new CodeMsg("200", "商品下架成功");
	public static CodeMsg UPDATE_STOCK_SUCCESS = new CodeMsg("200", "更新库存成功");

	public static CodeMsg SECKILL_FAIL = new CodeMsg("500", "秒杀失败");

	public static CodeMsg GEN_SECKILL_SUCCESS = new CodeMsg("200", "成功生成秒杀活动");

	public static CodeMsg DATE_ERROR = new CodeMsg("500", "时间转换错误");
	public static CodeMsg STOCK_ERROR = new CodeMsg("500", "库存不足");
	public static CodeMsg ORDER_PAY_SUCCESS = new CodeMsg("200", "订单支付成功");


	private CodeMsg(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}