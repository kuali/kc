-- 
-- Copyright 2009 The Kuali Foundation
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

-- #############################################################################
-- # demo-client-dataset-cleanup.sql                                           #
-- #                                                                           #
-- # This file will clean up and remove data from the client database to       # 
-- # ensure that it has the minimum amount of data required to function for    #
-- # the packaged demo dataset.                                                #
-- #############################################################################


-- ##############
-- # KSB Tables #
-- ##############

-- Quartz Data - delete all data from the quartz tables except krsb_qrtz_locks, jobs will get rescheduled when system starts

delete from krsb_qrtz_job_listeners
/
delete from krsb_qrtz_trigger_listeners
/
delete from krsb_qrtz_fired_triggers
/
delete from krsb_qrtz_simple_triggers
/
delete from krsb_qrtz_cron_triggers
/
delete from krsb_qrtz_blob_triggers
/
delete from krsb_qrtz_triggers
/
delete from krsb_qrtz_job_details
/
delete from krsb_qrtz_calendars 
/
delete from krsb_qrtz_paused_trigger_grps
/
delete from krsb_qrtz_scheduler_state 
/

-- Message Queue Tables - tables should be emptied

delete from krsb_bam_parm_t
/
delete from krsb_bam_t
/
delete from krsb_msg_pyld_t
/
delete from krsb_msg_que_t
/

-- ##############
-- # KNS Tables #
-- ##############

-- We need to clear all data from all of these tables with the exception of the KRNS_NTE_TYP_T table

delete from krns_adhoc_rte_actn_recip_t
/
delete from krns_att_t
/
delete from krns_lookup_rslt_t
/
delete from krns_lookup_sel_t
/
delete from krns_maint_doc_att_t
/
delete from krns_maint_doc_t
/
delete from krns_maint_lock_t
/
delete from krns_nte_t
/
delete from krns_pessimistic_lock_t
/
delete from krns_sesn_doc_t
/
delete from krns_doc_hdr_t
/
