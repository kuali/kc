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

INSERT INTO KRIM_GRP_MBR_ID_S VALUES (null);
INSERT INTO KRIM_GRP_MBR_T(GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT) 
SELECT MAX(ID), 0, UUID(), (select grp_id from krim_grp_t where GRP_NM = 'IRBAdmin' and NMSPC_CD = 'KC-WKFLW'), 
    (select prncpl_id from krim_prncpl_t where prncpl_nm = 'kr'), 'P', NULL, NULL, NULL FROM KRIM_GRP_MBR_ID_S;
