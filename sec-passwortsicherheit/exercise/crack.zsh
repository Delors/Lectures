#!/bin/zsh

INPUT_FILE="$1"

typeset -a predefined_values
predefined_values=(
    "ac3160b0a933ac03d7fb269baf8443e65936aa4322881e30c60443d7dda152d5"
    "748224afd4d37f9c3cae97bf2d38068a2ca0fabfa2f751a391bd2ac958df5403"
    "6e609749618fa564c83d712f96706337f6a78ef9132c16bea215774c8c1382be"
    "9d394775682dde10d89eb667f1049312d76bfcfc030a33f2598b117eb30a3966")

if [[ ! -f "$INPUT_FILE" ]]; then
  echo "File '$INPUT_FILE' not found."
  exit 1
fi

while IFS= read -r line || [[ -n "$line" ]]; do
  hash=$(echo -n "$line" | sha256sum | awk '{print $1}')
  for val in "${predefined_values[@]}"; do
    if [[ "$hash" == "$val" ]]; then
        echo "$val": "$line"
    fi
  done
done < "$INPUT_FILE"
