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
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/
-- Insert the new Delete Protocol permission data

INSERT INTO KRIM_PERM_T
(
PERM_ID,
PERM_TMPL_ID,
NMSPC_CD,
NM,
DESC_TXT,
ACTV_IND,
OBJ_ID
)
VALUES
(
(SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'KC-PROTOCOL',
'Delete Protocol',
'Delete Protocol Document',
'Y',
UUID()
)
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/


INSERT INTO KRIM_PERM_ATTR_DATA_T
(
ATTR_DATA_ID,
PERM_ID,
KIM_TYP_ID,
KIM_ATTR_DEFN_ID,
ATTR_VAL,
OBJ_ID
)
VALUES
(
(SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S),
(SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'),
'protocol',
UUID()
)
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/


INSERT INTO KRIM_PERM_ATTR_DATA_T
(
ATTR_DATA_ID,
PERM_ID,
KIM_TYP_ID,
KIM_ATTR_DEFN_ID,
ATTR_VAL,
OBJ_ID
)
VALUES
(
(SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S),
(SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProtocolDocument',
UUID()
)
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/


-- Insert the new Protocol Deleter role data

INSERT INTO KRIM_ROLE_T
(
ROLE_ID,
NMSPC_CD,
ROLE_NM,
DESC_TXT,
KIM_TYP_ID,
ACTV_IND,
LAST_UPDT_DT,
VER_NBR,
OBJ_ID
)
VALUES
(
(SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S),
'KC-PROTOCOL',
'Protocol Deleter',
'Protocol Deleter',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',
NULL,
1,
UUID()
)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

-- Insert the role-permission association data
-- for the newly created Protocol Deleter role

INSERT INTO KRIM_ROLE_PERM_T
(
ROLE_PERM_ID,
ROLE_ID,
PERM_ID,
ACTV_IND,
VER_NBR,
OBJ_ID

)
VALUES
(
(SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),
(SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S),
(SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),
'Y',
1,
UUID()
)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/


-- Insert the role-permission association data
-- for Protocol Aggregator and IRB Administrator roles

INSERT INTO KRIM_ROLE_PERM_T
(
ROLE_PERM_ID,
ROLE_ID,
PERM_ID,
ACTV_IND,
VER_NBR,
OBJ_ID
)
VALUES
(
(SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Protocol Aggregator' AND NMSPC_CD='KC-PROTOCOL'),
(SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),
'Y',
1,
UUID()
)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

INSERT INTO KRIM_ROLE_PERM_T
(
ROLE_PERM_ID,
ROLE_ID,
PERM_ID,
ACTV_IND,
VER_NBR,
OBJ_ID
)
VALUES
(
(SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IRB Administrator' AND NMSPC_CD='KC-UNT'),
(SELECT (MAX(ID)) FROM KRIM_PERM_ID_S),
'Y',
1,
UUID()
)
/
DELIMITER ;
