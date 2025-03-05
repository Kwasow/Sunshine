<?php

class AudioTrack implements JsonSerializable
{
  
    private $id;
    private $title;
    private $comment;
    private $resourceName;
    private $albumUuid;


    public function __construct($id, $title, $comment, $resourceName, $albumUuid)
    {
        $this->id = $id;
        $this->title = $title;
        $this->comment = $comment;
        $this->resourceName = $resourceName;
        $this->albumUuid = $albumUuid;
    }

    public function jsonSerialize()
    {
        return [
        'id' => $this->id,
        'title' => $this->title,
        'comment' => $this->comment,
        'resourceName' => $this->resourceName,
        'albumUuid' => $this->albumUuid
        ];
    }
}
