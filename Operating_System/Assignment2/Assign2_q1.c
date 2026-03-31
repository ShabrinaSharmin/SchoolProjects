/*--------------------------------------------------------------------

File: assign2_q1.c
Student name(s):Shabrina Sharmin 
Student id(s): 300230297
Group no:  

-------------------------------------------------------------------------*/

#include<stdio.h>
#include<stdlib.h>
#include<pthread.h>

//function to check if prime
int is_prime(int n){
    if(n <= 1){
        return 0;  //false
    }
    for(int i=2; i*i<=n; i++){  //check divisibility
        if(n%i == 0){
            return 0;
        }
    }
    return 1;
}

//function to deompose an even number into sum of two primes
void* decompose(void* num){
    int number = *((int*) num); //dereffernce
    //printf("%s value of n is %d\n", "decompose", number);

    for(int i=2; i<=number/2; i++){
        //printf("i: %d\n", i);
        if(is_prime(i) && is_prime(number-i)){
            //printf("#i: %d  #number-i: %d \n" , i, number-i);
            printf("%d = %d + %d\n", number, i, number - i);
            pthread_exit(0);
        }
        
    }
}


int main(){
    int value;
    printf("Please, enter an even nuber greater than 2: ");
    int input = scanf("%d", &value);
    //printf("input val: %d" , value);
    if((value < 2) || (value % 2 != 0)){
        printf("Number is not valid, Exiting... \n");
        exit(-1);
    }

    //create thread
    pthread_t thread;
    int result_t = pthread_create(&thread, NULL, decompose, (void*) &value);
        pthread_join(thread, NULL);
}
