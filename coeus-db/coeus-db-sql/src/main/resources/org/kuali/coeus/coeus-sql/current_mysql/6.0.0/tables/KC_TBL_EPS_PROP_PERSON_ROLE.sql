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
