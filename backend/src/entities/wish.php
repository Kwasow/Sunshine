<?php

class Wish implements JsonSerializable
{
    private $id;
    private $author;
    private $content;
    private $done;
    private $timestamp;

    public function __construct($id, $author, $content, $done, $timestamp)
    {
        $this->id = $id;
        $this->author = $author;
        $this->content = $content;
        $this->done = $done;
        $this->timestamp = $timestamp;
    }

    public function jsonSerialize()
    {
        return [
        'id' => $this->id,
        'author' => $this->author,
        'content' => $this->content,
        'done' => $this->done,
        'timestamp' => $this->timestamp
        ];
    }
}
