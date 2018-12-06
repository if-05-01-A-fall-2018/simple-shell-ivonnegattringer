#include <stdio.h>
#include <unistd.h>
#include <string.h>

char* arguments[256];
char* pointer;
//bei null stoppt der execvp
int main(int argc, char const *argv[]) {
  char input[256];
  char* save;
  gets( input);
  char* div = "&&";
pointer = strtok_r(input, div,&save);
 while(pointer != 0) {
   char* div2 = " ";
   char* save2;
  char* pointer2 = strtok_r(pointer, div2, &save2);
   int count = 0;
   while(pointer2 != 0){
     arguments[count] = pointer2;
     count++;
     pointer2 = strtok_r(0, div2, &save2);
   }
   arguments[count] = 0;
    int pid = fork();
      if(pid == 0){
        int errorcause = execvp(arguments[0], arguments);
        if(errorcause == -1){
          printf("fckn fail\n");
        }
        printf("fcinsadköfj fail\n"); //funktioniert auuuuuch!!!!! wegen execvp
        //das erste argument braucht das execvp um das segment leichter zu finden.
      }
      else{
        int status;
        waitpid(-1, &status, 0);
      }
      pointer = strtok_r(0, div,&save);
  }

  return 0;
}
