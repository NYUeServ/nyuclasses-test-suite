#!/bin/bash

echo "#########################################"
echo "Starting Automated Testing using Cucumber"
echo "#########################################"
#Initialize default variables
driver="virtualbox"
unset clean_flag
run_docker=true

#Parse Input Options
while test $# -gt 0; do
	case "$1" in
		-d|--driver)
			if [ -z "$2" ]
				then
					echo "No driver specified. Using default as virtualbox"
			else
				driver="$2"
			fi
			shift
			shift
			;;
		-c|--clean)
			echo "Warning: This will remove all docker images and remove docker machine"
			clean_flag=true
			unset run_docker
			shift
			;;
		-cr|--clean-run)
			echo "Warning: This will remove all docker images and remove docker machine"
			clean_flag=true
			run_docker=true
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
fi 

echo "Using $driver driver to run the docker machine"

#All the magic is here
docker-machine create --driver "$driver" cucumber
if [ $? -ne 0 ]; then
	docker-machine start cucumber
fi
eval $(docker-machine env cucumber)
if [[ "$clean_flag" = true ]]; then	
	docker-compose build
	if  [ -z "$run_docker" ]; then
		docker-machine stop cucumber
		exit 0;
	fi
fi
docker-compose up
docker-machine stop cucumber
eval $(docker-machine env)			#Resume to default machine and exit