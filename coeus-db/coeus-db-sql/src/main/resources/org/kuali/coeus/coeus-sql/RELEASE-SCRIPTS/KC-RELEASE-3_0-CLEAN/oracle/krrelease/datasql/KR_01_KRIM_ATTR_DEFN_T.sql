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

INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DEFN_ID_BS_S.NEXTVAL,'KC-SYS','unitNumber','Unit Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DEFN_ID_BS_S.NEXTVAL,'KC-SYS','subunits','Descend Flag','org.kuali.kra.kim.bo.KcKimAttributes','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DEFN_ID_BS_S.NEXTVAL,'KC-SYS','sectionName','Section Name','org.kuali.kra.kim.bo.KcKimAttributes','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DEFN_ID_BS_S.NEXTVAL,'KC-SYS','documentAction','Document Action','org.kuali.kra.kim.bo.KcKimAttributes','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DEFN_ID_BS_S.NEXTVAL,'KC-SYS','proposal','Proposal Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DEFN_ID_BS_S.NEXTVAL,'KC-SYS','protocol','Protocol Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DEFN_ID_BS_S.NEXTVAL,'KC-SYS','committee','Committee Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DEFN_ID_BS_S.NEXTVAL,'KC-SYS','award','Award Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',SYS_GUID(),1)
/
INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DEFN_ID_BS_S.NEXTVAL,'KC-SYS','timeandmoney','TimeAndMoney Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',SYS_GUID(),1)
/
