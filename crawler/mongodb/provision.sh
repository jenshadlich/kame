#!/bin/bash
export DEBIAN_FRONTEND=noninteractive

if test -f /sys/kernel/mm/transparent_hugepage/enabled; then
   echo never > /sys/kernel/mm/transparent_hugepage/enabled
fi
if test -f /sys/kernel/mm/transparent_hugepage/defrag; then
   echo never > /sys/kernel/mm/transparent_hugepage/defrag
fi

apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 7F0CEB10
echo "deb http://repo.mongodb.org/apt/ubuntu "$(lsb_release -sc)"/mongodb-org/3.0 multiverse" | tee /etc/apt/sources.list.d/mongodb-org-3.0.list

sudo apt-get update

MONGO_VERSION=3.0.4

# install mongodb
sudo apt-get install -y mongodb-org=$MONGO_VERSION \
                        mongodb-org-server=$MONGO_VERSION \
                        mongodb-org-shell=$MONGO_VERSION \
                        mongodb-org-mongos=$MONGO_VERSION \
                        mongodb-org-tools=$MONGO_VERSION

# keep mongodb version
echo "mongodb-org hold" | sudo dpkg --set-selections
echo "mongodb-org-server hold" | sudo dpkg --set-selections
echo "mongodb-org-shell hold" | sudo dpkg --set-selections
echo "mongodb-org-mongos hold" | sudo dpkg --set-selections
echo "mongodb-org-tools hold" | sudo dpkg --set-selections

echo
echo "mongod.log (last line):"

tail -n 1 /var/log/mongodb/mongod.log

echo
echo "Done."