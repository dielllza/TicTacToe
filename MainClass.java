
public class MainClass {

	public static void main(String[] args) {
		CheckWinner c = new CheckWinner();
		Controller ctrl = new Controller(c,new PlayComputer(c));
	    View a = new View(ctrl);
		new SetGameUp(a,ctrl); 

	}
}
