# Routing

In different networks, communication occurs through network routing, requiring the definition of a gateway.

In the example mentioned, the gateway is the IP address of the client's Wi-Fi router, which accesses a web server on the internet through a public IP. Routing is possible thanks to the gateway, directing the packet to leave the `192.168.0` network and reach the public IP.

# Ports

In a simplified manner, ports are used to connect client machines and servers during communication with a service.

Usually, servers have fixed port numbers for each service, while on the client side, random port numbers are used.

For example:
- A default WEB server (www) uses port 80.
- An FTP server uses port 21.
- An SSH server uses port 22, and so on.

You can check the list of default ports and services on Linux in the `/etc/services` file. While services often use default ports, they can be changed to any other available port.

Ports range from 0 to 65535 and are typically used with TCP and UDP protocols. On the client side, the port originating the access is usually a high port (above 1024) and is randomly assigned.

# Socket

We can say that the socket to connect to a server is represented by a combination of IP and Port, such as `192.168.0.200:80`. The socket is formed by the combination of the IP address and the port number.
