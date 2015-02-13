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
INSERT INTO KRNS_NMSPC_T (ACTV_IND,NM,NMSPC_CD,APPL_NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','KC Coi Disclosure','KC-COIDISCLOSURE','KC',UUID(),1)
/
INSERT INTO KRNS_PARM_DTL_TYP_T (ACTV_IND,NM,NMSPC_CD,OBJ_ID,PARM_DTL_TYP_CD,VER_NBR)
  VALUES ('Y','Document','KC-COIDISCLOSURE',UUID(),'Document',1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiDisclosureHelp','Coi Disclosure Page Help','default.htm?turl=Documents/coidisclosure.htm','A',UUID(),1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','disclosureActionsHelp','Coi Disclosure Actions Page Help','default.htm?turl=Documents/coidisclosureactions.htm','A',UUID(),1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','disclosureCommitteeHelp','Coi Committee Page Help','default.htm?turl=Documents/coicommittee.htm','A',UUID(),1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiFinancialInterestsHelp','Coi Financial Interests Page Help','default.htm?turl=Documents/coifinancialinterests.htm','A',UUID(),1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiNotesAndAttachmentsHelp','Coi Note and Attachment Page Help','default.htm?turl=Documents/coinoteandattachment.htm','A',UUID(),1)
/
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_TYP_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_DESC_TXT,TXT,CONS_CD,OBJ_ID,VER_NBR)
    VALUES ('KC','KC-COIDISCLOSURE','HELP','Document','coiCertificationHelp','Coi CertificationPage Help','default.htm?turl=Documents/coicertification.htm','A',UUID(),1)
/
DELIMITER ;
