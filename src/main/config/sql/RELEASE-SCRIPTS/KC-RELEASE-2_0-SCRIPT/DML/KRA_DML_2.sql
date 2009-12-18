INSERT INTO SH_PARM_T 
	(SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,OBJ_ID,VER_NBR,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,WRKGRP_NM,ACTIVE_IND) 
	VALUES 
	('KC-AWARD','D','reportClassForPaymentsAndInvoices',sys_guid(),1,'CONFG','5','Report Class For Payments And Invoices','A','WorkflowAdmin','Y');
INSERT INTO SH_PARM_T 
	(SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,OBJ_ID,VER_NBR,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,WRKGRP_NM,ACTIVE_IND) 
	VALUES 
	('KC-AWARD','D','scheduleGenerationPeriodInYearsWhenFrequencyBaseCodeIsFinalExpirationDate',sys_guid(),1,'CONFG','1','Schedule Generation Period In Years When Frequency Base Code Is Final Expiration Date','A','WorkflowAdmin','Y');

INSERT INTO REPORT_CLASS ( REPORT_CLASS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, GENERATE_REPORT_REQUIREMENTS ) 
VALUES ( '6', 'Fiscal', sysdate, user, 'Y' );

insert into KIM_ROLES_T (ID,NAME,DESCRIPTION, ROLE_TYPE_CODE, DESCEND_FLAG) values(14,'Protocol Unassigned','Protocol Unassigned - no permissions', 'R', 'N'); 

commit;

update SH_PARM_T
set SH_PARM_TXT='6' where SH_PARM_NMSPC_CD = 'KC-AWARD' AND SH_PARM_DTL_TYP_CD = 'D' AND SH_PARM_NM='reportClassForPaymentsAndInvoices';

COMMIT;

INSERT INTO SH_PARM_T 
	(SH_PARM_NMSPC_CD,SH_PARM_DTL_TYP_CD,SH_PARM_NM,OBJ_ID,VER_NBR,SH_PARM_TYP_CD,SH_PARM_TXT,SH_PARM_DESC,SH_PARM_CONS_CD,WRKGRP_NM,ACTIVE_IND) 
	VALUES 
	('KC-AWARD','D','contactTypeOther',sys_guid(),1,'CONFG','8','Contact Type Code For Contact Type Other','A','WorkflowAdmin','Y');
	
COMMIT;	

