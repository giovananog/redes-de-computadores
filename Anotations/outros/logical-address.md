# IP Address Overview

An IP address consists of four octets represented in decimal form and separated by dots.

Example: `XXX.XXX.XXX.XXX`

IP addresses can be assigned statically (user-configured) or dynamically through DHCP.

There are various classifications of IP addresses, each serving to define usage in a network based on needs and the number of hosts. Additionally, subnet masks are used to segment the network.

Example: `255.255.255.0`

In this example, with IP `192.168.0.102` and a subnet mask of `255.255.255.0`, we define the network and host portions. The range for each octet is 0 to 255. In the network `192.168.0`, we can have up to 254 hosts. Adjusting the subnet mask (e.g., `255.255.0.0`) can increase the number of hosts.

It's crucial to note that an IP like `192.168.0.102` is for internal network use and isn't directly accessible from the internet. Public IPs are used for internet accessibility.

## Summary:

- **IP Address Format:** XXX.XXX.XXX.XXX
- **Assignment:** Static or Dynamic (DHCP).
- **Classification:** Defines network usage based on needs and host quantity.
- **Subnet Mask:** Segments the network, e.g., `255.255.255.0`.
- **IP Range:** 0 to 255 per octet.
- **Internal vs. External:** Internal IPs (e.g., `192.168.x.x`) are not directly accessible from the internet. Public IPs are used for internet connectivity.

Understanding IP addresses, their classifications, and subnetting is essential for network configuration and management.
