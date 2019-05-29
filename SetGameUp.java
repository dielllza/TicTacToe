import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
public class SetGameUp extends JFrame {
	private View game;
	private int width = 400,height=400;

	private Controller gameController;
	private JPanel panelButtons,name1,name2,startPanel;
	private JButton[] hwManyPlayersBtnList;
	private String pl1 = "Player 1";
	private String pl2 = "Player 2";
	private Color buttonsColor,panelBackground,starterButtonColor;
	Label lblName1;
	Label lblName2;
	TextField txtName1,txtName2;	
	public SetGameUp(View game,Controller gameController){
		
		this.game = game;
		this.gameController = gameController;
		hwManyPlayersBtnList = new JButton[2];
		
		buttonsColor = game.getButtonsColor();
		panelBackground = game.getPanelBackground();
		
		startPanel = new JPanel();
		startPanel.setBackground(panelBackground);
		starterButtonColor = game.getStarterButtonColor();
		JButton startButton = new JButton("START");
		startButton.setBackground(starterButtonColor);
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				game.setSize(width,height);
				game.start();
				game.setLocation(475,190);
			}
		});
		startPanel.add(startButton);
		
	    panelButtons = new JPanel(new GridLayout(1, 3));
		Label players = new Label("Select the number of players");
		players.setBackground(panelBackground);
		setFontsAndColors(players,panelBackground,starterButtonColor);
		
		name1 = new JPanel();
		name1.setBackground(panelBackground);
		name2 = new JPanel();
		name2.setBackground(panelBackground);
		lblName1= new Label("Enter Player 1 name:");
		lblName1.setBounds(10,100 , 50, 50);
		setFontsAndColors(lblName1,panelBackground,starterButtonColor);
		txtName1 = new TextField(pl1,10);
		txtName1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionTextFields(txtName1,"Player 1",0);
			}
		});
		lblName2 = new Label("Enter Player 2 name:");
		lblName2.setBounds(10,115 , 50, 50);
		setFontsAndColors(lblName2,panelBackground,starterButtonColor);
		txtName2 = new TextField(pl2,10);
		txtName2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionTextFields(txtName2,"Player 2",1);
			}
		});
		name1.add(lblName1);
		name1.add(txtName1);
		name2.add(lblName2);
		name2.add(txtName2);
		//panelButtons.setSize(100, 100);
		//panelButtons.setLocation(100, 100);
		
		drawPlayerButtons(30,30,30);
		for(int i = 0;i<2;i++) {
		panelButtons.add(hwManyPlayersBtnList[i]);
		}
		
		//setLayout(new BorderLayout());  // "super" Frame sets to BorderLayout
		setLayout(new GridLayout(5,1));
		//game.setPl1Name(txtName1.getText());
		game.setPl2Name(txtName2.getText());
		gameController.setPl2Name(txtName2.getText());
		add(players,BorderLayout.CENTER);
	    add(panelButtons, BorderLayout.CENTER);
	    add(name1, BorderLayout.WEST);
	    add(name2, BorderLayout.EAST);
	    add(startPanel, BorderLayout.SOUTH);
		setSize(width,height);
		setTitle("TicTacToe");
		setLocation(475,190);
		setVisible(true);
	}
	private void drawPlayerButtons(int xPosition,int yPosition,int madhesia) {
		for(int i = 0;i<2;i++) {
			JButton button =new JButton(i+1+"");
			button.setBackground(buttonsColor);
			button.setForeground(Color.white);
			button.setOpaque(true);
			button.setFont(new Font("MV Boli", Font.PLAIN,50));
			button.setBounds(xPosition*i,xPosition*i,madhesia,madhesia);
			hwManyPlayersBtnList[i] = button;
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int index = game.indexOf(button, hwManyPlayersBtnList);
					gameController.setNrOfPlayers(index);
					if(index==0) {
						lblName2.setText("Player 2 name:");
						pl2 = "Computer";
						txtName2.setText(pl2);
						game.setPl2Name(pl2);
						gameController.setPl2Name(pl2);
						txtName2.setEditable(false);
					}
					hwManyPlayersBtnList[0].setEnabled(false);
					hwManyPlayersBtnList[1].setEnabled(false);
				}
			});
		}
	}
	public void setFontsAndColors(Label label,Color backgroundColor,Color fontColor){
		label.setBackground(backgroundColor);
		label.setForeground(fontColor);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN,getWidth()/70));
	}
	private void actionTextFields(TextField txt,String s,int i) {
		String emri = txt.getText();
		if(emri.length()>0) {
			if(i == 0) {
			game.setPl1Name(emri);
			gameController.setPl1Name(emri);
			}
			else if(i == 1) {
				game.setPl2Name(emri);
				gameController.setPl2Name(emri);
			}
			
		}
		else {
			txt.setText(s);
		}
		txt.setEditable(false);
	}
	public String getPl1() {
		return pl1;
	}
	public String getPl2() {
		return pl2;
	}

}