INSERT INTO AFFILIATION_TYPE (AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES(2, 'Non-Faculty', sysdate, user);
INSERT INTO AFFILIATION_TYPE(AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES(3, 'Affiliate', sysdate, user);
INSERT INTO AFFILIATION_TYPE(AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES(1, 'Faculty', sysdate, user);

commit;

INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 1, 'Regular', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 2, 'Fabricated Equipment', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 3, 'Draper Fellowship', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 4, 'Core Grant Administration', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 5, 'Gift', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 6, 'Conversion Account', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 7, 'Off-campus account', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 8, 'SBIR', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 9, 'STTR', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 10, 'No Account', sysdate, user ); 
INSERT INTO ACCOUNT_TYPE ( ACCOUNT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 11, 'Service Facilities', sysdate, user ); 

Commit;


INSERT INTO AFFILIATION_TYPE (AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES(4, 'Student Investigator', sysdate, user);
INSERT INTO AFFILIATION_TYPE (AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER)
VALUES(5, 'Faculty Supervisor', sysdate, user);

commit;


INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('1', 'Clinical studies of drugs and medical devices only when condition (a) or (b) is met. (a) Research on drugs for which an investigational new drug application (21 CFR Part 312) is not required.  (Note: Research on marketed drugs that significantly increases the risks or decreases the acceptability of the risks associated with the use of the product is not eligible for expedited review.) (b) Research on medical devices for which (i) an investigational device exemption application (21 CFR Part 812) is not required; or (ii) the medical device is cleared/approved for marketing and the medical device is being used in accordance with its cleared/approved labeling.', sysdate,'KRADEV');

INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('2', 'Collection of blood samples by finger stick, heel stick, ear stick, or venipuncture as follows: (a) from healthy, nonpregnant adults who weigh at least 110 pounds. For these subjects, the amounts drawn may not exceed 550 ml in an 8 week period and collection may not occur more frequently than 2 times per week; or (b) from other adults and children2, considering the age, weight, and health of the subjects, the collection procedure, the amount of blood to be collected, and the frequency with which it will be collected. For these subjects, the amount drawn may not exceed the lesser of 50 ml or 3 ml per kg in an 8 week period and collection may not occur more frequently than 2 times per week.', sysdate,'KRADEV');

INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('3', 'Prospective collection of biological specimens for research purposes by noninvasive means. Examples: (a) hair and nail clippings in a nondisfiguring manner; (b) deciduous teeth at time of exfoliation or if routine patient care indicates a need for extraction; (c) permanent teeth if routine patient care indicates a need for extraction; (d) excreta and external secretions (including sweat); (e) uncannulated saliva collected either in an unstimulated fashion or stimulated by chewing gumbase or wax or by applying a dilute citric solution to the tongue; (f) placenta removed at delivery; (g) amniotic fluid obtained at the time of rupture of the membrane prior to or during labor; (h) supra- and subgingival dental plaque and calculus, provided the collection procedure is not more invasive than routine prophylactic scaling of the teeth and the process is accomplished in accordance with accepted prophylactic techniques; (i) mucosal and skin cells collected by buccal scraping or swab, skin swab, or mouth washings; (j) sputum collected after saline mist nebulization.', sysdate,'KRADEV');

INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('4', 'Collection of data through noninvasive procedures (not involving general anesthesia or sedation) routinely employed in clinical practice, excluding procedures involving x-rays or microwaves. Where medical devices are employed, they must be cleared/approved for marketing. (Studies intended to evaluate the safety and effectiveness of the medical device are not generally eligible for expedited review, including studies of cleared medical devices for new indications.)', sysdate,'KRADEV');

INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('5', 'Research involving materials (data, documents, records, or specimens) that have been collected, or will be collected solely for nonresearch purposes (such as medical treatment or diagnosis). (NOTE: Some research in this category may be exempt from the HHS regulations for the protection of human subjects. 45 CFR 46.101(b)(4). This listing refers only to research that is not exempt.)',  sysdate,'KRADEV');

INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('6', 'Collection of data from voice, video, digital, or image recordings made for research purposes.', sysdate,'KRADEV');

INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('7', 'Research on individual or group characteristics or behavior (including, but not limited to, research on perception, cognition, motivation, identity, language, communication, cultural beliefs or practices, and social behavior) or research employing survey, interview, oral history, focus group, program evaluation, human factors evaluation, or quality assurance methodologies. (NOTE: Some research in this category may be exempt from the HHS regulations for the protection of human subjects. 45 CFR 46.101(b)(2) and (b)(3). This listing refers only to research that is not exempt.)', sysdate,'KRADEV');

INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('8', 'Continuing review of research previously approved by the convened IRB as follows (a) where (i) the research is permanently closed to the enrollment of new subjects; (ii) all subjects have completed all research-related interventions; and (iii) the research remains active only for long-term follow-up of subjects.', sysdate,'KRADEV');

INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('9', 'Continuing review of research previously approved by the convened IRB as follows (b) where no subjects have been enrolled and no additional risks have been identified.', sysdate,'KRADEV');

INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('10', 'Continuing review of research previously approved by the convened IRB as follows (c) where the remaining research activities are limited to data analysis.', sysdate,'KRADEV');

INSERT INTO EXPEDITED_REVIEW_CHECKLIST (EXPEDITED_REV_CHKLST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('11', 'Continuing review of research, not conducted under an investigational new drug application or investigational device exemption where categories two (2) through eight (8) do not apply but the IRB has determined and documented at a convened meeting that the research involves no greater than minimal risk and no additional risks have been identified.', sysdate,'KRADEV');

commit;

INSERT INTO KRIM_GRP_MBR_T (GRP_ID,GRP_MBR_ID,LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,VER_NBR) 
  VALUES ('1','1186',TO_DATE( '20081208124957', 'YYYYMMDDHH24MISS' ),'quickstart','P',sys_guid(),1);
  
commit;

UPDATE organization SET organization_name = trim(organization_name);

commit;


UPDATE YNQ SET GROUP_NAME = 'General Y/N Questions' where question_id='28'; 
UPDATE YNQ SET GROUP_NAME = 'General Y/N Questions' where question_id='29'; 

commit;

INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 1, 'human_subjects', 'No', 'This award has no human protocols attached', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 2, 'human_subjects', 'Unknown', 'Unkown if this award has human subjects', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 3, 'interest_revenue', '2', 'MIT has committed interest to the project', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 4, 'interest_revenue', '1', 'Sponsor requires interest be applied to the project', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 5, 'yes_no_flag', 'Yes', 'Yes', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 6, 'yes_no_flag', 'No', 'No', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 7, 'PI_Question', 'Z1', NULL, sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 8, 'PI_Question', 'Z2', NULL, sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 9, 'PI_Question', 'Z3', NULL, sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 10, 'PI_Question', 'Z4', NULL, sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 11, 'human_subjects', 'Yes', 'This award has human protocols attached', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 12, 'PI_Question', 'Z5', NULL, sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 13, 'Sponsor_routing', 'DOD', 'DOD sponsors', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 14, 'Sponsor_routing', 'DOE', 'DOE sponsors', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 15, 'Sponsor_routing', 'NASA', 'NASA sponsors', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 16, 'Sponsor_routing', 'NIH', 'NIH sponsors', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 17, 'Sponsor_routing', 'NSF', 'NSF sponsors', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 18, 'interest_revenue', '4', 'Account originally created as a non-sponsored account', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 19, 'interest_revenue', '5', 'Account is an endowment and is being replaced', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 20, 'interest_revenue', '3', 'MIT has committed interest to an internal order', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 21, 'revenue', 'Non-Transferable', 'Revenue is non-transferable', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 22, 'revenue', 'Transferable', 'Revenue may be transfered ', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 23, 'interest_revenue', '0', 'Undetermined or Non Interest Bearing', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 24, 'AppointmentTypes', '1', '9M DURA TION', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 25, 'AppointmentTypes', '2', '10M DURATION', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 26, 'AppointmentTypes', '3', '11M DURATION', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 27, 'AppointmentTypes', '4', '12M DURATION', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 28, 'AppointmentTypes', '5', 'REG EMPLOYEE', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 29, 'AppointmentTypes', '6', 'SUM EMPLOYEE', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 30, 'AppointmentTypes', '7', 'TMP EMPLOYEE', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 31, 'PeriodTypes', 'CY', 'Calendar', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 32, 'PeriodTypes', 'SP', 'Summer', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 33, 'PeriodTypes', 'CC', 'Cycle', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 34, 'PeriodTypes', 'AP', 'Academic', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 35, 'ProjectRoles', '1', 'Undefined', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 36, 'ProjectRoles', '2', 'Investigator', sysdate, user ); 
INSERT INTO ARG_VALUE_LOOKUP ( ARG_VALUE_LOOKUP_ID, ARGUMENT_NAME, VALUE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 37, 'ProjectRoles', '3', 'Co-Investigator', sysdate, user ); 

commit;
