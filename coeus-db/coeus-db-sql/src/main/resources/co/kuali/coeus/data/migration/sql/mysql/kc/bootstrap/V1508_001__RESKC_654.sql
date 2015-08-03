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
-- along with this program.  If not, see <http://www.gnu.org/lincenses/>.
--
DROP TABLE if EXISTS SEQ_QUESTION_ID;

CREATE TABLE SEQ_QUESTION_ID
(
id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;

DELIMITER /

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p()
BEGIN
DECLARE MAX_ID bigint(19);

SELECT MAX(question_id)+1 INTO MAX_ID FROM question;

set @sql = CONCAT('ALTER TABLE SEQ_QUESTION_ID auto_increment = ',MAX_ID);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
END
/
CALL p()
/
DROP PROCEDURE IF EXISTS p
/

DELIMITER ;