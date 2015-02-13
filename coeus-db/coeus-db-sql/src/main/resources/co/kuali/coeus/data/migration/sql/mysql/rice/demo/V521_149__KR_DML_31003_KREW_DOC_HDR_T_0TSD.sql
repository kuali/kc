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
INSERT INTO KREW_DOC_HDR_T (DOC_HDR_ID,DOC_TYP_ID,DOC_HDR_STAT_CD,TTL,INITR_PRNCPL_ID,RTE_PRNCPL_ID,DOC_VER_NBR,RTE_LVL,CRTE_DT,RTE_STAT_MDFN_DT,APRV_DT,FNL_DT,STAT_MDFN_DT,OBJ_ID,VER_NBR) 
    VALUES (3094,(SELECT MAX(DOC_TYP_ID) FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'CommitteeDocument'),'F','Committee Document - IRB Committee',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),1,0,NOW(),NOW(),NOW(),NOW(),NOW(),UUID(),24)
/
DELIMITER ;
