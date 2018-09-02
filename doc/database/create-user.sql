-- 使用root用户登录 mysql -uroot -p123456

-- 删除数据库
-- drop database dream_flower;

-- 删除用户
-- drop user 'dream_flower'@'%';

-- 创建用户
create user 'dream_flower'@'%' identified by 'dream_flower';

-- 创建数据库
create database dream_flower DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

-- 授予权限
grant all privileges on dream_flower.* to 'dream_flower'@'%';

-- 刷新权限
flush privileges;

-- 使用dream_flower登录

