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

-- assign open Time and Money permissions to Time And Money Viewer
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Viewer'), (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), 'Y');

-- assign view Time and Money permissions to Time And Money Viewer
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Viewer'), (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), 'Y');



-- assign all time and money permissions to Time and Money Modifier role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Create Time And Money Document'), 'Y');

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Modify Time And Money Document'), 'Y');

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Save Time And Money Document'), 'Y');

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Submit Time And Money Document'), 'Y');

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), 'Y');

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Cancel Time And Money Document'), 'Y');

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), 'Y');

-- assign time and money viewer permission to award budget viewer
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Budget Viewer'), (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), 'Y');

-- assign open Time and Money permissions to award budget Viewer
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
insert into krim_role_perm_t values ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Budget Viewer'), (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), 'Y');

COMMIT;
