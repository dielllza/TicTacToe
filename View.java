import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
public class View extends JFrame {
	private Controller gameController;
	public JPanel panelTurn,panelGame,panelResults,panelRestart,panelResultsNRestart;
	private Label turnsWinners,playerOne,ties,playerTwo;
	private JButton restartBut;
	private Color buttonsColor= new Color(185,217,195),panelBackground =new Color(243,211,184),starterButtonColor = new Color(198,0,0);
	private int x,y;
	private static String pl1Name = "Player 1";
	private static String pl2Name = "Player 2";
	private static JButton[] buttonList = new JButton[9];
	private static char[][] symbolList = new char[3][3];

	public View(Controller gameController) {
		this.gameController = gameController;
		drawButtons(15,15,100);
		panelTurn = new JPanel();
		setFontsAndColors(panelTurn,panelBackground,starterButtonColor);
		panelGame = new JPanel(new GridLayout(3, 3));
		panelResults = new JPanel();
		setFontsAndColors(panelResults,panelBackground,starterButtonColor);
		panelRestart = new JPanel();
		setFontsAndColors(panelRestart,panelBackground,starterButtonColor);
		panelResultsNRestart= new JPanel(new GridLayout(2, 1));
		setFontsAndColors(panelResultsNRestart,panelBackground,starterButtonColor);
		restartBut = new JButton("RESTART");
		restartBut.setBackground(starterButtonColor);
		restartBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		gameController.setRestartBut(restartBut);
		panelRestart.add(restartBut);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int i = 0; i < 9; i++) {
			panelGame.add(buttonList[i]);
		}
		gameController.setSymbolList(symbolList);
		gameController.setButtonList(buttonList);
	}
	public void setFontsAndColors(JPanel panel,Color backgroundColor,Color fontColor){
		panel.setBackground(backgroundColor);
		panel.setForeground(fontColor);
		panel.setFont(new Font("Comic Sans MS", Font.PLAIN,getWidth()/70));
	}
	public void setPanels() {
	//	System.out.println("yuppp");
		turnsWinners = new Label(pl1Name+ "'s turn");
		gameController.setTurnsWinners(turnsWinners);
		playerOne = new Label(pl1Name + "' score: "+ 0);
		gameController.setPlayerOne(playerOne);
		ties = new Label("ties :" + 0);
		gameController.setTies(ties);
		playerTwo = new Label(pl2Name + "' score: "+ 0);
		gameController.setPlayerTwo(playerTwo);
	}
	public void start() {
		setPanels();
		panelResults.add(playerOne);
		panelResults.add(ties);
		panelResults.add(playerTwo);
		panelTurn.add(turnsWinners);
		panelResultsNRestart.add(panelResults);
		panelResultsNRestart.add(panelRestart);
		getContentPane().add(panelTurn,BorderLayout.NORTH);
        getContentPane().add(panelGame,BorderLayout.CENTER);
        getContentPane().add(panelResultsNRestart,BorderLayout.SOUTH);
        setTitle("Play game");
		setVisible(true);
	}
	
	
	public void setPl1Name(String pl1Name) {
		this.pl1Name = pl1Name;
	}
	public void setPl2Name(String pl2Name) {
		this.pl2Name = pl2Name;
	}

	public Color getButtonsColor() {
		return buttonsColor;
	}
	public Color getPanelBackground() {
		return panelBackground;
	}
	public Color getStarterButtonColor() {
		return starterButtonColor;
	}
	private void drawButtons(int xPosition,int yPosition,int madhesia) {
		x = xPosition;
		y = yPosition;
		for(int i = 0; i < 9; i++) {
			if(i !=0 && i%3 == 0) {
				x = xPosition;
				y = y + madhesia + 4;
			}
			JButton button =new JButton();
			UIManager.getDefaults().put("Button.disabledText",Color.WHITE);
			button.setBackground(buttonsColor);
			button.setOpaque(true);
			button.setFont(new Font("MV Boli", Font.PLAIN,madhesia/2));
			button.setBounds(x,y,madhesia,madhesia);
			buttonList[i] = button;
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					gameController.controllGame(indexOf(button,buttonList));
				}
			});
			x = x + madhesia + 4;
		}
	}
	
	public int indexOf(JButton button,JButton[] lista) {
		int nr = -1;
		for(int i = 0; i<lista.length; i++) {
			if(lista[i] == button) {
				nr = i;
			}
		}
		return nr;
	}
	
	public void refresh() {
		for(int i = 0; i < buttonList.length; i++) {
			buttonList[i].setText("");
			buttonList[i].setEnabled(true);
			buttonList[i].setBackground(buttonsColor);
			int j = i/3;
			symbolList[j][i - j*3] = 0;
			turnsWinners.setText(pl1Name+"'s Turn");
			gameController.setTurn(0);
			restartBut.setText("RESTART");
			}
	}
}	
	
