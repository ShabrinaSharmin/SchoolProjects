/**********************************************************************
Filename: AudioFrame.cpp
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
#include <iomanip>
#include"AudioFrame.h"
#include"Frame.h"
using namespace std;
/********************************************************************
Function name:  operator<<
Purpose: Prints the audioframe
Out parameters: os stream
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/
std::ostream& operator<<(std::ostream& os, AudioFrame& aR) {
	os << static_cast<Frame&>(aR) << " and size is: " << aR.size << "." << endl;
    return os;

}
/********************************************************************
Function name:  CalculateFrameResource
Purpose: calculates the frame resourse
Out parameters: void
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/
void AudioFrame::CalculateFrameResource() {
	cout << "AudioFrame: ";
	cout << static_cast<Frame&>(*this) << endl;
	cout << "MP3 Lossy Compression" << endl;
	cout << "---------------------------------------------------------" << endl;
	cout << "\nbitrate (kbits/s):\t\t";
	// print bitrate range
	int length = sizeof(BITRATE) / sizeof(BITRATE[0]);
	for (size_t i = i = 0; i < length; i++) {
		cout << "| " << BITRATE[i] << "\t";
	}
	// print file size
	cout << "\n-------------------------------------------------------------" << endl;
	cout << "file size (MB):\t\t\t";
	for (size_t i = i = 0; i < length; i++) {

		cout << std::setprecision(3 ) << "| " << size / COMPRESSION_RATIO[i] << "\t";
	}
	cout << "\n-------------------------------------------------------------" << endl;

}