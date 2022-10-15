#include<stdio.h>

// It slower than normal fibonacci
int fib(int a){
  return (a > 1)? fib(a-2) + fib(a-1):a;
}

int main(int n){
  scanf("%d", &n);
  for(int i=1;i<=n;printf("%d ", fib(i++)));
  return 0;
}