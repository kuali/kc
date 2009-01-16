CREATE OR REPLACE VIEW OSP$PERSON_TRAINING AS SELECT 
	PERSON_ID, 
	TRAINING_NUMBER, 
	TRAINING_CODE, 
	DATE_REQUESTED, 
	DATE_SUBMITTED, 
	DATE_ACKNOWLEDGED, 
	FOLLOWUP_DATE, 
	SCORE, 
	COMMENTS, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM PERSON_TRAINING
/

CREATE OR REPLACE VIEW OSP$RESEARCH_AREAS AS SELECT 
	RESEARCH_AREA_CODE, 
	PARENT_RESEARCH_AREA_CODE, 
	HAS_CHILDREN_FLAG, 
	DESCRIPTION, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM RESEARCH_AREAS
/

CREATE OR REPLACE VIEW OSP$RISK_LEVEL AS SELECT 
	RISK_LEVEL_CODE, 
	DESCRIPTION, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM RISK_LEVEL
/

