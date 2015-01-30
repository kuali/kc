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

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD='KC-IACUC' AND NM='View IACUC Protocol Online Review Comments'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KC-SYS' AND NM='Document Section'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD='KR-WKFLW' AND NM='documentTypeName'),'iacucProtocolDocument',SYS_GUID(),1)
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD='KC-IACUC' AND NM='View IACUC Protocol Online Review Comments'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KC-SYS' AND NM='Document Section'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD='KC-SYS' AND NM='sectionName'),'reviewComments',SYS_GUID(),1)
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD='KC-IACUC' AND NM='Maintain IACUC Protocol Online Review Comments'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KC-SYS' AND NM='Document Section'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD='KR-WKFLW' AND NM='documentTypeName'),'IacucProtocolOnlineReviewDocument',SYS_GUID(),1)
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD='KC-IACUC' AND NM='Maintain IACUC Protocol Online Review Comments'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD='KC-SYS' AND NM='Document Section'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD='KC-SYS' AND NM='sectionName'),'reviewComments',SYS_GUID(),1)
/
