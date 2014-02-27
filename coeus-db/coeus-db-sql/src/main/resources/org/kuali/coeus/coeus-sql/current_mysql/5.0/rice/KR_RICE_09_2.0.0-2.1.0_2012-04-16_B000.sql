DELIMITER /
--
-- Copyright 2005-2014 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- KULRICE-7128 wire kim type attribute 'qualifierResolverProvidedIdentifier' to Responsibility type
Insert into `krim_typ_attr_t`
(`KIM_TYP_ATTR_ID`,
`OBJ_ID`,
`VER_NBR`,
`SORT_CD`,
`KIM_TYP_ID`,
`KIM_ATTR_DEFN_ID`,
`ACTV_IND`)
VALUES
((select KIM_TYP_ATTR_ID from (select (max(cast(KIM_TYP_ATTR_ID as decimal)) + 1)
  as KIM_TYP_ATTR_ID from krim_typ_attr_t
  where KIM_TYP_ATTR_ID is not NULL and KIM_TYP_ATTR_ID REGEXP '^[1-9][0-9]*$' and cast(KIM_TYP_ATTR_ID as decimal) < 10000) as tmptable),
  uuid(),
  1,
  'e',
  7,
  46,
  'Y')
/
DELIMITER ;
