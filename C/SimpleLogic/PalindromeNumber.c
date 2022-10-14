#include<stdio.h>

int revNumber(int a){
  int t = 0;
  while(a){
    t = (t * 10) + (a%10);
    a /=10;
  }
  return t;
}

int isPalindrom(int a){
  return (a == revNumber(a)) ? 1:0;
}

int main(void){
  int a;
  scanf("%d", &a);
  printf("%d is%s a Palindrome.\n", a, isPalindrom(a)? " ":"not");
}