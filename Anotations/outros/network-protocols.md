# Network Protocols Theory

Most network protocols have a structure that we can divide into two parts:
- Header: Contains traffic and control information specific to the protocol, each with its own structure.
- Payload: Contains the data that the protocol carries.

## Reference Models

Previously, you learned that communication between machines with different hardware and software is possible due to protocols. This standardization serves as a reference for software developers and hardware manufacturers to create compatible products.

The OSI (Open Systems Interconnection) model is a conceptual model consisting of 7 layers. The TCP/IP model has 4 layers and is a simplification of the OSI model.

## Communication in Detail

In our example, with a client accessing a web server in an internal network, various protocols are involved (Ethernet, ARP, IP, TCP, HTTP), each operating in a specific layer.

## Protocols Overview

### Ethernet Protocol

- Operates at Layer 2 of the OSI model.
- Encapsulates the IP protocol in local networks.
- Recognizes only physical addresses (MAC Address).
- Frame structure includes destination and source MAC addresses, type (identifying the payload protocol), payload (data to be transported), and checksum.

### ARP Protocol

- Discovers the MAC address associated with a given IP address.
- Utilizes ARP Request and ARP Reply to query and respond to IP-MAC mappings.
- ARP table stores IP-MAC associations.
- Important for activities like network discovery and ARP spoofing attacks.

### IP Protocol

- Responsible for packet delivery between machines.
- Not inherently reliable; relies on associated protocols like TCP or UDP for reliability or speed.
- Header structure includes version, IHL (header size), type of service, total length, identification, flags, time to live, protocol, header checksum, source and destination addresses, and options.

These protocols and their structures are crucial for understanding network communication and conducting effective penetration testing.


# Understanding Packet Fragmentation

An Ethernet frame typically accepts a maximum of 1500 bytes in its payload. If an IP packet exceeds this size, it must be broken into multiple pieces, a process known as packet fragmentation. Let's delve into this to understand not only how fragmentation works but also the fields identification, flags, and fragment offset.

## Fragmentation in Practice

A typical Ethernet frame accommodates a maximum of 1500 bytes in its payload, often carrying another protocol such as ARP or IP. When an IP packet surpasses 1500 bytes, it undergoes fragmentation—division into parts, each no larger than 1500 bytes. The Identification field in the IP header identifies these fragments. Flags in the header, specifically the MF (More Fragments) flag, indicate the presence of additional fragments. The fragment offset assists in organizing the sequence of fragments.

## Summary

In summary, an Ethernet frame allows a maximum payload of 1500 bytes, typically containing another protocol like ARP or IP. If an IP packet exceeds this limit, it undergoes fragmentation, with the Identification field in the IP header helping identify fragments, the MF flag indicating additional fragments, and the fragment offset assisting in sequencing the fragments.

# Encapsulation

The concept of encapsulation refers to the layering of protocols within each other, forming a hierarchical structure. This ensures that each layer handles specific functionalities, promoting modularity and flexibility. Let's explore the encapsulation in the context of networking protocols.

- **Ethernet Protocol:** This protocol operates at the data link layer (Layer 2) and utilizes MAC addresses. Its payload often includes the IP protocol.

- **IP Protocol:** Positioned at the network layer (Layer 3), the IP protocol operates with logical IP addresses. Its payload commonly contains the TCP protocol.

- **TCP Protocol:** Functioning at the transport layer (Layer 4), TCP communicates using port numbers.

To elaborate:
- The Ethernet protocol communicates with physical addresses (MAC Address).
- The IP protocol communicates with logical addresses (IP address).
- The TCP protocol communicates with port numbers.

This encapsulation allows for a modular and organized approach to network communication, with each layer responsible for specific aspects of the data exchange process.


# TCP Protocol

The Transmission Control Protocol (TCP) is a reliable protocol used for data transmission, ensuring the delivery of information. Unlike the IP protocol, TCP doesn't always have a payload. Let's delve into the structure of the TCP header and understand the TCP flags.

## TCP Header Structure

- **Source Port:** Indicates the origin port (ranging from 0 to 65535).
- **Destination Port:** Specifies the destination port.
- **Sequence Number:** Identifies the TCP segment.
- **Acknowledgment:** Confirms the segment, indicating the sequence number of the next segment.
- **Data Offset:** Represents the size of the TCP header.
- **Flags TCP:** URG, ACK, PSH, RST, SYN, FIN.
- **Windows (Window Size):** Determines the number of bytes the next segment can have.
- **Checksum:** Verifies the header and payload, including the IP header.
- **Urgent Pointer:** Activated when the URG flag is active, prioritizing content at the beginning of the payload.
- **Options:** Adds additional functionalities.

## TCP Flags Overview

- **SYN:** Synchronize - initiates a connection.
- **FIN:** Finish - indicates the connection should be terminated.
- **RST:** Reset - used when communication is not understood or encounters an issue.
- **PSH:** Push - indicates data in the TCP payload.
- **ACK:** Acknowledge - confirms knowledge of the next sequence number.
- **URG:** Urgent - prioritizes content.

## TCP Connection Establishment - Three-Way Handshake

TCP is connection-oriented, meaning it only transmits if a connection is successfully established. The initiation of this initial connection is known as the three-way handshake.

1. The client sends a packet with the SYN flag, indicating a desire to synchronize and start a connection.
2. The server responds with a packet containing SYN/ACK flags, confirming the beginning of the connection.
3. The client sends a packet with the ACK flag, confirming, completing the three-way handshake, and initiating information exchange.

## Connection Termination

When hosts wish to terminate the connection, the FIN flag comes into play.

## Connection Issues

In cases of connection problems, such as the server responding with the RST/ACK flags, it indicates the server's inability to understand. This can occur if the service is not active (closed port).


# UDP Protocol and DNS

## User Datagram Protocol (UDP)

The User Datagram Protocol (UDP), like TCP, is a data transport protocol; however, it is unreliable, meaning it doesn't guarantee data delivery. Despite its lack of control, UDP is faster due to its streamlined nature. It is not connection-oriented, allowing transmission without establishing a connection. Let's explore the UDP header.

### UDP Header Structure

- **Source Port:** The originating port, generated randomly in our example (e.g., 51183).
- **Destination Port:** The destination port, such as the DNS server port (e.g., 53).
- **Length:** Total size of the UDP segment.
- **Checksum:** Ensures data integrity.

UDP commonly operates in scenarios prioritizing speed, like audio and video transmissions. It is frequently used in services like DNS, DHCP, SNMP, etc.

## Domain Name System (DNS)

Before DNS, accessing resources on the internet required knowing their IP addresses, making navigation challenging. DNS resolves this issue by translating names to IP addresses. Let's delve into DNS and its workings.

### DNS Operation

1. **Translation Request:** When entering a domain like facebook.com, the client queries the DNS server for the corresponding IP address.
2. **DNS Resolution:** The DNS server responds with the IP address (e.g., 157.240.222.35), enabling communication with the host.
3. **DNS Configuration:** Hosts are configured with DNS server settings, crucial for resolving internet host names.
4. **Public DNS Servers:** In our example, the client uses Google's public DNS servers (8.8.8.8) for name resolution.

### DNS Server Hierarchy

If the DNS server has the information, it directly provides the IP address. Otherwise, it queries root servers, cascading down to find the DNS responsible for the sought IP. Root servers store data on Top Level Domains (TLDs), aiding in name resolution. All DNS servers must have the addresses of root servers in their configurations.