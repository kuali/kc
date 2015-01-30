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

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
  DECLARE maxAttributeId INT;
  CREATE TABLE IF NOT EXISTS SEQ_CUSTOM_ATTRIBUTE (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM;
  select ifnull(max(ID),0) into maxAttributeId from CUSTOM_ATTRIBUTE;
  IF maxAttributeId = 0 THEN
    set @alter_seq := concat('ALTER TABLE SEQ_CUSTOM_ATTRIBUTE auto_increment = ', maxAttributeId+1); 
    prepare alter_seq_stmt from @alter_seq;
    execute alter_seq_stmt;
    deallocate prepare alter_seq_stmt;
  END IF;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
DELIMITER ;
