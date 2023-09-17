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
)ENGINE = InnoDB
 AUTO_INCREMENT = 1
 DEFAULT CHARSET = utf8 COMMENT ='用户权限';

CREATE TABLE if not exists ``
(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT 'id',
    PRIMARY KEY (`id`)
)ENGINE = InnoDB
 AUTO_INCREMENT = 1
 DEFAULT CHARSET = utf8 COMMENT ='元件柜数据';

CREATE TABLE if not exists ``
(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT 'id',
    PRIMARY KEY (`id`)
)ENGINE = InnoDB
 AUTO_INCREMENT = 1
 DEFAULT CHARSET = utf8 COMMENT ='元件类别';

CREATE TABLE if not exists ``
(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT 'id',
    PRIMARY KEY (`id`)
)ENGINE = InnoDB
 AUTO_INCREMENT = 1
 DEFAULT CHARSET = utf8 COMMENT ='元件表';

CREATE TABLE if not exists ``
(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT 'id',
    PRIMARY KEY (`id`)
)ENGINE = InnoDB
 AUTO_INCREMENT = 1
 DEFAULT CHARSET = utf8 COMMENT ='元件柜存储数据';

CREATE TABLE if not exists ``
(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT 'id',
    PRIMARY KEY (`id`)
)ENGINE = InnoDB
 AUTO_INCREMENT = 1
 DEFAULT CHARSET = utf8 COMMENT ='借还记录';