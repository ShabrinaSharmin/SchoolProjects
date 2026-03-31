<!DOCTYPE html>
<html lang = "en">

<head>
	<link type="text/css" rel="stylesheet" href="style/styleSheet.css">
</head>

<body>

<?php include 'Header.php';?>

<div id = "content">


		<form method="post">
			<h2> Update the following fields</h2></br>
			<label>First Name</label>
				<input type = "text" name="firstName"></br>
			<label>Last Name</label>	
				<input type = "text" name="lastName"></br>
			<label>Email Address</label>	
				<input type = "email" name="emailAddress"></br>
			<label>Phone Number</label>	
				<input type = "text" name="phnNum"></br>
			<label>Designation</label>	
				<input type = "text" name="designation"></br>
			<label>Admin Code</label>	
				<input type = "text" name="adminCode"></br>		
				<input type = "Submit" name="updateRecord" value="Update Record">		


		</form>

<?php

		if(isset($_POST['firstName'])
		&& isset($_POST['lastName'])
		&& isset($_POST['emailAddress'])
		&& isset($_POST['phnNum'])
		&& isset($_POST['sinNum'])
		&& isset($_POST['passWord'])
		&& isset($_POST['designation'])
		&& isset($_POST['adminCode']) ) {

			$updatedfirstName = $_POST['firstName'];
			$updatedlastName = $_POST['lastName'];
			$updatedemail = $_POST['emailAddress'];
			$updatedphn = $_POST['phnNum'];
			$updatedsin = $_POST['sinNum'];
			$updateddesignation = $_POST['designation'];
			$updatedadmin = $_POST['adminCode'];


			//connecting to the server and thge database
  			$serverConnection = mysqli_connect($db_host, $db_username, $db_password, $db_name);

  			if (mysqli_connect_error()) {
    			die('Connect Error ('.mysqli_connect_errno().') '.mysqli_connect_error());
  			}else{
  				echo 'Connected successfully.';
  			}

  			
  			

			//$sqlQuery = "UPDATE Employee SET firstName = '$updatedfirstName',lastName = "$updatedlastName",emailAddress="$updatedemail",
			 //									phnNum = "$updatedphn",sinNum = "$updatedsin", designation= "$updateddesignation",adminCode="$updatedadmin" where firstname =;"


			//Query to the database
			$insert = mysqli_query($serverConnection, $sqlQuery);	
			if($insert){
				echo"Employee has successfully added";
			// 	//header("Location:ViewAllEmployees.php");
			 } else {
			 	die("Could not add Employee. Error: " .mysqli_errno($serverConnection));
			 }
			
			 $serverConnection->close();
	}
?>

</div>

<?php include 'Footer.php';?>

</body>
</html>		