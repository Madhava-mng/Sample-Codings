#include<stdio.h>
#include<math.h>

int len(int a){
  return (a)? (1 + len(a/10)):0;
}

int isArmstrong(int a){
  int l = len(a), t = 0, n = a;
  while(a){
    t +=  (int)pow(a%10, l);
    a /= 10;
  }
  return (t == n)? 1:0;
}

int main(void){
  int a;
  scanf("%d", &a);
  printf("%d is%san armstrong number", a, isArmstrong(a)? " ":" not ");
}