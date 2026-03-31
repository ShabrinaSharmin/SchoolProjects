#define _CRT_SECURE_NO_DEPRECATE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Node.h"

int main() {
    int numOfStudents = 3;
    //This is the head for the node
    node_t *head= NULL;
    printf("First you will enter 3 student's name that will be added to the start of the list\n");
    int index=-1;
    for(index=0;index<numOfStudents;index++){
        char buffer[50];
        student_t *tempSP = (student_t*) malloc(sizeof(student_t));
        printf("Please enter the first name of student %d:",index+1);
        scanf("%s" , buffer);
        tempSP->firstName = (char*) malloc(sizeof(char)*strlen(buffer));
        strcpy(tempSP->firstName, buffer);
        printf("Please enter the last name of student %d:",index+1);
        scanf("%s" , buffer);
        tempSP->lastName = (char*) malloc(sizeof(char)*strlen(buffer));
        strcpy(tempSP->lastName, buffer);
        
        addToStart(&head, tempSP);
    }
    print_list(head);
    
    printf("Then you will enter 3 student's name that will be added to the end of the list\n");
    for(index=0;index<numOfStudents;index++){
        char buffer[50];
        student_t *tempSP = (student_t*) malloc(sizeof(student_t));
        printf("Please enter the first name of student %d:",index+1);
        scanf("%s" , buffer);
        tempSP->firstName = (char*) malloc(sizeof(char)*strlen(buffer));
        strcpy(tempSP->firstName, buffer);
        printf("Please enter the last name of student %d:",index+1);
        scanf("%s" , buffer);
        tempSP->lastName = (char*) malloc(sizeof(char)*strlen(buffer));
        strcpy(tempSP->lastName, buffer);
        
        addToEnd(&head, tempSP);
    }
    print_list(head);
    
    /* Remove first three elements in the list*/
    printf("Then you will remove the first three elements in the list\n");
    for(index=0;index<numOfStudents;index++){
        removeFromStart(&head);
    }
    print_list(head);
    
    printf("Then you will remove the last three elements in the list\n");
    for(index=0;index<numOfStudents;index++){
        removeLast(&head);
    }
    print_list(head);
    
    printf("By now, your list should be empty, so you will enter three more students \n");
    for(index=0;index<numOfStudents;index++){
        char buffer[50];
        student_t *tempSP = (student_t*) malloc(sizeof(student_t));
        printf("Please enter the first name of student %d:",index+1);
        scanf("%s" , buffer);
        tempSP->firstName = (char*) malloc(sizeof(char)*strlen(buffer));
        strcpy(tempSP->firstName, buffer);
        printf("Please enter the last name of student %d:",index+1);
        scanf("%s" , buffer);
        tempSP->lastName = (char*) malloc(sizeof(char)*strlen(buffer));
        strcpy(tempSP->lastName, buffer);
        
        addToEnd(&head, tempSP);
    }
    print_list(head);
    printf("Finally, you will delete the second student in the list only\n");
    remove_by_index(&head, 1);
    print_list(head);
}
