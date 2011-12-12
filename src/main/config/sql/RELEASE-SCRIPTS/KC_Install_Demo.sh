#!/bin/sh

if [ ! -d LOGS ]
then
	mkdir LOGS
fi

getChoice() {
	prompt="$1"
	shift
	choices=$*
	chosen=0
	while [ -z "${chosen}" ] || [ "${chosen}" -lt 1 ] || [ "${chosen}" -gt $# ]
	do
		echo "${prompt}" 1>&2
		choiceNum=0
		for thisChoice in ${choices}
		do
			choiceNum=`expr ${choiceNum} + 1`
			echo "${choiceNum}. ${thisChoice}" 1>&2
		done
		printf 'Choice: ' 1>&2
		read chosen
	done
	echo "$choices" | cut -d' ' -f${chosen}
	echo 1>&2
	}

getAnswer() {
	answer=''
	while [ -z "${answer}" ]
	do
		printf "$1: " 1>&2
		read answer
	done
	echo "${answer}"
	echo 1>&2
	}	

mode=`getChoice 'Enter Rice Mode' BUNDLE EMBED`

dbtype=`getChoice 'Enter Database Type' ORACLE MYSQL`

un=`getAnswer 'Enter KC Database Username'`

pw=`getAnswer 'Enter KC Database Password'`

if [ "${dbtype}" = "ORACLE" ]
then
	DBSvrNm=`getAnswer 'Enter KC Database TNS Name'`
	if [ "${DBSvrNm}" = "_" ]
	then
		DBSvrNm=''
	else
		DBSvrNm="@${DBSvrNm}"
	fi
else
	DBSvrNm=`getAnswer 'Enter KC Schema Name'`
	if [ "${DBSvrNm}" = "_" ]
	then
		DBSvrNm="${un}"
	fi
fi

if [ "${mode}" = "EMBED" ]
then
	Riceun=`getAnswer 'Enter Rice Database Username'`
	Ricepw=`getAnswer 'Enter Rice Database Password'`
	if [ "${dbtype}" = "ORACLE" ]
	then
		RiceDBSvrNm=`getAnswer 'Enter Rice Database TNS Name'`
		if [ "${RiceDBSvrNm}" = "_" ]
		then
			RiceDBSvrNm=''
		else
			RiceDBSvrNm="@${RiceDBSvrNm}"
		fi
	else
		RiceDBSvrNm=`getAnswer 'Enter Rice Schema Name'`
		if [ "${RiceDBSvrNm}" = "_" ]
		then
			RiceDBSvrNm="${Riceun}"
		fi
	fi
else
	Riceun="${un}"
	Ricepw="${pw}"
	RiceDBSvrNm="${DBSvrNm}"
fi

case "${dbtype}" in
	"ORACLE")
        cd KC-RELEASE-3_1-SCRIPT
		sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC-RELEASE-3_1-Demo-ORACLE.sql		
		sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_1-Demo-ORACLE.sql
		sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1-Demo-ORACLE.sql
	            
        mv *.log ../LOGS/
        cd ..
        
        cd KC-RELEASE-3_1_1-SCRIPT   
        sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_1_1-Demo-ORACLE.sql
        
        mv *.log ../LOGS/
        cd ..
        
        cd KC-RELEASE-4_0-SCRIPT   
        sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-4_0-Demo-ORACLE.sql
        sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-4_0-Demo-ORACLE.sql
                
        mv *.log ../LOGS/
        cd .. ;;
		
	"MYSQL")
        cd KC-RELEASE-3_1-SCRIPT
		mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC-RELEASE-3_1-Demo-MYSQL.sql > KRC-RELEASE-3_1-Demo-MYSQL-Install.log 2>&1
		mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_1-Demo-MYSQL.sql > KC-RELEASE-3_1-Demo-MYSQL-Install.log 2>&1		
        mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1-Demo-MYSQL.sql > KR-RELEASE-3_1-Demo-MYSQL-Install.log 2>&1
        
        mv *.log ../LOGS/
        cd ..
        
        cd KC-RELEASE-3_1_1-SCRIPT
        mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_1_1-Demo-MYSQL.sql > KC-RELEASE-3_1_1-Demo-MYSQL-Install.log 2>&1
        
        mv *.log ../LOGS/
        cd ..
        
        cd KC-RELEASE-4_0-SCRIPT
        mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-4_0-Demo-MYSQL.sql > KC-RELEASE-4_0-Demo-MYSQL-Install.log 2>&1
        mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-4_0-Demo-MYSQL.sql > KR-RELEASE-4_0-Demo-MYSQL-Install.log 2>&1
        
        mv *.log ../LOGS/
        cd ..
esac

cd LOGS
echo 'Review log files for errors during database install.'
ls *.log



