package designpattern.composite;

import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends MenuComponent {
	ArrayList<MenuComponent> menuCompontents = new ArrayList<MenuComponent>();
	String name;
	String description;
	
	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void add(MenuComponent menuComponent) {
		menuCompontents.add(menuComponent);
	}
	public void remove(MenuComponent menuComponent) {
		menuCompontents.remove(menuComponent);
	}
	public MenuComponent getChild(int i) {
		return menuCompontents.get(i);
	}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	public void print() {
		System.out.println("\n" + getName());
		System.out.println(", " + getDescription());
		System.out.println("---------------");
		
		Iterator<MenuComponent> iterator = menuCompontents.iterator();
		while (iterator.hasNext()) {
			iterator.next().print();
		}	
	}
	
	public Iterator<MenuComponent> createIterator() {
		return new CompositeIterator(menuCompontents.iterator());
	}
}
