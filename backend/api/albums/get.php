<?php

require_once __DIR__.'/../../config/config.php';
require_once __DIR__.'/../../src/database.php';
require_once __DIR__.'/../../src/entities/album.php';
require_once __DIR__.'/../../src/entities/track.php';
require_once __DIR__.'/../../src/helpers/authorization.php';

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

// Get albums from database and return as JSON
header('Content-Type: application/json; charset=utf-8');

$result = mysqli_query(
    $conn,
    'SELECT * FROM Albums ORDER BY id'
);

$albums = [];
while ($row = mysqli_fetch_assoc($result)) {
    $uuid = $row['uuid'];

    // TODO: Fetch tracks
    $stmt = mysqli_prepare(
        $conn,
        'SELECT * FROM Tracks WHERE album_uuid = ? ORDER BY id'
    );
    mysqli_stmt_bind_param($stmt, 's', $uuid);
    mysqli_stmt_execute($stmt);

    $result_tracks = $stmt->get_result();
    $stmt->close();

    $tracks = [];
    while ($row_track = mysqli_fetch_assoc($result_tracks)) {
        $tracks[] = new AudioTrack(
            $row_track['id'],
            $row_track['title'],
            $row_track['comment'],
            $row_track['resource_name'],
            $row_track['album_uuid'],
        );
    }

    $albums[] = new Album(
        intval($row['id']),
        $row['uuid'],
        $row['title'],
        $row['artist'],
        $row['cover_name'],
        $tracks
    );
}

echo json_encode($albums);

mysqli_close($conn);
exit();

?>
