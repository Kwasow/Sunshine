<?php

require_once __DIR__.'/../config/config.php';

// Only endpoint files are allowed to open database connections. All other
// functions should accept a connection as an argument.
function openConnection()
{
  $config = $GLOBALS['sunshineConfig'];

  $db_address = $config['databaseAddress'];
  $db_username = $config['databaseUsername'];
  $db_password = $config['databasePassword'];
  $db_database = $config['databaseName'];

  $conn = mysqli_connect($db_address, $db_username, $db_password, $db_database);

  if (!$conn) {
    // 500 - server error
    http_response_code(500);
    die('Could not connect to database: ' . mysqli_connect_error());
  } else {
    mysqli_set_charset($conn, 'utf8mb4');

    return $conn;
  }
}

?>
