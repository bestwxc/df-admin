/**
drop table t_base_administrative_division if exists cascade;
drop table t_base_tree_node if exists casecade;

*/

-- 行政区域表
create table t_base_administrative_division
(
	id bigint(18) auto_increment comment '主键ID'
		primary key,
	division_code varchar(45) not null comment '区域代码',
	division_name varchar(45) not null comment '区域名称',
	parent_id bigint(18) default '0' not null comment '上级ID',
	parent_division_code varchar(45) default '86' not null comment '上级区域代码',
	division_level int(8) default '1' not null comment '区域级别',
	level_adjust int(8) default '0' not null comment '级别调整',
	division_type varchar(45) null comment '区域代码',
	order_num int(8) default '0' not null comment '排序值',
	flag int(8) default '0' not null comment '状态',
	create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint t_base_administrative_division_division_code_uindex
		unique (division_code)
)comment '中国行政区域表';

create index idx_devision_parent_code
	on t_base_administrative_division (parent_division_code);

create index idx_division_parent_id
	on t_base_administrative_division (parent_id);




-- 配置树节点表
create table t_base_tree_node
(
	id bigint auto_increment comment '主键ID'
		primary key,
	node_value varchar(45) not null comment '节点值',
	node_name varchar(45) not null comment '节点名称',
	parent_id bigint default '0' not null comment '父节点ID',
	tree_node_path varchar(200) null comment '节点路径',
	order_num int default '0' not null comment '排序值',
	flag int default '1' not null comment '状态值',
	create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time timestamp default CURRENT_TIMESTAMP not null comment '更新时间',
	constraint uk_tree_node unique (parent_id, node_value, flag)
)
comment '配置树节点表，数据字典表';
create index idx_tree_node_type
	on t_base_tree_node (tree_node_path, node_value);

