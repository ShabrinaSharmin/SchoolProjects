#!/bin/bash
#Lab9
#Shabrina Sharmin
#student Id:040927453
#CST 8102_300
#myScript.sh
#This script prompts user data in order to manage groups and user management such as adding  user,s account, assigning user groups, changing user's shell .

choice=n     #initialize to dummy value
while [ "$choice" != "Q" ] && [ "$choice" != "q" ]
	do
		clear
		echo "Choose one of the following options: "
		echo "A to Create a user account"
		echo "B to Delete a user account"
		echo "C to Change supplementary Group for a user account"
		echo "D to Change Initial Group for a user account"
		echo "E to Change default login shell for a user account"
		echo "F to Change account expiration date for a user account"	
		echo "Q to quit"
		echo -e "What would you like to do?: \c"
		read choice
		if [ "$choice" == "A" ] || [ "$choice" == "a" ];   #creating a user account
		then
			read -p "Enter Username: " userName
			read -p "Enter User's home directory (using absolute path): " homeDir			
			read -p "Enter User's default login shell (using absolute path): " defaultShell
			#add user account
			useradd -d $homeDir -m -s $defaultShell $userName
			echo "Added the account"

		elif [ "$choice" == "B" ] || [ "$choice" == "b" ];	# deleting a user account
		then
			read -p "Enter User's name for the account that to be deleted: "  userName	
			userdel -r $userName
		elif [ "$choice" == "C" ] || [ "$choice" == "c" ];	#changing a supplementary group for a account
		then
			read -p "Enter User's name for the account: "  userName
			read -p "Enter User group name that to be added: " groupName
                        usermod -G $groupName  $userName 
                        echo "Added the supplemetary group to the account"
	
		elif [ "$choice" == "D" ] || [ "$choice" == "d" ];	# changing initial group for an account
		then
                     	read -p "Enter User's name for the account:" userName
                     	read -p "Enter User's initial group:"  initialGroup  	
                        usermod -G $initialGroup  $userName
 
		elif [ "$choice" == "E" ] || [ "$choice" == "e" ];	#changing default login shell for an account
		then
	             	read -p "Enter User's name for the account: " userName
                     	read -p "Enter User's shell for the account: " uShell
		     	usermod -s $uShell $userName

		elif [ "$choice" == "F" ] || [ "$choice" == "f" ];	#changing expiration date for an account
		then
			read -p  "Enter User's name: " username
                        read -p "Enter the expiration date for this account\(YYYY-MM-DD/)"  expireDate 
		        usermod -e $expireDate $username
		else
                        
			echo "Done.."
		fi	
		sleep 3
    	done