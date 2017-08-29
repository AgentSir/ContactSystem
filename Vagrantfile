Vagrant.configure("2") do |config|

  # A standard Ubuntu 12.04 LTS 64-bit box
  config.vm.box = "hashicorp/precise64"
  config.vm.box_version = "1.0.0"  
  
  # Enable provisioning with a shell script.
  config.vm.provision "shell", path: "vagrant_provision.sh"
  
  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine.
  config.vm.network :forwarded_port, guest: 8088, host: 8088
  
  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  config.vm.network "private_network", ip: "192.168.33.10"

  config.vm.synced_folder "./", "/mnt/contactsystem"
end