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


CREATE TABLE SEQ_AWARD_POSTS_ID
(
	id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;

ALTER TABLE SEQ_AWARD_POSTS_ID auto_increment = 1;

CREATE TABLE AWARD_POSTS(
ID BIGINT(19) NOT NULL PRIMARY KEY,
ACCOUNT_NUMBER VARCHAR(15) NOT NULL,
AWARD_ID DECIMAL(22,0) NOT NULL,
AWARD_FAMILY VARCHAR(12),
DOCUMENT_NUMBER VARCHAR(40),
POSTED CHAR(1) NOT NULL DEFAULT 'Y',
ACTIVE CHAR(1) NOT NULL DEFAULT 'Y',
UPDATE_USER VARCHAR(60) NOT NULL,
UPDATE_TIMESTAMP DATETIME NOT NULL,
VER_NBR DECIMAL (8,0) NOT NULL DEFAULT 1,
OBJ_ID VARCHAR(36) NOT NULL);

INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
    VALUES ('RES-BOOT1001',
    (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
    'KC-SYS','Post Award','Post Award information to the account table.','Y',UUID(),1);

insert into KRIM_ROLE_PERM_T values ('RES-BOOT1001', UUID(), 1,
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'KC Superuser'),
(select PERM_ID from KRIM_PERM_T where NM = 'Post Award'), 'Y');

insert into KRIM_ROLE_PERM_T values ('RES-BOOT1002', UUID(), 1,
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'OSP Administrator'),
(select PERM_ID from KRIM_PERM_T where NM = 'Post Award'), 'Y');
