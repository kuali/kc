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

INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Add/Edit Questionnaire Usages for COI','Maintain Questionnaire Usage','KC-COIDISCLOSURE',SYS_GUID(),KRIM_PERM_ID_BS_S.NEXTVAL,
          (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD='KC-IDM' AND NM='Questionnaire Permission'),1)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT) 
VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Maintain COI Questionnaire', 'KC-COIDISCLOSURE', 'Grants the ability to maintain COI questionnaires.', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Default'), 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Questionnaire'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Question'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-COIDISCLOSURE' AND NM = 'Maintain Questionnaire Usage'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Technical Administrator' AND nmspc_cd='KR-SYS'),
       (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-COIDISCLOSURE' AND NM = 'Maintain Questionnaire Usage'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Maintain COI Questionnaire' AND nmspc_cd='KC-COIDISCLOSURE'),
       (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-COIDISCLOSURE' AND NM = 'Maintain Questionnaire Usage'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='KC Superuser' AND nmspc_cd='KC-SYS'),
       (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-COIDISCLOSURE' AND NM = 'Maintain Questionnaire Usage'), 'Y')
/
