DELIMITER /

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE temp INT;
    SELECT MIN(ROLE_PERM_ID) INTO temp FROM KRIM_ROLE_PERM_T 
        WHERE ROLE_ID = (SELECT ROLE_ID from KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Application Administrator')
        AND (PERM_ID IS NULL OR PERM_ID IN (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Entity'));
    IF temp IS NULL THEN
        SET @insert_role_perm_seq := 'INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES (null)';
        PREPARE insert_role_perm_seq_stmt FROM @insert_role_perm_seq;
        EXECUTE insert_role_perm_seq_stmt;
        DEALLOCATE PREPARE insert_role_perm_seq_stmt;
        SET @insert_role_perm := 'INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_BS_S),(SELECT ROLE_ID from KRIM_ROLE_T WHERE NMSPC_CD = ''KC-SYS'' AND ROLE_NM = ''Application Administrator''),(SELECT MAX(PERM_ID) FROM KRIM_PERM_T WHERE NM = ''Modify Entity''),''Y'',UUID(),1)';
        PREPARE insert_role_perm_stmt FROM @insert_role_perm;
        EXECUTE insert_role_perm_stmt;
        DEALLOCATE PREPARE insert_role_perm_stmt;
    ELSE
        SET @update_role_perm := CONCAT('UPDATE KRIM_ROLE_PERM_T SET PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = ''KR-IDM'' AND NM = ''Modify Entity'') WHERE ROLE_PERM_ID = ', temp);
        PREPARE update_role_perm_stmt FROM @update_role_perm;
        EXECUTE update_role_perm_stmt;
        DEALLOCATE PREPARE update_role_perm_stmt;
    END IF;
END
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE temp INT;
    SELECT MAX(ROLE_PERM_ID) INTO temp FROM KRIM_ROLE_PERM_T 
        WHERE ROLE_ID = (SELECT ROLE_ID from KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Application Administrator')
        AND (PERM_ID IS NULL OR PERM_ID IN (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Copy Document'));
    IF temp IS NULL THEN
        SET @insert_role_perm_seq := 'INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES (null)';
        PREPARE insert_role_perm_seq_stmt FROM @insert_role_perm_seq;
        EXECUTE insert_role_perm_seq_stmt;
        DEALLOCATE PREPARE insert_role_perm_seq_stmt;
        SET @insert_role_perm := 'INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_BS_S),(SELECT ROLE_ID from KRIM_ROLE_T WHERE NMSPC_CD = ''KC-SYS'' AND ROLE_NM = ''Application Administrator''),(SELECT MAX(PERM_ID) FROM KRIM_PERM_T WHERE NM = ''Copy Document''),''Y'',UUID(),1)';
        PREPARE insert_role_perm_stmt FROM @insert_role_perm;
        EXECUTE insert_role_perm_stmt;
        DEALLOCATE PREPARE insert_role_perm_stmt;
    ELSE
        SET @update_role_perm := CONCAT('UPDATE KRIM_ROLE_PERM_T SET PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = ''KR-SYS'' AND NM = ''Copy Document'') WHERE ROLE_PERM_ID = ', temp);
        PREPARE update_role_perm_stmt FROM @update_role_perm;
        EXECUTE update_role_perm_stmt;
        DEALLOCATE PREPARE update_role_perm_stmt;
    END IF;
END
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
delimiter ;