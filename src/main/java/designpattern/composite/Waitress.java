package designpattern.composite;

public class Waitress {
	MenuComponent allMenus;
	
	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}
	
	public void pringMenu() {
		allMenus.print();
	}
}
