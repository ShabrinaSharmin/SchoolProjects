//Animation.h
#pragma once
#include <string>
#include<iostream>
#include<forward_list>
#include"VideoFrame.h"
#include"AudioFrame.h"
using namespace std;
class Animation
{
	std::string animationName;
	std::forward_list<Frame*> frames;  // have to fix it 
public:
	Animation(std::string name) :animationName(name) {
		//std::cout << "Animation cons" << std::endl;
	}
	~Animation()
	{
	//	std::cout << "Destructor animation" << std::endl;

		for (auto it = frames.begin(); it != frames.end(); ++it) {
			delete(*it);
		}
		frames.clear();
	};
	void EditFrame();
	void DeleteFrame();
	friend std::istream& operator>>(istream&, Animation&);// Add a Frame as in cin >> A;
	friend std::ostream& operator<<(ostream&, Animation&);// output the Frames as in cout << A;
};

