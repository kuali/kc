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

-- KEW data for Proposal Development Person Certification Questionnaire

INSERT INTO KREW_ACTN_RQST_S VALUES (NULL);
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_ID,ACTN_RQST_CD,DOC_HDR_ID,STAT_CD,RSP_ID,PRNCPL_ID,RECIP_TYP_CD,PRIO_NBR,RTE_LVL_NBR,RTE_NODE_INSTN_ID,ACTN_TKN_ID,DOC_VER_NBR,CRTE_DT,RSP_DESC_TXT,FRC_ACTN,APPR_PLCY,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_RQST_S),'C',(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'Proposal Person Certification'),'D',-3,'10000000001','U',0,0,(SELECT RTE_NODE_INSTN_ID FROM KREW_RTE_NODE_INSTN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'Proposal Person Certification')),(SELECT ACTN_TKN_ID FROM KREW_ACTN_TKN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'Proposal Person Certification') AND ACTN_CD = 'C'),1,STR_TO_DATE('20100708','%Y%m%d'),'Initiator needs to complete document.',1,'F',1,0);

COMMIT;
