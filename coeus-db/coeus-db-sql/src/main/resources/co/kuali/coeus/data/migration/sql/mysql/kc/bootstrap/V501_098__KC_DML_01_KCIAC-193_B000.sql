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
UPDATE IACUC_PAIN_CATEGORY SET PAIN_LEVEL='0' WHERE PAIN_CATEGORY='None'
/
UPDATE IACUC_PAIN_CATEGORY SET PAIN_LEVEL='1' WHERE PAIN_CATEGORY='A'
/
UPDATE IACUC_PAIN_CATEGORY SET PAIN_LEVEL='2' WHERE PAIN_CATEGORY='B'
/
UPDATE IACUC_PAIN_CATEGORY SET PAIN_LEVEL='3' WHERE PAIN_CATEGORY='C'
/
UPDATE IACUC_PAIN_CATEGORY SET PAIN_LEVEL='4' WHERE PAIN_CATEGORY='D'
/
UPDATE IACUC_PAIN_CATEGORY SET PAIN_LEVEL='5' WHERE PAIN_CATEGORY='E'
/

DELIMITER ;
