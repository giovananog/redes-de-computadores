# Internet Control Message Protocol (ICMP)

## ICMP Overview

The Internet Control Message Protocol (ICMP) serves as a message-oriented alert protocol, providing network status notifications. ICMP's basic structure involves types and codes, each serving a specific function.

### Examples of ICMP Usage

#### Ping

The Ping utility, widely used to check network communication between hosts (assuming ICMP is not blocked), utilizes ICMP. For instance:
- Type 8 with code 0 represents an echo request (ping).
- Type 0 with code 0 is the echo reply, confirming successful communication.

Example command:
```bash
ping -c 1 192.168.0.200

Importance of ICMP in Practice
ICMP becomes crucial when dealing with UDP communication, lacking inherent control. Unlike TCP, UDP cannot signal issues independently. ICMP, specifically type 3, intervenes to provide information on communication problems.

Consider an example where a UDP client attempts communication with a closed port on a server. ICMP type 3 informs about the issue, enhancing diagnostic capabilities.

Additionally, ICMP aids in identifying non-existent hosts. Sending a ping to a non-existent host demonstrates ICMP's role in providing feedback on network occurrences.

Tracing Routes with ICMP
ICMP is instrumental in tracing routes. ICMP type 11 with code 0, triggered when the Time-to-Live (TTL) becomes zero, informs the sender. Using this, one can trace the complete route of a packet and identify all routers in the connection.

Example commands:
ping -c 1 -t 1 www.google.com
ping -c 1 -t 2 www.google.com

By manipulating TTL, routers send ICMP Time Exceeded messages, allowing for a comprehensive understanding of the packet's route. Traceroute leverages this technique to quickly trace the complete route.
Example command:
traceroute www.google.com
