package designpattern.command;

public class GarageDoor {
	public void up(){
		System.out.println("GarageDoor open");
	}
	public void down(){
		System.out.println("GarageDoor down");
	}
	public void stop(){
		System.out.println("GarageDoor stop");
	}
	public void lightOn(){
		System.out.println("GarageDoor lightOn");
	}
	public void lightOff(){
		System.out.println("GarageDoor lightOff");
	}
}
