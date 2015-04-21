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

CREATE SEQUENCE SEQ_PERSON_EDITABLE_FIELD
    INCREMENT BY 1
    START WITH 1001
    MAXVALUE 9999999999999999999999999999
    NOMINVALUE
    NOCYCLE
    NOCACHE
    ORDER;

CREATE SEQUENCE SEQ_BGT_SUM_PER_CALC_AMT_ID 
    INCREMENT BY 1 
    START WITH 1
    MAXVALUE 9999999999999999999999999999
    NOMINVALUE
    NOCYCLE
    NOCACHE
    ORDER;

CREATE SEQUENCE SEQ_AWRD_BDGT_LMT_ID 
    INCREMENT BY 1 
    START WITH 1 
    MAXVALUE 9999999999999999999999999999
    NOMINVALUE
    NOCYCLE
    NOCACHE
    ORDER;

CREATE SEQUENCE SEQ_NOTIFICATION_TYPE_ID
    INCREMENT BY 1 
    START WITH 10000 
    MINVALUE 1 
    MAXVALUE 9999999999999999999999999999
    NOCACHE 
    NOORDER 
    NOCYCLE;
