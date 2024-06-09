### Introduction to HTTP Protocol

It's impossible to talk about the web without mentioning the HTTP protocol. Practically everything we access on the web today, such as websites, e-commerce platforms, web applications, etc., is only possible thanks to the HTTP protocol. Even in our example of communication between two hosts, we encountered access to a website and observed the entire communication process happening until it reached the payload carrying the HTTP protocol. In the capture below, we can see that the payload of the TCP protocol is the HTTP (Hypertext Transfer Protocol).

The HTTP protocol works through exchanges of requests between client and server. Typically, on the client side, we have the web browser, and on the server side, we have web servers. The client could also be some client application that understands the HTTP protocol; it doesn't necessarily need to be a browser. For example: curl. On the web server side, we can have various options such as Microsoft's IIS, the open-source Apache, nginx, etc.

The HTTP protocol operates with requests; for example, request messages are called HTTP Request, and response messages are called HTTP Response.

The HTTP protocol supports various methods such as GET, POST, HEAD, PUT, DELETE, OPTIONS, and each method has a function. The most commonly used are the GET method for requesting resources, and it's also common to find the POST method for sending data, for example, on login pages. The structure of HTTP consists of a Header and a Body; typically, in response to a successful GET request, the resource comes in the Body, and when we use the POST method to send data, we usually place it in the Body.

Every request receives a response code known as status; there are several status codes, but we'll only discuss the most commonly used ones:
- 200 OK - The request was successful
- 301 Moved Permanently - The resource has been moved (redirection)
- 403 Forbidden - The client does not have permission to access the requested resource
- 404 Not Found - The requested resource was not found
- 500 Internal Server Error - An error occurred on the server side
