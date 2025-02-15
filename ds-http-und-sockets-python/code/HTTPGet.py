#!/usr/bin/env python3

import socket
import sys
import os
from urllib.parse import urlparse

HOST = "www.michael-eichberg.de"
PORT = 80
FILE = "/index.html"

if sys.argv[1:]:
    url = urlparse(sys.argv[1])
    HOST = url.hostname
    if url.port:
        PORT = url.port
    FILE = url.path

the_request = f"GET {FILE} HTTP/1.1\r\nHost: {HOST}\r\n\r\n"
# print(the_request)

def receive_all(conn, chunk_size=1024):
    data = b''
    while True:
        part = conn.recv(chunk_size)
        data += part
        if len(part) == 0: break
    return data

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))  
    s.sendall(the_request.encode())
    data = receive_all(s)
    headerEndIndex = data.index(b"\r\n\r\n")
    header = data[:headerEndIndex].decode()
    # print(header)
    contentType = next(filter(lambda h: h.strip().lower().startswith("content-type"),header.split("\r\n")))
    content = data[headerEndIndex+4:]
    
    filename = FILE[1:]
    if "/"  in filename:
        os.makedirs(os.path.dirname(filename), exist_ok=True)

    if "text/" in contentType:
        content = content.decode()
        print(content)
        with open(filename , "w") as f:
            f.write(content)
    else:
        print(f"Content-Type: {contentType}; saving as binary file.")
        with open(filename , "wb") as f:
            f.write(content)
    
    