
CREATE OR REPLACE VIEW OSP$UNIT_HIERARCHY ( 
UNIT_NUMBER, PARENT_UNIT_NUMBER, HAS_CHILDREN_FLAG, UPDATE_TIMESTAMP, UPDATE_USER 
)
AS select  
U.UNIT_NUMBER, PARENT_UNIT_NUMBER, decode(UH.UNIT_NUMBER,null,'N','Y') as HAS_CHILDREN_FLAG, UPDATE_TIMESTAMP, UPDATE_USER
	from UNIT U,
		(select unit_number 
		from unit a
		where unit_number in (select parent_unit_number from unit)) UH
	where U.UNIT_NUMBER = UH.UNIT_NUMBER(+) ; 

	
	