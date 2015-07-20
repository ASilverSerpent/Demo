package designpattern.strategy;

public class Context {
	private Strategy strategy;//环境类中维持一个对抽象策略类的引用
	
	public void setStrategy(Strategy strategy){
		this.strategy = strategy;
	}
	
	public void execute(){
		strategy.execute();
	}
}
