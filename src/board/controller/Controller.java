package board.controller;

public abstract class Controller {
	public abstract void doAction(String command, String actionMethodName);

	public abstract void makeTestData();
}
