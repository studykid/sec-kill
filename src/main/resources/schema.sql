CREATE TABLE `mall_user_info` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`username` varchar(10) NOT NULL DEFAULT '' COMMENT '用户姓名',
`password` varchar(100) NOT NULL COMMENT '用户密码',
`balance` int(11) NOT NULL DEFAULT 0 COMMENT '余额',
`version` int(2) NOT NULL DEFAULT 0 COMMENT '版本',
`gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_user_info_username` (`username`) USING BTREE,
KEY `idx_user_info_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城用户表';

CREATE TABLE `mall_product_info` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`name` varchar(10) NOT NULL DEFAULT '' COMMENT '商品名称',
`description` varchar(200) NOT NULL DEFAULT '' COMMENT '商品描述',
`price` int(11) NOT NULL COMMENT '价格',
`stock` int(11) NOT NULL COMMENT '库存',
`status` int(1) NOT NULL COMMENT '状态,0- 初始化,1 - 上架,2 - 下架',
`gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城商品表';

CREATE TABLE `mall_activity_info` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`activityName` varchar(10) NOT NULL DEFAULT '' COMMENT '活动名称',
`productId` int(11) NOT NULL COMMENT '商品id',
`stockNum` int(1) NOT NULL COMMENT '库存',
`activityPrice` int(1) NOT NULL COMMENT '活动价格',
`gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_user_info_username` (`productId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城活动表';

CREATE TABLE `mall_order_info` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`orderId` varchar(100) NOT NULL DEFAULT '' COMMENT '订单号',
`productId` varchar(200) NOT NULL DEFAULT '' COMMENT '商品id',
`userId` int(11) NOT NULL COMMENT '用户id',
`buyNum` int(11) NOT NULL COMMENT '购买数量',
`amount` int(1) NOT NULL COMMENT '订单金额',
`orderTime` timestamp NOT NULL COMMENT '订单时间',
`status` int(1) NOT NULL COMMENT '状态,0：初始化，1：成功待支付，2：完成，3：处理中，4：失败，5：订单取消',
`gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_product_info_orderId` (`orderId`) USING BTREE,
KEY `idx_product_info_orderId` (`orderId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城订单表';


CREATE TABLE `mall_payment_records` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`orderId` varchar(100) NOT NULL DEFAULT '' COMMENT '订单号',
`productId` varchar(200) NOT NULL DEFAULT '' COMMENT '商品id',
`userId` int(11) NOT NULL COMMENT '用户id',
`buyNum` int(11) NOT NULL COMMENT '购买数量',
`amount` int(1) NOT NULL COMMENT '订单金额',
`orderTime` timestamp NOT NULL COMMENT '订单时间',
`status` int(1) NOT NULL COMMENT '状态,0：初始化，1：成功待支付，2：完成，3：处理中，4：失败，5：订单取消',
`gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`),
UNIQUE KEY `uk_product_info_name` (`orderId`) USING BTREE,
KEY `idx_product_info_name` (`orderId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城订单记录表';

