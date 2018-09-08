insert into t_base_tree_node(id,node_value,node_name,tree_node_path,parent_id,order_num,flag,create_time,update_time)
values(1,'root','根节点','',0,1,1,current_timestamp,current_timestamp);
insert into t_base_tree_node(id,node_value,node_name,tree_node_path,parent_id,order_num,flag,create_time,update_time)
values(2,'system','系统','root',1,1,1,current_timestamp,current_timestamp);