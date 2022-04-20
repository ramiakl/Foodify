<?php

include ("db_info.php");


$item = $_POST["name"];
$price = $_POST["price"];
$weight = $_POST["weight"];

$response = [];
$response["status"] = "Completed!";

$json_response = json_encode($response);
echo($json_response);

$query = $mysqli->prepare("INSERT INTO cart (item_name, price, weight) VALUES (?, ?, ?)");
$query->bind_param("sss", $item, $price,$weight);
$query->execute();


?>