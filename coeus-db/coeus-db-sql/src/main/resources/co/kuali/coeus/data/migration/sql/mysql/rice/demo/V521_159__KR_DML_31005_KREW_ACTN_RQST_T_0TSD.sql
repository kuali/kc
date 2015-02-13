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
INSERT INTO KREW_ACTN_RQST_S VALUES(NULL)
/
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_ID,DOC_HDR_ID,RTE_NODE_INSTN_ID,ACTN_RQST_CD,STAT_CD,RSP_ID,RECIP_TYP_CD,PRIO_NBR,RTE_LVL_NBR,ACTN_TKN_ID,RSP_DESC_TXT,PRNCPL_ID,FRC_ACTN,APPR_PLCY,DOC_VER_NBR,CUR_IND,CRTE_DT,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KREW_ACTN_RQST_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'Committee Document - IRB Committee'),null,'C','D',-3,'U',0,0,(SELECT ACTN_TKN_ID FROM KREW_ACTN_TKN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'Committee Document - IRB Committee') AND ACTN_CD = 'C'),'Initiator needs to complete document.',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),1,'F',1,1,NOW(),0)
/
INSERT INTO KREW_ACTN_RQST_S VALUES(NULL)
/
DELIMITER ;
