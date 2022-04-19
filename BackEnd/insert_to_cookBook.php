<?php

include ("db_info.php");


$recipe_name = $_POST["name"];
$calories = $_POST["calories"];
$instructions = $_POST["instructions"];
$time = $_POST["time"];
$ing = $_POST["ingredients"];


$query = $mysqli->prepare("INSERT INTO recipe (Recipe_name, calories, Instructions, cooktime, Ingredients) VALUES (?, ?, ?, ?,?)");
$query->bind_param("sssss", $recipe_name, $calories,$instructions,$time,$ing);
$query->execute();

$response = [];
$response["status"] = "Completed!";

$json_response = json_encode($response);
echo($json_response);

?>