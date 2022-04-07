<?php

include ("db_info.php");


$user = $_POST["user"];
$password = $_POST["password"];
$email = $_POST["email"];


$date = date('d-m-y h:i:s'); 

$json_response = json_encode($response);
echo($json_response);

// i will later hash the password 

$query = $mysqli->prepare("INSERT INTO user (username, password, email,date) VALUES (?, ?, ?,?)");
$query->bind_param("ssss", $user, $password, $email,$date);
$query->execute();

?>