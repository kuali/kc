set serveroutput on size 1000000
begin
for i in (select u.table_name from user_tab_cols u where u.column_name = 'OBJ_ID' and u.nullable = 'Y')
loop
  begin
	execute immediate 'update ' || i.table_name || ' set OBJ_ID = sys_guid()' || ' WHERE OBJ_ID IS NULL';
	execute immediate 'alter table ' || i.table_name || ' modify obj_id not null';
  exception
    when others then dbms_output.put_line('Error: ' || SQLERRM || ' trying to modifying table: ' || i.table_name);
  end;
end loop;
end;
/
