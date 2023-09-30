#用户
CREATE TABLE if not exists `user`
(
    `id`     int          NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username` varchar(225) NOT NULL COMMENT '225',
    `log_id` varchar(225) NOT NULL COMMENT '登录id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='用户账号';

CREATE TABLE if not exists `user_role`
(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT '权限id',
    `user_id` int NOT NULL COMMENT '用户id',
    `role`    int NOT NULL COMMENT '权限',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES user (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='用户权限';

#元件柜
CREATE TABLE if not exists `cabinet`
(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT '元件柜id',
    `location`    varchar(1000) COMMENT '地点',
    `description` varchar(1000) COMMENT '描述',
    `box_size` int NOT NULL COMMENT '柜门数量',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='元件柜数据';

CREATE TABLE if not exists `box`
(
    `id`         int NOT NULL AUTO_INCREMENT COMMENT '元件柜联合id',
    `cabinet_id` int NOT NULL COMMENT '元件柜id',
    `box_id`     int NOT NULL COMMENT '元件柜门id',
    `action_type`     int NOT NULL default 0 COMMENT '开启类型默认0直接开启',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='元件柜数据';

#记录
CREATE TABLE if not exists `record`
(
    `id`         int NOT NULL AUTO_INCREMENT COMMENT 'id',
    `message_id` varchar(225) NOT NULL COMMENT '生成信息Id',
    `type`       int NOT NULL COMMENT '记录类型0拿1放2初始化3未更改',
    `user_id`    int NOT NULL COMMENT '用户id',
    `box_id`       int NOT NULL COMMENT '元件柜id',
    `component_index` int NOT NULL COMMENT '元件索引',
    `storage_size` int NOT NULL default 0 COMMENT '存储数量',
    `state`      int NOT NULL COMMENT '状态0创建1等待2成功3失败4超时',
    `message_state`      varchar(225) COMMENT '信息',
    `remark`     varchar(1000) COMMENT '备注',
    `update_time` datetime NOT NULL default CURRENT_TIMESTAMP COMMENT '时间',
    FOREIGN KEY (`user_id`) REFERENCES user (`id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='借还记录';