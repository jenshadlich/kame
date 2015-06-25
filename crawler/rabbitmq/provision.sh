#!/bin/bash
export DEBIAN_FRONTEND=noninteractive

# install RabbitMQ signing key if it was not already added
if ! `apt-key list | grep -q "RabbitMQ Release Signing Key <info@rabbitmq.com>"`; then
    wget https://www.rabbitmq.com/rabbitmq-signing-key-public.asc
    apt-key add rabbitmq-signing-key-public.asc
fi

apt-get update > /dev/null
apt-get -q -y install rabbitmq-server

# enable RabbitMQ plugins
service rabbitmq-server stop
rabbitmq-plugins enable rabbitmq_management
service rabbitmq-server start

# show RabbitMQ status
service rabbitmq-server status
