<?php

require_once __DIR__.'/../../config/config.php';
require_once __DIR__.'/../../src/database.php';
require_once __DIR__.'/../../src/helpers/authorization.php';

# Open database connection
$conn = openConnection();

# Check if user is authorized
$user = checkAuthorization($conn);
if ($user !== NULL) {
  http_response_code(200);
} else {
  http_response_code(401);

  mysqli_close($conn);
  exit();
}

# Get request details
$postData = json_decode(file_get_contents('php://input'), true);
$author = $postData['author'];
$content = $postData['content'];
$done = intval(FALSE);
$timestamp = strval($postData['timestamp']);

# Add wish to database
$stmt = mysqli_prepare(
  $conn,
  'INSERT INTO Wishlist VALUES(NULL, ?, ?, ?, ?)'
);
mysqli_stmt_bind_param($stmt, 'ssis', $author, $content, $done, $timestamp);
mysqli_stmt_execute($stmt);

mysqli_close($conn);
exit();

?>
