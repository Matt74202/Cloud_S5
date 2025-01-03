<?php

namespace App\Helper;

use Doctrine\DBAL\DriverManager;

class DatabaseConnection
{
    public static function getConnection()
    {
        $connectionParams = [
            'dbname' => 'releve',
            'user' => 'postgres',
            'password' => 'itu16',
            'host' => 'localhost',
            'driver' => 'pdo_pgsql',
        ];

        $conn = DriverManager::getConnection($connectionParams);

        return $conn;
    }
}
