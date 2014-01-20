
CREATE TABLE EXEMPT_STUDIES_CHECKLIST ( 
    EXEMPT_STUDIES_CHECKLIST_CODE VARCHAR2(4) NOT NULL, 
    DESCRIPTION VARCHAR2(2000) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL,
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);
    
ALTER TABLE EXEMPT_STUDIES_CHECKLIST
ADD CONSTRAINT PK_EXEMPT_STUDIES_CHECKLIST
PRIMARY KEY (EXEMPT_STUDIES_CHECKLIST_CODE);

CREATE OR REPLACE VIEW OSP$EXEMPT_STUDIES_CHECKLIST AS SELECT
  EXEMPT_STUDIES_CHECKLIST_CODE,
  DESCRIPTION,
  UPDATE_TIMESTAMP,
  UPDATE_USER
FROM EXEMPT_STUDIES_CHECKLIST;


INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('1', 'Educational Research Conducted in Educational Settings -  Research conducted in established or commonly accepted educational settings, involving normal educational practices, such as: i) research on regular and special education instructional strategies, or ii) research on the effectiveness of, or the comparison among instructional techniques, curricula, or classroom management methods.', sysdate,'KRADEV');

INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('2', 'Surveys/Interviews/Standardized Educational Tests/Observation of Public Behavior - Research involving the use of educational tests (cognitive, diagnostic, aptitude, achievement), survey procedures, interview procedures or observation of public behavior if: i) information obtained is recorded in such a manner that human subjects cannot be identified, directly or through identifiers linked to the subjects; or ii) any disclosure of the human subjects'' responses outside the research would not reasonably place the subjects at risk of criminal or civil liability or be damaging to the subjects'' financial standing, employability or reputation.', sysdate,'KRADEV');

INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('3', 'Surveys/ Interviews /Observation of Public Behavior - Research involving the use of educational tests (cognitive, diagnostic, aptitude, achievement), survey procedures, interview procedures or observation of public behavior if the subjects are elected or appointed public officials.', sysdate,'KRADEV');

INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('4', 'Secondary Use of Pre-Existing Data (Data must exist at the time the research is submitted for review.) - Research involving the collection or study of existing data, documents, records, pathological specimens or diagnostic specimens if :i) these sources are publicly available, or ii) the information is recorded by the investigator in such a manner that subjects cannot be identified, directly or through identifiers linked to the subjects.', sysdate,'KRADEV');

INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('5', 'Evaluation of Public Benefit or Service Program - Research and demonstration projects which are conducted by or subject to the approval of Department or Agency heads, and which are designed to study, evaluate, or otherwise examine public benefit or service programs. The following criteria must be satisfied to qualify for exemption: i) the program must deliver a public benefit (e.g. financial or medical benefits as provided under the Social Security Act) or service (e.g. social, supportive, or nutrition services as provided under the Older Americans Act);ii) project must be conducted pursuant to specific federal statutory authority; iii) there must be no statutory requirement that the project be reviewed by an IRB; iv) the project must not involve significant physical invasions or intrusions upon the privacy of participants.', sysdate,'KRADEV');

INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE, DESCRIPTION, UPDATE_TIMESTAMP,UPDATE_USER) VALUES ('6', 'Taste and Food Quality Evaluation - Taste and food quality evaluation and consumer acceptance studies: i) if wholesome foods without additive are consumed, or ii) If food is consumed that contains a food ingredient at or below the level and for a use found to be safe, or agricultural chemical or environmental contaminant at or below the level found to be safe, by the FDA or approved by the Environmental Protection Agency or the Food Safety and Inspection Service of the U.S. Department of Agriculture.', sysdate,'KRADEV');


/* Expedited Checklist */

CREATE TABLE EXPEDITED_REVIEW_CHECKLIST ( 
    EXPEDITED_REV_CHKLST_CODE VARCHAR2(4) NOT NULL, 
    DESCRIPTION VARCHAR2(2000) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL,
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);
    
ALTER TABLE EXPEDITED_REVIEW_CHECKLIST
ADD CONSTRAINT PK_EXPEDITED_REVIEW_CHECKLIST
PRIMARY KEY (EXPEDITED_REV_CHKLST_CODE);

CREATE OR REPLACE VIEW OSP$EXPEDITED_REVIEW_CHECKLIST AS SELECT
  EXPEDITED_REV_CHKLST_CODE,
  DESCRIPTION,
  UPDATE_TIMESTAMP,
  UPDATE_USER
FROM EXPEDITED_REVIEW_CHECKLIST;

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


INSERT INTO FP_DOC_TYPE_T (FDOC_TYP_CD,FDOC_NM,FDOC_TYP_ACTIVE_CD) VALUES ('ESCK','EXEMPT STUDIES CHECKLIST','Y');
INSERT INTO FP_DOC_TYPE_T (FDOC_TYP_CD,FDOC_NM,FDOC_TYP_ACTIVE_CD) VALUES ('ERCK','EXPEDITED REVIEW CHECKLIST','Y');

commit;


