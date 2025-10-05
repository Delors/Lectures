# Basis-Image verwenden 
# (Alpine-Linux mit einem vorinstallierten nginx.)
FROM nginx:alpine

# Arbeitsverzeichnis setzen innerhalb des Containers
WORKDIR /usr/share/nginx/html

# HTML-Datei kopieren 
# (kopiert die lokale Datei in den Container relativ zum WORKDIR)
COPY index.html .

# Port 80 freigeben
EXPOSE 80

# Nginx starten 
# (Nginx läuft dann im Vordergrund, damit der Docker-Container 
# sich nicht gleich wieder beendet.)
CMD ["nginx", "-g", "daemon off;"]