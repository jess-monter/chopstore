aws ecr get-login-password --region us-east-2 --profile py2lab | docker login --username AWS --password-stdin 356250700523.dkr.ecr.us-east-2.amazonaws.com

docker build --build-arg JAR_FILE=target/*.jar -t chopstore .

docker tag chopstore:latest 356250700523.dkr.ecr.us-east-2.amazonaws.com/chopstore:latest

docker push 356250700523.dkr.ecr.us-east-2.amazonaws.com/chopstore:latest
