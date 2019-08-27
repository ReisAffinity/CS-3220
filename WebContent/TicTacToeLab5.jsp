<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean class="Lab5.TicTacToeGameBean" id="game" scope="session" />    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tic Tac Toe</title>


<jsp:setProperty property="*"	name="game"  /> 

</head>
<body>


<h3>Player ${game.currentPlayer} turn</h3>

<h3>${game.winner}</h3>




<table>
  <tr>
  
  

    <td><a href="TicTacToeLab5.jsp?spot1=${game.currentPlayer}">${game.board[0]}</a></td>
    <td><a href="TicTacToeLab5.jsp?spot2=${game.currentPlayer}">${game.board[1]}</a></td>
    <td><a href="TicTacToeLab5.jsp?spot3=${game.currentPlayer}">${game.board[2]}</a></td>
  </tr>
  <tr>
    <td><a href="TicTacToeLab5.jsp?spot4=${game.currentPlayer}">${game.board[3]}</a></td>
    <td><a href="TicTacToeLab5.jsp?spot5=${game.currentPlayer}">${game.board[4]}</a></td> 
    <td><a href="TicTacToeLab5.jsp?spot6=${game.currentPlayer}">${game.board[5]}</a></td>
  </tr>
  <tr>
    <td><a href="TicTacToeLab5.jsp?spot7=${game.currentPlayer}">${game.board[6]}</a></td>
    <td><a href="TicTacToeLab5.jsp?spot8=${game.currentPlayer}">${game.board[7]}</a></td>
    <td><a href="TicTacToeLab5.jsp?spot9=${game.currentPlayer}">${game.board[8]}</a></td>

  </tr>

</table>


<a href="TicTacToeLab5.jsp?reset=9">Reset</a>



</body>
</html>