DELIMITER /
--
--

INSERT INTO KRCR_PARM_T (APPL_ID,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','A','KC-NEGOTIATION',UUID(),'A Negotiation Documet Overview','Document','negotiationNegotiationHelp','HELP','default.htm?turl=Documents/negotiation1.htm',1)
/

INSERT INTO KRCR_PARM_T (APPL_ID,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','A','KC-NEGOTIATION',UUID(),'A Negotiation Description','Document','negotiation','HELP','default.htm?turl=Documents/negotiation2.htm',1)
/

INSERT INTO KRCR_PARM_T (APPL_ID,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','A','KC-NEGOTIATION',UUID(),'Negotiation Activities and Attachments','Document','negotiationActivitiesHelp','HELP','default.htm?turl=Documents/activitiesattachments.htm',1)
/

INSERT INTO KRCR_PARM_T (APPL_ID,EVAL_OPRTR_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,VER_NBR)
  VALUES ('KC','A','KC-NEGOTIATION',UUID(),'Negotiation Medusa','Document','negotiationMedusaHelp','HELP','default.htm?turl=Documents/medusa5.htm',1)
/

UPDATE KRCR_PARM_T
SET VAL = 'default.htm?turl=Documents/customattribute.htm'
WHERE NMSPC_CD = 'KC-PD'
AND CMPNT_CD = 'Document'
AND PARM_NM = 'proposalDevelopmentCustomAttributeHelpUrl'
/

DELIMITER ;
