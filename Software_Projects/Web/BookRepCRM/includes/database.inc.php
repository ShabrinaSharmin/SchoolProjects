<?php

/*
 Sets up the PDO database connection
*/
function setConnectionInfo($values=array()) {
      //For EasyPHP Local Webserver
	  $connString = "mysql:host=localhost;dbname=books";
      $user = "bookrep"; 
      $password = "book@rep";
      
      //For Hebergement Webserver
      /*
      $connString = "mysql:host=localhost;dbname=your-username_books";
      $user = "your-username_bookrep";
      $password = "book@rep19";*/
      
      $pdo = new PDO($connString,$user,$password);
      $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
      return $pdo;      
}


?>
