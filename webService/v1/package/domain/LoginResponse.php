<?php


class LoginResponse {
    public $id;
    public $name;
    public $email;

    public function __construct($id=-1, $name="", $email="" ){
	$this->id = $id;
	$this->name = $name;
	$this->email = $email;
    }
}
