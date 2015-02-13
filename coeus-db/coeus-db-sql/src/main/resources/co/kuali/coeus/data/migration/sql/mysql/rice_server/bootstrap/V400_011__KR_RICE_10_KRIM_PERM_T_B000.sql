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

DROP PROCEDURE IF EXISTS p;

DELIMITER //
CREATE PROCEDURE p ()
BEGIN
    DECLARE riceId INT;
    DECLARE duplicateId INT;

    SELECT MIN(PERM_ID) into riceId FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'View Other Action List';
    SELECT MAX(PERM_ID) into duplicateId FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'View Other Action List';
    SET @update_krim_role_perm_t := CONCAT('UPDATE KRIM_ROLE_PERM_T SET PERM_ID = ', riceId, ' WHERE PERM_ID = ', duplicateId, '');
    PREPARE update_krim_role_perm_t_stmt FROM @update_krim_role_perm_t;
    EXECUTE update_krim_role_perm_t_stmt;
    DEALLOCATE PREPARE update_krim_role_perm_t_stmt;
    SET @delete_from_krim_perm_attr_data_t := CONCAT('DELETE FROM KRIM_PERM_ATTR_DATA_T WHERE PERM_ID = ', duplicateId, '');
    PREPARE delete_from_krim_perm_attr_data_t_stmt FROM @delete_from_krim_perm_attr_data_t;
    EXECUTE delete_from_krim_perm_attr_data_t_stmt;
    DEALLOCATE PREPARE delete_from_krim_perm_attr_data_t_stmt;
    SET @delete_from_krim_perm_t := CONCAT('DELETE FROM KRIM_PERM_T WHERE PERM_ID = ', duplicateId, '');
    PREPARE delete_from_krim_perm_t_stmt FROM @delete_from_krim_perm_t;
    EXECUTE delete_from_krim_perm_t_stmt;
    DEALLOCATE PREPARE delete_from_krim_perm_t_stmt;
END //
DELIMITER ;

CALL p ();

DROP PROCEDURE IF EXISTS p;

DELIMITER //
CREATE PROCEDURE p ()
BEGIN
    DECLARE riceId INT;
    DECLARE duplicateId INT;

    SELECT MIN(PERM_ID) into riceId FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Unrestricted Document Search';
    SELECT MAX(PERM_ID) into duplicateId FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Unrestricted Document Search';
    SET @update_krim_role_perm_t := CONCAT('UPDATE KRIM_ROLE_PERM_T SET PERM_ID = ', riceId, ' WHERE PERM_ID = ', duplicateId, '');
    PREPARE update_krim_role_perm_t_stmt FROM @update_krim_role_perm_t;
    EXECUTE update_krim_role_perm_t_stmt;
    DEALLOCATE PREPARE update_krim_role_perm_t_stmt;
    SET @delete_from_krim_perm_attr_data_t := CONCAT('DELETE FROM KRIM_PERM_ATTR_DATA_T WHERE PERM_ID = ', duplicateId, '');
    PREPARE delete_from_krim_perm_attr_data_t_stmt FROM @delete_from_krim_perm_attr_data_t;
    EXECUTE delete_from_krim_perm_attr_data_t_stmt;
    DEALLOCATE PREPARE delete_from_krim_perm_attr_data_t_stmt;
    SET @delete_from_krim_perm_t := CONCAT('DELETE FROM KRIM_PERM_T WHERE PERM_ID = ', duplicateId, '');
    PREPARE delete_from_krim_perm_t_stmt FROM @delete_from_krim_perm_t;
    EXECUTE delete_from_krim_perm_t_stmt;
    DEALLOCATE PREPARE delete_from_krim_perm_t_stmt;
END //
DELIMITER ;

CALL p ();

DROP PROCEDURE IF EXISTS p;

DELIMITER /
UPDATE KRIM_PERM_T SET NM = 'Initiate Simple Document' WHERE DESC_TXT = 'Authorizes the initiation of KC Simple Maintenance documents.'
/
UPDATE KRIM_PERM_T SET NM = 'Ad Hoc Review Document - Approve' WHERE DESC_TXT = 'Authorizes users to take the Approve action on KC documents Ad Hoc routed to them.'
/
UPDATE KRIM_PERM_T SET NM = 'Ad Hoc Review Document - FYI' WHERE DESC_TXT = 'Authorizes users to take the FYI action on KC documents Ad Hoc routed to them.'
/
UPDATE KRIM_PERM_T SET NM = 'Ad Hoc Review Document - Acknowledge' WHERE DESC_TXT = 'Authorizes users to take the Acknowledge action on KC documents Ad Hoc routed to them.'
/
DELIMITER ;
