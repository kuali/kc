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

delimiter ;
drop procedure if exists p;

delimiter //
create procedure p ()
begin
  declare l_new_seq INT;

  select ifnull(max(cast(ID as SIGNED)),'1') + 1 into l_new_seq from 
    (select max(cast(DOC_HDR_ID as SIGNED)) as ID from KREW_DOC_HDR_T
      union select max(cast(DOC_TYP_ID as SIGNED)) as ID from KREW_DOC_TYP_T
      union select max(cast(APP_DOC_STAT_TRAN_ID as SIGNED)) as ID from KREW_APP_DOC_STAT_TRAN_T) as t1;
  set @create_seq := 'CREATE TABLE IF NOT EXISTS KREW_DOC_HDR_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KREW_DOC_HDR_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
end //
delimiter ;

call p ();

drop procedure if exists p;
