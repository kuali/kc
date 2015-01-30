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
