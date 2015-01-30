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

CREATE TABLE CFDA (
    CFDA_NBR VARCHAR(7) NOT NULL,
    CFDA_PGM_TTL_NM varchar(300) NOT NULL,
    CFDA_MAINT_TYP_ID varchar(10),
    ACTIVE_FLAG VARCHAR(1) NOT NULL,
    VER_NBR DECIMAL(8) default 1 NOT NULL,
    OBJ_ID VARCHAR(36) NOT NULL
);

ALTER TABLE CFDA
    ADD CONSTRAINT CFDA_NBR
PRIMARY KEY (CFDA_NBR);
