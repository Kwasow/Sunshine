<?php

class AudioTrack implements JsonSerializable
{
  
    private $_id;
    private $_title;
    private $_comment;
    private $_resourceName;
    private $_albumUuid;


    public function __construct($id, $title, $comment, $resourceName, $albumUuid)
    {
        $this->_id = $id;
        $this->_title = $title;
        $this->_comment = $comment;
        $this->_resourceName = $resourceName;
        $this->_albumUuid = $albumUuid;
    }

    public function jsonSerialize()
    {
        return [
        'id' => $this->_id,
        'title' => $this->_title,
        'comment' => $this->_comment,
        'resourceName' => $this->_resourceName,
        'albumUuid' => $this->_albumUuid
        ];
    }

}

?>
