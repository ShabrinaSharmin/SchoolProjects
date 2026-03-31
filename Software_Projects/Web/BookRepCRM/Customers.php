<?php
include 'includes/database.inc.php';
//echo $test;
//call connect function and retrieve pdo obj

$myPdoH = setConnectionInfo();

$nameQuery = 'SELECT firstName,lastName ,email, address, city ,country FROM Customers order by lastName';
$cityQuery = 'SELECT firstName,lastName ,email, address, city ,country FROM Customers order by city';
$countryQuery = 'SELECT firstName,lastName ,email, address, city ,country FROM Customers order by country';



$dataRow = $myPdoH->query($nameQuery);
	while($individualRow = $dataRow->fetch()){
		   	echo"<tr>";
            // echo"<td><a href=\"BookRepCRM.php?cID=".$customers_array[$x][0]."\">".$customers_array[$x][1]."</a></td>";
    			echo"<td>".$individualRow['firstName'] ." ". $individualRow['lastName']."</td>";
    			echo"<td>".$individualRow['email'] ."</td>";
    			echo"<td>".$individualRow['address'] ."</td>";
    			echo"<td>".$individualRow['city'] ."</td>";
    			echo"<td>".$individualRow['country'] ."</td>";



    	 echo"</tr>";

	}
?>