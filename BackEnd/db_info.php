<?php

$db_host = "localhost";
$db_user = "root";
$db_pass = null;
$db_name = "mobilefinalproject";

$con = mysqli_connect($db_host, $db_user, $db_pass, $db_name); 

if(mysqli_connect_errno()){
    die("Conenction Failed!");
}

?>