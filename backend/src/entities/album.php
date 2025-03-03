<?php

class Album implements JsonSerializable {
  
  private $id;
  private $uuid;
  private $title;
  private $artist;
  private $coverName;
  private $tracks;


  public function __construct($id, $uuid, $title, $artist, $coverName, $tracks) {
    $this->id = $id;
    $this->uuid = $uuid;
    $this->title = $title;
    $this->artist = $artist;
    $this->coverName = $coverName;
    $this->tracks = $tracks;
  }

  public function jsonSerialize() {
    return [
        'id' => $this->id,
        'uuid' => $this->uuid,
        'title' => $this->title,
        'artist' => $this->artist,
        'coverName' => $this->coverName,
        'tracks' => $this->tracks
    ];
  }

}

?>
