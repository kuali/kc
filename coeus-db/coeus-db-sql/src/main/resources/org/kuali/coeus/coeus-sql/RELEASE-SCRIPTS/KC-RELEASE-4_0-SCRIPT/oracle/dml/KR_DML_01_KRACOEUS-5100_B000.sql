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

INSERT INTO KRIM_ROLE_T(ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR) VALUES('Y','Modify Sponsor Template',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),SYSDATE,'KC-AWARD',SYS_GUID(),KRIM_ROLE_ID_BS_S.NEXTVAL,'Modify Sponsor Template',1)
/
INSERT INTO KRIM_PERM_T(PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID) VALUES(KRIM_PERM_ID_BS_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD ='KR-SYS' AND NM ='Initiate Document'),'KC-AWARD','Create Award Sponsor Template','Create Award Sponsor Template','Y',SYS_GUID())
/
INSERT INTO KRIM_PERM_ATTR_DATA_T(ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID) VALUES(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Award Sponsor Template' AND NMSPC_CD='KC-AWARD'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),'AwardTemplateMaintenanceDocument', SYS_GUID())
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Sponsor Template' AND NMSPC_CD='KC-AWARD'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Award Sponsor Template' AND NMSPC_CD='KC-AWARD'), 'Y')
/
INSERT INTO KRIM_PERM_T(PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID) VALUES(KRIM_PERM_ID_BS_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'),'KC-AWARD','Modify Award Sponsor Template','Modify Award Sponsor Template','Y',SYS_GUID())
/
INSERT INTO KRIM_PERM_ATTR_DATA_T(ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID) VALUES(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Award Sponsor Template' AND NMSPC_CD='KC-AWARD'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),'AwardTemplateMaintenanceDocument', SYS_GUID())
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Sponsor Template' AND NMSPC_CD='KC-AWARD'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Award Sponsor Template' AND NMSPC_CD='KC-AWARD'), 'Y')
/
INSERT INTO KRIM_PERM_T(PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID) VALUES(KRIM_PERM_ID_BS_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'),'KC-AWARD','View Award Sponsor Template','View Award Sponsor Template','Y',SYS_GUID())
/
INSERT INTO KRIM_PERM_ATTR_DATA_T(ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID) VALUES(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Award Sponsor Template' AND NMSPC_CD='KC-AWARD'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),'AwardTemplateMaintenanceDocument', SYS_GUID())
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL, SYS_GUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Sponsor Template' AND NMSPC_CD='KC-AWARD'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Award Sponsor Template' AND NMSPC_CD='KC-AWARD'),'Y')
/
commit
/
