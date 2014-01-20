--  Creates a system parameter to define the default sort behavior of protocol attachments
INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,PARM_DESC_TXT,PARM_TYP_CD,CONS_CD,APPL_NMSPC_CD,OBJ_ID,VER_NBR) 
VALUES ('KC-PROTOCOL','Document','protocolAttachmentDefaultSort','ATTP','Default sort for protocol attachments','CONFG','A','KC',sys_guid(),'1');

--  MySql version
--  INSERT INTO KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,PARM_DESC_TXT,PARM_TYP_CD,CONS_CD,APPL_NMSPC_CD,OBJ_ID,VER_NBR) 
--  VALUES ('KC-PROTOCOL','Document','protocolAttachmentDefaultSort','ATTP','Default sort for protocol attachments','CONFG','A','KC',uuid(),'1');