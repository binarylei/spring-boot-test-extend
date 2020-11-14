DROP table if exists user;
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=innodb DEFAULT CHARSET=utf8;
