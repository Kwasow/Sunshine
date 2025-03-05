<?php

require_once __DIR__.'/../../config/config.php';
require_once __DIR__.'/../../src/entities/wish.php';
require_once __DIR__.'/../../src/helpers/authorization.php';
require_once __DIR__.'/../../src/helpers/database.php';

// Open database connection
$conn = openConnection();

// Check if user is authorized
$user = checkAuthorization($conn);
if ($user !== null) {
    http_response_code(200);
} else {
    http_response_code(401);

    mysqli_close($conn);
    exit();
}

// Get wishlist from database and return as JSON
header('Content-Type: application/json; charset=utf-8');

$result = mysqli_query(
    $conn,
    'SELECT * FROM Wishlist'
);

$wishlist = [];
while ($row = mysqli_fetch_assoc($result)) {
    $wishlist[] = new Wish(
        $row['id'],
        $row['author'],
        $row['content'],
        boolval($row['done']),
        $row['time_stamp']
    );
}

echo json_encode($wishlist);

mysqli_close($conn);
exit();
