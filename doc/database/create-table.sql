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

