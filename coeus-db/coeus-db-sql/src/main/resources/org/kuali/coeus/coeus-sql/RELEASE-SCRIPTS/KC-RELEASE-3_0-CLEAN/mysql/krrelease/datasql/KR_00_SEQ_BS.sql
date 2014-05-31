drop procedure if exists p;
drop function if exists numericVal;

delimiter //

create function numericVal (str VARCHAR(1000)) RETURNS INT
DETERMINISTIC
BEGIN
  DECLARE counter INT DEFAULT 0;
  DECLARE strLength INT DEFAULT 0;
  DECLARE strChar VARCHAR(1000) DEFAULT '' ;
  DECLARE retVal VARCHAR(1000) DEFAULT '0';
  DECLARE numVal INT DEFAULT 0;

  SET strLength = LENGTH(str);

  WHILE strLength > 0 DO
    SET counter = counter+1;
    SET strChar = SUBSTRING(str,counter,1);
    IF strChar REGEXP('[0-9]+') = 1
      THEN SET retVal = CONCAT(retVal,strChar);
    END IF;
    SET strLength = strLength -1;
    SET strChar = NULL;
  END WHILE;
RETURN CAST(retVal as DECIMAL);
END //

create procedure p ()
begin
  declare l_new_seq INT;
  
  select ifnull(max(numericVal(KIM_ATTR_DEFN_ID)) + 1,1) into l_new_seq from KRIM_ATTR_DEFN_T where numericVal(KIM_ATTR_DEFN_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ATTR_DEFN_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ATTR_DEFN_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt; 
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(GRP_ID)) + 1,1) into l_new_seq from KRIM_GRP_T where numericVal(GRP_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_GRP_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_GRP_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(GRP_MBR_ID)) + 1,1) into l_new_seq from KRIM_GRP_MBR_T where numericVal(GRP_MBR_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_GRP_MBR_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_GRP_MBR_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(PERM_TMPL_ID)) + 1,1) into l_new_seq from KRIM_PERM_TMPL_T where numericVal(PERM_TMPL_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_PERM_TMPL_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_PERM_TMPL_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt; 
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(PERM_ID)) + 1,1) into l_new_seq from KRIM_PERM_T where numericVal(PERM_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_PERM_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_PERM_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(ROLE_ID)) + 1,1) into l_new_seq from KRIM_ROLE_T where numericVal(ROLE_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ROLE_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ROLE_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(ROLE_MBR_ID)) + 1,1) into l_new_seq from KRIM_ROLE_MBR_T where numericVal(ROLE_MBR_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ROLE_MBR_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ROLE_MBR_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt; 
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(RSP_ID)) + 1,1) into l_new_seq from KRIM_RSP_T where numericVal(RSP_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_RSP_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_RSP_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt; 
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(ROLE_PERM_ID)) + 1,1) into l_new_seq from KRIM_ROLE_PERM_T where numericVal(ROLE_PERM_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ROLE_PERM_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ROLE_PERM_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;

  select ifnull(max(numericVal(ROLE_RSP_ID)) + 1,1) into l_new_seq from KRIM_ROLE_RSP_T where numericVal(ROLE_RSP_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ROLE_RSP_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ROLE_RSP_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
          
  select ifnull(max(numericVal(ROLE_RSP_ACTN_ID)) + 1,1) into l_new_seq from KRIM_ROLE_RSP_ACTN_T where numericVal(ROLE_RSP_ACTN_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_ROLE_RSP_ACTN_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ROLE_RSP_ACTN_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(ID)) + 1,1) into l_new_seq from 
    ((select ifnull(max(numericVal(ATTR_DATA_ID)),0) as ID from KRIM_RSP_ATTR_DATA_T where numericVal(ATTR_DATA_ID) < 10000) 
      union (select ifnull(max(numericVal(ATTR_DATA_ID)),0) as ID from KRIM_DLGN_MBR_ATTR_DATA_T where numericVal(ATTR_DATA_ID) < 10000)
      union (select ifnull(max(numericVal(ATTR_DATA_ID)),0) as ID from KRIM_ROLE_MBR_ATTR_DATA_T where numericVal(ATTR_DATA_ID) < 10000)
      union (select ifnull(max(numericVal(ATTR_DATA_ID)),0) as ID from KRIM_PERM_ATTR_DATA_T where numericVal(ATTR_DATA_ID) < 10000)) as t1;
  set @create_seq := 'CREATE TABLE KRIM_ATTR_DATA_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_ATTR_DATA_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(KIM_TYP_ID)) + 1,1) into l_new_seq from KRIM_TYP_T where numericVal(KIM_TYP_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_TYP_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_TYP_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(numericVal(KIM_TYP_ATTR_ID)) + 1,1) into l_new_seq from KRIM_TYP_ATTR_T where numericVal(KIM_TYP_ATTR_ID) < 10000;
  set @create_seq := 'CREATE TABLE KRIM_TYP_ATTR_ID_BS_S (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE KRIM_TYP_ATTR_ID_BS_S auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
end //
delimiter ;

call p ();

drop function if exists numericVal;
drop procedure if exists p;