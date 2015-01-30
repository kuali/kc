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

INSERT INTO KRIM_ROLE_T(ROLE_ID,KIM_TYP_ID,NMSPC_CD,ROLE_NM,DESC_TXT,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ROLE_ID_BS_S.NEXTVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'),'KC-UNT','Maintain Unit','Maintain Unit','Y',SYSDATE,SYS_GUID(),1)
/

INSERT INTO KRIM_PERM_T(PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES(KRIM_PERM_ID_BS_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),'KC-UNT','Add Unit','Add Unit','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_PERM_ATTR_DATA_T(ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-UNT' AND NM = 'Add Unit'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),'UnitMaintenanceDocument', SYS_GUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Maintain Unit'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-UNT' AND NM = 'Add Unit'),'Y',SYS_GUID(),1)
/

INSERT INTO KRIM_PERM_T(PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES(KRIM_PERM_ID_BS_S.NEXTVAL,(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),'KC-UNT','Modify Unit','Modify Unit','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_PERM_ATTR_DATA_T(ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-UNT' AND NM = 'Modify Unit'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),'UnitMaintenanceDocument', SYS_GUID(),1)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Maintain Unit'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-UNT' AND NM = 'Modify Unit'),'Y',SYS_GUID(),1)
/
