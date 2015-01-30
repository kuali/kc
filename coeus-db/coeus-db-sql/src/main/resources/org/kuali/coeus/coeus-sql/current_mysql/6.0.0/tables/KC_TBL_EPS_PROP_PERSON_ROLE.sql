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
alter table award_persons drop foreign key FK_PROP_PERS_ROLE_AWD_PERSONS
/

alter table EPS_PROP_PERSON_ROLE drop primary key
/

alter table EPS_PROP_PERSON_ROLE add PROP_PERSON_ROLE_CODE varchar(12)
/

update EPS_PROP_PERSON_ROLE set PROP_PERSON_ROLE_CODE = PROP_PERSON_ROLE_ID
/

alter table EPS_PROP_PERSON_ROLE modify column PROP_PERSON_ROLE_CODE varchar(12) not null
/

alter table EPS_PROP_PERSON_ROLE add SPONSOR_HIERARCHY_NAME varchar(50) not null default 'DEFAULT'
/

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE roleCode VARCHAR(12);
    DECLARE done INT DEFAULT FALSE;
    DECLARE cur CURSOR FOR SELECT PROP_PERSON_ROLE_CODE FROM EPS_PROP_PERSON_ROLE;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cur;
    
    insert_loop: LOOP
        FETCH cur INTO roleCode;
        IF done THEN
            LEAVE insert_loop;
        END IF;
        INSERT INTO SEQ_EPS_PROP_PERSON_ROLE VALUES (null);
        update EPS_PROP_PERSON_ROLE set PROP_PERSON_ROLE_ID = (select max(ID) from SEQ_EPS_PROP_PERSON_ROLE) where PROP_PERSON_ROLE_CODE = roleCode;
    END LOOP;

    CLOSE cur;
END;
/

call p()
/

drop procedure if exists p
/

alter table EPS_PROP_PERSON_ROLE modify column PROP_PERSON_ROLE_ID decimal(12,0) not null
/

alter table EPS_PROP_PERSON_ROLE add primary key (PROP_PERSON_ROLE_ID)
/

alter table EPS_PROP_PERSON_ROLE add constraint unique EPS_PROP_PERSON_ROLE_U1 (PROP_PERSON_ROLE_CODE, SPONSOR_HIERARCHY_NAME)
/

alter table EPS_PROP_PERSON_ROLE add READ_ONLY_ROLE char(1) not null default 'N'
/

DELIMITER ;
