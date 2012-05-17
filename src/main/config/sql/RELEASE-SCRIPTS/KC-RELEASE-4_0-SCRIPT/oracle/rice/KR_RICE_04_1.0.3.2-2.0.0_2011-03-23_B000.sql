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

update KREW_DOC_TYP_T set POST_PRCSR='org.kuali.rice.edl.framework.workflow.EDocLitePostProcessor'
where POST_PRCSR='org.kuali.rice.kew.edl.EDocLitePostProcessor'
/

update KREW_DOC_TYP_T set POST_PRCSR='org.kuali.rice.edl.framework.workflow.EDocLiteDatabasePostProcessor'
where POST_PRCSR='org.kuali.rice.kew.edl.EDLDatabasePostProcessor'
/

UPDATE KREW_DOC_TYP_T SET PARNT_ID='2681' WHERE DOC_TYP_NM='TravelAccountMaintenanceDocument'
/
UPDATE KREW_DOC_TYP_T SET PARNT_ID='2681' WHERE DOC_TYP_NM='FiscalOfficerMaintenanceDocument'
/
UPDATE KREW_DOC_TYP_T SET PARNT_ID='2681' WHERE DOC_TYP_NM='TravelRequest'
/
