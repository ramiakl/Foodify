<?php

include ("db_info.php");

$user = $_POST['name'];
$email = $_POST['email'];
$password = hash("sha256", $mysqli->real_escape_string($_POST['password']));

$date = date('d-m-y h:i:s'); 

$query = $mysqli->prepare("SELECT user_id From user where username = ?");
$query->bind_param("s", $user);
$query->execute();
$query->store_result();
$query->fetch();

if($query->num_rows == 1){
    echo("NO");
}else{
    echo("YES");
    $query = $mysqli->prepare("INSERT INTO user (username, password, email,date) VALUES (?, ?, ?,?)");
    $query->bind_param("ssss", $user, $password, $email,$date);
    $query->execute();
}

?>