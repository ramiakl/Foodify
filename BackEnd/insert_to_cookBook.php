<?php

include ("db_info.php");


$recipe_name = $_POST["name"];
$calories = $_POST["calories"];
$instructions = $_POST["instructions"];
$time = $_POST["time"];
$ing = $_POST["ingredients"];
$id = $_POST["user_id"];
$image = $_POST["image"];

$query = $mysqli->prepare("INSERT INTO recipe (Recipe_name, calories, Instructions, cooktime, Ingredients,image,user_id) VALUES (?, ?, ?, ?,?,?,?)");
$query->bind_param("sssssii", $recipe_name, $calories,$instructions,$time,$ing,$image,$id);
$query->execute();

$response = [];
$response["status"] = "Completed!";

$json_response = json_encode($response);
echo($json_response);

?>