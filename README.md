# 项目介绍

> 百创汇内部使用项目平台，主要集成项目管理、客户管理相关功能，便于数据分析和统计。

- JAVA为主要开发语言，python/shell为项目辅助语言。
- 主要框架：SpringMVC+mybatis+SpringShiro+Redis
- 风格：仿restful风格
- 主要功能：项目管理,客户管理,员工管理,日志管理

> 重大更新

- 数据库增加字段：sys_user add column locked int(11) 是否上锁,salt varcher(100) 密码随机盐值,status varcher(100) 是否删除
- 密码加密模式更新：(salt+login_name) md5两次迭代
- 数据库新增字段：tbl_custom add column country varchar(500), province varchar(500), city varchar(500)
