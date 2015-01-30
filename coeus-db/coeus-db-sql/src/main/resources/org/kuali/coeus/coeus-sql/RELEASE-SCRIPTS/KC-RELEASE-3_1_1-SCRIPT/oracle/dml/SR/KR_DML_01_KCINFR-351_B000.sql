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

DECLARE temp NUMBER;
BEGIN
    SELECT MIN(ROLE_PERM_ID) INTO temp FROM KRIM_ROLE_PERM_T 
        WHERE ROLE_ID = (SELECT ROLE_ID from KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Application Administrator')
        AND (PERM_ID IS NULL OR PERM_ID IN (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Entity'));
    IF temp IS NULL THEN
        EXECUTE IMMEDIATE 'INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID from KRIM_ROLE_T WHERE NMSPC_CD = ''KC-SYS'' AND ROLE_NM = ''Application Administrator''),(SELECT MAX(PERM_ID) FROM KRIM_PERM_T WHERE NM = ''Modify Entity''),''Y'',SYS_GUID(),1)';
    ELSE
        EXECUTE IMMEDIATE 'UPDATE KRIM_ROLE_PERM_T SET PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = ''KR-IDM'' AND NM = ''Modify Entity'') WHERE ROLE_PERM_ID = ''' || temp || '''';
    END IF;
END;
/

DECLARE temp NUMBER;
BEGIN
    SELECT MAX(ROLE_PERM_ID) INTO temp FROM KRIM_ROLE_PERM_T 
        WHERE ROLE_ID = (SELECT ROLE_ID from KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Application Administrator')
        AND (PERM_ID IS NULL OR PERM_ID IN (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Copy Document'));
    IF temp IS NULL THEN
        EXECUTE IMMEDIATE 'INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID from KRIM_ROLE_T WHERE NMSPC_CD = ''KC-SYS'' AND ROLE_NM = ''Application Administrator''),(SELECT MAX(PERM_ID) FROM KRIM_PERM_T WHERE NM = ''Copy RICE Document''),''Y'',SYS_GUID(),1)';
    ELSE
        EXECUTE IMMEDIATE 'UPDATE KRIM_ROLE_PERM_T SET PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = ''KR-SYS'' AND NM = ''Copy RICE Document'') WHERE ROLE_PERM_ID = ' || temp || '';
    END IF;
END;
/
