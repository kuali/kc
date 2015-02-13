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
CREATE TABLE S2S_ERROR (
    S2S_ERROR_ID DECIMAL(12,0) NOT NULL,
    MESSAGE_KEY VARCHAR(200) NOT NULL,
    MESSAGE VARCHAR(200) NOT NULL,
    FIX_LINK VARCHAR(200) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR(60) NOT NULL,
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID VARCHAR(36) NOT NULL)
/

ALTER TABLE S2S_ERROR
ADD CONSTRAINT PK_S2S_ERROR
PRIMARY KEY (S2S_ERROR_ID)
/

ALTER TABLE S2S_ERROR
ADD CONSTRAINT UQ_S2S_ERROR
UNIQUE (MESSAGE_KEY)
/

DELIMITER ;
