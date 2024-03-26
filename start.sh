docker compose down

docker build -t wexbackendchallenge .

docker compose up --build --force-recreate --remove-orphans