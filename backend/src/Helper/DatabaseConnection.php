<?php

namespace App\Helper;

use Doctrine\DBAL\DriverManager;

class DatabaseConnection
{
    public static function getConnection()
    {
        $connectionParams = [
            'dbname' => 'cloud',
            'user' => 'cloud',
            'password' => 'cloud',
            'host' => 'db',
            'driver' => 'pdo_pgsql',
        ];

        $conn = DriverManager::getConnection($connectionParams);

        return $conn;
    }
}
