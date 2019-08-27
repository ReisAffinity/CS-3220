package Lab5;

public class TicTacToeGameBean {

	char[] board = new char[9];
	
	char currentPlayer = 'X';
	
	boolean hasWon = false;

	String winner = "";
	int reset = 0;
	



	char spot1 = board[0];
	char spot2 = board[1];
	char spot3 = board[2];
	char spot4 = board[3];
	char spot5 = board[4];
	char spot6 = board[5];
	char spot7 = board[6];
	char spot8 = board[7];
	char spot9 = board[8];
	

	public TicTacToeGameBean(){		
			for(int x = 0; x < 9; x++) board[x] = '_';
	}
		
	
	public void switchPlayers(){
		if(currentPlayer == 'X'){
			currentPlayer = 'O';
		} else currentPlayer = 'X';
	}
	
	public void checkWin(char[] enterBoard){
		/*	012
		 *	345 
		 *	678
		 */
		
		//Horizontal
		if(enterBoard[0] != '_' && enterBoard[0] == enterBoard[1] && enterBoard[0] == enterBoard[2]){
			winner = "Player " +currentPlayer+ " has won!";
			hasWon = true;
		}
		
		if(enterBoard[3] != '_' && enterBoard[3] == enterBoard[4] && enterBoard[3] == enterBoard[5]){
			winner = "Player " +currentPlayer+ " has won!";
			hasWon = true;
		}
		if(enterBoard[6] != '_' && enterBoard[6] == enterBoard[7] && enterBoard[6] == enterBoard[8]){
			winner = "Player " +currentPlayer+ " has won!";
		hasWon = true;
		}
		//Vertical

		if(enterBoard[0] != '_' && enterBoard[0] == enterBoard[3] && enterBoard[0] == enterBoard[6]){
			winner = "Player " +currentPlayer+ " has won!";
			hasWon = true;
		}
		
		if(enterBoard[1] != '_' && enterBoard[1] == enterBoard[4] && enterBoard[1] == enterBoard[7]){
			winner = "Player " +currentPlayer+ " has won!";
			hasWon = true;
		}
		if(enterBoard[2] != '_' && enterBoard[2] == enterBoard[5] && enterBoard[2] == enterBoard[8]){
			winner = "Player " +currentPlayer+ " has won!";
			hasWon = true;
		}
		//Diagonal
		if(enterBoard[0] != '_' && enterBoard[0] == enterBoard[4] && enterBoard[0] == enterBoard[8]){
			winner = "Player " +currentPlayer+ " has won!";
			hasWon = true;
		}
			
		if(enterBoard[2] != '_' && enterBoard[2] == enterBoard[4] && enterBoard[2] == enterBoard[6]){
			winner = "Player " +currentPlayer+ " has won!";
			hasWon = true;
		}
		
		if(enterBoard[0] != '_' && enterBoard[1] != '_' && enterBoard[2] != '_' && enterBoard[3] != '_' && enterBoard[4] != '_' && enterBoard[5] != '_' && enterBoard[6] != '_' && enterBoard[7] != '_' && enterBoard[8] != '_' && hasWon == false ){
			winner = "Draw!";
		}
		
	}
	
	
	public void setSpot1(char spot1) {
		if(board[0] == '_' && hasWon == false){
		this.spot1 = spot1;
		board[0] = spot1;
		checkWin(board);
		if(hasWon == false) switchPlayers();
		}
	}


	public void setSpot2(char spot2) {
		if(board[1] == '_' && hasWon == false){
		this.spot2 = spot2;
		board[1] = spot2;
		checkWin(board);
		if(hasWon == false) switchPlayers();
		}
	}


	public void setSpot3(char spot3) {
		if(board[2] == '_' && hasWon == false){
		this.spot3 = spot3;
		board[2] = spot3;
		checkWin(board);
		if(hasWon == false) switchPlayers();
		}
	}


	public void setSpot4(char spot4) {
		if(board[3] == '_' && hasWon == false){
		this.spot4 = spot4;
		board[3] = spot4;
		checkWin(board);
		if(hasWon == false) switchPlayers();
		}
	}


	public void setSpot5(char spot5) {
		if(board[4] == '_' && hasWon == false){
		this.spot5 = spot5;
		board[4] = spot5;
		checkWin(board);
		if(hasWon == false) switchPlayers();
		}
	}


	public void setSpot6(char spot6) {
		if(board[5] == '_' && hasWon == false){
		this.spot6 = spot6;
		board[5] = spot6;
		checkWin(board);
		if(hasWon == false) switchPlayers();
		}
	}


	public void setSpot7(char spot7) {
		if(board[6] == '_' && hasWon == false){
		this.spot7 = spot7;
		board[6] = spot7;
		checkWin(board);
		if(hasWon == false) switchPlayers();
		}
	}


	public void setSpot8(char spot8) {
		if(board[7] == '_' && hasWon == false){
		this.spot8 = spot8;
		board[7] = spot8;
		checkWin(board);
		if(hasWon == false) switchPlayers();
		
		}
	}


	public void setSpot9(char spot9) {
		checkWin(board);
		if(board[8] == '_' && hasWon == false){
		this.spot9 = spot9;
		board[8] = spot9;
		checkWin(board);
		if(hasWon == false) switchPlayers();
		}
	}


	public char[] getBoard() {
		return board;
	}


	public void setBoard(char[] board) {
		this.board = board;
	}


	public char getCurrentPlayer() {
		return currentPlayer;
	}


	public void setCurrentPlayer(char currentPlayer) {
		this.currentPlayer = currentPlayer;
	}


	public String getWinner() {
		return winner;
	}


	public void setWinner(String winner) {
		this.winner = winner;
	}





	public int getReset() {
		return reset;
	}
	


	public void setReset(int reset) {
		for(int x = 0; x < 9; x++) board[x] = '_';
		winner = "";
		hasWon = false;
		currentPlayer = 'X';
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
