#ifndef Node_h
#define Node_h

#include <stdio.h>
/* Add student struct and Node struct */

/*
 Prints all student names in the following format
 LastName, firstName
 */
typedef struct Student{
    char *firstName;
    char *lastName;
}student_t;

typedef struct Node{
    student_t *recentStudent;
    struct Node *next;
}node_t;

void print_list (node_t *head);

/*
 Adds a new node to the end of the list
 */
void addToEnd(node_t **headP, student_t *student);

/*
 Adds a new node to the beginning of thr list
 */
void addToStart(node_t ** headP, student_t *student);

/*
 Remove the first node in the list
 */
void removeFromStart(node_t **headP);

/*
 Removes the last node in the list
 */
void removeLast(node_t **headP);

/*
 Removes a node by index
 */
void remove_by_index(node_t **headP, int n);


#endif /* Node_h */
