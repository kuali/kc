-- 
-- Copyright 2005-2013 The Kuali Foundation
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

-- KULRICE-3408 - Certain parameters on the parameter lookup show blank component names

INSERT INTO krns_parm_dtl_typ_t (nmspc_cd, parm_dtl_typ_cd, obj_id, ver_nbr, nm, actv_ind) 
VALUES ('KR-WKFLW', 'All', SYS_GUID(), 1, 'All', 'Y');

UPDATE krns_parm_dtl_typ_t SET parm_dtl_typ_cd = 'QuickLinks' WHERE parm_dtl_typ_cd = 'QuickLink';
UPDATE krns_parm_t SET parm_dtl_typ_cd = 'PersonDocumentName' WHERE parm_dtl_typ_cd = 'EntityNameImpl';

COMMIT;