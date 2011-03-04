delimiter /
TRUNCATE TABLE KREW_ATT_T
/
INSERT INTO KREW_ATT_T (ATTACHMENT_ID,FILE_LOC,FILE_NM,MIME_TYP,NTE_ID,VER_NBR)
  VALUES (2001,'/opt/ears/wf_att_2213_G1_Example1_For_version094_EDL_xml_204458687.tmp','G1.Example1.For.version094.EDL.xml','text/xml',2000,1)
/
INSERT INTO KREW_ATT_T (ATTACHMENT_ID,FILE_LOC,FILE_NM,MIME_TYP,NTE_ID,VER_NBR)
  VALUES (2003,'/opt/ears/wf_att_2219_hasActiveNode_class_204558692.tmp','hasActiveNode.class','text/plain',2002,1)
/
INSERT INTO KREW_ATT_T (ATTACHMENT_ID,FILE_LOC,FILE_NM,MIME_TYP,NTE_ID,VER_NBR)
  VALUES (2005,'/opt/ears/wf_att_2219_ReadMe_txt_204658693.tmp','ReadMe.txt','text/plain',2004,1)
/
delimiter ;
