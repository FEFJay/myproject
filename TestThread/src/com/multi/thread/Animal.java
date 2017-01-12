package com.multi.thread;


public class Animal {

	/*
	 * 动物类：指定动物的属性，还有构造方法
	 */
	class MyAnimal {
		private String name;
		private String furColor;
		private boolean hasFur;
		private int num;

		public MyAnimal() {
		}

		public MyAnimal(String name) {
			this.name = name;
		}

		public MyAnimal(int num) {
			this.num = num;
		}
		
		public MyAnimal(String name, int num) {
			this.name = name;
			this.num = num;
		}

		public MyAnimal(String name, boolean hasFur, String furColor) {
			this.name = name;
			this.hasFur = hasFur;
			if (hasFur) {
				this.furColor = furColor;
			} else {
				furColor = null;
			}
		}

		public String getFurColor() {
			return furColor;
		}

		public void setFurColor(String furColor) {
			if (hasFur) {
				this.furColor = furColor;
			} else {
				this.furColor = null;
			}
		}

		public boolean isHasFur() {
			return hasFur;
		}

		public void setHasFur(boolean hasFur) {
			this.hasFur = hasFur;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

	}

	/*
	 * 动物行为接口：实现该接口表示动物有什么样的功能
	 */
	interface AnimalBehavior {
		public void move();

		public void eat();
	}

	/*
	 * 猫
	 */
	class Cat extends MyAnimal implements AnimalBehavior {

		public Cat() {

		}

		public Cat(String color) {
			super("cat", true, color);
		}

		public Cat(int num) {
			super("cat", num);
		}

		@Override
		public void move() {
			// TODO Auto-generated method stub

		}

		@Override
		public void eat() {
			// TODO Auto-generated method stub

		}

	}

	/*
	 * 狗
	 */
	class Dog extends MyAnimal implements AnimalBehavior {

		public Dog() {

		}

		public Dog(String color) {
			super("Dog", true, color);
		}

		public Dog(int num) {
			super("Dog", num);
		}

		@Override
		public void move() {
			// TODO Auto-generated method stub

		}

		@Override
		public void eat() {
			// TODO Auto-generated method stub

		}

	}

	/*
	 * 鱼
	 */
	class Fish extends MyAnimal implements AnimalBehavior {

		public Fish() {

		}

		public Fish(int num) {
			super("Fish", num);
		}

		@Override
		public void move() {
			// TODO Auto-generated method stub

		}

		@Override
		public void eat() {
			// TODO Auto-generated method stub

		}

	}

}
