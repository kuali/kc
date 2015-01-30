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

delimiter /
INSERT INTO KRIM_AFLTN_TYP_T (AFLTN_TYP_CD,NM,EMP_AFLTN_TYP_IND,DISPLAY_SORT_CD,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES ('GRD_STDNT_STAFF','Graduate Student Staff','Y','e','Y',NOW(),UUID(),1)
/
INSERT INTO KRIM_AFLTN_TYP_T (AFLTN_TYP_CD,NM,EMP_AFLTN_TYP_IND,DISPLAY_SORT_CD,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES ('RSRCH_STAFF','Research Staff','Y','f','Y',NOW(),UUID(),1)
/
INSERT INTO KRIM_AFLTN_TYP_T (AFLTN_TYP_CD,NM,EMP_AFLTN_TYP_IND,DISPLAY_SORT_CD,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES ('SRVC_STAFF','Service Staff','Y','g','Y',NOW(),UUID(),1)
/
INSERT INTO KRIM_AFLTN_TYP_T (AFLTN_TYP_CD,NM,EMP_AFLTN_TYP_IND,DISPLAY_SORT_CD,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES ('SUPPRT_STAFF','Support Staff','Y','h','Y',NOW(),UUID(),1)
/
INSERT INTO KRIM_AFLTN_TYP_T (AFLTN_TYP_CD,NM,EMP_AFLTN_TYP_IND,DISPLAY_SORT_CD,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES ('OTH_ACADMC_GRP','Other Academic Group','N','i','Y',NOW(),UUID(),1)
/
INSERT INTO KRIM_AFLTN_TYP_T (AFLTN_TYP_CD,NM,EMP_AFLTN_TYP_IND,DISPLAY_SORT_CD,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES ('MED_STAFF','Medical Staff','Y','j','Y',NOW(),UUID(),1)
/
delimiter ;
