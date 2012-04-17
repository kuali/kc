CREATE TABLE TEMP_QUESTION
	(QUESTION_ID NUMBER(6,0) NOT NULL ENABLE,
	QUESTION VARCHAR2(2000) NOT NULL ENABLE, 
	 MAX_ANSWERS NUMBER(2,0) NOT NULL ENABLE, 
	 VALID_ANSWER VARCHAR2(20) NOT NULL ENABLE, 
	 LOOKUP_NAME VARCHAR2(50), 
	ANSWER_DATA_TYPE VARCHAR2(30) NOT NULL ENABLE, 
	ANSWER_MAX_LENGTH NUMBER(4,0), 
	LOOKUP_GUI VARCHAR2(50), 
	UPDATE_TIMESTAMP DATE NOT NULL ENABLE, 
	UPDATE_USER VARCHAR2(20) NOT NULL ENABLE, 
	GROUP_TYPE_CODE NUMBER(3,0) NOT NULL ENABLE, 
	VERSION_NUMBER NUMBER(3,0) DEFAULT 1 NOT NULL ENABLE, 
	STATUS VARCHAR2(1) DEFAULT 'A' NOT NULL ENABLE
  )
/
  CREATE TABLE TEMP_QUESTIONNAIRE
   (QUESTIONNAIRE_ID NUMBER(6,0) NOT NULL ENABLE, 
	NAME VARCHAR2(50) NOT NULL ENABLE, 
	DESCRIPTION VARCHAR2(2000), 
	UPDATE_TIMESTAMP DATE NOT NULL ENABLE, 
	UPDATE_USER VARCHAR2(20) NOT NULL ENABLE, 
	IS_FINAL VARCHAR2(1) NOT NULL ENABLE, 
	VERSION_NUMBER NUMBER(3,0) DEFAULT 1 NOT NULL ENABLE, 
	FILE_NAME VARCHAR2(1000), 
	TEMPLATE CLOB, 
	GROUP_TYPE_CODE NUMBER(3,0)
  )
/
  CREATE TABLE TEMP_QUESTIONNAIRE_QUESTIONS 
   (	QUESTIONNAIRE_ID NUMBER(6,0) NOT NULL ENABLE, 
	QUESTION_ID NUMBER(6,0) NOT NULL ENABLE, 
	QUESTION_NUMBER NUMBER(6,0) NOT NULL ENABLE, 
	PARENT_QUESTION_NUMBER NUMBER(6,0) NOT NULL ENABLE, 
	CONDITION_FLAG VARCHAR2(1) NOT NULL ENABLE, 
	CONDITION VARCHAR2(50), 
	CONDITION_VALUE VARCHAR2(2000), 
	UPDATE_TIMESTAMP DATE NOT NULL ENABLE, 
	UPDATE_USER VARCHAR2(20) NOT NULL ENABLE, 
	QUESTION_SEQ_NUMBER NUMBER(3,0),
	QUESTION_VERSION_NUMBER NUMBER(3,0) DEFAULT 1 NOT NULL ENABLE,
	QUESTIONNAIRE_VERSION_NUMBER NUMBER(3,0) DEFAULT 1 NOT NULL ENABLE,
	RULE_ID NUMBER(6,0)
  )
/
CREATE TABLE TEMP_QUESTIONNAIRE_USAGE
   (MODULE_ITEM_CODE NUMBER(3,0) NOT NULL ENABLE,
	MODULE_SUB_ITEM_CODE NUMBER(3,0) NOT NULL ENABLE,
	QUESTIONNAIRE_ID NUMBER(6,0) NOT NULL ENABLE,
	RULE_ID NUMBER(6,0),
	QUESTIONNAIRE_LABEL VARCHAR2(50),
	UPDATE_TIMESTAMP DATE NOT NULL ENABLE,
	UPDATE_USER VARCHAR2(20) NOT NULL ENABLE,
	VERSION_NUMBER NUMBER(3,0) DEFAULT 1 NOT NULL ENABLE,
	IS_MANDATORY VARCHAR2(1) DEFAULT 'N' NOT NULL ENABLE
  )
