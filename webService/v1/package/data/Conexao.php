<?php

class Conexao {
    private $user= 'test';
    private $pass= 'Test_root_123';
    private $dbname ='test_db';
    private $servidor='localhost';
    private $dns = '';
    public function Conexao() {
		try {
			$pdo = new PDO("mysql:host=$this->servidor;dbname=$this->dbname;charset=UTF8;",  $this->user,  $this->pass);
			return $pdo;
		} catch (PDOException $e) {
			echo 'Connection failed: ' . $e->getMessage();
		}       
	}
}
