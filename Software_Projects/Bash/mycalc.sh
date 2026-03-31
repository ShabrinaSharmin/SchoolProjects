#!/bin/bash
#Shabrina Sharmin
#student Id:040927453
#Lab10
#CST 8102_300
#mycalc.sh
#This script can calculate the sum or the difference of two numbers. 
#The script has two modes. If arguments are provided to the script,
#it will calculate the result and present it.
#If no arguments are provided it will prompt user for input and 
#desired operation. in this mode userhas to type "X" to exit.

#function to add two numbers
function add ()
{
	sum=$(( $1 + $2 ))
	echo "The sum of $1 plus $2 equals $sum "
}

#function to substract two numbers
function subtract ()
{
	subtract=$(( $1 - $2 ))
	echo "The subtraction of $1 from $2 equals $subtract "
}

#prompt user for menu1: "C" for calculation , "X" for exit
function menu1 ()
{
	choice=n
	#looping until valid value has not been given
	while [ "$choice" != "C" ] && [ "$choice" != "c" ]
	do
		echo -e "C) Calculation"
		echo -e "X) Exit"
		read choice
		if [ "$choice" == "X" ] || [ "$choice" == "x" ]
		then
			exit
		fi
	clear
	done
}

#prompts user for desired operation or prompts to exit
function menu3 ()
{
	operation=n
	while  [ "$operation" != "+" ] && [ "$operation" != "-" ]
	do
		clear
		echo "+) Add"
		echo "-) Subtract"
		echo "X) Exit"
		read operation
		clear
		if [ "$operation" == "X" ] || [ "$operation" == "x" ]
		then
			exit
		elif  [ "$operation" == "+" ]
		then
			return 0
		elif  [ "$operation" == "-" ]
		then
			return 1
		fi
	clear
	done
}

if [ $# -eq 0 ]   # "$#" is number of arguments passed in by user 
then
	#this is no arg mode
	while true
	do
		op=n
		menu1
		#menu2
		echo -e "Please enter an integer number or X to exit: \c" 
		read value1
			
		if [ "$value1" == "X" ] || [ "$value1" == "x" ]
		then
			exit
		fi
		clear
		#menu3
		if menu3
			then
			op="+"	# menu3 has returned op = 0
		else
			op="-"	# menu3 has returned op = 1
		fi

		#menu for second digit
		echo -e "Please enter an integer number or X to exit: \c" 
		read value2
		if [ "$value2" == "X" ] || [ "$value2" == "x" ]
		then
			exit
		fi
		clear
		if [ "$op" == "+"  ]
		then
			add $value1 $value2
		elif [ "$op" == "-" ]
		then
			subtract $value1 $value2

		fi
	sleep 3
	clear	
	done
else
	#this is arg mode
	if [ $# -eq 3 ]
	then
		if [ "$2" == "+" ]
		then
			add $1 $3 
		elif [ "$2" == "-" ]
		then
			subtract $1 $3	
		else
			echo "Please enter correct operation "
		
		fi
	else
		echo "Please enter correct number of arguments"
	fi
fi


