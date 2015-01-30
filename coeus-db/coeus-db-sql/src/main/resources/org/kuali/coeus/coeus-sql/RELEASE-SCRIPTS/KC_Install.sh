########################################
# Kuali Coeus, a comprehensive research administration system for higher education.
# 
# Copyright 2005-2015 Kuali, Inc.
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as
# published by the Free Software Foundation, either version 3 of the
# License, or (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Affero General Public License for more details.
# 
# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
########################################
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

if [ "${mode}" = "EMBED" ]
then
	InstRice=`getChoice 'Install/Upgrade Embedded Rice Server Side (KC-related Rice data will still be loaded, regardless of response)' Y N`
	if [ "${InstRice}" = 'N' ]
	then
		echo 'WARNING: By choosing this option, it is expected that your standalone Rice is already upgraded to a current version.  If it is not, please stop this installation until that is completed.'
	fi
else
	InstRice='Y'
fi

dbtype=`getChoice 'Enter Database Type' ORACLE MYSQL`

version=`getChoice 'Enter Currently Installed Version' NEW 3.1.1 5.0 5.0.1 5.1 5.1.1 5.2 5.2.1`

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
	
		cd KC-RELEASE-0_0_0-SCRIPT
        sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-0_0_0-Upgrade-ORACLE.sql
        mv *.log ../LOGS/
        cd ..
        
        cd KC-RELEASE-3_0-CLEAN/oracle
		
		if [ "${version}" = "NEW" ]
		then
			if [ "${mode}" = "BUNDLE" ]
			then
				sqlplus "${un}"/"${pw}${DBSvrNm}" < oracle_server_rice.sql
			else
				if [ "${mode}" = "EMBED" ]
				then
					if [ "${InstRice}" = "Y" ]
					then
						sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < oracle_server_rice.sql
					fi
				fi
			fi
		fi
		
		sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < krrelease/datasql/KR_00_SEQ_BS.sql

		cd ../..
	    
	    if [ "${version}" = "NEW" ]
        then
        	cd KC-RELEASE-3_0-CLEAN/oracle
        	if [ "${InstRice}" = 'Y' ]
        	then
    			sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < oracle_server.sql
            else
            	sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < oracle_server_SR.sql
            fi
            sqlplus "${un}"/"${pw}${DBSvrNm}" < oracle_client.sql
        
			mv *.log ../../LOGS/
	        cd ../..

			cd KC-RELEASE-3_0_1-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-Release-3_0-3_0_1-Upgrade-Oracle-Install.sql
			if [ "${InstRice}" = 'Y' ]
			then
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-Release-3_0-3_0_1-Upgrade-Oracle-Install.sql
			else
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-Release-3_0-3_0_1-SR-Oracle-Install.sql
			fi
			
			mv *.log ../LOGS/
			cd .. 

			cd KC-RELEASE-3_1_SP1-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-Release-3_0_1-3_1_S1-Upgrade-Oracle-Install.sql

			
			if [ "${InstRice}" = "Y" ]
			then
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-Server-Release-1_0_3-1_0_3_1-Upgrade-Oracle-Install.sql
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-Release-3_0_1-3_1_S1-Upgrade-Oracle-Install.sql
			else
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-Release-3_0_1-3_1_S1-SR-Oracle-Install.sql
			fi
			mv *.log ../LOGS/
			cd ..
			
			cd KC-RELEASE-3_1_SP2-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC-RELEASE-3_1_SP2-Upgrade-ORACLE.sql
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_1_SP2-Upgrade-ORACLE.sql
			if [ "${InstRice}" = 'Y' ]
			then
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_SP2-Upgrade-ORACLE.sql
			else
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_SP2-SR-ORACLE.sql
			fi
			mv *.log ../LOGS/
			cd ..

			cd KC-RELEASE-3_1_SP3-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC-RELEASE-3_1_SP3-Upgrade-ORACLE.sql
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_1_SP3-Upgrade-ORACLE.sql
			if [ "${InstRice}" = 'Y' ]
			then
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_SP3-Upgrade-ORACLE.sql
			else
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_SP3-SR-ORACLE.sql
			fi
			mv *.log ../LOGS/
			cd ..
			
			cd KC-RELEASE-3_1_SP4-SCRIPT
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC-RELEASE-3_1_SP4-Upgrade-ORACLE.sql
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_1_SP4-Upgrade-ORACLE.sql
            if [ "${InstRice}" = "Y" ]
            then
                sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < RICE-1_0_3_1-1_0_3_2-Upgrade-ORACLE.sql
                sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_SP4-Upgrade-ORACLE.sql
            else
            	sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_SP4-SR-ORACLE.sql
            fi
            mv *.log ../LOGS/
            cd ..

			cd KC-RELEASE-3_1_1-SCRIPT
			sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_1_1-Upgrade-ORACLE.sql
			if [ "${InstRice}" = 'Y' ]
			then
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_1-Upgrade-ORACLE.sql
			else
				sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_1_1-SR-ORACLE.sql
			fi
			mv *.log ../LOGS/
			cd ..
		fi
		
		if [ "${version}" = "NEW" ] || [ "${version}" = '3.1.1' ]
		then
            cd KC-RELEASE-3_2-SCRIPT
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-3_2-Upgrade-ORACLE.sql
            if [ "${InstRice}" = 'Y' ]
            then
            	sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_2-Upgrade-ORACLE.sql
            else
            	sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-3_2-SR-ORACLE.sql
            fi
            mv *.log ../LOGS/
            cd ..

            cd KC-RELEASE-4_0-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC_RICE-RELEASE-4_0-Upgrade-ORACLE.sql
            fi
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC_RICE-RELEASE-4_0-Upgrade-ORACLE.sql
            if [ "${InstRice}" = "Y" ]
            then
                sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR_RICE-RELEASE-4_0-Upgrade-ORACLE.sql
                sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-4_0-Upgrade-ORACLE.sql
            else
            	sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-4_0-SR-ORACLE.sql
            fi
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-4_0-Upgrade-ORACLE.sql
            mv *.log ../LOGS/
            cd ..

            cd KC-RELEASE-5_0-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC_RICE-RELEASE-5_0-Upgrade-ORACLE.sql
            fi
            if [ "${InstRice}" = "Y" ]
            then
                sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR_RICE-RELEASE-5_0-Upgrade-ORACLE.sql
            fi
            sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-5_0-Upgrade-ORACLE.sql
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-5_0-Upgrade-ORACLE.sql
            mv *.log ../LOGS/
            cd ..
        fi 

        if [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-5_0_1-SCRIPT
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-5_0_1-Upgrade-ORACLE.sql
            sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-5_0_1-Upgrade-ORACLE.sql
            mv *.log ../LOGS/
            cd ..
        fi

        if [ "${version}" = "5.0.1" ] || [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-5_1_0-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC_RICE-RELEASE-5_1_0-Upgrade-ORACLE.sql
            fi
            if [ "${InstRice}" = "Y" ]
            then
                sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR_RICE-RELEASE-5_1_0-Upgrade-ORACLE.sql
            fi
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-5_1_0-Upgrade-ORACLE.sql
            sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-5_1_0-Upgrade-ORACLE.sql
            mv *.log ../LOGS/
            cd ..
		fi

        if [ "${version}" = "5.1" ] || [ "${version}" = "5.0.1" ] || [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-5_1_1-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC_RICE-RELEASE-5_1_1-Upgrade-ORACLE.sql
            fi
            if [ "${InstRice}" = "Y" ]
            then
                sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR_RICE-RELEASE-5_1_1-Upgrade-ORACLE.sql
            fi
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-5_1_1-Upgrade-ORACLE.sql
            sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-5_1_1-Upgrade-ORACLE.sql
            mv *.log ../LOGS/
            cd ..
		fi
		
		if [ "${version}" = "5.1.1" ] || [ "${version}" = "5.1" ] || [ "${version}" = "5.0.1" ] || [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-5_2_0-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC_RICE-RELEASE-5_2_0-Upgrade-ORACLE.sql
            fi
            if [ "${InstRice}" = "Y" ]
            then
                sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR_RICE-RELEASE-5_2_0-Upgrade-ORACLE.sql
            fi
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-5_2_0-Upgrade-ORACLE.sql
            sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-5_2_0-Upgrade-ORACLE.sql
            mv *.log ../LOGS/
            cd ..
		fi
		
		if [ "${version}" = "5.2" ] || [ "${version}" = "5.1.1" ] || [ "${version}" = "5.1" ] || [ "${version}" = "5.0.1" ] || [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-5_2_1-SCRIPT
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-5_2_1-Upgrade-ORACLE.sql
            sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-5_2_1-Upgrade-ORACLE.sql
            mv *.log ../LOGS/
            cd ..
		fi
		
		if [ "${version}" = "5.2.1" ] || [ "${version}" = "5.2" ] || [ "${version}" = "5.1.1" ] || [ "${version}" = "5.1" ] || [ "${version}" = "5.0.1" ] || [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-6_0_0-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                sqlplus "${un}"/"${pw}${DBSvrNm}" < KRC_RICE-RELEASE-6_0_0-Upgrade-ORACLE.sql
            fi
            if [ "${InstRice}" = "Y" ]
            then
                sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR_RICE-RELEASE-6_0_0-Upgrade-ORACLE.sql
            fi
            sqlplus "${un}"/"${pw}${DBSvrNm}" < KC-RELEASE-6_0_0-Upgrade-ORACLE.sql
            sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-6_0_0-Upgrade-ORACLE.sql
            mv *.log ../LOGS/
            cd ..
		fi
		
	cd KC-RELEASE-99_9_9-SCRIPT
	sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < KR-RELEASE-99_9_9-Upgrade-ORACLE.sql
	mv *.log ../LOGS/
	cd ..
			
	cd KC-RELEASE-3_0-CLEAN/oracle
	sqlplus "${Riceun}"/"${Ricepw}${RiceDBSvrNm}" < krrelease/datasql/KR_00_CLEAN_SEQ_BS.sql
        mv *.log ../../LOGS/
        cd ../.. ;;
		
	"MYSQL")
	
		cd KC-RELEASE-0_0_0-SCRIPT
        mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-0_0_0-Upgrade-MYSQL.sql > KC-RELEASE-0_0_0-Upgrade-MYSQL-Install.log 2>&1
        mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-0_0_0-Upgrade-MYSQL.sql > KR-RELEASE-0_0_0-Upgrade-MYSQL-Install.log 2>&1
        mv *.log ../LOGS/
        cd ..
	
        cd KC-RELEASE-3_0-CLEAN/mysql
        
	if [ "${version}" = "NEW" ]
	then
		if [ "${mode}" = "BUNDLE" ]
		then
			mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < mysql_server_rice.sql > KC-Release-3_0-Clean-Server-Rice-Mysql-Install.log 2>&1
		else 
			if [ "${mode}" = "EMBED" ]
			then
				if [ "${InstRice}" = "Y" ]
				then
					mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < mysql_server_rice.sql > KC-Release-3_0-Clean-Server-Rice-Mysql-Install.log 2>&1
				fi
			fi
		fi
	fi
		
        mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < krrelease/datasql/KR_00_SEQ_BS.sql > KR_SEQ_BS-Mysql-Install.log 2>&1
        
        mv *.log ../../LOGS/
        cd ../..
        
        if [ "${version}" = "NEW" ]
        then
           cd KC-RELEASE-3_0-CLEAN/mysql
           if [ "${InstRice}" = 'Y' ]
           then
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < mysql_server.sql > KC-Release-3_0-Clean-Server-Mysql-Install.log 2>&1
            else
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < mysql_server_SR.sql > KC-Release-3_0-Clean-Server-SR-Mysql-Install.log 2>&1
            fi
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < mysql_client.sql > KC-Release-3_0-Clean-Client-Mysql-Install.log 2>&1
        
    	    mv *.log ../../LOGS/
	        cd ../..
		

            cd KC-RELEASE-3_0_1-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-Release-3_0-3_0_1-Upgrade-Mysql-Install.sql > KC-Release-3_0-3_0_1-Upgrade-Mysql-Install.log 2>&1
            if [ "${InstRice}" = 'Y' ]
            then
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-Release-3_0-3_0_1-Upgrade-Mysql-Install.sql > KR-Release-3_0-3_0_1-Upgrade-Mysql-Install.log 2>&1
            else
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-Release-3_0-3_0_1-SR-Mysql-Install.sql > KR-Release-3_0-3_0_1-SR-Mysql-Install.log 2>&1
            fi
            mv *.log ../LOGS/
            cd .. 

	    cd KC-RELEASE-3_1_SP1-SCRIPT
		mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.sql > KC-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.log 2>&1
			
		if [ "${InstRice}" = 'Y' ]
		then
            		mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-Server-Release-1_0_3-1_0_3_1-Upgrade-Mysql-Install.sql > KR-Server-Release-1_0_3_1-Upgrade-Mysql-Install.log 2>&1
			mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.sql > KR-Release-3_0_1-3_1_S1-Upgrade-Mysql-Install.log 2>&1
		else
			mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-Release-3_0_1-3_1_S1-SR-Mysql-Install.sql > KR-Release-3_0_1-3_1_S1-SR-Mysql-Install.log 2>&1
		fi
            mv *.log ../LOGS/
            cd ..
            
            cd KC-RELEASE-3_1_SP2-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC-RELEASE-3_1_SP2-Upgrade-MYSQL.sql > KRC-RELEASE-3_1_SP2-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_1_SP2-Upgrade-MYSQL.sql > KC-RELEASE-3_1_SP2-Upgrade-MYSQL-Install.log 2>&1
            if [ "${InstRice}" = 'Y' ]
            then
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_SP2-Upgrade-MYSQL.sql > KR-RELEASE-3_1_SP2-Upgrade-MYSQL-Install.log 2>&1
            else
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_SP2-SR-MYSQL.sql > KR-RELEASE-3_1_SP2-SR-MYSQL-Install.log 2>&1
            fi
            mv *.log ../LOGS/
            cd ..

            cd KC-RELEASE-3_1_SP3-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC-RELEASE-3_1_SP3-Upgrade-MYSQL.sql > KRC-RELEASE-3_1_SP3-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_1_SP3-Upgrade-MYSQL.sql > KC-RELEASE-3_1_SP3-Upgrade-MYSQL-Install.log 2>&1
            if [ "${InstRice}" = 'Y' ]
            then
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_SP3-Upgrade-MYSQL.sql > KR-RELEASE-3_1_SP3-Upgrade-MYSQL-Install.log 2>&1
            else
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_SP3-SR-MYSQL.sql > KR-RELEASE-3_1_SP3-SR-MYSQL-Install.log 2>&1
            fi
            mv *.log ../LOGS/
            cd ..

		cd KC-RELEASE-3_1_SP4-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC-RELEASE-3_1_SP4-Upgrade-MYSQL.sql > KRC-RELEASE-3_1_SP4-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_1_SP4-Upgrade-MYSQL.sql  > KC-RELEASE-3_1_SP4-Upgrade-MYSQL-Install.log 2>&1
            if [ "${InstRice}" = "Y" ]
            then
                mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < RICE-1_0_3_1-1_0_3_2-Upgrade-MYSQL.sql > RICE-1_0_3_1-1_0_3_2-Upgrade-MYSQL-Install.log 2>&1
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_SP4-Upgrade-MYSQL.sql > KR-RELEASE-3_1_SP4-Upgrade-MYSQL-Install.log 2>&1
            else
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_SP4-SR-MYSQL.sql > KR-RELEASE-3_1_SP4-SR-MYSQL-Install.log 2>&1
            fi
            mv *.log ../LOGS/
            cd ..
 
            cd KC-RELEASE-3_1_1-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_1_1-Upgrade-MYSQL.sql > KC-RELEASE-3_1_1-Upgrade-MYSQL-Install.log 2>&1
            if [ "${InstRice}" = 'Y' ]
            then
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_1-Upgrade-MYSQL.sql > KR-RELEASE-3_1_1-Upgrade-MYSQL-Install.log 2>&1
            else
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_1_1-SR-MYSQL.sql > KR-RELEASE-3_1_1-SR-MYSQL-Install.log 2>&1
            fi
            mv *.log ../LOGS/
            cd ..
		fi
		
		if [ "${version}" = "NEW" ] || [ "${version}" = '3.1.1' ]
		then
            cd KC-RELEASE-3_2-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-3_2-Upgrade-MYSQL.sql > KC-RELEASE-3_2-Upgrade-MYSQL-Install.log 2>&1
            if [ "${InstRice}" = 'Y' ]
            then
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_2-Upgrade-MYSQL.sql > KR-RELEASE-3_2-Upgrade-MYSQL-Install.log 2>&1
            else
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-3_2-SR-MYSQL.sql > KR-RELEASE-3_2-SR-MYSQL-Install.log 2>&1
            fi
            mv *.log ../LOGS/
            cd ..

            cd KC-RELEASE-4_0-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC_RICE-RELEASE-4_0-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1
            fi
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC_RICE-RELEASE-4_0-Upgrade-MYSQL.sql > KC_RICE-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1
            if [ "${InstRice}" = "Y" ]
            then
                mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR_RICE-RELEASE-4_0-Upgrade-MYSQL.sql > KR_RICE-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-4_0-Upgrade-MYSQL.sql > KR-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1
            else
            	mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-4_0-SR-MYSQL.sql > KR-RELEASE-4_0-SR-MYSQL-Install.log 2>&1
            fi
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-4_0-Upgrade-MYSQL.sql > KC-RELEASE-4_0-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..

            cd KC-RELEASE-5_0-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC_RICE-RELEASE-5_0-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-5_0-Upgrade-MYSQL-Install.log 2>&1
            fi
            if [ "${InstRice}" = "Y" ]
            then
                mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR_RICE-RELEASE-5_0-Upgrade-MYSQL.sql > KR_RICE-RELEASE-5_0-Upgrade-MYSQL-Install.log 2>&1
            fi
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-5_0-Upgrade-MYSQL.sql > KC-RELEASE-5_0-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-5_0-Upgrade-MYSQL.sql > KR-RELEASE-5_0-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..
        fi

        if [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-5_0_1-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-5_0_1-Upgrade-MYSQL.sql > KC-RELEASE-5_0_1-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-5_0_1-Upgrade-MYSQL.sql > KR-RELEASE-5_0_1-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..
        fi

        if [ "${version}" = "5.0.1" ] || [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-5_1_0-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC_RICE-RELEASE-5_1_0-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-5_1_0-Upgrade-MYSQL-Install.log 2>&1
            fi
            if [ "${InstRice}" = "Y" ]
            then
                mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR_RICE-RELEASE-5_1_0-Upgrade-MYSQL.sql > KR_RICE-RELEASE-5_1_0-Upgrade-MYSQL-Install.log 2>&1
            fi
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-5_1_0-Upgrade-MYSQL.sql > KC-RELEASE-5_1_0-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-5_1_0-Upgrade-MYSQL.sql > KR-RELEASE-5_1_0-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..
        fi

        if [ "${version}" = "5.1" ] || [ "${version}" = "5.0.1" ] || [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-5_1_1-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC_RICE-RELEASE-5_1_1-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-5_1_1-Upgrade-MYSQL-Install.log 2>&1
            fi
            if [ "${InstRice}" = "Y" ]
            then
                mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR_RICE-RELEASE-5_1_1-Upgrade-MYSQL.sql > KR_RICE-RELEASE-5_1_1-Upgrade-MYSQL-Install.log 2>&1
            fi
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-5_1_1-Upgrade-MYSQL.sql > KC-RELEASE-5_1_1-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-5_1_1-Upgrade-MYSQL.sql > KR-RELEASE-5_1_1-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..
        fi
        
        if [ "${version}" = "5.1.1" ] || [ "${version}" = "5.1" ] || [ "${version}" = "5.0.1" ] || [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-5_2_0-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC_RICE-RELEASE-5_2_0-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-5_2_0-Upgrade-MYSQL-Install.log 2>&1
            fi
            if [ "${InstRice}" = "Y" ]
            then
                mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR_RICE-RELEASE-5_2_0-Upgrade-MYSQL.sql > KR_RICE-RELEASE-5_2_0-Upgrade-MYSQL-Install.log 2>&1
            fi
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-5_2_0-Upgrade-MYSQL.sql > KC-RELEASE-5_2_0-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-5_2_0-Upgrade-MYSQL.sql > KR-RELEASE-5_2_0-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..
        fi
  
        if [ "${version}" = "5.2" ] || [ "${version}" = "5.1.1" ] || [ "${version}" = "5.1" ] || [ "${version}" = "5.0.1" ] || [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-5_2_1-SCRIPT
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-5_2_1-Upgrade-MYSQL.sql > KC-RELEASE-5_2_1-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-5_2_1-Upgrade-MYSQL.sql > KR-RELEASE-5_2_1-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..
        fi
        
        if [ "${version}" = "5.2.1" ] || [ "${version}" = "5.2" ] || [ "${version}" = "5.1.1" ] || [ "${version}" = "5.1" ] || [ "${version}" = "5.0.1" ] || [ "${version}" = "5.0" ] || [ "${version}" = '3.1.1' ] || [ "${version}" = "NEW" ]
        then
            cd KC-RELEASE-6_0_0-SCRIPT
            if [ "${mode}" = "EMBED" ]
            then
                mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KRC_RICE-RELEASE-6_0_0-Upgrade-MYSQL.sql > KRC_RICE-RELEASE-6_0_0-Upgrade-MYSQL-Install.log 2>&1
            fi
            if [ "${InstRice}" = "Y" ]
            then
                mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR_RICE-RELEASE-6_0_0-Upgrade-MYSQL.sql > KR_RICE-RELEASE-6_0_0-Upgrade-MYSQL-Install.log 2>&1
            fi
            mysql -u ${un} -p${pw} -D ${DBSvrNm} -s -f < KC-RELEASE-6_0_0-Upgrade-MYSQL.sql > KC-RELEASE-6_0_0-Upgrade-MYSQL-Install.log 2>&1
            mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-6_0_0-Upgrade-MYSQL.sql > KR-RELEASE-6_0_0-Upgrade-MYSQL-Install.log 2>&1
            mv *.log ../LOGS/
            cd ..
        fi
                      
		cd KC-RELEASE-99_9_9-SCRIPT
		mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < KR-RELEASE-99_9_9-Upgrade-MYSQL.sql > KR-RELEASE-99_9_9-Upgrade-MYSQL-Install.log 2>&1
		mv *.log ../LOGS/
		cd ..
				
        cd KC-RELEASE-3_0-CLEAN/mysql
        mysql -u ${Riceun} -p${Ricepw} -D ${RiceDBSvrNm} -s -f < krrelease/datasql/KR_00_CLEAN_SEQ_BS.sql > KR_CLEAN_SEQ_BS-Mysql-Install.log 2>&1
        mv *.log ../../LOGS/
        cd ../.. ;;
esac

cd LOGS
echo 'Review log files for errors during database install.'
ls *.log



