--
-- Copyright 2005-2012 The Kuali Foundation
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

alter table kren_chnl_subscrp_t add OBJ_ID varchar2(36)
/
alter table kren_cntnt_typ_t add OBJ_ID varchar2(36) 
/
alter table kren_chnl_t add OBJ_ID varchar2(36) 
/
alter table kren_ntfctn_msg_deliv_t add OBJ_ID varchar2(36) 
/
alter table kren_ntfctn_t add OBJ_ID varchar2(36) 
/
alter table kren_prio_t add OBJ_ID varchar2(36) 
/
alter table kren_prodcr_t add OBJ_ID varchar2(36) 
/
alter table kren_recip_list_t add OBJ_ID varchar2(36)
/
alter table kren_sndr_t add OBJ_ID varchar2(36)
/
alter table kren_recip_t add OBJ_ID varchar2(36) 
/
alter table kren_rvwer_t add OBJ_ID varchar2(36) 
/
alter table kren_chnl_subscrp_t add ver_nbr NUMBER(8)
/
alter table kren_recip_list_t add ver_nbr NUMBER(8)
/
alter table kren_sndr_t add ver_nbr NUMBER(8)
/
alter table kren_recip_t add ver_nbr NUMBER(8)
/
