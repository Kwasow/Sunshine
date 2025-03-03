<?php

class Wish implements JsonSerializable
{
  
    private $_id;
    private $_author;
    private $_content;
    private $_done;
    private $_timestamp;

    public function __construct($id, $author, $content, $done, $timestamp)
    {
        $this->_id = $id;
        $this->_author = $author;
        $this->_content = $content;
        $this->_done = $done;
        $this->_timestamp = $timestamp;
    }

    public function jsonSerialize()
    {
        return [
        'id' => $this->_id,
        'author' => $this->_author,
        'content' => $this->_content,
        'done' => $this->_done,
        'timestamp' => $this->_timestamp
        ];
    }

}

?>
