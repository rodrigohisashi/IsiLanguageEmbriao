#include <iostream>
#include <string>
using namespace std;
int main(void) { 
int  a;
double  b;
string  t1;
string  t2;
cin >> a;
cin >> b;
t1 = "Olá mundo!";
t2 = "Sou da UFABC";
a = 31213;
b = (4*3)+(1+2.8989+3+4);
switch (a) {
    case 1:
        cout << a << endl;
    case 2:
        cout << t2 << endl;
    default:
        a = 534;
}

if (a>b) {
cout << a << endl;}
while (a>b) {
a = a+1;}
return 0;}