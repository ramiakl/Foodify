<?php

include ("db_info.php");

$name = $_POST['name'];
$password = hash("sha256", $mysqli->real_escape_string ($_POST['password']));

$query = $mysqli->prepare("SELECT user_id From user where username = ? AND password = ?");
$query->bind_param("ss", $name, $password);
$query->execute();


$array = $query->get_result();
$response = [];

while($ids = $array->fetch_assoc()){
    $response[] = $ids;
}
$json_response = json_encode($response);

if($array->num_rows > 0){  
    echo $json_response; 
}  
else{  
    echo("NO");  
}     

?>