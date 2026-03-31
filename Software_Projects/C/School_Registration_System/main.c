#define _CRT_SECURE_NO_DEPRECATE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include "Assign4.h"

//void display_Menu();
int main() {
    int option = -1;
    
    do{
        printf("\n");
        option = -1;
        printf("1- Display Students\n");
        printf("2- Display Courses\n");
        printf("3- Add a Student\n");
        printf("4- Add a Course\n");
        printf("5- Quit\n");
        printf("Please choose an option:\n");
        scanf("%d" , &option);
        
        char studentId[20];
        char studentFName[30];
        char studentLName[30];
        char courseId[20];
        char courseName[255];
        
        switch(option){
                
            case 1:
                readFromFile("Students.txt");
                break;
            case 2:
                readFromFile("Courses.txt");
                break;
            case 3:
                printf("Please enter student id: ");
                scanf("%s", studentId);
                printf("Please enter student's first name: ");
                scanf("%s", studentFName);
                printf("Please enter student's last name : ");
                scanf("%s", studentLName);
                appendToStudentFile(studentFName, studentLName, studentId);
                break;
            case 4:
                printf("Please enter course id: ");
                scanf("%s", courseId);
                fflush(stdin);
                printf("Please enter course name: ");
               scanf("%s[^\n]", courseName);
                //fgets(courseName,255,stdin);
                fflush(stdin);
                appendToCourseFile(courseName, courseId);
                break;
            default:
                break;
        }
    }while(option != 5);
    if(option == 5){
        printf("Quiting\n");
    }
    
}


int readFromFile(char *fileName){
    FILE *fptr;
    char lineSize[85];
    fptr = fopen(fileName,"r");
    if(fptr != NULL){
        while(!feof(fptr)){
            fgets(lineSize, 85, fptr);
            fprintf(stdout, "%s", lineSize);
            memset(lineSize, 0, 85);
        }
        fclose(fptr);
    }else{
        printf("Could not read file.\n");
    }
    return 1;
}

int appendToStudentFile(char* fName, char* lName, char* studentId){
    FILE *fptr ;
    fptr = fopen("Students.txt","a" );
    if(fptr != NULL){
        fprintf(fptr, "%s %s %s\n" , fName,lName,studentId);
        fclose(fptr);
    }else{
        printf("Could not write to %s file.\n", fName);
    }
    return 1;
}

int appendToCourseFile(char* courseName, char* courseCode){
    FILE *fptr ;
     fptr = fopen("Courses.txt","a" );
     if(fptr != NULL){
         fprintf(fptr, "%s %s\n" ,courseCode,courseName );
         fclose(fptr);
     }else{
         printf("Could not write to Courses.txt file.\n");
     }
     return 1;
}



