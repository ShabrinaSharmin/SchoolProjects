/*--------------------------------------------------------------------

File: dp.c
Student name(s):Shabrina Sharmin 
Student id(s): 300230297
Group no:  

Description:  Double pipe program.  To pipe the output from the standard
          output to the standard input of two other processes.
          Usage:  dp  <cmd1> : <cmd2> : <cmd3>
          Output from process created with cmd1 is piped to
          processes created with cmd2 and cmd3

-------------------------------------------------------------------------*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <signal.h>
/* prototypes */
int doublePipe(char **,char **,char **);

/*--------------------------------------------------------------------

File: dp.c

Description: Main will parse the command line arguments into three arrays
         of strings one for each command to execv().
--------------------------------------------------------------------*/

int main(int argc, char *argv[])
{

   int i,j;         /*indexes into arrays */
   char *cmd1[10];  /*array for arguments of first command */
   char *cmd2[10];  /*array for arguments of second command */
   char *cmd3[10];  /*array for arguments of third command */
   if(argc == 1)
   {
     printf("Usage: dp <cmd1 arg...> : <cmd2 arg...> : <cmd3 arg....>\n");
     exit(1);
   }

   /* get the first command */
   for(i=1,j=0 ; i<argc ; i++,j++)
   {
      if(!strcmp(argv[i],":")) break; /* found first command */
      cmd1[j]=argv[i];
   }
   cmd1[j]=NULL;
   if(i == argc) /* missing : */
   {
      printf("Bad command syntax - only one command found\n");
      exit(1);
   }
   else i++;

   /* get the second command */
   for(j=0 ; i<argc ; i++,j++)
   {
      if(!strcmp(argv[i],":")) break; /* found second command */
      cmd2[j]=argv[i];
   }
   cmd2[j]=NULL;
   if(i == argc) /* missing : */
   {
      printf("Bad command syntax - only two commands found\n");
      exit(1);
   }
   else i++;

   /* get the third command */
   for(j=0 ; i<argc ; i++,j++) cmd3[j]=argv[i];
   cmd3[j]=NULL;
   if(j==0) /* no command after last : */
   {
      printf("Bad command syntax - missing third command\n");
      exit(1);
   }

   exit(doublePipe(cmd1,cmd2,cmd3));
}

/*--------------------------------------------------------------------------
  ----------------- You have to implement this function --------------------
  --------------------------------------------------------------------------
Function: doublePipe()

Description:  Starts three processes, one for each of cmd1, cmd2, and cmd3.
          The parent process will receive the output from cmd1 and
          copy the output to the other two processes.
-------------------------------------------------------------------------*/

int doublePipe(char **cmd1, char **cmd2, char **cmd3)
{
	int pipe12fd[2];
	int pipe23fd[2];
	int p1;
	int p2;
	int p3;
	//let us create the two pipes required
	if(pipe(pipe12fd) == -1)
	{
		printf("1st pipe creation failed.\n");
		exit(-1);	
	}
	if(pipe(pipe23fd) == -1)
	{
		printf("2nd pipe creation failed.\n");
		exit(-1);	
	}

	p1 = fork();
	if (p1 == -1)
	{
		printf("fork has failed p1\n");
	}
	else if (p1==0) 
	{	
		//we need to route the stdout of cmd1 to
		// pipefd12
  		//printf("Executing command 1\n");
		
		dup2(pipe12fd[1],1);
		close(pipe12fd[0]);
		close(pipe23fd[0]);
		close(pipe23fd[1]);

		execvp(cmd1[0], cmd1);
		close(pipe12fd[1]);
		exit(0);
	}
	p2 = fork();
	if (p2 == -1)
	{
		printf("fork has failed for p2\n");
	}
	else if (p2==0) 
	{	
		//we need to read the stdin of pipefd12
		dup2(pipe12fd[0],0);
  		//printf("Executing command 2\n");
		close(pipe12fd[1]);
		//we need to route the stdout of cmd2 to pipefd23
		dup2(pipe23fd[1],1);
		close(pipe23fd[0]);
		execvp(cmd2[0], cmd2);
		close(pipe23fd[1]);
		exit(0);
	}
	p3 = fork();
	if (p3 == -1)
	{
		printf("fork has failed for p3\n");
	}
	else if (p3==0) 
	{	
		//we need to read the stdin of pipefd23
		
  		//printf("Executing command 3\n");
		close(pipe12fd[0]);
		close(pipe12fd[1]);
		dup2(pipe23fd[0],0);
		close(pipe23fd[1]);
		execvp(cmd3[0], cmd3);
		close(pipe23fd[0]);
		exit(0);
	}
	
	close(pipe12fd[0]);
	close(pipe12fd[1]);
	close(pipe23fd[0]);
	close(pipe23fd[1]);
	printf("\n");	
	

	return 0;

}
