/**********************************************************************
Filename: VideoFrame.cpp
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
#include<iostream>
#include<iomanip>
#include "VideoFrame.h"
#include "Frame.h"

/********************************************************************
Function name:  CalculateFrameResource
Purpose: calculates the frame resourse
Out parameters: void
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/
void VideoFrame::CalculateFrameResource() {
	cout << "VideoFrame: ";
	cout << static_cast<Frame&>(*this) << endl;
   // cout << *this << endl;
	cout << "Lempel-Ziv-Welch Lossless Compression" << endl;
	cout << "------------------------------------------------";
	cout << "----------------------------------------" << endl;
	cout << "colours:\t ";
	int c_range, count = 1;
	int length = sizeof(BITDEPTH_FACTOR) / sizeof(BITDEPTH_FACTOR[0]);
	for (size_t i = 0; i < length; i++) {
		c_range = (int)pow(2, count++);
		cout << "| " << c_range << "\t  ";
	}

	// print the file size
	cout << "\n---------------------------------------------------------------------------------" << endl;
	cout << "file size (MB):\t | ";
	cout << std::setprecision(4) << size / (COMPRESSION_RATIO * BITDEPTH_FACTOR[0]) << "    |" << size / (COMPRESSION_RATIO * BITDEPTH_FACTOR[1]) << "  |"
		<< size / (COMPRESSION_RATIO * BITDEPTH_FACTOR[2]) << "   |" << size / (COMPRESSION_RATIO * BITDEPTH_FACTOR[3]) << "   |"
		<< size / (COMPRESSION_RATIO * BITDEPTH_FACTOR[4]) << "   |" << size / (COMPRESSION_RATIO * BITDEPTH_FACTOR[5]) << "   |"
		<< size / (COMPRESSION_RATIO * BITDEPTH_FACTOR[6]) << "   |" << size / (COMPRESSION_RATIO * BITDEPTH_FACTOR[7]) << endl;
	cout << "---------------------------------------------------------------------------------" << endl;

}
/********************************************************************
Function name:  operator<<
Purpose: Prints the videoframe
Out parameters: os stream
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/
std::ostream& operator<<(std::ostream& os, VideoFrame& vF) {
    os << static_cast<Frame&>(vF) << " and size is: " <<vF.size <<"."<< endl;

    return os;
}