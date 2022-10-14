#include<stdio.h>

int sumOfDigit(int a){
  if(a){
    return sumOfDigit(a/10) + a%10;
  }
  return a;
}

int main(){
  int a = 0;
  scanf("%d", &a);
  printf("%d", sumOfDigit(a));
  return 0;
}
