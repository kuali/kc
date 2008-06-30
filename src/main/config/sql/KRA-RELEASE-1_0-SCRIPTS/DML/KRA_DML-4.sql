
 INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME, COLUMN_LABEL, DATA_TYPE, DATA_LENGTH, HAS_LOOKUP, LOOKUP_WINDOW, LOOKUP_ARGUMENT, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('PROPOSAL_TYPE_CODE', 'Proposal Type', 'NUMBER', '3', 'Y', 'W_ARG_CODE_TBL', 'org.kuali.kra.proposaldevelopment.bo.ProposalType', sysdate, user, '1', '4FFAF0D9CC3CBFDBE0404F8189D81D41')
 /
 INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME, COLUMN_LABEL, DATA_TYPE, DATA_LENGTH, HAS_LOOKUP, LOOKUP_WINDOW, LOOKUP_ARGUMENT, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('TITLE', 'Title', 'STRING', '150', 'N', null, null, sysdate, user, '1', '4FFAF0D9CC3DBFDBE0404F8189D81D41')
 /
 INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME, COLUMN_LABEL, DATA_TYPE, DATA_LENGTH, HAS_LOOKUP, LOOKUP_WINDOW, LOOKUP_ARGUMENT, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NOTICE_OF_OPPORTUNITY_CODE', 'Notice of Opportunity', 'NUMBER', '3', 'Y', 'W_ARG_CODE_TBL', 'org.kuali.kra.bo.NoticeOfOpportunity', sysdate, user, '1', '4FFAF0D9CC3EBFDBE0404F8189D81D41')
 /
 INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME, COLUMN_LABEL, DATA_TYPE, DATA_LENGTH, HAS_LOOKUP, LOOKUP_WINDOW, LOOKUP_ARGUMENT, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('ACTIVITY_TYPE_CODE', 'Activity Type', 'NUMBER', '3', 'Y', 'W_ARG_CODE_TBL', 'org.kuali.kra.proposaldevelopment.bo.ActivityType', sysdate, user, '1', '4FFAF0D9CC3FBFDBE0404F8189D81D41')
 /
 INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME, COLUMN_LABEL, DATA_TYPE, DATA_LENGTH, HAS_LOOKUP, LOOKUP_WINDOW, LOOKUP_ARGUMENT, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('DEADLINE_DATE', 'Sponsor Deadline Date', 'DATE', '10', 'N', null, null, sysdate, user, '1', '4FFAF0D9CC40BFDBE0404F8189D81D41')
 /
 INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME, COLUMN_LABEL, DATA_TYPE, DATA_LENGTH, HAS_LOOKUP, LOOKUP_WINDOW, LOOKUP_ARGUMENT, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('DEADLINE_TYPE', 'Deadline Type', 'STRING', '1', 'N', null, null, sysdate, user, '1', '4FFAF0D9CC41BFDBE0404F8189D81D41')
 /
 INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME, COLUMN_LABEL, DATA_TYPE, DATA_LENGTH, HAS_LOOKUP, LOOKUP_WINDOW, LOOKUP_ARGUMENT, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('PROGRAM_ANNOUNCEMENT_NUMBER', 'Program Number', 'STRING', '50', 'N', null, null, sysdate, user, '1', '4FFAF0D9CC42BFDBE0404F8189D81D41')
 /
 INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME, COLUMN_LABEL, DATA_TYPE, DATA_LENGTH, HAS_LOOKUP, LOOKUP_WINDOW, LOOKUP_ARGUMENT, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('PROGRAM_ANNOUNCEMENT_TITLE', 'Program Title', 'STRING', '255', 'N', null, null, sysdate, user, '1', '4FFAF0D9CC43BFDBE0404F8189D81D41')
 /
 INSERT INTO EPS_PROP_COLUMNS_TO_ALTER (COLUMN_NAME, COLUMN_LABEL, DATA_TYPE, DATA_LENGTH, HAS_LOOKUP, LOOKUP_WINDOW, LOOKUP_ARGUMENT, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('MAILING_ADDRESS_ID', 'Mailing Address', 'NUMBER', '22', 'N', null, null, sysdate, user, '1', '4FFAF0D9CC44BFDBE0404F8189D81D41')
 /
 INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('1', 'Not Submitted', 'not yet submitted to spondor', sysdate, user, '1', '504D5618EC9D7760E0404F8189D822F8')
 /
 INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('2', 'Pending', 'awaiting sponsor decision', sysdate, user, '1', '504D5618EC9E7760E0404F8189D822F8')
 /
 INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('3', 'Funded', 'grant proposal was accepted by sponsor and funds are forthcoming', sysdate, user, '1', '504D5618EC9F7760E0404F8189D822F8')
 /
 INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('4', 'Withdrawn', 'the proposal was submitted to the sponsor but the submitting institution decided to cancel their application', sysdate, user, '1', '504D5618ECA07760E0404F8189D822F8')
 /
 INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('5', 'Rejected', 'the sponsor did not accept and approve the grant proposal for funding', sysdate, user, '1', '504D5618ECA17760E0404F8189D822F8')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('4', 'Human Subjects', 'O', sysdate, user, '1', '12FDD6B17B6E4ACA87EEA9596DDA00E8')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('5', 'Vertebrate Animals', 'O', sysdate, user, '1', '001BDC6E47314A229D31829F9EE219EF')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('6', 'Publication Costs/Documentation/Dissemenation', 'O', sysdate, user, '1', '4C7CCC465F324DF4AEB4CEEAB894B6E2')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('7', 'Travel - Domestic', 'T', sysdate, user, '1', '0B9A6A9772D64E039C39FCE914C66731')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('8', 'Alteration and Renovations', 'O', sysdate, user, '1', '2CF5F33997E34EEEAFCA7C188879BF6F')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('9', 'Inpatient Care Costs', 'O', sysdate, user, '1', 'ED54ABF92BC9447FB0A1FFB80FED8219')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('10', 'Duplicating', 'O', sysdate, user, '1', '41792237CCDA41DDBC28307A2169912C')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('11', 'Postage', 'O', sysdate, user, '1', 'B2EB2615F2484BE3BA30A9284FFAD92A')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('12', 'Telephone, Fax', 'O', sysdate, user, '1', 'F26884BF7CF2402A8D30449862245A27')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('13', 'Equipment Rental', 'E', sysdate, user, '1', '37994198272C41D9B4453C82E65D08E8')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('14', 'Service Agreement(s)', 'O', sysdate, user, '1', '9C2D228A1EE54EF5A235840EA06F7B21')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('15', 'Communications/Marketing', 'O', sysdate, user, '1', '2C63BD412D1E4E13912709E5A5CDAACB')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('16', 'Software', 'O', sysdate, user, '1', '92F027003F674DD9B6707B4F406776DC')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('17', 'Computer Time', 'O', sysdate, user, '1', '3545B1AB028340FC8052393D4A1ABF1B')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('18', 'Meeting Costs', 'O', sysdate, user, '1', '94A84826266A410C9668F4229B5F2945')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('19', 'Other Operating Expenses', 'O', sysdate, user, '1', 'DA759492C1FC412B87A605CC7C313878')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('20', 'Equipment', 'E', sysdate, user, '1', '5661155D102048CE80AB347BF57BB300')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('21', 'Professional Services/Consultant', 'O', sysdate, user, '1', 'A6A765D8D38D4BD795749B1FFCE7B82F')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('22', 'Subcontracts', 'O', sysdate, user, '1', '40A78900F93041609CD3D3F4C04481AF')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('23', 'Travel - Foreign', 'T', sysdate, user, '1', '44952B7A8146407E9A1E98DC9BE00D7D')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('31', 'Trainee/Participant Costs - Travel', 'S', sysdate, user, '1', 'D225CB6498B54F53AEE5F3AE2508F341')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('32', 'Trainee/Participant Costs - Stipends', 'S', sysdate, user, '1', '25FC4435C2604F2697716B2F091CB7B8')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('33', 'Outpatient costs', 'O', sysdate, user, '1', 'DF6BABBA0BA94707B11E0E1B49B2CD4C')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('34', 'Calculated Costs', 'O', sysdate, user, '1', 'D8F2CFF051F14F2C86D4C68A96B29B04')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('35', 'Trainee/Participant Costs - Tuition', 'S', sysdate, user, '1', 'FED5C26DC15546A0BC8E15AF44C93A9E')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('36', 'Trainee/Participant Costs - Subsistence', 'S', sysdate, user, '1', '212FC5A2BDF84C25955822B56F925CFF')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('1', 'Senior Personnel', 'P', sysdate, user, '1', '7100B8CCABBF4B14B199CD8941230CD3')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('25', 'Postdoctoral', 'P', sysdate, user, '1', '66A8FAF872724010B114A9EE88436533')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('26', 'Other Professionals', 'P', sysdate, user, '1', '6FD785CC401B4B0EA6368D0A3A5C2BF9')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('27', 'Graduate Students', 'P', sysdate, user, '1', '31A3A73440FD44399093E823DD020DED')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('28', 'Undergraduate Students', 'P', sysdate, user, '1', 'C404A834EEC149A493FFFB329EDC79FB')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('29', 'Project Support Staff', 'P', sysdate, user, '1', '72B19477627F4D92A876079D158B3984')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('30', 'Other Personnel', 'P', sysdate, user, '1', '765FF91FB1B24A9FBE66B748B0B49430')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('2', 'Trainee/Participant Costs - Other', 'S', sysdate, user, '1', 'DD33A72679CC4A6BB044F769739D309E')
 /
 INSERT INTO BUDGET_CATEGORY (BUDGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('3', 'Materials ', 'O', sysdate, user, '1', '28BB3B6BFD544942AF55192DA1AB1971')
 /
 INSERT INTO BUDGET_STATUS (BUDGET_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('1', 'Complete', sysdate, user, '1', '448024AD631610C6E043814FD88110C6')
 /
 INSERT INTO BUDGET_STATUS (BUDGET_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('2', 'Incomplete', sysdate, user, '1', '448024AD631710C6E043814FD88110C6')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', 'Other Direct Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '40', 'Alterations', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '42', 'Purchased Equipment', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '43', 'Materials and Supplies', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '45', 'Equipment Rental', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '73', 'Domestic Travel', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '74', 'Foreign Travel', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '75', 'Participant Stipends', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '76', 'Participant Tuition', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '77', 'Participant Travel', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0A17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '78', 'Participant Other', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0B17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '79', 'Participant Subsistence', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0C17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '80', 'Publication Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0D17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '81', 'Consultant Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0E17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '82', 'Computer Services', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D0F17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '79', 'Participant Subsistence', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '04', 'Subcontract', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', 'Other Direct Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '40', 'Alterations and Renovations', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '41', 'Patient Care', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '42', 'Purchased Equipment', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '43', 'Materials and Supplies', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '73', 'Travel', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '81', 'Consultant Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '90', 'Outpatient Services', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01', 'SENIOR PERSONNEL', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1A17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-Graduates', 'GRADUATE STUDENTS', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1B17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-Other', 'OTHER', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1C17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-Other Profs', 'OTHER PROFESSIONALS', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1D17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-PostDocs', 'POST DOCTORAL', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1E17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-Secretarial', 'SECRETARIAL', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D1F17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-Undergrads', 'UNDERGRADUATE STUDENTS', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '04', 'Subcontract', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', 'Other Direct Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '42', 'Purchased Equipment', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '43', 'Materials and Supplies', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '73', 'Domestic Travel', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '74', 'Foreign Travel', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '75', 'Participant Stipends', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '77', 'Participant Travel', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '78', 'Participant Other', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '80', 'Publication Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2A17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '81', 'Consultant Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2B17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '82', 'Computer Services', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2C17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '88', 'Cost Sharing', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2D17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01', 'Senior Personnel', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2E17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-Graduates', 'Graduate Students', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D2F17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-Other', 'Other Personnel', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-Other Profs', 'Other Professionals', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-PostDocs', 'Post Docs', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-Secretarial', 'Secretarial - Clerical', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-Undergrads', 'Undergraduate Students', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '04', 'Subcontract', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', 'Other Direct Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '42', 'Purchased Equipment', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '43', 'Materials and Supplies', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '73', 'Domestic Travel', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '74', 'Foreign Travel', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3A17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '75', 'Participant Stipends', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3B17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '77', 'Participant Travel', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3C17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '78', 'Participant Other', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3D17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '80', 'Publication Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3E17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '81', 'Consultant Costs', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D3F17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '82', 'Computer Services', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D4017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01', 'Senior Personnel', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D4117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-Graduates', 'Graduate Students', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D4217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-Other', 'Other Personnel', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D4317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-Other Profs', 'Other Professionals', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D4417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-PostDocs', 'Post Doctoral Associates', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D4517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-Secretarial', 'Secretarial / Clerical', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D4617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-Undergrads', 'Undergraduate Students', 'P                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D4717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPS (MAPPING_NAME, TARGET_CATEGORY_CODE, DESCRIPTION, CATEGORY_TYPE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '04', 'Subcontract', 'O                                                                                                                                                                                                       ', sysdate, user, '1', '482D3DCE4D4817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '79', '36', sysdate, user, '1', '482D3DCE4C8417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '04', '22', sysdate, user, '1', '482D3DCE4C8517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '2', sysdate, user, '1', '482D3DCE4C8617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '4', sysdate, user, '1', '482D3DCE4C8717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '5', sysdate, user, '1', '482D3DCE4C8817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '6', sysdate, user, '1', '482D3DCE4C8917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '10', sysdate, user, '1', '482D3DCE4C8A17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '12', sysdate, user, '1', '482D3DCE4C8B17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '13', sysdate, user, '1', '482D3DCE4C8C17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '14', sysdate, user, '1', '482D3DCE4C8D17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '15', sysdate, user, '1', '482D3DCE4C8E17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '16', sysdate, user, '1', '482D3DCE4C8F17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '17', sysdate, user, '1', '482D3DCE4C9017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '18', sysdate, user, '1', '482D3DCE4C9117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '19', sysdate, user, '1', '482D3DCE4C9217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '31', sysdate, user, '1', '482D3DCE4C9317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '39', '32', sysdate, user, '1', '482D3DCE4C9417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '40', '8', sysdate, user, '1', '482D3DCE4C9517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '41', '9', sysdate, user, '1', '482D3DCE4C9617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '42', '20', sysdate, user, '1', '482D3DCE4C9717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '43', '3', sysdate, user, '1', '482D3DCE4C9817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '43', '11', sysdate, user, '1', '482D3DCE4C9917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '73', '7', sysdate, user, '1', '482D3DCE4C9A17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '73', '23', sysdate, user, '1', '482D3DCE4C9B17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '81', '21', sysdate, user, '1', '482D3DCE4C9C17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NIH_PRINTING', '90', '33', sysdate, user, '1', '482D3DCE4C9D17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01', '1', sysdate, user, '1', '482D3DCE4C9E17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-Graduates', '27', sysdate, user, '1', '482D3DCE4C9F17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-Other', '30', sysdate, user, '1', '482D3DCE4CA017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-Other Profs', '26', sysdate, user, '1', '482D3DCE4CA117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-PostDocs', '25', sysdate, user, '1', '482D3DCE4CA217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-Secretarial', '29', sysdate, user, '1', '482D3DCE4CA317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '01-Undergrads', '28', sysdate, user, '1', '482D3DCE4CA417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '04', '22', sysdate, user, '1', '482D3DCE4CA517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '4', sysdate, user, '1', '482D3DCE4CA617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '5', sysdate, user, '1', '482D3DCE4CA717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '8', sysdate, user, '1', '482D3DCE4CA817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '9', sysdate, user, '1', '482D3DCE4CA917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '10', sysdate, user, '1', '482D3DCE4CAA17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '12', sysdate, user, '1', '482D3DCE4CAB17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '13', sysdate, user, '1', '482D3DCE4CAC17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '14', sysdate, user, '1', '482D3DCE4CAD17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '15', sysdate, user, '1', '482D3DCE4CAE17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '18', sysdate, user, '1', '482D3DCE4CAF17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '19', sysdate, user, '1', '482D3DCE4CB017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '39', '33', sysdate, user, '1', '482D3DCE4CB117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '42', '20', sysdate, user, '1', '482D3DCE4CB217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '43', '3', sysdate, user, '1', '482D3DCE4CB317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '43', '11', sysdate, user, '1', '482D3DCE4CB417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '73', '7', sysdate, user, '1', '482D3DCE4CB517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '74', '23', sysdate, user, '1', '482D3DCE4CB617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '75', '32', sysdate, user, '1', '482D3DCE4CB717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '77', '31', sysdate, user, '1', '482D3DCE4CB817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '78', '2', sysdate, user, '1', '482D3DCE4CB917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '80', '6', sysdate, user, '1', '482D3DCE4CBA17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '81', '21', sysdate, user, '1', '482D3DCE4CBB17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '82', '16', sysdate, user, '1', '482D3DCE4CBC17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_194', '82', '17', sysdate, user, '1', '482D3DCE4CBD17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01', '1', sysdate, user, '1', '482D3DCE4CBE17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-Graduates', '27', sysdate, user, '1', '482D3DCE4CBF17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-Other', '30', sysdate, user, '1', '482D3DCE4CC017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-Other Profs', '26', sysdate, user, '1', '482D3DCE4CC117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-PostDocs', '25', sysdate, user, '1', '482D3DCE4CC217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-Secretarial', '29', sysdate, user, '1', '482D3DCE4CC317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '01-Undergrads', '28', sysdate, user, '1', '482D3DCE4CC417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '04', '22', sysdate, user, '1', '482D3DCE4CC517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '4', sysdate, user, '1', '482D3DCE4CC617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '5', sysdate, user, '1', '482D3DCE4CC717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '8', sysdate, user, '1', '482D3DCE4CC817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '9', sysdate, user, '1', '482D3DCE4CC917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '10', sysdate, user, '1', '482D3DCE4CCA17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '12', sysdate, user, '1', '482D3DCE4CCB17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '13', sysdate, user, '1', '482D3DCE4CCC17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '14', sysdate, user, '1', '482D3DCE4CCD17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '15', sysdate, user, '1', '482D3DCE4CCE17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '18', sysdate, user, '1', '482D3DCE4CCF17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '19', sysdate, user, '1', '482D3DCE4CD017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '33', sysdate, user, '1', '482D3DCE4CD117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '42', '20', sysdate, user, '1', '482D3DCE4CD217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '43', '3', sysdate, user, '1', '482D3DCE4CD317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '43', '11', sysdate, user, '1', '482D3DCE4CD417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '73', '7', sysdate, user, '1', '482D3DCE4CD517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '74', '23', sysdate, user, '1', '482D3DCE4CD617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '75', '32', sysdate, user, '1', '482D3DCE4CD717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '77', '31', sysdate, user, '1', '482D3DCE4CD817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '78', '2', sysdate, user, '1', '482D3DCE4CD917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '80', '6', sysdate, user, '1', '482D3DCE4CDA17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '81', '21', sysdate, user, '1', '482D3DCE4CDB17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '82', '16', sysdate, user, '1', '482D3DCE4CDC17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '82', '17', sysdate, user, '1', '482D3DCE4CDD17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01', '1', sysdate, user, '1', '482D3DCE4CDE17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-Graduates', '27', sysdate, user, '1', '482D3DCE4CDF17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-Other', '30', sysdate, user, '1', '482D3DCE4CE017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-Other Profs', '26', sysdate, user, '1', '482D3DCE4CE117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-PostDocs', '25', sysdate, user, '1', '482D3DCE4CE217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-Secretarial', '29', sysdate, user, '1', '482D3DCE4CE317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '01-Undergrads', '28', sysdate, user, '1', '482D3DCE4CE417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '04', '22', sysdate, user, '1', '482D3DCE4CE517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '4', sysdate, user, '1', '482D3DCE4CE617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '5', sysdate, user, '1', '482D3DCE4CE717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '9', sysdate, user, '1', '482D3DCE4CE817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '10', sysdate, user, '1', '482D3DCE4CE917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '12', sysdate, user, '1', '482D3DCE4CEA17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '14', sysdate, user, '1', '482D3DCE4CEB17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '15', sysdate, user, '1', '482D3DCE4CEC17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '16', sysdate, user, '1', '482D3DCE4CED17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '18', sysdate, user, '1', '482D3DCE4CEE17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '19', sysdate, user, '1', '482D3DCE4CEF17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '39', '33', sysdate, user, '1', '482D3DCE4CF017A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '40', '8', sysdate, user, '1', '482D3DCE4CF117A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '42', '20', sysdate, user, '1', '482D3DCE4CF217A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '43', '3', sysdate, user, '1', '482D3DCE4CF317A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '43', '11', sysdate, user, '1', '482D3DCE4CF417A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '45', '13', sysdate, user, '1', '482D3DCE4CF517A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '73', '7', sysdate, user, '1', '482D3DCE4CF617A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '74', '23', sysdate, user, '1', '482D3DCE4CF717A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '75', '32', sysdate, user, '1', '482D3DCE4CF817A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '76', '35', sysdate, user, '1', '482D3DCE4CF917A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '77', '31', sysdate, user, '1', '482D3DCE4CFA17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '78', '2', sysdate, user, '1', '482D3DCE4CFB17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '79', '36', sysdate, user, '1', '482D3DCE4CFC17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '80', '6', sysdate, user, '1', '482D3DCE4CFD17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '81', '21', sysdate, user, '1', '482D3DCE4CFE17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('S2S', '82', '17', sysdate, user, '1', '482D3DCE4CFF17A1E0404F8189D81CE1')
 /
 INSERT INTO BUDGET_CATEGORY_MAPPING (MAPPING_NAME, TARGET_CATEGORY_CODE, COEUS_CATEGORY_CODE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 VALUES ('NSF_PRINTING', '39', '35', sysdate, user, '1', '482D3DCE4D0017A1E0404F8189D81CE1')
 /
 INSERT INTO EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, CERTIFICATION_REQUIRED, UNIT_DETAILS_REQUIRED)
 VALUES ('PI', 'Principal Investigator', sysdate, user, '1', '4181F0171FDEC0DAE043814FD881C0DA', 'Y', 'Y')
 /
 INSERT INTO EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, CERTIFICATION_REQUIRED, UNIT_DETAILS_REQUIRED)
 VALUES ('COI', 'Co-Investigator', sysdate, user, '1', '4181F0171FDFC0DAE043814FD881C0DA', 'Y', 'Y')
 /
 INSERT INTO EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID, CERTIFICATION_REQUIRED, UNIT_DETAILS_REQUIRED)
 VALUES ('KP', 'Key Person', sysdate, user, '1', '4181F0171FE0C0DAE043814FD881C0DA', 'O', 'O')
 /
 INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
 VALUES ('4', 'Approved', sysdate, user)
 /
 INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
 VALUES ('3', 'Rejected', sysdate, user)
 /
 INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
 VALUES ('1', 'In Progress', sysdate, user)
 /
 INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
 VALUES ('2', 'Approval In Progress', sysdate, user)
 /
 INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
 VALUES ('5', 'Submitted', sysdate, user)
 /
 INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
 VALUES ('6', 'Post-Submission Approval', sysdate, user)
 /
 INSERT INTO EPS_PROPOSAL_STATUS (STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
 VALUES ('7', 'Post-Submission Rejection', sysdate, user)
 /