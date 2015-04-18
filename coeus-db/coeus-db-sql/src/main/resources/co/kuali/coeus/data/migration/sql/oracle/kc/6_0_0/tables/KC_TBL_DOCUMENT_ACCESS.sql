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

CREATE TABLE DOCUMENT_ACCESS (
    DOC_ACCESS_ID NUMBER(12,0) NOT NULL,
    DOC_HDR_ID varchar2(40) NOT NULL,
    PRNCPL_ID varchar2(40) NOT NULL,
    ROLE_NM varchar2(80) NOT NULL,
    NMSPC_CD varchar2(40) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR2(60) NOT NULL,
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID VARCHAR2(36) NOT NULL)
/

ALTER TABLE DOCUMENT_ACCESS
ADD CONSTRAINT PK_DOCUMENT_ACCESS
PRIMARY KEY (DOC_ACCESS_ID)
/

ALTER TABLE DOCUMENT_ACCESS
ADD CONSTRAINT UQ_DOCUMENT_ACCESS1
UNIQUE (DOC_HDR_ID, PRNCPL_ID, ROLE_NM, NMSPC_CD)
/

ALTER TABLE DOCUMENT_ACCESS
ADD CONSTRAINT UQ_DOCUMENT_ACCESS2
UNIQUE (OBJ_ID)
/
