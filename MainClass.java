import java.util.Scanner;
public class MainClass{ 
  public static void main(String args[]){
       Scanner _key = new Scanner(System.in);
int  a;
double  b;
String  t1;
String  t2;
a= _key.nextInt();
b= _key.nextDouble();
t1 = "Olá mundo!";
t2 = "Sou da UFABC";
a = 31213;
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