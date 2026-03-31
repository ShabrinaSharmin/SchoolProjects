// AnimationManager.h
#pragma once
#include <string>
#include<vector>
#include"Animation.h"
class AnimationManager
{
	std::string managerName;
	std::vector<Animation> animations;
public:
	AnimationManager(std::string name) :managerName(name) {
		//std::cout << "Animation___Manager cons" << std::endl;
	}
	~AnimationManager() {
		//std::cout << "Destructor Animation___Manager" << std::endl;
	}
	void EditAnimation();
	void DeleteAnimation();
	friend std::istream& operator>>(istream&, AnimationManager&);// add an Animation
	friend std::ostream& operator<<(ostream&, AnimationManager&);// output the Animations
};

