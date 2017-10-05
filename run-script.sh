#!/bin/bash/bash

echo "#########################################"
echo "Starting Automated Testing using Cucumber"
echo "#########################################"
#clear all variables
unset driver
unset clean_flag

#Parse Input Options
while test $# -gt 0; do
	case "$1" in
		-d|--driver)
			if [ -z "$2" ]
				then
					echo "No driver specified"
					break
			else
				driver="$2"
			fi
			shift
			shift
			;;
		-c|--clean)
			echo "Warning: This will remove all docker images and remove docker machine"
			clean_flag=true
			shift
			;;
		*)
			echo "Unknown option $1"
			exit 1		
			;;
	esac
done

#Cleaning Phase
if [[ "$clean_flag" = true ]]; then
	docker-machine start cucumber
	eval $(docker-machine env cucumber)
	docker images -qa | xargs docker rmi -f
	docker-compose rm -fs
	docker-machine stop cucumber
	docker-machine rm -y cucumber
	if  [ -z "$driver" ]; then
		exit 0;
	fi
fi

#Check for edge cases
if [ -z "$driver" ]; then
	echo "Please provide the driver to be used. Eg: virtualbox, digitalocean, aws, etc. Usage -d 'driver_name' or --driver 'driver_name'"
	exit 1;
fi
echo "Using $driver driver to run the docker machine"

#All the magic is here
docker-machine create --driver "$driver" cucumber
if [ $? -ne 0 ]; then
	docker-machine start cucumber
fi
eval $(docker-machine env cucumber) 
docker-compose up > logs.txt  # Decide what to do with this
docker-machine stop cucumber