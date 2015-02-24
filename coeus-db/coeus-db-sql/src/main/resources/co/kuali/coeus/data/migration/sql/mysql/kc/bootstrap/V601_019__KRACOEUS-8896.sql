DELIMITER /
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/
INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_ResearchPlan_2_0/ResearchPlanAttachments/ResearchStrategy', 'Research Strategy attachment is required for Research Plan.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
DELIMITER ;
