<?php

include ("db_info.php");

$name = $_POST['name'];
$password = hash("sha256", $mysqli->real_escape_string ($_POST['password']));

$query = $mysqli->prepare("SELECT user_id From user where username = ? AND password = ?");
$query->bind_param("ss", $name, $password);
$query->execute();
$query->store_result();
$query->fetch();


if($query->num_rows > 0){  
    echo("YES"); 
}  
else{  
    echo("NO");  
}     

?>