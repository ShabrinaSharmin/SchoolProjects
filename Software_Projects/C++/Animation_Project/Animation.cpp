/**********************************************************************
Filename: Animation.cpp
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
#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include "animation.h"

/********************************************************************
Function name:  Animation::EditFrame()
Purpose: Edits a frame in the frames list
Out parameters: void
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/

void  Animation::EditFrame() {
    int index = -1;
    char* newFrameName = NULL;
    double newSize = 0;
    std::string name;
    std::cout << "Edit a Frame in the Animation" << std::endl;
    int count = 0;
    for (auto it = frames.begin(); it != frames.end(); ++it) {
        count++;
    }
    std::cout << " There are " << count << " Frame(s) in the list. Please specify the index (<=0) to edit at : ";
    std::cin >> index;
    if (index >= 0 && index < count) {

        std::cout << "The name of the Frame is: ";
        count = 0;
        auto it = frames.begin();
        for (;it != frames.end(); ++it) {
            if (index == count) {
               // std::cout << (*(*it));
                if (dynamic_cast<AudioFrame*>(*it) != NULL) {
                    std::cout << dynamic_cast<AudioFrame&>(*(*it));
                }
                else if (dynamic_cast<VideoFrame*>(*it) != NULL) {
                    std::cout << dynamic_cast<VideoFrame&>(*(*it));
                }
             
                break;
                count++;
            }
        }
        std::cout << ". What do you wish to replace them with?" << std::endl;
        std::cin >> name;
        std::cin >> newSize;
        // creating new frame
        Frame* currentF = *it;
        if (dynamic_cast<AudioFrame*>(currentF) != NULL) {
            
            *it = new AudioFrame(name, newSize);
            delete (currentF);
        }
        else if (dynamic_cast<VideoFrame*>(currentF) != NULL) {

            *it = new VideoFrame(name, newSize);
            delete (currentF);
        }
        std::cout << "Frame #" << index << " edit completed" << std::endl;
    }
}

/********************************************************************
Function name:  Animation::DeleteFrame()
Purpose: deletes a frame in the frames list
Out parameters: void
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/
void  Animation::DeleteFrame() {
    Frame* f = frames.front();
    delete(f);
    frames.pop_front();
    std::cout << "First Frame deleted" << std::endl;

}

/********************************************************************
Function name: operator>>
Purpose: Takes user input ,create and add an frame in the frames list
Out parameters: istream& iS
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/

// Add a frame as in cin>>A;
std::istream& operator>>(std::istream& iS, Animation& ani) {
    char option = -88;
    bool RUNNIG = true;
    //  std::cout << "DEBUG::Animation" << __FUNCTION__ << std::endl;

    while (RUNNIG) {
        std::cout << "Menu\n 1. Insert a Frame.\n 2. Delete a Frame \n";
        std::cout << " 3. Edit a Frame\n 4. Quit" << std::endl;
        iS >> option;

        switch (option)
        {
        case'1':   //insert a frame in animation
        {
            std::string frameName = "";
            double size = 0;
            char frameType;
            Frame* frame = NULL;
            std::cout << "Insert a Frame in the Animation" << std::endl;
            std::cout << "Please enter the Frame frameName:";
            iS >> frameName;
            std::cout << "Please enter the Frame size(MB): ";
            iS >> size;
            std::cout << "Please enter the Frame type (AudioFrame = A, VideoFrame = V): ";
            iS >> frameType;
            if (frameType == 'A'|| frameType == 'a') {
                //creating frame
                 frame = new AudioFrame(frameName, size);

            }
            else if (frameType == 'V'|| frameType == 'v'){
                frame = new VideoFrame(frameName, size);
            }
            else {
                continue;
            }
            // std::cout << "created" << std::endl;
            // ani.frames.emplace_front(frame); // cpy constructor is being called
            if (frame != NULL) {
                ani.frames.push_front(frame);
                std::cout << "Frame " << frameName << " added at the front of frames" << std::endl;
                break;
            }
        }
        case'2':
        {
            ani.DeleteFrame();
            break;
        }
        case'3':
        {
            ani.EditFrame();
            break;
        }
        case'4':
        {
            RUNNIG = false;
            break;
        }
        }
    }
    return iS;
}


/********************************************************************
Function name:operator<<(overload func for std::cout)
Purpose:Output the  frames in the Frame list
Out parameters: std::ostram& oS
Version: 1.0
Author: Shabrina Sharmin
**********************************************************************/

// Output the frames as in cout<<A;
std::ostream& operator<<(std::ostream& oS, Animation& ani) {
    oS << "\tAnimation name is " << ani.animationName << std::endl;
    oS << "\tReport the Animation" << std::endl;
    if (ani.frames.empty()) {
        oS << "\tNo frames in the Animation" << std::endl;
    }
    else {
        int i = 0;
        for (auto it = ani.frames.begin(); it != ani.frames.end(); it++) {
            oS << "Frame#" << i << endl;
            (*it)->CalculateFrameResource();
            i++;
        }
    }



    return oS;
}


