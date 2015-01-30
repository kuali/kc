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

-- Create responsibility
INSERT INTO KRIM_RSP_T (RSP_ID,RSP_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) VALUES 
(KRIM_RSP_ID_BS_S.NEXTVAL,(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'), 'KC-WKFLW','COIReview','COI Disclosure Document - COIReview','Y',SYS_GUID(),1)
/
-- the attr names are in KRIM_ATTR_DEFN_T and the values for those are in the KRIM_RSP_ATTR_DATA_T
-- document for responsibility
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
    (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'COIReview'),
    (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type, Routing Node & Action Information'), 
    (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'), 'CoiDisclosureDocument',
     SYS_GUID())
/
-- node name
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'COIReview'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM = 'Document Type, Routing Node & Action Information'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND
    NM = 'routeNodeName'),'COIReview',SYS_GUID(),1)
/
-- if node is mandatory
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'COIReview'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM = 'Document Type, Routing Node & Action Information'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'required'),
'true', SYS_GUID())
/
-- action
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'COIReview'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
NM = 'Document Type, Routing Node & Action Information'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'actionDetailsAtRoleMemberLevel' AND NMSPC_CD = 'KR-WKFLW'), 'false', SYS_GUID())
/

-- role responsibility mapping
INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, OBJ_ID, VER_NBR, ROLE_ID, RSP_ID, ACTV_IND) VALUES 
(KRIM_ROLE_RSP_ID_BS_S.NEXTVAL, SYS_GUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'COI Administrator') , 
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'COIReview'), 'Y')
/
-- inserting action for responsibility
INSERT INTO krim_role_rsp_actn_t (ROLE_RSP_ACTN_ID, OBJ_ID, VER_NBR, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_MBR_ID, ROLE_RSP_ID, FRC_ACTN) values 
(KRIM_ROLE_RSP_ACTN_ID_BS_S.NEXTVAL, sys_guid(), '1', 'A', '1', 'F', '*', (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'COI Administrator') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'COIReview')),
'Y')
/
