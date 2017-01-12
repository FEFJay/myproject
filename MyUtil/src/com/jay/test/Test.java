package com.jay.test;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Test {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> t = Class.forName("Test");
		Test A = (Test) t.newInstance();
		
		int n = A.getN();
		if(n % 2 == 0){
			A.writeAll(n+1, n);
		}else {
            A.writeAll(n, n);
		}

		
	}
	
	
	private int getN(){
		int n = 0;
		//条件过滤
		System.out.println("input:");
		Scanner in = new Scanner(System.in);
		Object N = in.next();
		Pattern p = Pattern.compile("\\d{1,3}");
		Matcher matcher = p.matcher(String.valueOf(N));
		boolean flag=false;
		do{
			if (!matcher.matches()) {
				System.out.println("请输入1到100的整数");
				N = in.next();
				matcher = p.matcher(String.valueOf(N));
			}else {
				n = Integer.parseInt(String.valueOf(N));
				if (n<1 || n > 100){
					System.out.println("整数在1到100之间");
					N = in.next();
					matcher = p.matcher(String.valueOf(N));
				}else {
					flag = true;
				}
			}
		}while (!flag) ;
		
		return n;
	}
	
	
	//	//参数1：打印多少个；参数2：打印谁
	private void writeAll(int lineNum, int n){
		int halfNum = lineNum/2;
		for (int i = 0; i <= lineNum; i++) {
			if (i==0 || i== lineNum) {
				write(lineNum, n);
				System.out.println();
			}else{
				write(halfNum, n);
				write(1, i);
				write(halfNum,n);
				System.out.println();
			}
		}
	}
	
	
	//参数1：打印多少个；参数2：打印谁
	private void write(int lineNum, int n){
		for (int i = 0; i < lineNum; i++) {
			System.out.print(n);
		}
		
	}
	
	
	
	
	

}
