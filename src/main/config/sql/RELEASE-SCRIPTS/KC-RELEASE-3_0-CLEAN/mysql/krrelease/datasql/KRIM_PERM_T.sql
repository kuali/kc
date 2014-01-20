delimiter /
TRUNCATE TABLE KRIM_PERM_T
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to access the Document Operation screen.','Use Screen','KR-WKFLW','5B4F09744944EF33E0404F8189D84F24','140','29',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to access the Java Security Management screen.','Use Screen','KR-BUS','5B4F09744945EF33E0404F8189D84F24','141','29',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to access the Message Queue screen.','Use Screen','KR-BUS','5B4F09744946EF33E0404F8189D84F24','142','29',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to access the Service Registry screen.','Use Screen','KR-BUS','5B4F09744947EF33E0404F8189D84F24','143','29',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to access the Thread Pool screen.','Use Screen','KR-BUS','5B4F09744948EF33E0404F8189D84F24','144','29',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to access the Quartz Queue screen.','Use Screen','KR-BUS','5B4F09744949EF33E0404F8189D84F24','145','29',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows a user to receive ad hoc requests for RICE Documents.','Ad Hoc Review Document','KR-SYS','5B4F0974494AEF33E0404F8189D84F24','146','9',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to open RICE Documents via the Super search option in Document Search and take Administrative workflow actions on them (such as approving the document, approving individual requests, or sending the document to a specified route node).','Administer Routing for Document','KR-SYS','5B4F0974494BEF33E0404F8189D84F24','147','3',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows access to the Blanket Approval button on RICE Documents.','Blanket Approve Document','KR-SYS','5B4F0974494CEF33E0404F8189D84F24','148','4',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes the initiation of RICE Documents.','Initiate Document','KR-SYS','5B4F0974494DEF33E0404F8189D84F24','149','10',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to modify the information on the Assignees Tab of the Role Document and the Roles section of the Membership Tab on the Person Document for Roles with a Module Code beginning with KR.','Assign Role','KR-SYS','5B4F0974494EEF33E0404F8189D84F24','150','35',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to modify the information on the Permissions tab of the Role Document for roles with a module code beginning with KR.','Grant Permission','KR-SYS','5B4F0974494FEF33E0404F8189D84F24','151','36',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to modify the information on the Responsibility tab of the Role Document for roles with a Module Code that begins with KR.','Grant Responsibility','KR-SYS','5B4F09744950EF33E0404F8189D84F24','152','37',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to modify the information on the Assignees Tab of the Group Document and the Group section of the Membership Tab on the Person Document for groups with namespaces beginning with KR.','Populate Group','KR-SYS','5B4F09744953EF33E0404F8189D84F24','155','38',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to copy RICE Documents.','Copy Document','KR-SYS','5B4F09744954EF33E0404F8189D84F24','156','2',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to access Kuali RICE inquiries.','Inquire Into Records','KR-SYS','5B4F09744959EF33E0404F8189D84F24','161','24',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access Kuali RICE lookups.','Look Up Records','KR-SYS','5B4F0974495AEF33E0404F8189D84F24','162','23',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes to initiate and edit the Parameter document for parameters with a module code beginning with KR.','Maintain System Parameter','KR-SYS','5B4F0974495BEF33E0404F8189D84F24','163','34',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to access and run Batch Jobs associated with KR modules via the Schedule link.','Modify Batch Job','KR-SYS','5B4F0974495CEF33E0404F8189D84F24','164','32',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to open RICE Documents.','Open Document','KR-SYS','5B4F0974495DEF33E0404F8189D84F24','165','40',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to access all RICE screens.','Use Screen','KR-SYS','5B4F0974495EEF33E0404F8189D84F24','166','29',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to cancel a document prior to it being submitted for routing.','Cancel Document','KUALI','5B4F0974495FEF33E0404F8189D84F24','167','14',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to submit a document for routing.','Route Document','KUALI','5B4F09744960EF33E0404F8189D84F24','168','5',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to take the Approve action on documents routed to them.','Take Requested Action','KUALI','5B4F09744962EF33E0404F8189D84F24','170','8',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to take the FYI action on documents routed to them.','Take Requested Action','KUALI','5B4F09744964EF33E0404F8189D84F24','172','8',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to take the Acknowledge action on documents routed to them.','Take Requested Action','KUALI','5B4F09744965EF33E0404F8189D84F24','173','8',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to login to the Kuali portal.','Log In','KUALI','5B4F09744966EF33E0404F8189D84F24','174','1',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to edit Kuali documents that are in ENROUTE status.','Edit Document','KUALI','5B4F0974496CEF33E0404F8189D84F24','180','16',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to edit Kuali documents that are in ENROUTE status.','Edit Document','KUALI','5B4F0974496DEF33E0404F8189D84F24','181','16',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to view the entire Tax Identification Number on the Payee ACH document and Inquiry.','Full Unmask Field','KR-SYS','5B4F0974496FEF33E0404F8189D84F24','183','27',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Users who can add notes and attachments to any document answering to the Kuali Document parent document type.','Add Note / Attachment','KUALI','606763510FC396D3E0404F8189D857A2','259','45',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to view notes and attachments on documents answering to the KualiDocument parent document type.','View Note / Attachment','KUALI','606763510FC696D3E0404F8189D857A2','261','46',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to delete notes and attachments created by any user on documents answering to the RICE Document parent document type.','Delete Note / Attachment','KR-SYS','606763510FCD96D3E0404F8189D857A2','264','47',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Use Screen','KR-WKFLW','606763510FF296D3E0404F8189D857A2','265','29',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Administer Pessimistic Locking','Administer Pessimistic Locking','KR-NS','611BE30E404E5818E0404F8189D801C2','289','1',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Users who can save RICE documents','Save Document','KR-SYS','5B4F09744961EF33E0404F8189D84F24','290','15',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to access other users action lists via the Help Desk Action List Login.','View Other Action List','KR-WKFLW','641E580969E31B49E0404F8189D86190','298','1',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Users who can perform a document search with no criteria or result limits.','Unrestricted Document Search','KR-WKFLW','641E580969E51B49E0404F8189D86190','299','1',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to view the entire Tax Identification Number on the Person document and inquiry.','Full Unmask Field','KR-SYS','6314CC58CF58B7B5E0404F8189D84439','306','27',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Users who can modify entity records in Kuali Identity Management.','Modify Entity','KR-IDM','638DD46953F9BCD5E0404F8189D86240','307','1',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to send FYI ad hoc requests for Kuali Documents','Send Ad Hoc Request','KR-SYS','662384B381B967A1E0404F8189D868A6','332','49',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to send Acknowledge ad hoc requests for Kuali Documents','Send Ad Hoc Request','KR-SYS','662384B381BD67A1E0404F8189D868A6','333','49',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to send Approve ad hoc requests for Kuali Documents','Send Ad Hoc Request','KR-SYS','662384B381C167A1E0404F8189D868A6','334','49',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows a user to override entity privacy preferences','Override Entity Privacy Preferences','KR-IDM','5B4F09744953EF33E0404F8189D84F29','378','1',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Rule Template lookup.','Look Up Records','KR-WKFLW','04C7706012554535AF8DC48DC6CC331C','701','23',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Stylesheet lookup.','Look Up Records','KR-WKFLW','471FF4B19A4648D4B84194530AE22158','702','23',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the eDocLite lookup.','Look Up Records','KR-WKFLW','E6875070DC5B4FD59193F7445C33E7AB','703','23',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Rule Attribute lookup.','Look Up Records','KR-WKFLW','28CE0127B8A14AB4BFD39920C5398A69','707','23',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Parameter Component lookup.','Look Up Records','KR-NS','45F0E8E1B9784756A3C0582980912991','719','23',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Namespace lookup.','Look Up Records','KR-NS','FFF2C6639C6041F1B148AA9901C8A1F7','720','23',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Parameter Type lookup.','Look Up Records','KR-NS','B1BD57EE64274E62AC9425C7FF185A44','721','23',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Rule Template inquiry.','Inquire Into Records','KR-WKFLW','606763510FD196D3E0404F8189D857A2','801','24',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Stylesheet inquiry.','Inquire Into Records','KR-WKFLW','606763510FD496D3E0404F8189D857A2','802','24',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the eDocLite inquiry.','Inquire Into Records','KR-WKFLW','606763510FD796D3E0404F8189D857A2','803','24',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Rule Attribute inquiry.','Inquire Into Records','KR-WKFLW','606763510FDA96D3E0404F8189D857A2','807','24',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Pessimistic Lock inquiry.','Inquire Into Records','KR-NS','606763510FE196D3E0404F8189D857A2','814','24',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Parameter Component inquiry.','Inquire Into Records','KR-NS','606763510FE496D3E0404F8189D857A2','819','24',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Namespace inquiry.','Inquire Into Records','KR-NS','606763510FE796D3E0404F8189D857A2','820','24',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allow users to access the Parameter Type inquiry.','Inquire Into Records','KR-NS','606763510FEA96D3E0404F8189D857A2','821','24',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to modify the information on the Assignees Tab of the Group Document and the Group section of the Membership Tab on the Person Document for groups with the KUALI namespace.','Populate Group','KR-SYS','5B4F09744953EF33E0404F8189D84F25','833','38',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to modify the information on the Assignees Tab of the Role Document and the Roles section of the Membership Tab on the Person Document for Roles with the KUALI namespace.','Assign Role','KR-SYS','5B4F09744953EF33E0404F8189D84F26','834','35',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to modify the information on the Permissions tab of the Role Document for roles with the KUALI namespace.','Grant Permission','KR-SYS','5B4F09744953EF33E0404F8189D84F27','835','36',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Authorizes users to modify the information on the Responsibility tab of the Role Document for roles with the KUALI namespace.','Grant Responsibility','KR-SYS','5B4F09744953EF33E0404F8189D84F28','836','37',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Allows users to access the Configuration Viewer screen','Use Screen','KR-BUS','97469975-D110-9A65-5EE5-F21FD1BEB5B2','840','29',1)
/
INSERT INTO KRIM_PERM_T (ACTV_IND,NM,NMSPC_CD,OBJ_ID,PERM_ID,PERM_TMPL_ID,VER_NBR)
  VALUES ('Y','Add Message to Route Log','KUALI','65677409-89e4-11df-98b1-1300c9ee50c1','841','51',1)
/
delimiter ;
