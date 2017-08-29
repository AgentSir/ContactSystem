#!/usr/bin/env bash
sudo apt-get update
echo "Installing Git.."
sudo apt-get install -y git
echo "Installing Maven.."
sudo apt-get install -y maven
echo "Installing Java 8.."
sudo apt-get install -y software-properties-common python-software-properties
echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
sudo add-apt-repository ppa:webupd8team/java -y
sudo apt-get update
sudo apt-get install oracle-java8-installer
echo "Setting environment variables for Java 8.."
sudo apt-get install -y oracle-java8-set-default
echo "Installing MYSQL.."
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password root'
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password root'
sudo apt-get -y update
sudo apt-get -y install mysql-server-5.5
echo "Build ContactSystem project with maven.."
if [ -d "/mnt/contactsystem" ];
	then 
		cd /mnt/contactsystem && mvn clean install package
else
    git clone https://github.com/AgentSir/ContactSystem.git /vagrant
fi
echo "Dump.."
if [ -f /mnt/contactsystem/data/db/backup_migrate/dump.sql ];
    then
        mysql -uroot -proot < /mnt/contactsystem/data/db/backup_migrate/dump.sql
fi
echo "Run application.."
if [ -f /mnt/contactsystem/target/contactsystem.jar ];
	then 
		cd /mnt/contactsystem/target && java -jar contactsystem.jar > /dev/null &
fi
