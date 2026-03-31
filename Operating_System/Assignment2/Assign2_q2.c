/*--------------------------------------------------------------------

File: assign2_q2.c
Student name(s):Shabrina Sharmin 
Student id(s): 300230297
Group no:  

-------------------------------------------------------------------------*/

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
int calcfib(int);
struct square{
    int x; // x axis
    int y; // y axis
} square;
struct thread_arg{
    int t_num;
    int last;
} thread_arg;

int num = 0;
struct square* squares = NULL;
pthread_mutex_t mutex_lock; 

void* calcSquares(void* thread_arg_t)
{
    pthread_mutex_lock(&mutex_lock); 
    struct thread_arg* thread_arg_p = (struct thread_arg*) thread_arg_t;
    int t_num_val = thread_arg_p->t_num;
    int last = thread_arg_p->last;
    //printf("thread num arg: %d, last : %d\n", t_num_val, last);

    struct square* tempS = squares;
    tempS = tempS + (t_num_val-1);
    if(t_num_val % 2 != 0) //odd
    {
        tempS->x =0;
        tempS->y = calcfib(t_num_val+2);
        if(last)
        {
            printf("child thread:\n");
            printf("length %d, width %d\n", tempS->y, calcfib(t_num_val+1) );

        }
        
    }
    else //even
    {
        tempS->x = calcfib(t_num_val);
        tempS->y = calcfib(t_num_val+1);
        if(last)
        {
            printf("child thread:\n");
            printf("length %d, width %d\n", tempS->y,tempS->x);
  
        }
    }
    //printf("p_thread %d done \n", t_num_val);
    

    pthread_mutex_unlock(&mutex_lock); 
    
    return 0; 
}

int calcfib(int n )
{
    int term_1 =0, term_2 = 1, next=0;
    if (n == 1)
        return term_1;
    if (n == 2) 
        return term_2;
    for (int i = 2; i <n; i++){
        next = term_1 + term_2;
        term_1 = term_2;
        term_2 = next;
    }
    return next;
}

int main (int argc, char **argv)
{

    printf("input:");
    int result = scanf("%d", &num);
    if (result != 1)
    {
        printf("Number is not valid, Exiting... \n");
        exit(-1);
    }
    if (pthread_mutex_init(&mutex_lock, NULL) != 0) { 
        printf("p_thread mutex failed\n"); 
        return 1; 
    } 
    
    squares = malloc(num * sizeof(struct square));
    struct square* tempS = squares; 
    pthread_t threads[num];
    for(int i = 1; i <= num; i++)
    {
        tempS->x = -1;
        tempS->y = -1;
        tempS++;

        struct thread_arg* thread_arg_p = malloc(sizeof(struct thread_arg));
        thread_arg_p->t_num = i;
        if(i == num)
        {
            thread_arg_p->last = 1;
        }else
        {
            thread_arg_p->last = 0;
        }
        pthread_create(&threads[i], NULL, calcSquares, (void*)thread_arg_p);
        
    }
    for(int i = 1; i <= num; i++)
    {
        pthread_join(threads[i], NULL);  
    }
    printf("parent thread:\n");
    struct square* square_p = squares;
    for(int i =1; i <= num; i++)
    {   
    	char* count = "th";
    	if ( i==1 ) count = "st";
    	if ( i==2 ) count = "nd";
    	if ( i==3 ) count = "rd";

        printf("the");
        printf(" %d %s", i, count);
        printf(" square: (%d, %d)\n", square_p->x, square_p->y);
        square_p++;
    }

    return 0;
}
