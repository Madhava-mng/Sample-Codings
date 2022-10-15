#include<stdio.h>

int main(void){
  int a = 0, n = 0, b = 1,s = 0;
  scanf("%d", &n);
  while (n--){
    printf("%d", a);
    s = a + b;
    b = a;
    a = s;
  }  
}