/
COMMIT
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (1, 'Is Human Subjects involvement in this project indefinite?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (2, 'Is a Clinical Trial part of this project?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (3, 'Is this an NIH-defined Phase III clinical trial?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (4, 'Is the inclusion of vertebrate animals use in this project indefinite?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (5, 'Does the proposed project involve human embryonic stem cells?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (6, 'Can a specific stem cell line be referenced at this time? A "N" answer affirms that an undefined registry cell line will be used. A "Y" answer requires entering the cell IDs in the next question.', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (7, 'List the registration number of the specific cell line(s) from the stem cell registry found at: http://stemcells.nih.gov/registry/', 20, 'Text', 'String', 4, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (8, 'Have you already completed a graduate level degree?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (9, 'Please provide the specific Other Masters (MOTH) degree type here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (10, 'Please provide the specific Other Doctorate (DOTH) degree type here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (11, 'Please provide the specific Other Doctor of Med Dentistry (DDOT) degree type here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (12, 'Please provide the specific Other Doctor of Med (MDOT) degree type here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (13, 'Please provide the specific Other Doctor of Vet Med (VDOT) degree type here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (14, 'Please provide the specific Other Double Degree Program (DBOTH) degree type(s) here.', 1, 'Text', 'String', 15, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
  (15, 'Please select the type of degree sought during the proposed award, from the list of options provided. If the degree being sought does not appear on the list, please select the most appropriate "other" degree type from the list.', 1, 'Search', 'Graduate Level Degree', 'String', 8, 'VALUELIST', sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (16, 'Please provide the specific Other Masters (MOTH) degree type here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (17, 'Please provide the specific Other Doctorate (DOTH) degree type here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (18, 'Please provide the specific Other Doctor Of Med Dentistry (DDOT) degree type here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (19, 'Please provide the specific Other Doctor of Vet Med (VDOT) degree type here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (20, 'Please provide the specific Other Double Degree Program (DBOTH) degree type(s) here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (21, 'Please provide the specific Other Doctor of Med (MDOT) degree type here.', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (22, 'Select the field of training that best applies to the proposed award from the sub category list. A list of broad categories is available if there is no suitable subcategory, though use of broad categories is discouraged by the sponsor.', 1, 'Search', 'Field of Training-Sub Category', 'String', 100, 'VALUELIST', sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (23, 'Please only use one of these broader category descriptions of field of training if it is truly the best fit.', 1, 'Search', 'Field of Training-Broad', 'String', 100, 'VALUELIST', sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (24, 'If you have current or previous Kirschstein-NRSA support, check "yes" and provide details on the support in the following question.', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (25, 'What was the start date of this support? Enter the date in format MM/DD/YYYY, otherwise enter UNKNOWN', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'I')
/
Insert into  TEMP_QUESTION 
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (26, 'What was the end date of this support?  Enter the date in format MM/DD/YYYY, otherwise enter UNKNOWN', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'I')
/
Insert into  TEMP_QUESTION 
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (27, 'Please enter the NIH grant number for this prior support, or return to the previous question and change your answer to No.', 1, 'Text', 'String', 25, sysdate, 'admin', 4, 1, 'A')
/
Insert into  TEMP_QUESTION 
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (28, 'Has this application been previously submitted by a different institution?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into  TEMP_QUESTION 
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (29, 'If this application reflects a change in grantee institution, enter the name of the former institution here.', 1, 'Text', 'String', 40, sysdate, 'admin', 4, 1, 'A')
/
Insert into  TEMP_QUESTION 
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (30, 'Please select the type of graduate level degree earned from the list.  If the degree you''ve earned does not appear in the list, please select the most appropriate "other" degree type from the list.', 1, 'Search', 'Graduate Level Degree', 'String', 8, 'VALUELIST', sysdate, 'admin', 4, 1, 'A')
/
Insert into  TEMP_QUESTION 
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (31, 'Do you have another current or prior Kirschstein-NRSA support award to report? Answer yes to record another award/ maximum of 4 allowed on this form.', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into  TEMP_QUESTION 
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (32, 'Was the Kirschstein NRSA support level for Predoctoral or Postdoctoral training?', 1, 'Search', 'Kirschstein-NRSA support level', 'String', 25, 'VALUELIST', sysdate, 'admin', 4, 1, 'A')
/
Insert into  TEMP_QUESTION 
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (33, 'Was the prior Kirschstein NRSA support for an Individual or an Institution?', 1, 'Search', 'Kirschstein-NRSA Award TYPE', 'String', 25, 'VALUELIST', sysdate, 'admin', 4, 1, 'A')
/
Insert into  TEMP_QUESTION 
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (34, 'What date did you earn the graduate degree? Type in format MM/DD/YYYY or use the calendar tool.', 1, 'Text', 'Date', 0, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (35, 'For the degree sought during the proposed award, what is the expected degree completion date? Type in format MM/DD/YYYY or use the calendar tool.', 1, 'Text', 'Date', 0, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (36, 'Is this a Senior Fellowship Application?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (37, 'Are you receiving any supplementation funds from other sources? (sabbatical leave, salary, etc?)', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (38, 'What is the supplemental funding amount anticipated for the first year of the proposed fellowship?  (Enter a numeric value only, no commas or non-numeric characters).', 1, 'Text', 'Number', 6, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (39, 'Enter the number of months receiving the supplemental funds in the first year of the proposed fellowship. Fractions of months (using two decimal places) may be expressed. (Enter a numeric value only, no commas or non-numeric characters).', 1, 'Text', 'Number', 5, sysdate, 'admin', 4, 1, 'I')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (40, 'Enter the type of supplemental funding (sabbatical leave, salary, etc.)', 1, 'Text', 'String', 50, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (41, 'What is the source of the supplemental funding?', 1, 'Text', 'String', 100, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (42, 'Are you seeking a degree during the proposed Award', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (43, 'Do you know the START date of this current or prior support?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (44, 'What was the start date of this support? Enter the date in 10 character format MM/DD/YYYY or use the calendar tool.', 1, 'Text', 'Date', 0, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (45, 'What was the end date of this support? Enter the date in 10 character format MM/DD/YYYY or use the calendar tool.', 1, 'Text', 'Date', 0, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (46, 'Do you know the NIH grant number for this prior support?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (47, 'Please enter the dollar amount of your present institutional base salary. Do not use commas or decimal points', 1, 'Text', 'Number', 6, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (48, 'Please select the academic period of time on which the salary is determined (e.g., academic year of 9 months, full-time 12 months, etc.  Select a value from the list presented:', 1, 'Search', 'Academic Appointment Period', 'String', 15, 'VALUELIST', sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (49, 'Do you know the END date of this current or prior support?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (50, 'Please enter the number of months you will receive the salary in the first year of the proposed fellowship.  Fractions of months (using two decimal places) may be entered. The number may not be more than 12, but may include a decimal indicating partial months (e.g., 9.5).', 1, 'Text', 'String', 5, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (51, 'Enter the number of months receiving the supplemental funds in the first year of the proposed fellowship. The number may not be more than 12.', 1, 'Text', 'String', 5, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (52, 'Are you currently serving (or have previously served) as a PI, co-PI or Program Director (PD) on any Federally funded project?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (53, 'Are you an NSF Beginning Investigator (GPG Chapter I.G.2)?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (54, 'Does this proposal include a request for Rapid Response Grants? RAPID (GPG, Chapter II.D.1)', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (55, 'Does this proposal include a request for EArly-concept Grants for Exploratory Research?  EAGER (GPG, Chapter II.D.2)', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (56, 'Is this proposal an NSF Accomplishment Based renewal? (GPG, Chapter V.B.)', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (57, 'Does the proposal contain high resolution graphics or other graphics where exact color representation is required for proper interpretation (GPG, Chapter I.G.1)?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (58, 'Is this a new full application related to a submission of a preliminary application?', 1, 'YNX', 'String', 1, sysdate, 'admin', 4, 1, 'I')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (59, 'Did you ensure both merit review criteria are described as an integral part of the narrative?  See GPG Chapter II.C.2.d(I).2.', 1, 'YNX', 'String', 1, sysdate, 'admin', 4, 1, 'I')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (60, 'Does your proposal include funding to support postdoctoral researcher(s)?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'I')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (61, 'Did you include a section describing the mentoring activities provided for the postdoctoral researcher(s) within the 15 page Project Description?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'I')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (62, 'Results from prior NSF support: Has the PI or any co-PI identified on the project received NSF funding in the past five (5) years?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'I')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (63, 'If there was more than one award, did you include the required information in the project narrative? See GPG Chapter II.C.2.d.(iii).', 1, 'YNX', 'String', 1, sysdate, 'admin', 4, 1, 'I')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (64, 'Is the Project Narrative self-contained? NSF PI''s are cautioned that URLs (Internet addresses) that provide information necessary to the review of the application should not be used because reviewers are under no obligation to view such sites.  See GPG Chapter II.C.2.d.(ii).', 1, 'YNX', 'String', 1, sysdate, 'admin', 4, 1, 'I')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (65, 'Did you include the required information on human resources development at the postdoctoral, graduate and undergraduate levels?', 1, 'YNX', 'String', 1, sysdate, 'admin', 4, 1, 'I')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (99, 'Please select the type of degree sought during the proposed award, from the list of options provided. If the degree being sought does not appear on the list, please select the most appropriate "other" degree type from the list.', 1, 'Search', 'Graduate Level Degree 1-2', 'String', 8, 'VALUELIST', sysdate, 'admin', 5, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (100, 'Please provide the specific Other (OTH) degree type here', 1, 'Text', 'String', 10, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (101, 'Will any civil service personnel work on this project?', 1, 'YN', 'String', 1, sysdate, 'admin', 6, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (102, 'Please record the total number of civil service FTEs by Government fiscal year in the upcoming questions. Up to six (6) fiscal years can be detailed on this form.', 1, 'YN', 'String', 1, sysdate, 'admin', 6, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (103, 'Select a Government Fiscal Year from the list to report the FTE''s  (Form version 1-0 supports years 2006-2021)', 1, 'Search', 'Fiscal_Year', 'String', 4, 'VALUELIST', sysdate, 'admin', 6, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (104, 'Enter the Number of FTE''s for the selected Fiscal Year.', 1, 'Text', 'Number', 4, sysdate, 'admin', 6, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (105, 'Is there another year to report?', 1, 'YN', 'String', 1, sysdate, 'admin', 6, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (106, 'Does this project have the potential to affect historic, archeological, or traditional cultural sites (such as Native American burial or ceremonial grounds) or historic objects (such as an historic aircraft or spacecraft)?', 1, 'YN', 'String', 1, sysdate, 'admin', 6, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (107, 'Briefly explain.', 1, 'Text', 'String', 300, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (108, 'Does this proposed project involve any international participation, either non-U.S. employees or non-U.S. organizations, providing support for facilities, equipment, etc.? (see opportunity instructions for details)', 1, 'YN', 'String', 1, sysdate, 'admin', 6, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (109, 'Please select all that are provided with support (PI, Co-I, Collaborator, Equipment, Facility)', 5, 'Search', 'PROPOSAL_SUPPORT_lIST', 'String', 25, 'VALUELIST', sysdate, 'admin', 6, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (110, 'Is the principal investigator participating in this project as an employee of the U.S. Government?', 1, 'YN', 'String', 1, sysdate, 'admin', 7, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (111, 'Select a U.S. Government Agency:', 1, 'Search', 'Agency_US GOV', 'String', 3, 'VALUELIST', sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (112, 'Is the principal investigator participating in this project as an employee of a foreign organization?', 1, 'YN', 'String', 1, sysdate, 'admin', 7, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (113, 'Enter the total dollar amount requested ($)', 1, 'Text', 'Number', 6, sysdate, 'admin', 7, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (114, 'Does this application reflect a change in principal investigator/program director from that indicated on a previous application?', 1, 'YN', 'String', 1, sysdate, 'admin', 8, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (115, 'Search and select the former PD/PI', 1, 'Search', 'Number', 6, 'ROLODEXSEARCH', sysdate, 'admin', 8, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (116, 'Does this application reflect a change in grantee institution from that indicated on a previous application?', 1, 'YN', 'String', 1, sysdate, 'admin', 8, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (117, 'Search and select the former Institution from the Organization records', 1, 'Search', 'Number', 6, 'ORGANIZATIONSEARCH', sysdate, 'admin', 8, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (118, 'Is this a Renewal Application?', 1, 'YN', 'String', 1, sysdate, 'admin', 8, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (119, 'Check "No" if no inventions were conceived or reduced to practice during the course of work under this project.  Check "Yes" if any inventions were conceived or reduced to practice during the previous period of support.', 1, 'YN', 'String', 1, sysdate, 'admin', 8, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (120, 'If "Yes" (inventions were concieved or reduced to practice) indicate Yes or No as to whether this information has been reported previously to the PHS or to the applicant organization official responsible for patent matters.', 1, 'YN', 'String', 1, sysdate, 'admin', 8, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (121, 'If this application does not result in an award, is the Government permitted to disclose the title of your proposed project, and the name, address, telephone number and e-mail address of the official signing for the applicant organization, to organizations that may be interested in contacting you for further information (e.g., possible collaborations, investment)?', 1, 'YN', 'String', 1, sysdate, 'admin', 8, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (122, 'Is proprietary/privileged information included in the application?', 1, 'YN', 'String', 1, sysdate, 'admin', 10, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (123, 'Does this project have an actual or potential impact on the environment?', 1, 'YN', 'String', 1, sysdate, 'admin', 10, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (124, 'If this project has an actual or potential impact on the environment, has an exemption been authorized or a environmental impact statement (EIS) been performed?', 1, 'YN', 'String', 1, sysdate, 'admin', 10, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (125, 'Is the research performance site designated, or eligible to be designated, as a historic place?', 1, 'YN', 'String', 1, sysdate, 'admin', 10, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (126, 'Does this project involve activities outside of the United States or partnerships with international collaborators?', 1, 'YN', 'String', 1, sysdate, 'admin', 10, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (127, 'Identify the countries', 1, 'Text', 'String', 300, sysdate, 'admin', 10, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (128, 'Is this application being submitted to other agencies?', 1, 'YN', 'String', 1, sysdate, 'admin', 11, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (129, 'Is the proposal subject to review by state executive order 12372 process?', 1, 'YN', 'String', 1, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (130, 'If Yes: Please provide the date the application was made available for review (submitted to the state). Enter in MM/DD/YYYY format.', 1, 'Text', 'Date', 0, sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, LOOKUP_NAME, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, LOOKUP_GUI, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (131, 'If No: Is the program not selected for review or not covered by  E.O. 12372? Select a response.', 1, 'Search', 'STATE_EO_12372', 'String', 25, 'VALUELIST', sysdate, 'admin', 4, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (132, 'Is the PI a New NIH Investigator?', 1, 'YN', 'String', 1, sysdate, 'admin', 9, 1, 'A')
/
Insert into TEMP_QUESTION
   (QUESTION_ID, QUESTION, MAX_ANSWERS, VALID_ANSWER, ANSWER_DATA_TYPE, ANSWER_MAX_LENGTH, UPDATE_TIMESTAMP, UPDATE_USER, GROUP_TYPE_CODE, VERSION_NUMBER, STATUS)
 Values
   (133, 'Is assistance being requested under a program that gives special consideration to novice applicants? Yes, No, or Not Applicable?', 1, 'YNX', 'String', 1, sysdate, 'admin', 13, 1, 'A')
/
COMMIT
/
Insert into TEMP_QUESTIONNAIRE
   (QUESTIONNAIRE_ID, NAME, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, IS_FINAL, VERSION_NUMBER)
 Values(5, 'GG S2S Forms', 'Multiple Grants.gov forms:
NASA Other Project Information, NASA PI/AOR Data Sheet, PHS 398 Checklist, PHS 398 Cover Page Supplement, RR Other Project Information, SF 424 R&R, SF 424, ED SF424 Supplement', sysdate, 'admin', 'Y', 1)
/
COMMIT
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 2, 37, 0, 'N', sysdate, 'admin', 10, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 3, 38, 37, 'Y', 'ENDS WITH', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 5, 39, 0, 'N', sysdate, 'admin', 11, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 6, 40, 39, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 7, 41, 40, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 101, 1, 0, 'N', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 102, 2, 1, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 103, 15, 14, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 103, 18, 17, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 103, 6, 5, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 103, 3, 2, 'N', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 103, 9, 8, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 103, 12, 11, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 104, 4, 2, 'N', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 104, 19, 17, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 104, 13, 11, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 104, 7, 5, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 104, 16, 14, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 104, 10, 8, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 105, 17, 16, 'N', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 105, 8, 7, 'N', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 105, 5, 4, 'N', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 105, 14, 13, 'N', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 105, 11, 10, 'N', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 106, 20, 0, 'N', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 107, 52, 50, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 107, 45, 44, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 107, 47, 46, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 107, 24, 22, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 107, 21, 20, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 107, 49, 48, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 108, 22, 0, 'N', sysdate, 'admin', 3, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 109, 23, 22, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 110, 25, 0, 'N', sysdate, 'admin', 4, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 111, 54, 53, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 111, 26, 25, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 112, 28, 0, 'N', sysdate, 'admin', 5, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 113, 27, 25, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 114, 29, 0, 'N', sysdate, 'admin', 6, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 115, 30, 29, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 116, 31, 0, 'N', sysdate, 'admin', 7, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 117, 32, 31, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 118, 33, 0, 'N', sysdate, 'admin', 8, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 119, 34, 33, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 120, 35, 34, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 121, 36, 0, 'N', sysdate, 'admin', 9, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 122, 43, 0, 'N', sysdate, 'admin', 13, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 123, 44, 0, 'N', sysdate, 'admin', 14, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 124, 46, 44, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 125, 48, 0, 'N', sysdate, 'admin', 15, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 126, 50, 0, 'N', sysdate, 'admin', 16, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 127, 51, 50, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 128, 53, 0, 'N', sysdate, 'admin', 17, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 129, 55, 0, 'N', sysdate, 'admin', 18, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 130, 56, 55, 'Y', 'EQUAL TO', 'Y', sysdate, 'admin', 1, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, CONDITION, CONDITION_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 131, 57, 55, 'Y', 'EQUAL TO', 'N', sysdate, 'admin', 2, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 132, 42, 0, 'N', sysdate, 'admin', 12, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 132, 58, 0, 'N', sysdate, 'admin', 19, 1, 1)
/
Insert into TEMP_QUESTIONNAIRE_QUESTIONS
   (QUESTIONNAIRE_ID, QUESTION_ID, QUESTION_NUMBER, PARENT_QUESTION_NUMBER, CONDITION_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, QUESTION_SEQ_NUMBER, QUESTION_VERSION_NUMBER, QUESTIONNAIRE_VERSION_NUMBER)
 Values
   (5, 133, 59, 0, 'N', sysdate, 'admin', 20, 1, 1)
/
COMMIT
/
Insert into TEMP_QUESTIONNAIRE_USAGE
   (MODULE_ITEM_CODE, MODULE_SUB_ITEM_CODE, QUESTIONNAIRE_ID, RULE_ID, QUESTIONNAIRE_LABEL, UPDATE_TIMESTAMP, UPDATE_USER, VERSION_NUMBER, IS_MANDATORY)
 Values
   (3, 0, 5, 0, 'S2S FAT & Flat Questionnaire', sysdate, 'admin', 1, 'N')
/
COMMIT
/
DECLARE
ls_seq_status VARCHAR2(2):='C';
ls_valid_answer VARCHAR2(20);
li_quest_typ_id  NUMBER(12,0);
li_quest_ref_id NUMBER(12,0);
li_quest_id NUMBER(6,0);
ls_lookup_class  VARCHAR2(100);
ls_lookup_gui VARCHAR2(50);
ls_loopup_return VARCHAR2(30);
ls_lookup_name VARCHAR2(50);
li_ver_nbr number:=1;
li_questionnaire_id number;
li_questionnaire_ref_id number;
li_questionnaire_usg_id number;
li_questionnaire number;
li_questionnaire_seq_num number;
li_q_ansHeader_id number;
li_rule_id NUMBER(6,0);
li_qnr_cid number;
li_questionnaire_ans_id number;
li_condition_type VARCHAR2(3);
li_condition VARCHAR2(50);
li_questionnaire_quest_id number;
li_quest_expl_id number;
li_qnir number;
li_q_ref_id number;
li_count_qst number;
li_count_qst_expl number;
CURSOR c_qid is
select coe.* from TEMP_QUESTION coe;
r_qid c_qid%ROWTYPE;
BEGIN
     IF c_qid%ISOPEN THEN
        CLOSE c_qid;
     END IF;
     OPEN c_qid;
     LOOP
     FETCH c_qid INTO r_qid;
      EXIT WHEN c_qid%NOTFOUND;
   SELECT  SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL INTO li_quest_ref_id FROM DUAL; 
   li_quest_id:=r_qid.QUESTION_ID;
   ls_valid_answer:=UPPER(TRIM(r_qid.VALID_ANSWER));    
   IF ls_valid_answer='YN' THEN
    li_quest_typ_id:=1;
   ELSIF  ls_valid_answer='YNX' THEN
     li_quest_typ_id:=2;
   ELSIF  ls_valid_answer='SEARCH' THEN
     li_quest_typ_id:=6;
   ELSIF  ls_valid_answer='TEXT' THEN
          if    UPPER(TRIM(r_qid.ANSWER_DATA_TYPE))='DATE' then
                li_quest_typ_id:=4;
          elsif UPPER(TRIM(r_qid.ANSWER_DATA_TYPE))='NUMBER' then  
                li_quest_typ_id:=3;
          else
                li_quest_typ_id:=5; 
          end if;      
   END IF;   
     ls_lookup_gui:=UPPER(TRIM(r_qid.LOOKUP_GUI));
     ls_lookup_name:=TRIM(r_qid.LOOKUP_NAME);     
     IF ls_lookup_gui ='VALUELIST' THEN
        ls_lookup_class:='org.kuali.kra.bo.ArgValueLookup';
                 IF    ls_lookup_name='Graduate Level Degree' THEN
                       ls_loopup_return:='GraduateLevelDegree';
                 ELSIF ls_lookup_name='Field of Training-Sub Category' THEN
                       ls_loopup_return:='FieldOfTraining-SubCategory';
                 ELSIF ls_lookup_name ='Field of Training-Broad' THEN
                       ls_loopup_return:='FieldOfTraining-Broad';
                 ELSIF ls_lookup_name='Kirschstein-NRSA support level' THEN
                       ls_loopup_return:='Kirschstein-NRSASupportLevel';
                 ELSIF ls_lookup_name='Kirschstein-NRSA Award TYPE' THEN
                       ls_loopup_return:='Kirschstein-NRSAAwardTYPE';  
                 ELSIF ls_lookup_name='Academic Appointment Period' THEN
                       ls_loopup_return:='AcademicAppointmentPeriod'; 
                ELSIF  ls_lookup_name='Agency_US GOV' THEN
                       ls_loopup_return:='Agency_US GOV'; 
                ELSIF  ls_lookup_name='PROPOSAL_SUPPORT_lIST' THEN
                       ls_loopup_return:='PROPOSAL_SUPPORT_lIST'; 
                ELSIF  ls_lookup_name='STATE_EO_12372' THEN
                       ls_loopup_return:='STATE_EO_12372'; 
                 ELSE
                       ls_loopup_return:=NULL; 
                 END IF;         	
     ELSIF  ls_lookup_gui='CODETABLE' THEN    
                 IF    ls_lookup_name='STATE' THEN
                       ls_lookup_class:='org.kuali.rice.kns.bo.StateImpl';
                       ls_loopup_return:='postalStateName';
                 ELSIF ls_lookup_name='COUNTRY' THEN
                       ls_lookup_class:='org.kuali.rice.kns.bo.CountryImpl';
                       ls_loopup_return:='postalCountryName';     
                 ELSE
                       ls_lookup_class:=NULL;
                       ls_loopup_return:=NULL;  
                 END IF;
    ELSIF  ls_lookup_gui='ROLODEXSEARCH' THEN     
           ls_lookup_class:='org.kuali.kra.bo.NonOrganizationalRolodex';
           ls_loopup_return:='rolodexId';
    ELSIF  ls_lookup_gui='ORGANIZATIONSEARCH' THEN     
           ls_lookup_class:='org.kuali.kra.bo.Organization';
           ls_loopup_return:='organizationName';       
    ELSE
           ls_lookup_class:=NULL;
           ls_loopup_return:=NULL;           
     END IF;     
    INSERT INTO QUESTION(QUESTION_REF_ID,QUESTION_ID,SEQUENCE_NUMBER,SEQUENCE_STATUS,QUESTION,STATUS,GROUP_TYPE_CODE,QUESTION_TYPE_ID,LOOKUP_CLASS,LOOKUP_RETURN,DISPLAYED_ANSWERS,MAX_ANSWERS,ANSWER_MAX_LENGTH,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID)
    VALUES(li_quest_ref_id,li_quest_id,r_qid.VERSION_NUMBER,ls_seq_status,r_qid.QUESTION,r_qid.STATUS,r_qid.GROUP_TYPE_CODE,li_quest_typ_id,ls_lookup_class,ls_loopup_return,null,r_qid.MAX_ANSWERS,r_qid.ANSWER_MAX_LENGTH,r_qid.UPDATE_TIMESTAMP,r_qid.UPDATE_USER,li_ver_nbr,SYS_GUID());
    END LOOP;
    CLOSE c_qid;
  END;
/
DECLARE
ls_seq_status VARCHAR2(2):='C';
ls_valid_answer VARCHAR2(20);
li_quest_typ_id  NUMBER(12,0);
li_quest_ref_id NUMBER(12,0);
li_quest_id NUMBER(6,0);
ls_lookup_class  VARCHAR2(100);
ls_lookup_gui VARCHAR2(50);
ls_loopup_return VARCHAR2(30);
ls_lookup_name VARCHAR2(50);
li_ver_nbr number:=1;
li_questionnaire_id number;
li_questionnaire_ref_id number;
li_questionnaire_usg_id number;
li_questionnaire number;
li_questionnaire_seq_num number;
li_q_ansHeader_id number;
li_rule_id NUMBER(6,0);
li_qnr_cid number;
li_questionnaire_ans_id number;
li_condition_type VARCHAR2(3);
li_condition VARCHAR2(50);
li_questionnaire_quest_id number;
li_qnr_quest_ref_id number;
li_quest_expl_id number;
ls_module_item_key VARCHAR2(20);
     li_count_qnr number;
     li_count_qnr_qst number;
     li_count_qnr_usg number;
     li_count_qnr_ans number;
     li_count_qnr_ans_hdr number;     
ls_condition_value	VARCHAR2(2000);
li_condtion_check number;
CURSOR c_questionnaire IS
SELECT * FROM TEMP_QUESTIONNAIRE;
r_questionnaire c_questionnaire%ROWTYPE;
CURSOR c_usage IS
SELECT * FROM TEMP_QUESTIONNAIRE_USAGE;
r_usage c_usage%ROWTYPE;
CURSOR c_qnr IS
SELECT QUESTIONNAIRE_ID,QUESTION_NUMBER,QUESTIONNAIRE_VERSION_NUMBER,QUESTION_ID,PARENT_QUESTION_NUMBER,CONDITION_FLAG,CONDITION
,CONDITION_VALUE,UPDATE_TIMESTAMP,UPDATE_USER,QUESTION_SEQ_NUMBER,QUESTION_VERSION_NUMBER 
FROM TEMP_QUESTIONNAIRE_QUESTIONS;
r_qnr c_qnr%ROWTYPE; 
BEGIN           
                     IF c_questionnaire%ISOPEN THEN
                        CLOSE c_questionnaire;
                     END IF;                     
                     OPEN c_questionnaire;
                     LOOP
                     FETCH c_questionnaire INTO r_questionnaire;
                     EXIT WHEN c_questionnaire%NOTFOUND;               
                     
                      SELECT  SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL INTO li_questionnaire_ref_id FROM DUAL;
                   --   SELECT SEQ_QUESTIONNAIRE_ID.NEXTVAL INTO li_questionnaire FROM DUAL;                     
                      li_questionnaire_seq_num:= r_questionnaire.VERSION_NUMBER; 
                     
                     INSERT INTO QUESTIONNAIRE(QUESTIONNAIRE_REF_ID,QUESTIONNAIRE_ID,SEQUENCE_NUMBER,NAME,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,IS_FINAL,VER_NBR,OBJ_ID,FILE_NAME,TEMPLATE,DOCUMENT_NUMBER)
                     VALUES(li_questionnaire_ref_id,r_questionnaire.QUESTIONNAIRE_ID,li_questionnaire_seq_num,r_questionnaire.NAME,r_questionnaire.DESCRIPTION,r_questionnaire.UPDATE_TIMESTAMP,r_questionnaire.UPDATE_USER,r_questionnaire.IS_FINAL,li_ver_nbr,SYS_GUID(),r_questionnaire.FILE_NAME,r_questionnaire.TEMPLATE,NULL);
                      END LOOP;
                     CLOSE c_questionnaire;                     
              IF c_qnr%ISOPEN THEN
                  CLOSE c_qnr;
               END IF;
               
               OPEN c_qnr;
               LOOP
               FETCH c_qnr INTO r_qnr;
               EXIT WHEN c_qnr%NOTFOUND;
                ls_condition_value:=r_qnr.CONDITION_VALUE;
                li_condition := UPPER(TRIM(r_qnr.CONDITION));
                      IF li_condition='CONTAINS' THEN
                       li_condition_type:='1';
                      ELSIF  li_condition='BEGIN WITH' THEN
                       li_condition_type:='2';
                      ELSIF  li_condition='ENDS WITH' THEN
                     li_condition_type:='3';
                      ELSIF  li_condition='EQUAL TO' THEN
                     li_condition_type:='7';
                      ELSIF  li_condition='NOT EQUAL' THEN
                     li_condition_type:='8';
                      ELSIF  li_condition='GREATER THAN' THEN
                     li_condition_type:='10';
                      ELSIF  li_condition='LESS THAN' THEN
                     li_condition_type:='5';
                      ELSIF  li_condition='GREATER THAN EQUAL' THEN
                     li_condition_type:='9';
                      ELSIF  li_condition='LESS THAN EQUAL' THEN
                     li_condition_type:='6';
                        END IF; 
                        if li_condition_type='7' then
                        begin
                        li_condtion_check:=to_number(ls_condition_value);                         
                        exception when others then
                        li_condition_type:='4';
                        end;
                        end if;
                        
                        if li_condition_type='8' then
                        begin
                        li_condtion_check:=to_number(ls_condition_value);                         
                        exception when others then
                        li_condition_type:='4';
                        end;
                        end if;
                SELECT  SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL INTO li_questionnaire_quest_id FROM DUAL;           
                
                SELECT QUESTION_REF_ID INTO li_quest_ref_id FROM QUESTION WHERE QUESTION_ID=r_qnr.QUESTION_ID AND SEQUENCE_NUMBER=r_qnr.QUESTION_VERSION_NUMBER;               
                
               select QUESTIONNAIRE_REF_ID into li_questionnaire_ref_id  from QUESTIONNAIRE  where QUESTIONNAIRE_ID=r_qnr.QUESTIONNAIRE_ID and SEQUENCE_NUMBER=r_qnr.QUESTIONNAIRE_VERSION_NUMBER;
                
               INSERT INTO QUESTIONNAIRE_QUESTIONS(QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,CONDITION_FLAG,CONDITION_TYPE,CONDITION_VALUE,UPDATE_TIMESTAMP,UPDATE_USER,QUESTION_SEQ_NUMBER,VER_NBR,OBJ_ID)
               VALUES(li_questionnaire_quest_id,li_questionnaire_ref_id,li_quest_ref_id,r_qnr.QUESTION_NUMBER,r_qnr.PARENT_QUESTION_NUMBER,r_qnr.CONDITION_FLAG,li_condition_type,r_qnr.CONDITION_VALUE,r_qnr.UPDATE_TIMESTAMP,r_qnr.UPDATE_USER,r_qnr.QUESTION_SEQ_NUMBER,li_ver_nbr,SYS_GUID());               
       
                 END LOOP; 
               CLOSE c_qnr;
               IF c_usage%ISOPEN THEN
                        CLOSE c_usage;
                     END IF;
                     OPEN c_usage;
                     LOOP
                     FETCH c_usage INTO r_usage;
                     EXIT WHEN c_usage%NOTFOUND;                     
                      SELECT  SEQ_QUESTIONNAIRE_REF_ID.NEXTVAL INTO li_questionnaire_usg_id FROM DUAL;
                      select QUESTIONNAIRE_REF_ID into li_questionnaire_ref_id  from QUESTIONNAIRE  where QUESTIONNAIRE_ID=r_usage.QUESTIONNAIRE_ID and SEQUENCE_NUMBER=r_usage.VERSION_NUMBER;
                     INSERT INTO QUESTIONNAIRE_USAGE(QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,IS_MANDATORY)                   
                     VALUES(li_questionnaire_usg_id,r_usage.MODULE_ITEM_CODE,r_usage.MODULE_SUB_ITEM_CODE,li_questionnaire_ref_id,r_usage.VERSION_NUMBER,li_rule_id,r_usage.QUESTIONNAIRE_LABEL,r_usage.UPDATE_TIMESTAMP,r_usage.UPDATE_USER,li_ver_nbr,sys_guid(),r_usage.IS_MANDATORY);
                     END LOOP;
                     CLOSE c_usage;        
END;
/
drop table  TEMP_QUESTIONNAIRE_USAGE
/
drop table  TEMP_QUESTIONNAIRE_QUESTIONS
/
drop table  TEMP_QUESTIONNAIRE
/
drop table  TEMP_QUESTION
/
commit
/
