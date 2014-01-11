DELIMITER /

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE roleCount INT DEFAULT 0;
    DECLARE rolePermCount INT DEFAULT 0;
    DECLARE id VARCHAR(40);
    DECLARE done INT DEFAULT FALSE;
    DECLARE cur CURSOR FOR SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD LIKE 'KC%';
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    
    OPEN cur;

    SELECT COUNT(*) INTO roleCount FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'KC Superuser';
    IF roleCount = 0 THEN
        SET @insert_role_seq := 'INSERT INTO KRIM_ROLE_ID_BS_S VALUES (null)';
        PREPARE insert_role_seq_stmt FROM @insert_role_seq;
        EXECUTE insert_role_seq_stmt;
        DEALLOCATE PREPARE insert_role_seq_stmt;
        SET @insert_role := 'INSERT INTO KRIM_ROLE_T (ROLE_ID,KIM_TYP_ID,NMSPC_CD,ROLE_NM,DESC_TXT,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_BS_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = ''Default''),''KC-SYS'',''KC Superuser'',''KC Superuser role for administration access'',''Y'',NOW(),UUID(),1)';
        PREPARE insert_role_stmt FROM @insert_role;
        EXECUTE insert_role_stmt;
        DEALLOCATE PREPARE insert_role_stmt;
    END IF;
    
    insert_loop: LOOP
        FETCH cur INTO id;
        IF done THEN
            LEAVE insert_loop;
        END IF;
        SET @count_role_perm := CONCAT('SELECT COUNT(*) INTO @rolePermCount FROM KRIM_ROLE_PERM_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = ''KC-SYS'' AND ROLE_NM = ''KC Superuser'') AND PERM_ID = ', id);
        PREPARE count_role_perm_stmt FROM @count_role_perm;
        EXECUTE count_role_perm_stmt;
        DEALLOCATE PREPARE count_role_perm_stmt;
        IF @rolePermCount = 0 THEN
            SET @insert_role_perm_seq := 'INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES (null)';
            PREPARE insert_role_perm_seq_stmt FROM @insert_role_perm_seq;
            EXECUTE insert_role_perm_seq_stmt;
            DEALLOCATE PREPARE insert_role_perm_seq_stmt;
            SET @insert_role_perm := CONCAT('INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = ''KC-SYS'' AND ROLE_NM = ''KC Superuser''),',id,',''Y'',UUID(),1)');        
            PREPARE insert_role_perm_stmt FROM @insert_role_perm;
            EXECUTE insert_role_perm_stmt;
            DEALLOCATE PREPARE insert_role_perm_stmt;
        END IF;
    END LOOP;
    
    CLOSE cur;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/

CREATE PROCEDURE p ()
BEGIN
    DECLARE roleMbrCount INT;

    SELECT COUNT(*) INTO roleMbrCount FROM KRIM_ROLE_MBR_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'KC Superuser') AND MBR_ID = (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'admin');
    IF roleMbrCount = 0 THEN
        INSERT INTO KRIM_ROLE_MBR_ID_BS_S VALUES(NULL);
        INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID,ROLE_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR)
            VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_BS_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'KC Superuser'),(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'admin'),'P',NOW(),UUID(),1);
        INSERT INTO KRIM_ATTR_DATA_ID_BS_S VALUES(NULL);
        INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ROLE_MBR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
            VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_BS_S),(SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_BS_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'unitNumber'),'000001',UUID(),1);
        INSERT INTO KRIM_ATTR_DATA_ID_BS_S VALUES(NULL);
        INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ROLE_MBR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
            VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_BS_S),(SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_BS_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'subunits'),'Y',UUID(),1);
    END IF;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
DELIMITER ;
