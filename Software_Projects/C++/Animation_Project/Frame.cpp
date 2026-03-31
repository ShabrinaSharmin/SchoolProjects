/**********************************************************************
Filename: Frame.cpp
Version: 1.0
Author: Shabrina Sharmin
Student No: 040927453
Course Name/Number: C++ Programming CST 8219
Lab Sect: 300
Assignment #: 3
Assignment name: Animation Project in C++
Due Date: April 7 2020
Submission Date: April 7 2020
Professor: Abdullah Kadri
Purpose:
*********************************************************************/
#pragma once
#include"Frame.h"

/********************************************************************
Function name:  operator<<
Purpose: Prints the frame
Out parameters: os stream
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/
ostream& operator<<(ostream& oS, Frame& fr) {
	oS << "frameName = " << fr.frameName;
	return oS;
 }