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
DROP PROCEDURE IF EXISTS insert_doc_hdr_data
/

CREATE PROCEDURE insert_doc_hdr_data() 

BEGIN

DECLARE doc_typ_id_0, doc_typ_id_1, doc_typ_id_2, doc_typ_id_3, doc_typ_id_4, doc_typ_id_5 INT;

SELECT DOC_TYP_ID INTO doc_typ_id_0 FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KualiDocument' AND DOC_TYP_VER_NBR = 0;

UPDATE KREW_DOC_TYP_T SET CUR_IND='0'WHERE DOC_TYP_NM = 'KualiDocument' AND DOC_TYP_VER_NBR = 0;

INSERT INTO KREW_DOC_HDR_S VALUES(NULL);

SELECT (SELECT MAX(ID) FROM KREW_DOC_HDR_S) INTO doc_typ_id_1;

INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,APPL_ID,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) VALUES (doc_typ_id_1,'KualiDocument','KualiDocument','KualiDocument',1,null,doc_typ_id_0,'${application.url}/kr/maintenance.do?methodToCall=docHandler',null,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',null,null,null,1,1,UUID(),3);

INSERT INTO KREW_DOC_HDR_S VALUES(NULL);

SELECT MAX(ID) FROM KREW_DOC_HDR_S INTO doc_typ_id_2;

INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,APPL_ID,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) VALUES (doc_typ_id_2,'KC',null,'Undefined',0,doc_typ_id_1,null,null,null,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2','KC',null,null,1,1,UUID(),11);

INSERT INTO KREW_DOC_HDR_S VALUES(NULL);

SELECT MAX(ID) FROM KREW_DOC_HDR_S INTO doc_typ_id_3;

INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,APPL_ID,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) VALUES (doc_typ_id_3,'KcMaintenanceDocument','Parent of maintenance documents in KC','KcMaintenanceDocument',0,doc_typ_id_2,null,'${kuali.docHandler.url.prefix}/kr/maintenance.do?methodToCall=docHandler',null,'org.kuali.rice.kns.workflow.postprocessor.KualiPostProcessor','2',null,(SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KC-WKFLW' AND GRP_NM = 'KcAdmin'),(SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KC-WKFLW' AND GRP_NM = 'KcAdmin'),1,1,UUID(),24);

INSERT INTO KREW_DOC_HDR_S VALUES(NULL);

SELECT MAX(ID) FROM KREW_DOC_HDR_S INTO doc_typ_id_4;

INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,APPL_ID,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) VALUES (doc_typ_id_4,'KcSharedMaintenanceDocument','Parent of shared maintenance documents in KC','KcSharedMaintenanceDocument',0,doc_typ_id_3,null,null,null,null,'2',null,null,null,1,1,UUID(),61);

INSERT INTO KREW_DOC_HDR_S VALUES(NULL);

SELECT MAX(ID) FROM KREW_DOC_HDR_S INTO doc_typ_id_5;

INSERT INTO KREW_DOC_TYP_T (DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_DESC,LBL,DOC_TYP_VER_NBR,PARNT_ID,PREV_DOC_TYP_VER_NBR,DOC_HDLR_URL,HELP_DEF_URL,POST_PRCSR,RTE_VER_NBR,APPL_ID,GRP_ID,BLNKT_APPR_GRP_ID,CUR_IND,ACTV_IND,OBJ_ID,VER_NBR) VALUES (doc_typ_id_5,'QuestionnaireMaintenanceDocument','Create/Edit a Questionnaire','Questionnaire',0,doc_typ_id_4,null,'${kuali.docHandler.url.prefix}/maintenanceQn.do?methodToCall=docHandler','default.htm?turl=Documents/questionnaire1.htm',null,'2',null,null,null,1,1,UUID(),1);

END 
/

DELIMITER ;

call insert_doc_hdr_data();
