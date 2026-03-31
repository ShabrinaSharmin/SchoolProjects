#!/bin/bash
#040927453
#Operating System Fundamentals
#CST 8102 300
#This scripts prompts user to input deposit amount,calculates the totalvalue of deposited 
#amount in dollar and add interest to them accordingly

read -p "Enter deposit amount 1: " val_1
read -p "Enter deposit amount 2: " val_2
read -p "Enter deposit amount 3: " val_3

echo
echo "You have deposited 3 amounts:"
echo "\$$val_1"
echo "\$$val_2" 
echo "\$$val_3"
total=$((val_1 + val_2 + val_3))
interest=0
if [ $total -ge 0 ] && [ $total -le 3999 ] 
then
	interest=0
elif [ $total -ge 4000 ] && [ $total -le 6999 ]
then
	interest=1
elif [ $total -ge 7000 ] && [ $total -le 9999 ]
then
	interest=2
elif [ $total -ge 1000 ] && [ $total -le 13999 ]
then
	interest=3
elif [ $total -ge 14000 ] && [ $total -le 17999 ]
then
	interest=4
elif [ $total -ge 18000 ] && [ $total -le 21999 ]
then
	interest=5
elif [ $total -ge 22000 ] && [ $total -le 29999 ]
then
	interest=6
elif [ $total -ge 30000 ]
then
	interest=7
else
	interest=0
fi
echo
echo "These deposit amounts total \$$total, and the interest rate for this deposit amount is $interest %."
