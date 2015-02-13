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

DELIMITER /
INSERT INTO KREN_PRODCR_S VALUES(NULL)
/
INSERT INTO KREN_PRODCR_T (PRODCR_ID,NM,DESC_TXT,CNTCT_INFO,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KREN_PRODCR_S),'University Library System','This producer represents messages sent from the University Library system.','kuali-ken-testing@cornell.edu',1)
/
INSERT INTO KREN_PRODCR_S VALUES(NULL)
/
INSERT INTO KREN_PRODCR_T (PRODCR_ID,NM,DESC_TXT,CNTCT_INFO,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KREN_PRODCR_S),'University Events Office','This producer represents messages sent from the University Events system.','kuali-ken-testing@cornell.edu',1)
/
DELIMITER ;
