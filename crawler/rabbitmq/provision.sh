#!/bin/bash
export DEBIAN_FRONTEND=noninteractive

wget https://www.rabbitmq.com/rabbitmq-signing-key-public.asc
apt-key add rabbitmq-signing-key-public.asc

apt-get update > /dev/null
apt-get -q -y install rabbitmq-server

# enable RabbitMQ plugins
service rabbitmq-server stop
rabbitmq-plugins enable rabbitmq_management
service rabbitmq-server start
