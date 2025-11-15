# We don't want caching for development purposes
npx http-server -p 8888 -c-1 -g --cors="Access-Control-Allow-Origin:*" --no-dotfiles .
