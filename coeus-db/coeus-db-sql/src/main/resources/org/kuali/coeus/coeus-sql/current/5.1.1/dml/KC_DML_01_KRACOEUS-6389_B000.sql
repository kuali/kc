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

insert into COEUS_SUB_MODULE (COEUS_SUB_MODULE_ID , MODULE_CODE , SUB_MODULE_CODE , DESCRIPTION, UPDATE_TIMESTAMP ,UPDATE_USER, VER_NBR, OBJ_ID)   values 
	(25, (SELECT MODULE_CODE FROM COEUS_MODULE WHERE DESCRIPTION = 'COI Disclosure'), 8, 'Screening', SYSDATE, 'admin', 1, SYS_GUID())
/

insert into COI_DISCLOSURE_EVENT_TYPE (EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, EXCLUDE_FROM_MASTER_DISCL, ACTIVE_FLAG, 
		EXCLUDE_FIN_ENT, SYSTEM_EVENT, PROJECT_ID_LABEL, PROJECT_TITLE_LABEL)
	VALUES ('8', 'Screening Questionnaire Event Placeholder', SYSDATE, 'admin', 1, SYS_GUID(), 'Y', 'N', 
		'Y', 'Y', null, null)
/
