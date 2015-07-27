package designpattern.command;

public class GarageDoorOpenCommand implements Command{
	private GarageDoor garageDoor;
	public GarageDoorOpenCommand(GarageDoor garageDoor){
		this.garageDoor = garageDoor;
	}
	public void execute() {
		garageDoor.up();
	}
	public void undo(){
		garageDoor.down();
	}
}
