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

CREATE TABLE AWARD_BUDGET_LIMIT (
    BUDGET_LIMIT_ID DECIMAL(22,0) NOT NULL,
    AWARD_ID DECIMAL(22,0),
    BUDGET_ID DECIMAL(22,0),
    LIMIT_TYPE VARCHAR(100) NOT NULL,
    LIMIT_AMOUNT DECIMAL(12,2),
    VER_NBR DECIMAL(8,0) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR(60) NOT NULL,
    OBJ_ID VARCHAR(36) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin;;

ALTER TABLE AWARD_BUDGET_LIMIT ADD CONSTRAINT PK_AWARD_BUDGET_LIMIT
  PRIMARY KEY (BUDGET_LIMIT_ID);
