public class PlayComputer {
		private CheckWinner kontrollo ;
		
		public PlayComputer( CheckWinner kontrollo) {
			this.kontrollo = kontrollo;
		}
		public int random(char[][] symbList) {
			int numri = (int)(Math.floor(Math.random()*9));
			int nr = numri/3;
			int colIndex = numri-nr*3;
		//	System.out.println(numri);
			char c = symbList[nr][colIndex];
			boolean isFull = kontrollo.checkFilled(symbList);
			if(!(kontrollo.checkEmpty(c)) && !isFull){//e pambushur
				numri = random(symbList);
			}
			return numri;
		}
}

