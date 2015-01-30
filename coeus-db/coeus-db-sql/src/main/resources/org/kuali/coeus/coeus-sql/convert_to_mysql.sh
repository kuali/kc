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

if [ $# -lt 1 ]
then
	echo "Usage: $0 version"
	echo "   Ex: $0 3.1"
	exit
fi

handle_sequences()
{
	while [ "$#" -gt 0 ]
	do
		thisCmd="$1"
		shift
		seqName="`echo ${thisCmd} | cut -d'.' -f1`"
		nextCmd="${seqName}.NEXTVAL"
		numNexts=`for otherCmd in $*; do echo ${otherCmd}; done | grep -ic "${nextCmd}"`
		if [ `echo ${thisCmd} | grep -c '.NEXTVAL'` -gt 0 ]
		then
			echo "INSERT INTO ${seqName} VALUES(NULL)"
			echo "/"
		fi
		minusStr=''
		if [ "${numNexts}" -gt 0 ]
		then
			minusStr=' - 1 ';
		fi
		sed -i "s/${thisCmd}/(SELECT (MAX(ID)${minusStr}) FROM ${seqName})/i" cmd.sql
		
	done
}

process_sequences()
{
	thisLine=''
	if [ -f cmd.sql ]
	then
		rm cmd.sql
	fi
	while read thisLine
	do
		echo ${thisLine} >> cmd.sql
		if [ `grep -c '/' cmd.sql` -gt 0 ]
		then
			seqCommands=`grep -o -E '[A-Z_a-z]*\.[A-Z/a-z]*' cmd.sql | grep -i -e '.NEXTVAL' -e '.CURRVAL'`
			if [ -n "${seqCommands}" ]
			then
				handle_sequences ${seqCommands}
			fi
			cat cmd.sql
			rm cmd.sql
		fi
	done
	if [ -f cmd.sql ]
	then
		cat cmd.sql
		rm cmd.sql
	fi	
				
}

process_sequence_creation()
{
	thisLine=''
	if [ -f cmd.sql ]
	then
		rm cmd.sql
	fi
	while read thisLine
	do
		echo ${thisLine} >> cmd.sql
		if [ `grep -c '/' cmd.sql` -gt 0 ]
		then
			if [ `grep -ci 'create sequence' cmd.sql` -gt 0 ]
			then
				seq_name=`grep -i 'create sequence' cmd.sql | tr -s ' ' | cut -d' ' -f3`
				
				echo "CREATE TABLE ${seq_name} (" > cmd.sql
				echo '		id bigint(19) not null auto_increment, primary key (id)' >> cmd.sql
				echo ') ENGINE MyISAM' >> cmd.sql
				echo '/' >> cmd.sql
				echo "ALTER TABLE ${seq_name} auto_increment = 1" >> cmd.sql
				echo '/' >> cmd.sql
			fi
			cat cmd.sql
			rm cmd.sql
		fi
	done
	if [ -f cmd.sql ]
	then
		cat cmd.sql
		rm cmd.sql
	fi	
				
}

convert_file()
{
	oracleFile="$1"
	mysqlFile="$2"
	echo "Converting ${oracleFile}..."
	if [ `grep -c 'DELIMITER /' ${oracleFile}` -lt 1 ]
	then
		echo 'DELIMITER /' > ${mysqlFile}
	fi
	cat ${oracleFile} >> ${mysqlFile}
	if [ `grep -c 'DELIMITER ;' ${mysqlFile}` -lt 1 ]
	then
		echo ' ' >> ${mysqlFile}
		echo "DELIMITER ;" >> ${mysqlFile}
	fi
	sed -i -e s/SYS_GUID/UUID/ig -e s/SYSDATE/NOW\(\)/ig -e 's/to_date(/STR_TO_DATE(/ig' -e 's/YYYYMMDD/%Y%m%d/ig' -e 's/HH24MI/%H%i/ig' ${mysqlFile}
	if [ `grep -ci '.NEXTVAL' ${mysqlFile}` -gt 0 ]
	then
		cat ${mysqlFile} | process_sequences > ${mysqlFile}.tmp
		mv ${mysqlFile}.tmp ${mysqlFile}
	fi
	if [ `grep -ci 'create sequence' ${mysqlFile}` -gt 0 ]
	then
		cat ${mysqlFile} | process_sequence_creation > ${mysqlFile}.tmp
		mv ${mysqlFile}.tmp ${mysqlFile}
	fi
	if [ `echo "${mysqlFile}" | grep -c '_TBL_'` -gt 0 ] && [ `grep -ci 'NUMBER(' "${mysqlFile}"` -gt 0 ]
	then
		sed -i -e 's/NUMBER(/DECIMAL(/gi' -e 's/VARCHAR2/VARCHAR/gi' "${mysqlFile}"
	fi
}

version_num="$1"
oracle_base="`pwd`/current/${version_num}"
if [ ! -d "${oracle_base}" ]
then
	echo "${oracle_base} does not exist!"
	exit
fi

mysql_base="`pwd`/current_mysql/${version_num}"

if [ ! -d "${mysql_base}" ]
then
	mkdir -p "${mysql_base}"
fi

#convert_file ${oracle_base}/dml/$2 /tmp/test.sql

cd ${mysql_base}
dir_list='sequences tables views dml constraints'
for thisDir in ${dir_list}
do
	if [ -d "${oracle_base}/${thisDir}" ]
	then
		if [ ! -d ${thisDir} ]
		then
			mkdir ${thisDir}
		fi
		for srcFile in `find ${oracle_base}/${thisDir} -maxdepth 1 -type f`
		do
			shortFile=`basename ${srcFile}`
			if [ ! -f ${thisDir}/${shortFile} ]
			then
				convert_file ${srcFile} ${mysql_base}/${thisDir}/${shortFile}
			fi
		done
	fi
done
