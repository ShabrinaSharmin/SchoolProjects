

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

<?php include 'includes/book-header.inc.php';?>
   
<div class="container">
   <div class="row">  <!-- start main content row -->

      <div class="col-md-2">  <!-- start left navigation rail column -->
         <?php include 'includes/book-left-nav.inc.php'; ?>
      </div>  <!-- end left navigation rail --> 

      <div class="col-md-10">  <!-- start main content column -->
        
         <!-- book panel  -->
         <div class="panel panel-danger spaceabove">           
           <div class="panel-heading"><h4>Book Details</h4></div>
           
            <?php include 'includes/database.inc.php';
    
    //call connect function and retrieve pdo obj
            if(isset($_POST['ID'])){
             
              $bookId = $_POST['ID'];
              
              
              $bookQ = "SELECT Title, ISBN10, ISBN13, CopyrightYear, LatestInstockDate, TrimSize, PageCountsEditorialEst, Description FROM Books WHERE ID =" .$bookId;

              $imprintQ = "SELECT Imprint  FROM Imprints WHERE ID = (SELECT ImprintID from Books where ID=". $bookId. ")";

              $bindingQ = "SELECT BindingType  FROM BindingTypes WHERE ID = (SELECT BindingTypeID from Books where ID=". $bookId. ")";

              $subQ = "SELECT SubcategoryName  FROM Subcategories WHERE ID = (SELECT SubcategoryID from Books where ID=". $bookId . ")";
              $authorsQ = "SELECT FirstName, LastName FROM Authors WHERE ID IN (SELECT AuthorID from BookAuthors where BookId =". $bookId . ")";
              
              $proQ = "SELECT ProductionStatus  FROM ProductionStatuses WHERE ID = (SELECT ProductionStatusID from Books where ID=". $bookId . ")";


              $myPdoH = setConnectionInfo();

              

               $dataRow = $myPdoH->query($bookQ);
               $bookDataRow = $dataRow->fetch();
              }
              ?>



           <table class="table">
             <tr>
               <th>Cover</th>

               <td><?php echo'<img src="images/tinysquare/'.$bookDataRow['ISBN10'].'.jpg"/>'  ?></td>
             </tr>            
             <tr>
               <th>Title</th>
               <td><em><?php echo $bookDataRow['Title'];?></em></td>
             </tr>    
             <tr>
               <th>Authors</th>
               <td>
               <?php
               $authorRow = $myPdoH->query($authorsQ);
               while($individualRow = $authorRow->fetch()){
                echo $individualRow['FirstName']. " " . $individualRow['LastName']. "<br/>";
               }
               ?>
               </td>
             </tr>   
             <tr>
               <th>ISBN-10</th>
               <td><?php echo $bookDataRow['ISBN10'];?></td>
             </tr>
             <tr>
               <th>ISBN-13</th>
               <td><?php echo $bookDataRow['ISBN13'];?></td>
             </tr>
             <tr>
               <th>Copyright Year</th>
               <td><?php echo $bookDataRow['CopyrightYear'];?></td>
             </tr>   
             <tr>
               <th>Instock Date</th>
               <td>
               <?php echo $bookDataRow['LatestInstockDate'];?>
               </td>
             </tr>              
             <tr>
               <th>Trim Size</th>
               <td><?php echo $bookDataRow['TrimSize'];?></td>
             </tr> 
             <tr>
               <th>Page Count</th>
               <td><?php echo $bookDataRow['PageCountsEditorialEst'];?></td>
             </tr> 
             <tr>
               <th>Description</th>
               <td><?php echo $bookDataRow['Description'];?></td>
             </tr> 
             <tr>
               <th>Sub Category</th>
               <td>
                <?php
                $subDataRow = $myPdoH->query($subQ);
                $subRow = $subDataRow->fetch();
                echo $subRow['SubcategoryName'];
               ?>
               </td>
             </tr>
             <tr>
               <th>Imprint</th>
               <td> <?php
                $imprintDataRow = $myPdoH->query($imprintQ);
                $imprintRow = $imprintDataRow->fetch();
                echo $imprintRow['Imprint'];
               ?></td>
             </tr>   
             <tr>
               <th>Binding Type</th>
               <td> <?php
                $bindDataRow = $myPdoH->query($bindingQ);
                $bindRow = $bindDataRow->fetch();
                echo $bindRow['BindingType'];
               ?></td>
             </tr> 
             <tr>
               <th>Production Status</th>
               <td> <?php
                $pDataRow = $myPdoH->query($proQ);
                $pRow = $pDataRow->fetch();
                echo $pRow['ProductionStatus'];
                $myPdoH = NULL;
               ?></td>
             </tr>              
           </table>

         </div>           
      </div>



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