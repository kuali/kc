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

-- KULRICE-7128 wire kim type attribute 'qualifierResolverProvidedIdentifier' to Responsibility type
Insert into krim_typ_attr_t
(KIM_TYP_ATTR_ID,
OBJ_ID,
VER_NBR,
SORT_CD,
KIM_TYP_ID,
KIM_ATTR_DEFN_ID,
ACTV_IND)
VALUES
  ((select (max(to_number(KIM_TYP_ATTR_ID)) + 1) from  krim_typ_attr_t where KIM_TYP_ATTR_ID is not NULL and REGEXP_LIKE(KIM_TYP_ATTR_ID, '^[1-9][0-9]*$') and to_number(KIM_TYP_ATTR_ID) < 10000),
  sys_guid(),
  1,
  'e',
  7,
  46,
  'Y')
/
