begin
for i in (select u.table_name from user_tab_cols u where u.column_name = 'OBJ_ID')
loop
   execute immediate 'update ' || i.table_name || ' set OBJ_ID = sys_guid()' || ' WHERE OBJ_ID IS NULL';
end loop;
end;
