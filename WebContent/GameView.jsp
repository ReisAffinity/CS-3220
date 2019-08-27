<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tic-Tac-Toe</title>
</head>
<body>

	<h1>Tic-Tac-Toe</h1>

	<c:if test="${not game.gameOver}">
		<h3>Player ${game.curPlayer}'s Turn.</h3>
	</c:if>	
	
	<h3>
		${game.message} 
	</h3>
	
	<table border = "2" cellpadding = "25">
	
		<tr>
			<td>
				<a href="PlayController?location=0">${game.game[0]}</a>
			</td>
			<td>
				<a href="PlayController?location=1">${game.game[1]}</a>
			</td>
			<td>
				<a href="PlayController?location=2">${game.game[2]}</a>
			</td>
		</tr>
	
		<tr>
			<td>
				<a href="PlayController?location=3">${game.game[3]}</a>
			</td>
			<td>
				<a href="PlayController?location=4">${game.game[4]}</a>
			</td>
			<td>
				<a href="PlayController?location=5">${game.game[5]}</a>
			</td>
		</tr>
		
		<tr>
			<td>
				<a href="PlayController?location=6">${game.game[6]}</a>
			</td>
			<td>
				<a href="PlayController?location=7">${game.game[7]}</a>
			</td>
			<td>
				<a href="PlayController?location=8">${game.game[8]}</a>
			</td>
		</tr>
	
	</table>
	
	<c:if test="${game.gameOver}">
		<a href="NewGameController">Play Again?</a>
	</c:if>
	
</body>
</html>