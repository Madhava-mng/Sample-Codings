#include<stdio.h>

int fact(int n){
  return (n>1)? fact(n-1)*n:n;
}

int main(int n){
  scanf("%d", &n);
  printf("%d", fact(n));
  return 0;
}