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

-- add standard permissions for time and money document
INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'), 'KC-T', 'Create Time And Money Document', 'Initiate a new Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'), 'KC-T', 'Modify Time And Money Document', 'Modify a Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Save Document'), 'KC-T', 'Save Time And Money Document', 'Save a Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Route Document'), 'KC-T', 'Submit Time And Money Document', 'Submit a Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'), 'KC-T', 'Open Time And Money Document', 'Open a Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Cancel Document'), 'KC-T', 'Cancel Time And Money Document', 'Cancel a Time and Money Document', 'Y');

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
insert into krim_perm_t values ((SELECT MAX(ID) FROM KRIM_PERM_ID_S), uuid(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'), 'KC-T', 'View Time And Money Document', 'View a Time and Money Document', 'Y');

COMMIT;
