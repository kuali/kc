delete from osp$parameter where parameter='KRADEV_SCHEDULER_SERVICE_ENABLED';
insert into osp$parameter(parameter,effective_date,value,update_timestamp,update_user) 
	values('KRA_SCHEDULER_SERVICE_ENABLED',sysdate,'1',sysdate,substr(user,1,8));
	
commit;	