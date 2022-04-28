<?php

include ("db_info.php");


$item = $_POST["name"];
$expiration_date = $_POST["doe"];
$location = $_POST["location"];
$weight = $_POST["weight"];
$id = 14;

$response = [];
$response["status"] = "Completed!";

$json_response = json_encode($response);
echo($json_response);

$query = $mysqli->prepare("INSERT INTO pantry (item_name, date_of_expiration, location, weight, user_id) VALUES (?, ?, ?,?,?)");
$query->bind_param("ssssi", $item, $expiration_date, $location,$weight,$id);
$query->execute();

?>