/**********************************************************************
Filename: animationManager.cpp
Version: 1.0
Author: Shabrina Sharmin
Student No: 040927453
Course Name/Number: C++ Programming CST 8219
Lab Sect: 300
Assignment #: 3
Assignment name: Animation Project in C++
Due Date: April 7  2020
Submission Date:  April 7 2020
Professor: Abdullah Kadri
Purpose:
*********************************************************************/
#include<string>
#include<iostream>
#include <algorithm>

#include "animationManager.h"

/********************************************************************
Function name:  AnimationManager::EditAnimation()
Purpose: Edits an  animation in the animations vector
Out parameters: void
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/

void AnimationManager::EditAnimation() {

    int temp = -1;
    std::cout << "Which Animation do you wish to edit? Please give the index (from 0 to " << animations.size() - 1 << "):";
    std::cin >> temp;
    if (temp >= 0 && temp < animations.size()) {
        std::cout << "Editing Animation # " << temp << std::endl;
        std::cin >> animations[temp];      //invoking the operator>> func from animation.cpp
        std::cout << "Animation #" << temp << " edit complete " << std::endl;
    }
    else {
        std::cout << "Please input a correct index in order to edit " << std::endl;
    }

}

/********************************************************************
Function name:  AnimationManager::DeleteAnimation()
Purpose: Deletes an  animation in the animations vector
Out parameters: void
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/

void AnimationManager::DeleteAnimation() {
    int index = -1;
    int count = 0;
    std::cout << "Delete an Animation from the Animation Manager" << std::endl;
    std::cout << "   Animation do you wish to delete ? Please give the index in the range 0 to " << animations.size() - 1 << ": ";
    std::cin >> index;
    if (index >= 0 && index < animations.size()) {
        animations.erase(animations.begin() + index);
    }
    else {
        std::cout << "Please input a correct index in order to delete " << std::endl;
    }
    std::cout << "Animation #" << index << " deleted" << std::endl;
}

/********************************************************************
Function name:operator>>(overload func for std::cin)
Purpose:Take user input create and add an animation to the animation manger
Out parameters: std::istram& iS
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/
// Add an animation
std::istream& operator>>(std::istream& iS, AnimationManager& aM) {
    //  std::cout << "DEBUG::AnimationManager" << __FUNCTION__ << std::endl;
    std::string animationName;
    std::cout << "Add an Animation to the Animation Manager\nPlease enter the Animation Name : ";
    iS >> animationName;
    //create the animation
    Animation a(animationName);
    //push_back the animation in the animations
    aM.animations.push_back(animationName);
    std::cout << "Animation " << animationName << " added at the back of animations" << std::endl;
    return iS;     /////////
}

 
/********************************************************************
Function name:operator<<(overload func for std::cout)
Purpose:Output the animations in the Animation vector
Out parameters: std::ostram& oS
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/
// Output the animations
std::ostream& operator<<(std::ostream& oS, AnimationManager& aM) {
    if (aM.animations.empty()) {
        oS << "No animations to report" << std::endl;
    }
    else {
        oS << "AnimationManager: " << aM.managerName << std::endl;
        for (int i = 0; i < aM.animations.size(); i++) {
            oS << "Animation: " << i << std::endl;
            oS << aM.animations[i];   ///invoking the operator<< func from animation.cpp

        }
    }
    return oS;
}

