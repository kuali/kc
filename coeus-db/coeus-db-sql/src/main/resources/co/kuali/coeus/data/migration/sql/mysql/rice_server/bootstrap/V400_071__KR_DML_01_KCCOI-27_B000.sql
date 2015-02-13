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
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
VALUES ('Y','View Restricted Notes in Coi Disclosure','View Coi Restricted Notes','KC-COIDISCLOSURE',UUID(),(SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'View Document Section'),'1')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM= 'COI Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Coi Restricted Notes'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM= 'KC Superuser'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM= 'View Coi Restricted Notes'), 'Y')
/
-- Creates a system parameter to define the default sort behavior of protocol attachments
INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,VAL,PARM_DESC_TXT,PARM_TYP_CD,EVAL_OPRTR_CD,APPL_ID,OBJ_ID,VER_NBR)
VALUES ('KC-COIDISCLOSURE','Document','coiAttachmentDefaultSort','LAUP','Default sort for coi disclosure attachments','CONFG','A','KC',UUID(),'1')
/
DELIMITER ;
