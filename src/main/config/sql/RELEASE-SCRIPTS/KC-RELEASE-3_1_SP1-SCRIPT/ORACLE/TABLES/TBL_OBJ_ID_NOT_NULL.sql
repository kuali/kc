begin
for i in (select u.table_name from user_tab_cols u where u.column_name = 'OBJ_ID')
loop
   execute immediate 'update ' || i.table_name || ' set OBJ_ID = sys_guid()' || ' WHERE OBJ_ID IS NULL';
end loop;
for i in (select u.table_name from user_tab_cols u where u.column_name = 'OBJ_ID' and u.nullable = 'Y')
loop
	execute immediate 'alter table ' || i.table_name || ' modify obj_id not null';
end loop;
end;
