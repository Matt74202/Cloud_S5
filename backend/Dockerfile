# Use PHP Apache base image
FROM php:8.3-apache

# Install system dependencies
RUN apt-get update && apt-get install -y \
    libzip-dev \
    unzip \
    libpng-dev \
    libjpeg-dev \
    libfreetype6-dev \
    libpq-dev \
    && docker-php-ext-configure gd --with-freetype --with-jpeg \
    && docker-php-ext-install zip gd pdo pdo_pgsql \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Copy Composer from official image
COPY --from=composer:2.7 /usr/bin/composer /usr/bin/composer

# Set working directory
WORKDIR /var/www/html

# Copy the Apache configuration
COPY apache.conf /etc/apache2/sites-available/000-default.conf

# Enable necessary Apache modules
RUN a2enmod rewrite
RUN a2enmod headers

# Copy application files
COPY . .

# Install Composer dependencies
RUN composer install --no-interaction --optimize-autoloader --prefer-dist

# Generate Symfony cache
RUN php bin/console cache:warmup

# Set proper permissions
RUN chown -R www-data:www-data /var/www/html/var /var/www/html/public /var/www/html/config && \
    chmod -R 777 /var/www/html/var

# Expose port 80
EXPOSE 80

# Start Apache
CMD ["apache2-foreground"]