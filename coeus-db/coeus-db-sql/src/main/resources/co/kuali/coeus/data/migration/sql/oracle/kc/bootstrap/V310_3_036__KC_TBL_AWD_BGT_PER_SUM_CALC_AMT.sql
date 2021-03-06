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

CREATE TABLE AWD_BGT_PER_SUM_CALC_AMT (
    AWD_BGT_PER_SUM_CALC_AMT_ID NUMBER(12,0),
    BUDGET_PERIOD_ID NUMBER(12,0) NOT NULL,
    COST_ELEMENT VARCHAR2(8) NOT NULL,
    ON_OFF_CAMPUS_FLAG VARCHAR2(1),
    RATE_CLASS_TYPE VARCHAR2(1) NOT NULL,
    CALCULATED_COST NUMBER(12,2), 
    CALCULATED_COST_SHARING NUMBER(12,2), 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1, 
    OBJ_ID VARCHAR2(36) NOT NULL
);

ALTER TABLE AWD_BGT_PER_SUM_CALC_AMT ADD CONSTRAINT AWD_BGT_PER_SUM_CALC_AMT_PK
              PRIMARY KEY (AWD_BGT_PER_SUM_CALC_AMT_ID);
