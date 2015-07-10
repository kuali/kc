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
ALTER TABLE FILE_DATA MODIFY ID VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_bin
/
ALTER TABLE BUDGET_SUB_AWARDS ADD FILE_DATA_ID VARCHAR(36)
/
ALTER TABLE BUDGET_SUB_AWARDS ADD XML_DATA_ID VARCHAR(36)
/
UPDATE BUDGET_SUB_AWARDS SET FILE_DATA_ID = UUID() WHERE SUB_AWARD_XFD_FILE IS NOT NULL
/
UPDATE BUDGET_SUB_AWARDS SET XML_DATA_ID = UUID() WHERE SUB_AWARD_XML_FILE IS NOT NULL
/
INSERT INTO FILE_DATA (SELECT FILE_DATA_ID, SUB_AWARD_XFD_FILE FROM BUDGET_SUB_AWARDS WHERE FILE_DATA_ID IS NOT NULL)
/
INSERT INTO FILE_DATA (SELECT XML_DATA_ID, SUB_AWARD_XML_FILE FROM BUDGET_SUB_AWARDS WHERE XML_DATA_ID IS NOT NULL)
/
ALTER TABLE BUDGET_SUB_AWARDS DROP COLUMN SUB_AWARD_XFD_FILE
/
ALTER TABLE BUDGET_SUB_AWARDS DROP COLUMN SUB_AWARD_XML_FILE
/
ALTER TABLE BUDGET_SUB_AWARDS ADD FOREIGN KEY FK2_BUDGET_SUB_AWARDS (FILE_DATA_ID) REFERENCES FILE_DATA (ID)
/
ALTER TABLE BUDGET_SUB_AWARDS ADD FOREIGN KEY FK3_BUDGET_SUB_AWARDS (XML_DATA_ID) REFERENCES FILE_DATA (ID)
/
DELIMITER ;
