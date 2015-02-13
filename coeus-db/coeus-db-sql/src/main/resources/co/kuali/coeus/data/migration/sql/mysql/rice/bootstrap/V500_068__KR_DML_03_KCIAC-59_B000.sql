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

DELIMITER /
INSERT INTO KRIM_RSP_ID_S VALUES(NULL)
/
-- Create responsibility
INSERT INTO KRIM_RSP_T (RSP_ID,RSP_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) VALUES
((SELECT (MAX(ID)) FROM KRIM_RSP_ID_S),(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'), 'KC-WKFLW','IACUCReview','IACUC Protocol Document - IACUCReview','Y',UUID(),1)
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/
-- the attr names are in KRIM_ATTR_DEFN_T and the values for those are in the KRIM_RSP_ATTR_DATA_T
-- document for responsibility
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES
((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S), (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'), 'IacucProtocolDocument',
UUID())
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/
-- node name
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S),(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview'),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND
NM = 'Document Type, Routing Node & Action Information'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND
NM = 'routeNodeName'),'IACUCReview',UUID(),1)
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/
-- if node is mandatory
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES
((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S), (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND
NM = 'Document Type, Routing Node & Action Information'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'required'),
'true', UUID())
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/
-- action
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES
((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S), (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND
NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'actionDetailsAtRoleMemberLevel' AND NMSPC_CD = 'KR-WKFLW'), 'false', UUID())
/
INSERT INTO KRIM_ROLE_RSP_ID_S VALUES(NULL)
/
-- role responsibility mapping
INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, OBJ_ID, VER_NBR, ROLE_ID, RSP_ID, ACTV_IND) VALUES
((SELECT (MAX(ID)) FROM KRIM_ROLE_RSP_ID_S), UUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC ProtocolApprover') ,
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview'), 'Y')
/
INSERT INTO KRIM_ROLE_RSP_ACTN_ID_S VALUES(NULL)
/
-- inserting action for responsibility
-- ACTN_TYP_CD = 'A' indicates "approve" action
INSERT INTO krim_role_rsp_actn_t (ROLE_RSP_ACTN_ID, OBJ_ID, VER_NBR, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_MBR_ID, ROLE_RSP_ID, FRC_ACTN) values
((SELECT (MAX(ID)) FROM KRIM_ROLE_RSP_ACTN_ID_S), UUID(), '1', 'A', '1', 'F', '*', (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC ProtocolApprover') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview')),
'Y')
/
DELIMITER ;
