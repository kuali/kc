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

-- creates additional attribute for krms data validation rules
DELIMITER /

insert into KRMS_ATTR_DEFN_T (ATTR_DEFN_ID, NM, NMSPC_CD, LBL, ACTV, CMPNT_NM, VER_NBR, DESC_TXT) values('KC1001','actionArea','KC-KRMS','Area Name','Y',null,0,'The name of area where this error occurs')
/
insert into KRMS_ATTR_DEFN_T (ATTR_DEFN_ID, NM, NMSPC_CD, LBL, ACTV, CMPNT_NM, VER_NBR, DESC_TXT) values('KC1002','actionSection','KC-KRMS','Section Name','Y',null,0,'The name of the section where this error occurs')
/
insert into KRMS_ATTR_DEFN_T (ATTR_DEFN_ID, NM, NMSPC_CD, LBL, ACTV, CMPNT_NM, VER_NBR, DESC_TXT) values('KC1003','actionNavigateToPageId','KC-KRMS','Page Id','Y',null,0,'The Id for the area where this error occurs')
/
insert into KRMS_ATTR_DEFN_T (ATTR_DEFN_ID, NM, NMSPC_CD, LBL, ACTV, CMPNT_NM, VER_NBR, DESC_TXT) values('KC1004','actionNavigateToSectionId','KC-KRMS','Section Id','Y',null,0,'The Id for the section where this error occurs')
/

-- creates new krms action type
insert into KRMS_TYP_T (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR) values ('KC1009','KC Validation Action','KC-KRMS','kcValidationActionTypeService','Y',1)
/

-- adds additiona attributes and old attributes to new krms typ
insert into KRMS_TYP_ATTR_T (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID, ACTV, VER_NBR) values ('KC1002',1,'KC1009','KC1001','Y',0)
/
insert into KRMS_TYP_ATTR_T (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID, ACTV, VER_NBR) values ('KC1003',1,'KC1009','KC1002','Y',0)
/
insert into KRMS_TYP_ATTR_T (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID, ACTV, VER_NBR) values ('KC1004',1,'KC1009','KC1003','Y',0)
/
insert into KRMS_TYP_ATTR_T (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID, ACTV, VER_NBR) values ('KC1005',1,'KC1009','KC1004','Y',0)
/
insert into KRMS_TYP_ATTR_T (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID, ACTV, VER_NBR) values ('KC1006',1,'KC1009','1004','Y',0)
/
insert into KRMS_TYP_ATTR_T (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID, ACTV, VER_NBR) values ('KC1007',1,'KC1009','1005','Y',0)
/

-- adds new krms action type as a valid action for prop dev context
insert into KRMS_CNTXT_VLD_ACTN_TYP_T (CNTXT_VLD_ACTN_ID, CNTXT_ID, ACTN_TYP_ID, VER_NBR) values ('KC1027','KC-PD-CONTEXT','KC1009',1)
/

DELIMITER ;
