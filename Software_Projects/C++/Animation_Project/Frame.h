//Frame.h
#pragma once
#include<iostream>
using namespace std;
class Frame {
	std::string frameName;
protected:
	double size;
public:
	Frame(string name, double sz) :frameName(name), size(sz) {};
	virtual ~Frame() {  };
	virtual void CalculateFrameResource() = 0;
	friend std::ostream& operator<<(ostream&, Frame&);
};

