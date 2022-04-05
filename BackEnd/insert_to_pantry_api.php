<?php

include ("db_info.php");


$item = $_POST["item"];
$expiration_date = $_POST["date"];
$location = $_POST["location"];
$weight = $_POST["weight"];


$json_response = json_encode($response);
echo($json_response);

// i will later hash the password 

$query = $mysqli->prepare("INSERT INTO pantry (item_name, date_of_expiration, location, Weight) VALUES (?, ?, ?,?)");
$query->bind_param("ssss", $item, $expiration_date, $location,$weight);
$query->execute();

?>