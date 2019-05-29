public class CheckWinner {
	int[] fituesi = new int[2];
	
	private boolean compare(char c1,char c2,char c3) {
		boolean ok = false;
		if(c1 == c2 && c1 == c3 && c1 != 0) {
			ok = true;
		}
		return ok;
	}
	public boolean checkWin(char[][] tabela) {
		boolean ok =  false;
		boolean checkDiagonal1 = compare(tabela[0][0],tabela[1][1],tabela[2][2]);
		if(checkDiagonal1){
			fituesi[0] = 1;
			fituesi[1] = 1;
		}
		boolean checkDiagonal2 = compare(tabela[0][2],tabela[1][1],tabela[2][0]);
		if(checkDiagonal2){
			fituesi[0] = 1;
			fituesi[1] = 2;
		}
		if(checkDiagonal1 || checkDiagonal2) {
			ok = true;
		}
		else {
			for(int i = 0; i < 3; i++) {
				boolean checkRow = compare(tabela[i][0],tabela[i][1],tabela[i][2]);
				if(checkRow){
					fituesi[0] = 2;
					fituesi[1] = i;
				}
				boolean checkColumn = compare(tabela[0][i],tabela[1][i],tabela[2][i]);
				if(checkColumn){
					fituesi[0] = 3;
					fituesi[1] = i;
				}
				if(checkRow || checkColumn ) {
					ok = true;
					break;
				}
			}
		}
		return ok;
	}
	public boolean checkEmpty(char c) {
		if(c == '\u0000') {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean checkFilled(char[][] c) { // e kthen true nese eshte e mbushur 
		//int j = 0;
		boolean ok = true;
		search:
		for(int i = 0; i < c.length; i++) {
			for(int j = 0; j < c.length; j++) {	
				if(checkEmpty(c[i][j])) {
					ok = false;
					break search;
				}
			}
		}		
		return ok;
	}
	public boolean isDraw(char[][] c) {
		if(checkFilled(c) && !checkWin(c)) {
			return true;
		}
		else {
			return false;
		}
	}
	public int[] getFituesi() {
		return fituesi;
	}
}
