

<!DOCTYPE html>
<html lang="en">
<head>
   <meta http-equiv="Content-Type" content="text/html; 
   charset=UTF-8" />
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta name="description" content="">
   <meta name="author" content="">
   <title>Book Template</title>

   <link rel="shortcut icon" href="../../assets/ico/favicon.png">   

   <!-- Bootstrap core CSS -->
   <link href="bootstrap3_bookTheme/dist/css/bootstrap.min.css" rel="stylesheet">
   <!-- Bootstrap theme CSS -->
   <link href="bootstrap3_bookTheme/theme.css" rel="stylesheet">


   <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
   <!--[if lt IE 9]>
   <script src="bootstrap3_bookTheme/assets/js/html5shiv.js"></script>
   <script src="bootstrap3_bookTheme/assets/js/respond.min.js"></script>
   <![endif]-->
</head>

<body>

<?php include 'includes/book-header.inc.php'; ?>
   
<div class="container">
   <div class="row">  <!-- start main content row -->

      <div class="col-md-2">  <!-- start left navigation rail column -->
         <?php include 'includes/book-left-nav.inc.php'; ?>
      </div>  <!-- end left navigation rail --> 

      <div class="col-md-6">  <!-- start main content column -->
        
         <!-- book panel  -->
         <div class="panel panel-danger spaceabove">           
           <div class="panel-heading"><h4> </h4></div>
           <table class="table">
             <tr>
               <th>Cover</th>
               <th>ISBN</th>
               <th>Title</th>
             </tr>
             <?php include 'includes/database.inc.php';
                $query;
                $myPdoH = setConnectionInfo(); 
                $titleQuery = "SELECT ID,coverImage,ISBN10, Title FROM Books order by Title";
                $query = $titleQuery;
                $dataRow = $myPdoH->query($query);
                
                while($individualRow = $dataRow->fetch()){
                    echo"<tr>";
                    echo '<td><img src="images/tinysquare/'.$individualRow['ISBN10'].'.jpg"/></td>';
                       //echo"<td>".$individualRow['coverImage']."</td>";
                        echo"<td>".$individualRow['ISBN10'] ."</td>";
                        


                        //echo"<td>".$individualRow['Title'] ."</td>";
          echo"<td><form id=\"form-id".$individualRow['ID']. "\" method=\"post\" action=\"book-details.php\">";
              echo"<input type=\"hidden\" name=\"ID\" value=\"".$individualRow["ID"]."\">";
              echo"<div onclick=\"document.getElementById('form-id".$individualRow['ID']."').submit();\"><a>".$individualRow['Title'] ."</a></div>";
            echo"</form></td>";                    
                }
             ?>



           </table>
         </div>           
      </div>
      
      <div class="col-md-2">  <!-- start left navigation rail column -->
         <div class="panel panel-info spaceabove">
            <div class="panel-heading"><h4>Categories</h4></div>
               <ul class="nav nav-pills nav-stacked">
                 <?php 
                  $subRow = $myPdoH->query("SELECT SubcategoryName FROM Subcategories ORDER BY SubcategoryName ASC LIMIT 20");
                   while($iRow = $subRow->fetch()){
                    echo"<li><a>". $iRow['SubcategoryName'] ."</a></li>";
                   }
                 ?>
             </ul>
         </div>
                 
      </div>  <!-- end left navigation rail --> 
      
      <div class="col-md-2">  <!-- start left navigation rail column -->
         <div class="panel panel-info spaceabove">
            <div class="panel-heading"><h4>Imprints</h4></div>
            <ul class="nav nav-pills nav-stacked">
               <?php 
                  $impRow = $myPdoH->query("SELECT Imprint from Imprints");
                   while($imRow = $impRow->fetch()){
                    echo"<li><a>". $imRow['Imprint'] ."</a></li>";
                   }
                 ?>   
            </ul>
         </div>    

         <div class="panel panel-info">
            <div class="panel-heading"><h4>Status</h4></div>
            <ul class="nav nav-pills nav-stacked">
               <?php 
                  $prodRow = $myPdoH->query("SELECT ProductionStatus FROM ProductionStatuses");
                   while($pRow = $prodRow->fetch()){
                    echo"<li><a>". $pRow['ProductionStatus'] ."</a></li>";
                   }
                 ?>    
            </ul>
         </div>  
         
         <div class="panel panel-info">
            <div class="panel-heading"><h4>Binding</h4></div>
            <ul class="nav nav-pills nav-stacked">
              <?php 
                  $binRow = $myPdoH->query("SELECT BindingType  FROM BindingTypes");
                   while($bRow = $binRow->fetch()){
                    echo"<li><a>". $bRow['BindingType'] ."</a></li>";
                   }
                 ?>    
            </ul>
         </div>           
      </div>  <!-- end left navigation rail -->       


      </div>  <!-- end main content column -->
   </div>  <!-- end main content row -->
</div>   <!-- end container -->
   


   
   
 <!-- Bootstrap core JavaScript
 ================================================== -->
 <!-- Placed at the end of the document so the pages load faster -->
 <script src="bootstrap3_bookTheme/assets/js/jquery.js"></script>
 <script src="bootstrap3_bookTheme/dist/js/bootstrap.min.js"></script>
 <script src="bootstrap3_bookTheme/assets/js/holder.js"></script>
</body>
</html>