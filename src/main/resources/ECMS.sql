#用户
CREATE TABLE if not exists `user`
(
    `id`     int          NOT NULL AUTO_INCREMENT COMMENT '用户id',
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
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='元件柜数据';

CREATE TABLE if not exists `box`
(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT '元件柜单元id',
    `cabinet_id`   int NOT NULL COMMENT '元件柜id',
    `each_id` varchar(1000) COMMENT '',
    `total_size`  int NOT NULL default 0 COMMENT '存储总量',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='元件柜数据';

#元件
CREATE TABLE if not exists `component`
(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT 'id',
    `type`        int NOT NULL COMMENT '元件类别enum',
    `description` varchar(1000) COMMENT '描述',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='元件表';

CREATE TABLE if not exists `storage`
(
    `id`           int NOT NULL AUTO_INCREMENT COMMENT 'id',
    `box_id`   int NOT NULL COMMENT '元件柜id',
    `component_id` int NOT NULL COMMENT '元件id',
    `storage_size` int NOT NULL default 0 COMMENT '存储数量',
    `storage_unit` int NOT NULL default 0 COMMENT '存储单位',
    FOREIGN KEY (`box_id`) REFERENCES box (`id`),
    FOREIGN KEY (`component_id`) REFERENCES component (`id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='元件柜存储数据';

#记录
CREATE TABLE if not exists `record`
(
    `id`         int NOT NULL AUTO_INCREMENT COMMENT 'id',
    `type`       int NOT NULL COMMENT '记录类型0拿1放',
    `user_id`    int NOT NULL COMMENT '用户id',
    `storage_id` int NOT NULL COMMENT '存储id',
    `remark`     varchar(1000) COMMENT '备注',
    FOREIGN KEY (`user_id`) REFERENCES user (`id`),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='借还记录';