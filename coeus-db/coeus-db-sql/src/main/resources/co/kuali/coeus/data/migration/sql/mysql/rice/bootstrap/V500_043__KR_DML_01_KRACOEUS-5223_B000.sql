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
INSERT INTO KRIM_TYP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES( (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), UUID(), 1, 'Derived Role: Award Investigators', 'awardInvestigatorsRoleTypeService', 'Y', 'KC-AWARD')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, 'Investigators', 'KC-AWARD', 'Award Investigator Role', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: Award Investigators'), 'Y', NOW())
/
INSERT INTO KRIM_TYP_ID_S VALUES(NULL)
/

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES( (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), UUID(), 1, 'Derived Role: All Award Unit Administrators', 'awardAllUnitAdministratorDerivedRoleTypeService', 'Y', 'KC-AWARD')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, 'All Unit Administrators', 'KC-AWARD', 'All Award Unit Administrators', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Derived Role: All Award Unit Administrators'), 'Y', NOW())
/

-- report tracking notification parms
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-AWARD', 'Document', 'REPORT_TRACKING_NOTIFICATIONS_BATCH_RECIPIENT', UUID(), 1, 'CONFG', 'quickstart', 'This will be the user who receives the report tracking notification batch log.', 'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-AWARD', 'Document', 'REPORT_TRACKING_NOTIFICATIONS_BATCH_CRON_TRIGGER', UUID(), 1, 'CONFG', '0 */10 * * * ?', 'The cron expression to determine when the report tracking notification batch process will run.', 'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-AWARD', 'Document', 'REPORT_TRACKING_NOTIFICATIONS_BATCH_ENABLED', UUID(), 1, 'CONFG', 'N', 'Used to determine whether the report tracking notification batch process will run at all.', 'A', 'KC')
/
DELIMITER ;
