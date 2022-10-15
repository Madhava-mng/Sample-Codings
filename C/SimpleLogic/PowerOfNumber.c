#include<stdio.h>

int pow_(int a, int b){
  return (b > 1)? pow_(a, b-1)*a:a;
}

int main(int a, int b){
  scanf("%d %d", &a, &b);
  printf("%d", pow_(a, b));
  return 0;
}