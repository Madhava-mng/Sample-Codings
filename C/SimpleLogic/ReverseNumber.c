#include<stdio.h>

int revNumber(int a){
  int t = 0;
  while(a){
    t = (t * 10) + (a%10);
    a /=10;
  }
  return t;
}

int main(void){
  int a;
  scanf("%s", &a);
  printf(revNumber(a));
}