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

ALTER TABLE KREW_DOC_TYP_T CHANGE DOC_TYP_ID DOC_TYP_ID VARCHAR(40)
/
ALTER TABLE KREW_DOC_TYP_T CHANGE PARNT_ID PARNT_ID VARCHAR(40)
/
ALTER TABLE KREW_DOC_TYP_T CHANGE PREV_DOC_TYP_VER_NBR PREV_DOC_TYP_VER_NBR VARCHAR(40)
/
ALTER TABLE KREW_DOC_HDR_T CHANGE DOC_TYP_ID DOC_TYP_ID VARCHAR(40)
/
ALTER TABLE KREW_DOC_TYP_PLCY_RELN_T CHANGE DOC_TYP_ID DOC_TYP_ID VARCHAR(40)
/
ALTER TABLE KREW_DOC_TYP_APP_DOC_STAT_T CHANGE DOC_TYP_ID DOC_TYP_ID VARCHAR(40)
/
ALTER TABLE KREW_DOC_TYP_ATTR_T CHANGE DOC_TYP_ID DOC_TYP_ID VARCHAR(40)
/
ALTER TABLE KREW_RTE_NODE_T CHANGE DOC_TYP_ID DOC_TYP_ID VARCHAR(40)
/
ALTER TABLE KREW_DOC_TYP_PROC_T CHANGE DOC_TYP_ID DOC_TYP_ID VARCHAR(40)
/

DELIMITER ;
