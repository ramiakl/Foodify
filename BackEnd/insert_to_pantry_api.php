<?php

include ("db_info.php");


$item = $_POST["name"];
$expiration_date = $_POST["doe"];
$location = $_POST["location"];
$weight = $_POST["weight"];

$response = [];
$response["status"] = "Completed!";

$json_response = json_encode($response);
echo($json_response);

$query = $mysqli->prepare("INSERT INTO pantry (item_name, date_of_expiration, location, Weight) VALUES (?, ?, ?,?)");
$query->bind_param("ssss", $item, $expiration_date, $location,$weight);
$query->execute();

?>