
CREATE OR REPLACE VIEW OSP$UNIT   ( 
UNIT_NUMBER, UNIT_NAME, ADMINISTRATIVE_OFFICER, OSP_ADMINISTRATOR, UNIT_HEAD, DEAN_VP, 
OTHER_INDIVIDUAL_TO_NOTIFY, ORGANIZATION_ID, UPDATE_TIMESTAMP, UPDATE_USER 
)
AS select  
	   U.UNIT_NUMBER, U.UNIT_NAME, 
	   AO.person_id as ADMINISTRATIVE_OFFICER, 
	   OA.person_id as OSP_ADMINISTRATOR, 
	   UH.person_id as UNIT_HEAD, 
	   DV.person_id as DEAN_VP, 
	   OI.person_id as OTHER_INDIVIDUAL_TO_NOTIFY, 
	   ORGANIZATION_ID, UPDATE_TIMESTAMP, UPDATE_USER
	from UNIT U,
	         (select unit_number,person_id from unit_administrator where UNIT_ADMINISTRATOR_TYPE_CODE=1) AO,
	         (select unit_number,person_id from unit_administrator where UNIT_ADMINISTRATOR_TYPE_CODE=2) OA,
	         (select unit_number,person_id from unit_administrator where UNIT_ADMINISTRATOR_TYPE_CODE=3) UH,
	         (select unit_number,person_id from unit_administrator where UNIT_ADMINISTRATOR_TYPE_CODE=4) DV,
	         (select unit_number,person_id from unit_administrator where UNIT_ADMINISTRATOR_TYPE_CODE=5) OI
	where U.UNIT_NUMBER = AO.UNIT_NUMBER(+)
	           and U.UNIT_NUMBER = OA.UNIT_NUMBER(+)
	           and U.UNIT_NUMBER = UH.UNIT_NUMBER(+)
	           and U.UNIT_NUMBER = DV.UNIT_NUMBER(+)
	           and U.UNIT_NUMBER = OI.UNIT_NUMBER(+);
	
	