DELIMITER /

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE version INT;
    SELECT VER_NBR INTO version FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Maintain Questionnaire Usage';
    IF version <= 0 THEN 
        SET @alter_ver_nbr := 'UPDATE KRIM_PERM_T SET VER_NBR = 1 WHERE NMSPC_CD = ''KC-PD'' AND NM = ''Maintain Questionnaire Usage''';
        PREPARE alter_ver_nbr_stmt FROM @alter_ver_nbr;
        EXECUTE alter_ver_nbr_stmt;
        DEALLOCATE PREPARE alter_ver_nbr_stmt;
    END IF;
END
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE version INT;
    SELECT VER_NBR INTO version FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Questionnaire Usage';
    IF version <= 0 THEN 
        SET @alter_ver_nbr := 'UPDATE KRIM_PERM_T SET VER_NBR = 1 WHERE NMSPC_CD = ''KC-PROTOCOL'' AND NM = ''Maintain Questionnaire Usage''';
        PREPARE alter_ver_nbr_stmt FROM @alter_ver_nbr;
        EXECUTE alter_ver_nbr_stmt;
        DEALLOCATE PREPARE alter_ver_nbr_stmt;
    END IF;
END
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE version INT;
    SELECT VER_NBR INTO version FROM KRIM_ROLE_PERM_T 
        WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KR-SYS' AND ROLE_NM = 'Technical Administrator')
        AND PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Maintain Questionnaire Usage');
    IF version <= 0 THEN 
        SET @alter_ver_nbr := 'UPDATE KRIM_ROLE_PERM_T SET VER_NBR = 1 WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = ''KR-SYS'' AND ROLE_NM = ''Technical Administrator'') AND PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = ''KC-PD'' AND NM = ''Maintain Questionnaire Usage'')';
        PREPARE alter_ver_nbr_stmt FROM @alter_ver_nbr;
        EXECUTE alter_ver_nbr_stmt;
        DEALLOCATE PREPARE alter_ver_nbr_stmt;
    END IF;
END
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE version INT;
    SELECT VER_NBR INTO version FROM KRIM_ROLE_PERM_T 
        WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KR-SYS' AND ROLE_NM = 'Technical Administrator')
        AND PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Questionnaire Usage');
    IF version <= 0 THEN 
        SET @alter_ver_nbr := 'UPDATE KRIM_ROLE_PERM_T SET VER_NBR = 1 WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = ''KR-SYS'' AND ROLE_NM = ''Technical Administrator'') AND PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = ''KC-PROTOCOL'' AND NM = ''Maintain Questionnaire Usage'')';
        PREPARE alter_ver_nbr_stmt FROM @alter_ver_nbr;
        EXECUTE alter_ver_nbr_stmt;
        DEALLOCATE PREPARE alter_ver_nbr_stmt;
    END IF;
END
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
DELIMITER ;
