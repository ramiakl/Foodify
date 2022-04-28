<?php

include ("db_info.php");


$item = $_POST["name"];
$price = $_POST["price"];
$weight = $_POST["weight"];
$id = 14;

$response = [];
$response["status"] = "Completed!";

$json_response = json_encode($response);
echo($json_response);

$query = $mysqli->prepare("INSERT INTO cart (item_name, price, weight, user_id) VALUES (?, ?, ?,?)");
$query->bind_param("sssi", $item, $price,$weight,$id);
$query->execute();


?>