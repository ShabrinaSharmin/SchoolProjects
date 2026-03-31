<?php
session_start();
if(isset($_SESSION['emailAddressK'])){
	session_destroy();
}
require "ConnectionInfo.php";
?>
<?php

	if(isset($_POST['firstName']) && isset($_POST['lastName']) && isset($_POST['emailAddress']) && isset($_POST['phnNum']) && isset($_POST['sinNum']) && isset($_POST['passWord']) && isset($_POST['designation']) && isset($_POST['adminCode']) ) {
			$firstName = $_POST['firstName'];
			$lastName = $_POST['lastName'];
			$email = $_POST['emailAddress'];
			$phn = $_POST['phnNum'];
			$sin = $_POST['sinNum'];
			$password = $_POST['passWord'];
			$designation = $_POST['designation'];
			$admin = $_POST['adminCode'];
			if($designation == "Manager") 
			{
				$admin = "999";

			}else{
				$admin = "111";
			}

			//connecting to the server and thge database
  			$serverConnection = mysqli_connect($db_host, $db_username, $db_password, $db_name);

  			if (mysqli_connect_error()) {
    			die('Connect Error ('.mysqli_connect_errno().') '.mysqli_connect_error());
  			}
  			$sqlQuery = "INSERT into Employee (firstName, lastName, emailAddress, phnNum, sinNum, password, designation, adminCode) VALUES( '$firstName', '$lastName', '$email', '$phn', '$sin', '$password', '$designation', '$admin')";
			//Query to the database
			$insert = mysqli_query($serverConnection, $sqlQuery);	
			if($insert){
				$serverConnection->close();
				$_SESSION['emailAddressK'] = $email;
				header("Location:ViewAllEmployees.php");
				//header("Location:https://www.shabrinasharmin.com/CST8238/Lab9/ViewAllEmployees.php");
				exit;
			} else {
			 	die("Could not add Employee. Error: " .mysqli_errno($serverConnection));
			}
			$serverConnection->close();	 
	}			
?>

<!DOCTYPE html>
<html lang = "en">

<head>
	<link type="text/css" rel="stylesheet" href="style/styleSheet.css">
</head>

<body>

<?php include 'Header.php';?>

<div id = "content">
		<form method="post" action="CreateAccount.php">
			<h2> Create your new accont</h2></br>
			<p>Please fill in the information</p></br>
			<label>First Name</label>
				<input type = "text" name="firstName"></br>
			<label>Last Name</label>	
				<input type = "text" name="lastName"></br>
			<label>Email Address</label>	
				<input type = "email" name="emailAddress"></br>
			<label>Phone Number</label>	
				<input type = "tel" name="phnNum"></br>
			<label>SIN</label>	
				<input type = "number" name="sinNum"></br>
			<label>Password</label>	
				<input type = "password" name="passWord"></br>
			<label>Designation</label>	
			<input type="radio" name="designation" value="Manager" checked="checked">Manager<br/>
			<input type="radio" name="designation" value="IT Developer">IT Developer<br/>
			<label>Admin Code</label>	
				<input type = "number" name="adminCode" readonly></br>

				<input type = "submit" name="submitData" value= "Submit information">
		</form>

</div>

<?php include 'Footer.php';?>

</body>
</html>
