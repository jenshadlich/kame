#!/bin/bash
export DEBIAN_FRONTEND=noninteractive

wget https://www.rabbitmq.com/rabbitmq-signing-key-public.asc
sudo apt-key add rabbitmq-signing-key-public.asc

apt-get update > /dev/null
apt-get -y install rabbitmq-server

rabbitmq-plugins enable rabbitmq_management
service rabbitmq-server restart
