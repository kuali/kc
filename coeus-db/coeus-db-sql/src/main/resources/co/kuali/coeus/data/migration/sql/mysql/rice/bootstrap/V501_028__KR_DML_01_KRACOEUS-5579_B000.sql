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
UPDATE KRIM_PERM_ATTR_DATA_T SET ATTR_VAL = 'SubAwardInvoiceMaintenanceDocument'
WHERE PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Subaward Invoice' AND NMSPC_CD='KC-SUBAWARD')
AND KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)')
AND KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName')
/

UPDATE KRIM_PERM_ATTR_DATA_T SET ATTR_VAL = 'SubAwardInvoiceMaintenanceDocument'
WHERE PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Subaward Invoice' AND NMSPC_CD='KC-SUBAWARD')
AND KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)')
AND KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName')
/

UPDATE KRIM_PERM_ATTR_DATA_T SET ATTR_VAL = 'SubAwardInvoiceMaintenanceDocument'
WHERE PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Subaward Invoice' AND NMSPC_CD='KC-SUBAWARD')
AND KIM_TYP_ID = (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)')
AND KIM_ATTR_DEFN_ID = (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES
((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),UUID(), 1,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'View Subaward' AND NMSPC_CD='KC-SUBAWARD'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Subaward Invoice' AND NMSPC_CD='KC-SUBAWARD'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES
((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S),UUID(), 1,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Requisitioner' AND NMSPC_CD='KC-SUBAWARD'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Subaward Invoice' AND NMSPC_CD='KC-SUBAWARD'), 'Y')
/
DELIMITER ;
