--
-- Copyright 2005-2014 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
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
