DELIMITER /
update ynq set status = 'I' where question_id in('11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','31','EC1',
'EC2','FG','G3','G4','G6','G8','G9','H1','H2','J4')
/
commit
/
DELIMITER ;
