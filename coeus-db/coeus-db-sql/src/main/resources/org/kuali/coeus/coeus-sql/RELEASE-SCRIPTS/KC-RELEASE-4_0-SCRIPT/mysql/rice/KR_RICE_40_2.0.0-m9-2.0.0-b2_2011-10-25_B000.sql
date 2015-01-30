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

update KREW_RULE_ATTR_T set RULE_ATTR_TYP_CD='DocumentSecurityAttribute' where RULE_ATTR_TYP_CD='DocumentSearchSecurityFilterAttribute'
/

update KRCR_PARM_T set CMPNT_CD='DocumentSearch' where CMPNT_CD='DocSearchCriteriaDTO'
/
insert into KRCR_CMPNT_T (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR)
values ('KR-WKFLW', 'DocumentSearch', 'Document Search', 'Y', uuid(), 1)
/
DELIMITER ;
