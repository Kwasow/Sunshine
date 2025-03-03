<?php

class Memory implements JsonSerializable
{
    private $_id;
    private $_startDate;
    private $_endDate;
    private $_title;
    private $_description;
    private $_photo;

    public function __construct(
        $id,
        $startDate,
        $endDate,
        $title,
        $description,
        $photo
    ) {
        $this->_id = $id;
        $this->_startDate = $startDate;
        $this->_endDate = $endDate;
        $this->_title = $title;
        $this->_description = $description;
        $this->_photo = $photo;
    }

    public function jsonSerialize()
    {
        return [
        'id' => $this->_id,
        'startDate' => $this->_startDate,
        'endDate' => $this->_endDate,
        'title' => $this->_title,
        'description' => $this->_description,
        'photo' => $this->_photo
        ];
    }

}

?>
