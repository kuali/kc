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

CREATE TABLE TRAINING_STIPEND_RATES (   
        TRAINING_STIPEND_RATES_ID DECIMAL(12,0) NOT NULL,
        CAREER_LEVEL VARCHAR(50) NOT NULL, 
        EXPERIENCE_LEVEL DECIMAL(3,0) NOT NULL, 
        STIPEND_RATE DECIMAL(12,2), 
        EFFECTIVE_DATE DATE,
        DESCRIPTION VARCHAR(200),
        UPDATE_TIMESTAMP DATE NOT NULL, 
        UPDATE_USER VARCHAR(8) NOT NULL,
        VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL, 
        OBJ_ID VARCHAR(36) DEFAULT '' NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin; 

ALTER TABLE TRAINING_STIPEND_RATES 
    ADD CONSTRAINT PK_TRAINING_STIPEND_RATES 
    PRIMARY KEY (TRAINING_STIPEND_RATES_ID);
