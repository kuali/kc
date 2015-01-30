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

UPDATE KRCR_PARM_T SET VAL='Maintain Questionnaire Usage:KC-IACUC;Modify Protocol:KC-PROTOCOL;Maintain Questionnaire Usage:KC-PD;Maintain Questionnaire Usage:KC-PROTOCOL;Edit Institutional Proposal:KC-IP;Maintain Questionnaire Usage:KC-COIDISCLOSURE'
WHERE NMSPC_CD='KC-QUESTIONNAIRE' and CMPNT_CD='P'
/
INSERT INTO KRIM_PERM_T (PERM_ID,OBJ_ID,VER_NBR,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND)
VALUES (KRIM_PERM_ID_BS_S.NEXTVAL,SYS_GUID(),1,(SELECT PERM_TMPL_ID from KRIM_PERM_TMPL_T WHERE NMSPC_CD='KC-IDM' AND NM='Questionnaire Permission'),'KC-IACUC','Maintain Questionnaire Usage','Add/Edit Questionnaire Usages for IACUC Protocol','Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator' AND NMSPC_CD='KC-UNT'),
    (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain Questionnaire Usage' AND NMSPC_CD='KC-IACUC'),'Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT) 
VALUES(KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'Maintain IACUC Questionnaire', 'KC-IACUC', 'Grants the ability to maintain IACUC questionnaires.', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Default'), 'Y', SYSDATE)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Questionnaire'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Question'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, KRIM_ROLE_ID_BS_S.CURRVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IACUC' AND NM = 'Maintain Questionnaire Usage'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Technical Administrator' AND nmspc_cd='KR-SYS'),
       (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IACUC' AND NM = 'Maintain Questionnaire Usage'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Maintain IACUC Questionnaire' AND nmspc_cd='KC-IACUC'),
       (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IACUC' AND NM = 'Maintain Questionnaire Usage'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
VALUES(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='KC Superuser' AND nmspc_cd='KC-SYS'),
       (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IACUC' AND NM = 'Maintain Questionnaire Usage'), 'Y')
/
