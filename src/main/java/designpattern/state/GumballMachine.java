package designpattern.state;

public class GumballMachine {
	State soldOutState;
	State noQuarterState;
	State hasQuarterState;
	State soldState;
	
	State state = soldOutState;
	int count = 0;
	
	public GumballMachine(int numberGuballs) {
		soldOutState = new SoldOutState(this);
		noQuarterState = new NoQuarterState(this);
		hasQuarterState = new HasQuarterState(this);
		soldState = new SoldState(this);
		this.count = numberGuballs;
		if (numberGuballs > 0) {
			state = noQuarterState;
		}
	}
	
	void refill(int count) {
		this.count = count;
		state = noQuarterState;
	}
	public void insertQuarter() {
		state.insertQuarter();
	}

	public void ejectQuarter() {
		state.ejectQuarter();
	}
	
	public void turnCrank() {
		state.turnCrank();
		state.dispense();
	}

	void releaseBall() { 
		if (count != 0) {
			count--;
		}
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getSoldOutState() {
		return soldOutState;
	}

	public State getNoQuarterState() {
		return noQuarterState;
	}

	public State getHasQuarterState() {
		return hasQuarterState;
	}

	public State getSoldState() {
		return soldState;
	}

	public State getState() {
		return state;
	}

	public int getCount() {
		return count;
	}
}





















