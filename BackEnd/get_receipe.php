<?php

include ("db_info.php");

$name = $_POST["name"];

$sql = "select * From recipe where Recipe_name = '$name'";  

$result = mysqli_query($con ,$sql);  
$row = mysqli_fetch_array($result, MYSQLI_ASSOC);  
$count = mysqli_num_rows($result);  
          
$response = [];

if($count == 1){  
    $response["status"] = "YES"; 
}  
else{  
    $response["status"] = "NO";  
}     

$json_response = json_encode($response);
echo $json_response;

?>