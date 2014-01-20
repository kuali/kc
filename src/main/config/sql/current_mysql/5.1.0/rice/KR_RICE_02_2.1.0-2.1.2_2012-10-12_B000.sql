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

--
--    KULRICE-7792 & KULRICE-7793
--

UPDATE KREW_RTE_NODE_CFG_PARM_T
    SET VAL = REPLACE( VAL, 'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver', 'org.kuali.rice.kim.impl.type.KimTypeQualifierResolver' )
    WHERE val LIKE '%org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver%'
/
UPDATE KREW_RTE_NODE_CFG_PARM_T
    SET VAL = REPLACE( VAL, 'org.kuali.rice.kns.workflow.attribute.DataDictionaryQualifierResolver', 'org.kuali.rice.krad.workflow.attribute.DataDictionaryQualifierResolver' )
    WHERE val LIKE '%org.kuali.rice.kns.workflow.attribute.DataDictionaryQualifierResolver%'
/
DELIMITER ;
