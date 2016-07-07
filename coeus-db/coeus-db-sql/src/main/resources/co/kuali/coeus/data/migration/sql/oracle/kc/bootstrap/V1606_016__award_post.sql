--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

CREATE SEQUENCE SEQ_AWARD_POSTS_ID INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE TABLE AWARD_POSTS(
ID NUMBER(19,0),
ACCOUNT_NUMBER VARCHAR2(15) NOT NULL,
AWARD_ID NUMBER(22) NOT NULL,
AWARD_FAMILY VARCHAR2(12),
DOCUMENT_NUMBER VARCHAR2(40),
POSTED CHAR(1) default 'Y' NOT NULL,
ACTIVE CHAR(1) default 'Y' NOT NULL,
UPDATE_USER VARCHAR2(60) NOT NULL,
UPDATE_TIMESTAMP DATE NOT NULL,
VER_NBR NUMBER (8) DEFAULT 1 NOT NULL,
OBJ_ID VARCHAR2(36) NOT NULL);

INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
    VALUES ('RES-BOOT1001',
    (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
    'KC-SYS','Post Award','Post Award information to the account table.','Y',SYS_GUID(),1);

insert into KRIM_ROLE_PERM_T values ('RES-BOOT1001', SYS_GUID(), 1,
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'KC Superuser'),
(select PERM_ID from KRIM_PERM_T where NM = 'Post Award'), 'Y');

insert into KRIM_ROLE_PERM_T values ('RES-BOOT1002', SYS_GUID(), 1,
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'OSP Administrator'),
(select PERM_ID from KRIM_PERM_T where NM = 'Post Award'), 'Y');
