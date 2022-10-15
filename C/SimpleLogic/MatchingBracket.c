#include <stdio.h>
#include <string.h>

int main(void){
  char a[100], c=0;
  scanf("%s", a);
  int l = strlen(a);
  int t =0;
  for(int i=0;i<strlen(a);i++){
    if(a[i] == '('){
      t++;
    }
    if(a[i] == ')'){
      t--;
    }
    if(t <= -1){
      printf("Not Match");
      return 1;
    }
  }
  printf((t == 0)? "Match":"Not Match");
  return t;
}