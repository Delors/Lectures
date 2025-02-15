#!/usr/bin/env python3

import sys
import socket

def scan_port(host, port):
  """
  Attempts to connect to a specific port on a host.
  Prints a message if the connection is successful.
  """
  try:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
      s.settimeout(0.5)  # Set a timeout to avoid hanging connections
      s.connect((host, port))
      print(f"Port {port} is open on {host}")
      s.close()
  except (ConnectionRefusedError, TimeoutError) as e:
    pass  # Port is likely closed, expected behavior

def main():
  """
  Gets the target host from command line arguments or defaults to localhost.
  Scans ports 1 to 1023.
  """
  host = "localhost"
  if len(sys.argv) > 1: host = sys.argv[1]
  for port in range(1, 1024): scan_port(host, port)

if __name__ == "__main__":
  main()

"""
./Portscanner.py gibt.es.nicht.de
Port 21 is open on gibt.es.nicht.de
Port 22 is open on gibt.es.nicht.de
Port 25 is open on gibt.es.nicht.de
CTRL-C
"""