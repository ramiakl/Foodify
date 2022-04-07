<?php

include ("db_info.php");


$item = $_POST["item"];
$price = $_POST["price"];
$weight = $_POST["weight"];


$json_response = json_encode($response);
echo($json_response);


$query = $mysqli->prepare("INSERT INTO cart (item, price, Weight) VALUES (?, ?, ?)");
$query->bind_param("sss", $item, $price,$weight);
$query->execute();

?>