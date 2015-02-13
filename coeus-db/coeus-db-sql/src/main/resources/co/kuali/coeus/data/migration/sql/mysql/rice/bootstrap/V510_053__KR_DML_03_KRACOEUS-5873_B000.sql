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
update KRMS_TERM_SPEC_T A set NM = (select FUNC_ID from KRMS_FUNC_T B where B.NM=A.NM) 
  where exists (select FUNC_ID from KRMS_FUNC_T C where C.NM=A.NM)
/
update krms_term_t set desc_txt = 
    (select CONCAT(krms_term_spec_t.desc_txt,'(',krms_term_t.desc_txt,')') from krms_term_spec_t
          where krms_term_spec_t.term_spec_id=krms_term_t.term_spec_id 
          		and krms_term_spec_t.nm in (select func_id from krms_func_t))
where exists  (select CONCAT(krms_term_spec_t.desc_txt,'(',krms_term_t.desc_txt,')') from krms_term_spec_t 
          where krms_term_spec_t.term_spec_id=krms_term_t.term_spec_id 
          		and krms_term_spec_t.nm in (select func_id from krms_func_t))
/

DELIMITER ;
