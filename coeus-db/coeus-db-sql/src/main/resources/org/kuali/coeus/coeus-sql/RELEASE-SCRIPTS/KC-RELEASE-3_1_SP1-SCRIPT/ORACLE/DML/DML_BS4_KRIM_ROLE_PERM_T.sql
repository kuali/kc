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

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Protocol Notes'), 'Y');

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',SYS_GUID(),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Create Award Account'),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'OSP Administrator'),
KRIM_ROLE_PERM_ID_BS_S.nextval,1);

INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',SYS_GUID(),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Modify AwardBudget'),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Aggregator'),
KRIM_ROLE_PERM_ID_BS_S.nextval,1);

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
	VALUES(KRIM_ROLE_PERM_ID_BS_S.nextval, SYS_GUID(), 0, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KR-SYS' AND ROLE_NM = 'Technical Administrator'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Maintain Questionnaire Usage'), 'Y');

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
	VALUES(KRIM_ROLE_PERM_ID_BS_S.nextval, SYS_GUID(), 0, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KR-SYS' AND ROLE_NM = 'Technical Administrator'), 
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Questionnaire Usage'), 'Y');

COMMIT;
