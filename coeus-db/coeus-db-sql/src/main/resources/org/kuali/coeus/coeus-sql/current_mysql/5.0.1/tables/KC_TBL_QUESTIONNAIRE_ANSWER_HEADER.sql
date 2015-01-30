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
UPDATE QUESTIONNAIRE_ANSWER_HEADER HEAD SET 
  MODULE_ITEM_KEY = (SELECT COI_DISCLOSURE_ID FROM COI_DISCLOSURE DISC WHERE DISC.COI_DISCLOSURE_NUMBER = HEAD.MODULE_ITEM_KEY AND DISC.SEQUENCE_NUMBER = CAST(HEAD.MODULE_SUB_ITEM_KEY as DECIMAL)),
  MODULE_SUB_ITEM_KEY = '-1'
  WHERE MODULE_ITEM_CODE = '8'
/

UPDATE QUESTIONNAIRE_ANSWER_HEADER HEAD SET MODULE_SUB_ITEM_KEY = COALESCE(
	(SELECT COI_PROJECT_ID FROM COI_DISCL_PROJECTS PROJ 
		WHERE PROJ.COI_DISCL_PROJECTS_ID = 
			(SELECT MAX(PROJ2.COI_DISCL_PROJECTS_ID) FROM COI_DISCL_PROJECTS PROJ2 
				WHERE PROJ2.COI_DISCLOSURE_ID = HEAD.MODULE_ITEM_KEY AND
				(PROJ2.ORIGINAL_COI_DISCLOSURE_ID = HEAD.ORIGINAL_COI_DISCLOSURE_ID OR PROJ2.ORIGINAL_COI_DISCLOSURE_ID IS NULL))), 
		'-1')
	WHERE MODULE_ITEM_CODE = '8' AND MODULE_SUB_ITEM_KEY = '-1' AND MODULE_SUB_ITEM_CODE != '6' AND MODULE_SUB_ITEM_CODE != '14' 
  		AND HEAD.ORIGINAL_COI_DISCLOSURE_ID IS NOT NULL
/

ALTER TABLE QUESTIONNAIRE_ANSWER_HEADER DROP COLUMN ORIGINAL_COI_DISCLOSURE_ID
/

DELIMITER ;
