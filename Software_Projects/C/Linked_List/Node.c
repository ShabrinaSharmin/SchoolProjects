#include <stdlib.h>
#include <stdio.h>
#include "Node.h"
/* Add student struct and Node struct */

/*
 Prints all student names in the following format
 LastName, firstName
 */
void print_list (node_t *head)
{
    if(head == NULL){
        return;
    }
    while(head != NULL){
        printf("%s %s\n" , head->recentStudent->firstName,head->recentStudent->lastName);
        head = head->next;
    }
}

/*
 Adds a new node to the end of the list
 */
void addToEnd(node_t **headP, student_t *student){
    /* Create node_t with student and next pointing to null */
    /* this will be added to the list */
    node_t *newNodeToAdd = (node_t*) malloc(sizeof(node_t));
    newNodeToAdd->recentStudent = student;
    newNodeToAdd->next=NULL;
    
    /*if this list is empty *headP is null (*headP is list head pointer) */
    if((*headP) == NULL){
        (*headP) = newNodeToAdd;
        return;
        /*This means there are more than one nodes in the llist*/
    }else{
        /* copy the pointer of *headP which is the address of  to head to newNodeToloop */
        node_t *copyHeadToLoop = *headP;
        while (copyHeadToLoop->next != NULL) {
            /*transfer the address of newNodeToloop->next to newNodeToloop */
            copyHeadToLoop = copyHeadToLoop->next  ;
        }
        /* copyHeadToLoop is pointing to the last list element and */
        /*  its next is NULL, here we want its next to point to newNodeToAdd */
        copyHeadToLoop->next=newNodeToAdd;
    }
}

/*
 Adds a new node to the beginning of thr list
 */

void addToStart(node_t** headP, student_t *student){
    /* Create node_t with student and next pointing to null */
    /* this will be added to the list */
    node_t *newNodeToAdd = (node_t*)malloc(sizeof(node_t));
    newNodeToAdd->recentStudent =  student;
    newNodeToAdd->next = NULL;
    /*if this list is empty *headP is null (*headP is list head pointer) */
    if((*headP)==NULL){
        (*headP) = newNodeToAdd;
        
    }
    /*This means there are more than one nodes in the llist*/
    else{
        newNodeToAdd->next = *headP; /* saving the content of headp in the  newNode->next as we want to add aiways at first */
        *headP=newNodeToAdd;    /*Updating the content of head with newNode address as we want to add at first*/
    }
}

/*
 Remove the first node in the list
 */
void removeFromStart(node_t ** headP){
    node_t *toRemoveNodeFromFirst = *headP;  //assigning headP's content which is first node address to toRemoveNode
    *headP = toRemoveNodeFromFirst->next; //updating head with next nodes address after the node toRemoveNode
    free(toRemoveNodeFromFirst);
}

/*
 Removes the last node in the list
 */
void removeLast(node_t **headP){
    /*list is empty */
    if((*headP) == NULL){
        return;
    }
    /*List has only one elements */
    if((*headP)->next == NULL){
        free((*headP));
        (*headP)=NULL;
    }
    /*This means there are more than one nodes in the llist*/
    else{
        node_t *nodeBeforeNodetoDelete = *headP;
        /*Standing at node n and checking (n+1)->next*/
        while(nodeBeforeNodetoDelete->next->next != NULL){
            nodeBeforeNodetoDelete = nodeBeforeNodetoDelete->next;
        }
        free(nodeBeforeNodetoDelete->next);
        nodeBeforeNodetoDelete->next=NULL;
    }
    
}

/*
 Removes a node by index
 */
void remove_by_index(node_t **headP, int n){
    
    if((*headP) == NULL){
        return;
    }
    /*There is one element in the list so freeing that  */
    if(n==0){
        node_t *temp = *headP;
        (*headP)= temp->next;
        free((*headP));
        return;
    }
    /*This means there are more than one nodes in the llist*/
    //else{
    node_t *nodeBeforeIndex = *headP;
    
    int i = 0;
    for (i=0; i<n-1; i++) {
        if(nodeBeforeIndex->next == NULL){
            printf("index>lllenght\n");
            return;
        }
        nodeBeforeIndex = nodeBeforeIndex->next;
    }
    nodeBeforeIndex->next = nodeBeforeIndex->next->next;
    free(nodeBeforeIndex->next);
}

