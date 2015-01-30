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

INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','Funding Source Monitor',(select KIM_TYP_ID from KRIM_TYP_T where nm = 'UnitHierarchy'),sysdate,'KC-UNT',sys_guid(),KRIM_ROLE_ID_BS_S.nextval,'Funding Source Monitor',1)
/


INSERT INTO KRIM_ROLE_MBR_T (LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,ROLE_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (SYSDATE,
(select role_id from KRIM_ROLE_T where NMSPC_CD = 'KC-UNT' AND role_nm = 'IRB Administrator'),'R',SYS_GUID(),
KRIM_ROLE_ID_BS_S.currval,KRIM_ROLE_MBR_ID_BS_S.nextval,1)
/



INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,'000001',
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'unitNumber'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),SYS_GUID(),
KRIM_ROLE_MBR_ID_BS_S.currval,1)
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,'Y',
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'subunits'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),SYS_GUID(),
KRIM_ROLE_MBR_ID_BS_S.currval,1)
/


INSERT INTO KRIM_ROLE_MBR_T (LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,ROLE_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (SYSDATE,
(select role_id from KRIM_ROLE_T where NMSPC_CD = 'KC-ADM' AND role_nm = 'OSP Administrator'),'R',SYS_GUID(),
KRIM_ROLE_ID_BS_S.currval,KRIM_ROLE_MBR_ID_BS_S.nextval,1)
/



INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,'000001',
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'unitNumber'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),SYS_GUID(),
KRIM_ROLE_MBR_ID_BS_S.currval,1)
/

INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID,ATTR_VAL,KIM_ATTR_DEFN_ID,KIM_TYP_ID,OBJ_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,'Y',
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'subunits'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),SYS_GUID(),
KRIM_ROLE_MBR_ID_BS_S.currval,1)
/



