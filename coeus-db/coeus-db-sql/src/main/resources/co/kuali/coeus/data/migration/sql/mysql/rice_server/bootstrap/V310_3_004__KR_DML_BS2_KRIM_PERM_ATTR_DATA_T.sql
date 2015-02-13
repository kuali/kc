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

-- Add KRIM_PERM_ATTR_DATA_T with ATTR_DATA_ID = '1704' and PERM_ID = '1247' and KIM_TYP_ID = '1007' and KIM_ATTR_DEFN_ID = '1004'
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES (NULL);
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
    VALUES ((SELECT MAX(ID) FROM KRIM_ATTR_DATA_ID_S), uuid(), 1, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Delete Proposal'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Action'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentAction'), 'delete_proposal');
    
-- Add KRIM_PERM_ATTR_DATA_T with ATTR_DATA_ID = '1705' and PERM_ID = '1247' and KIM_TYP_ID = '1007' and KIM_ATTR_DEFN_ID = '13'    
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES (NULL);
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
    VALUES ((SELECT MAX(ID) FROM KRIM_ATTR_DATA_ID_S), uuid(), 1, (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Delete Proposal'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Action'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName'), 'ProposalDevelopmentDocument');
    
COMMIT;
