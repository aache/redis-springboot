Redis Caching with Spring Boot 4 – Weather API

This project demonstrates how to build a high-traffic API using Spring Boot 4 and Redis to cache external API responses in a production-grade way.
It shows how to protect an upstream API from excessive calls while still serving thousands of client requests efficiently.

The example uses a Weather API, but the same pattern applies to stock prices, flight status, exchange rates, or any frequently queried external system.

Redis Caching with Spring Boot 4 – Weather API

This project demonstrates how to build a high-traffic API using Spring Boot 4 and Redis to cache external API responses in a production-grade way.
It shows how to protect an upstream API from excessive calls while still serving thousands of client requests efficiently.

The example uses a Weather API, but the same pattern applies to stock prices, flight status, exchange rates, or any frequently queried external system.

GET /api/weather/{city}

Each request returns the current weather for a city.

Instead of calling the upstream weather provider on every request, Redis is used as a cache with a 60-second TTL per city.

Within that 60-second window:
•	All requests are served from Redis
•	The external weather API is not called
•	All users receive the same snapshot of data

After the TTL expires:
•	The next request refreshes the cache
•	A new snapshot is stored
•	The cycle repeats