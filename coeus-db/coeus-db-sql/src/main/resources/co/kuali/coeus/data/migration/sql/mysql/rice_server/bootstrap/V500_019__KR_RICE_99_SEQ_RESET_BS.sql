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

DELIMITER /
drop procedure if exists p
/

create procedure p ()
begin
  declare l_new_seq INT;
  
  drop table KRIM_ATTR_DEFN_ID_S;
  select ifnull(max(cast(KIM_ATTR_DEFN_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_ATTR_DEFN_T where cast(KIM_ATTR_DEFN_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ATTR_DEFN_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ATTR_DEFN_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt; 
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_GRP_ID_S;
  select ifnull(max(cast(GRP_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_GRP_T where cast(GRP_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_GRP_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_GRP_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_GRP_MBR_ID_S;
  select ifnull(max(cast(GRP_MBR_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_GRP_MBR_T where cast(GRP_MBR_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_GRP_MBR_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_GRP_MBR_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_PERM_TMPL_ID_S;
  select ifnull(max(cast(PERM_TMPL_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_PERM_TMPL_T where cast(PERM_TMPL_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_PERM_TMPL_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_PERM_TMPL_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt; 
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_PERM_ID_S;
  select ifnull(max(cast(PERM_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_PERM_T where cast(PERM_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_PERM_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_PERM_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_ROLE_ID_S;
  select ifnull(max(cast(ROLE_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_ROLE_T where cast(ROLE_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ROLE_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ROLE_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_ROLE_MBR_ID_S;
  select ifnull(max(cast(ROLE_MBR_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_ROLE_MBR_T where cast(ROLE_MBR_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ROLE_MBR_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ROLE_MBR_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt; 
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_RSP_ID_S;
  select ifnull(max(cast(RSP_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_RSP_T where cast(RSP_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_RSP_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_RSP_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt; 
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_ROLE_PERM_ID_S;
  select ifnull(max(cast(ROLE_PERM_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_ROLE_PERM_T where cast(ROLE_PERM_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ROLE_PERM_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ROLE_PERM_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;

  drop table KRIM_ROLE_RSP_ID_S;
  select ifnull(max(cast(ROLE_RSP_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_ROLE_RSP_T where cast(ROLE_RSP_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ROLE_RSP_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ROLE_RSP_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_ROLE_RSP_ACTN_ID_S;
  select ifnull(max(cast(ROLE_RSP_ACTN_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_ROLE_RSP_ACTN_T where cast(ROLE_RSP_ACTN_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ROLE_RSP_ACTN_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ROLE_RSP_ACTN_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_ATTR_DATA_ID_S;
  select ifnull(max(cast(ID as SIGNED)),'1') + 1 into l_new_seq from 
    ((select max(cast(ATTR_DATA_ID as SIGNED)) as ID from KRIM_RSP_ATTR_DATA_T where cast(ATTR_DATA_ID as SIGNED) < 10000) 
      union (select max(cast(ATTR_DATA_ID as SIGNED)) as ID from KRIM_DLGN_MBR_ATTR_DATA_T where cast(ATTR_DATA_ID as SIGNED) < 10000)
      union (select max(cast(ATTR_DATA_ID as SIGNED)) as ID from KRIM_ROLE_MBR_ATTR_DATA_T where cast(ATTR_DATA_ID as SIGNED) < 10000)
      union (select max(cast(ATTR_DATA_ID as SIGNED)) as ID from KRIM_PERM_ATTR_DATA_T where cast(ATTR_DATA_ID as SIGNED) < 10000)) as t1;
  set @create_seq := 'CREATE TABLE KRIM_ATTR_DATA_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ATTR_DATA_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_TYP_ID_S;
  select ifnull(max(cast(KIM_TYP_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_TYP_T where cast(KIM_TYP_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_TYP_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_TYP_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  drop table KRIM_TYP_ATTR_ID_S;
  select ifnull(max(cast(KIM_TYP_ATTR_ID as SIGNED)),'1') + 1 into l_new_seq from KRIM_TYP_ATTR_T where cast(KIM_TYP_ATTR_ID as SIGNED) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_TYP_ATTR_ID_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_TYP_ATTR_ID_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
end
/

call p ()
/

drop procedure if exists p
/
DELIMITER ;
