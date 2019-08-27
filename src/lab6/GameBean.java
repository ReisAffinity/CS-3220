package models;

import java.util.Arrays;


public class GameBean
{
  private static final char empty = '-';
  private char[] game;
  private char curPlayer;
  private String message;
  
  public GameBean()
  {
    game = new char[9];
    Arrays.fill(game, '-');
    
    curPlayer = 'X';
    
    message = "";
  }
  

  public char[] getGame()
  {
    return game;
  }
  

  public String getMessage()
  {
    return message;
  }
  

  public char getCurPlayer()
  {
    return curPlayer;
  }
  

  public void togglePlayer()
  {
    curPlayer = (curPlayer == 'X' ? 'O' : 'X');
  }
  

  public void action(int location)
  {
    if (isGameOver()) {
      return;
    }
    
    if ((location < 0) || (location >= game.length)) {
      return;
    }
    
    if (game[location] == '-')
    {
      game[location] = curPlayer;
      
      if (!isGameOver())
      {
        togglePlayer();
      }
    }
  }
  



  public boolean isGameOver()
  {
    if (((game[0] != '-') && (
      ((game[0] == game[1]) && (game[1] == game[2])) || 
      ((game[0] == game[3]) && (game[3] == game[6])) || (
      (game[0] == game[4]) && (game[4] == game[8])))) || 
      ((game[2] != '-') && (
      ((game[2] == game[5]) && (game[2] == game[8])) || (
      (game[2] == game[4]) && (game[2] == game[6])))) || 
      ((game[4] != '-') && 
      (game[4] == game[3]) && (game[3] == game[5])) || (
      (game[7] != '-') && (
      ((game[7] == game[4]) && (game[7] == game[1])) || (
      (game[7] == game[6]) && (game[7] == game[8])))))
    {
      message = ("Player " + curPlayer + " has won the Tic-Tac-Toe Game!");
      return true;
    }
    



    int openMoves = 0;
    
    for (int i = 0; i < game.length; i++)
    {
      if (game[i] == '-')
      {
        openMoves++;
      }
    }
    


    if (openMoves == 0)
    {
      message = "Draw! Neither player wins!";
      return true;
    }
    



    return false;
  }
}
