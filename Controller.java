import java.awt.Color;
import java.awt.Label;
import javax.swing.JButton;

public class Controller {
	private CheckWinner kontrollo;
	private PlayComputer pc;
	private Label turnsWinners;
	private Label playerOne,ties,playerTwo;
	private JButton restartBut;
	private char[][] symbolList;
	private JButton[] buttonList;
	private int turn;
	private String pl1Name = "Player 1",pl2Name = "Player 2";
	private String[] namesList ;
	private int[] results = new int[3];
	private int nrOfPlayers;
	public Controller(CheckWinner kontrollo, PlayComputer pc) {
		this.kontrollo = kontrollo;
		this.pc = pc;
		namesList = new String[2];
		namesList[0] = pl2Name;
		namesList[1] = pl1Name;
	}

	public void setSymbolList(char[][] symbolList) {
		this.symbolList = symbolList;
	}

	public void setButtonList(JButton[] buttonList) {
		this.buttonList = buttonList;
	}

	public void setNrOfPlayers(int nrOfPlayers) {
		this.nrOfPlayers = nrOfPlayers;
	}
	public void setPl1Name(String pl1Name) {
		this.pl1Name = pl1Name;
		namesList[1] = pl1Name;
	}
	public void setPl2Name(String pl2Name) {
		this.pl2Name = pl2Name;
		namesList[0] = pl2Name;
	}

	public void setTurnsWinners(Label turnsWinners) {
		this.turnsWinners = turnsWinners;
	}

	public void setPlayerOne(Label playerOne) {
		this.playerOne = playerOne;
	}

	public void setTies(Label ties) {
		this.ties = ties;
	}

	public void setPlayerTwo(Label playerTwo) {
		this.playerTwo = playerTwo;
	}

	public void setRestartBut(JButton restartBut) {
		this.restartBut = restartBut;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public void controllGame(int index) {
		int nr = index/3;
		int	rowIndex = nr;
		int	colIndex = index-nr*3; 			
		if(turn == 0) {
			buttonList[index].setText("X");
			buttonList[index].setEnabled(false);
			symbolList[rowIndex][colIndex] = 'X';
		//	System.out.println(pl2Name+"'s Turn");
			turnsWinners.setText(pl2Name+"'s Turn");
			turn = 1;
			if(!isOver(symbolList,buttonList) && nrOfPlayers == 0 && !(kontrollo.checkFilled(symbolList))) {
				controllGame(pc.random(symbolList));
			//	System.out.println("HERE I PLAY");
			}
			if(nrOfPlayers == 0) {
				theWinner();
			}
		}
		else if(turn == 1) {
			buttonList[index].setText("O");
			buttonList[index].setEnabled(false);
			symbolList[rowIndex][colIndex] = 'O';
	//		System.out.println(pl1Name+"'s Turn");
			turnsWinners.setText(pl1Name+"'s Turn");
			turn = 0;
			}
		if(nrOfPlayers != 0) {
			theWinner();
		}
		playerOne.setText(pl1Name + "' score: "+ results[1]);
		ties.setText("ties :" + results[2]);
		playerTwo.setText(pl2Name + "' score: "+ results[0]);
		}
	
	
	public void theWinner() {
		if(isOver(symbolList,buttonList) && !(kontrollo.isDraw(symbolList))) {
		//	System.out.println("The winner is" + namesList[turn]);
			turnsWinners.setText("The winner is " + namesList[turn]);
			results[turn]+=1;
			restartBut.setText("Play again");
		}
		else if(kontrollo.isDraw(symbolList)) {
		//	System.out.println("Issa draw");
			turnsWinners.setText("It's a draw");
			results[2]+=1;
			restartBut.setText("Play again");
		}
	}
	
	
	public boolean isOver(char[][] simbolet,JButton[] butonat) {
		boolean ok = true;
		if(kontrollo.checkWin(simbolet)){
			for(int i = 0; i< 9; i++) {
				butonat[i].setEnabled(false);
			}
			int tipi = kontrollo.getFituesi()[0];
			int drejtimi = kontrollo.getFituesi()[1];
			if(tipi == 1 && drejtimi == 1 ) {
				setRed(4,0,butonat);
			}
			else if(tipi == 1 && drejtimi == 2) {
				setRed(2,2,butonat);
			}
			else if(tipi == 2) {
				setRed(1,drejtimi*3,butonat);
			}
			else if(tipi == 3) {
				setRed(3,drejtimi,butonat);
			}
		}
		else {
			ok = false;
		}
		return ok;
	}
	
	
	public void setRed(int add,int start,JButton[] butonat) {
		int count = 0;
		int i = start;
		while(count < 3) {
			butonat[i].setBackground(new Color(198,0,0));
			i = i + add;
			count++;
		}
	}

}
