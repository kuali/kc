--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

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
