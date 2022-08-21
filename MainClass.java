import java.util.Scanner;
public class MainClass{ 
  public static void main(String args[]){
       Scanner _key = new Scanner(System.in);
boolean  b2;
int  a;
boolean  b3;
double  b;
String  t1;
String  t2;
char  c1;
String  t3;
boolean  b1;
a= _key.nextInt();
b= _key.nextDouble();
t1 = "Olá mundo!";
t2 = 'd';
b1 = true;
b2 = false;
b3 = a==b;
c1 = a;
t3 = c1;
a = 2;
b = (4*3)+(1+2.8989+3+4);
switch (a) {
    case 1:
        System.out.println(a);
    case 2:
        System.out.println(t2);
    default:
        a = 534;
}

if (a>b) {
System.out.println(a);}
while (a>b) {
a = a+1;}
  }}