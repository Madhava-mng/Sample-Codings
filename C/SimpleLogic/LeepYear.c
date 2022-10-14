#include <stdio.h>

int isLeep(int y){
  if(y % 400 == 0 || (y%4 == 0 && y%100 != 0)){
    return 1;
  }
  return 0;
}

int main (){
    int year = 2000;
    printf("Is %d is%s a Leep Year\n", year, isLeep(year)? " ":" Not");
    
}