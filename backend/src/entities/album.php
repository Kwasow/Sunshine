<?php

class Album implements JsonSerializable
{
  
    private $_id;
    private $_uuid;
    private $_title;
    private $_artist;
    private $_coverName;
    private $_tracks;


    public function __construct($id, $uuid, $title, $artist, $coverName, $tracks)
    {
        $this->_id = $id;
        $this->_uuid = $uuid;
        $this->_title = $title;
        $this->_artist = $artist;
        $this->_coverName = $coverName;
        $this->_tracks = $tracks;
    }

    public function jsonSerialize()
    {
        return [
        'id' => $this->_id,
        'uuid' => $this->_uuid,
        'title' => $this->_title,
        'artist' => $this->_artist,
        'coverName' => $this->_coverName,
        'tracks' => $this->_tracks
        ];
    }

}

?>
