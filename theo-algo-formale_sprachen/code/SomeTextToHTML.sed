# "<" und ">" ersetzen wir durch "&lt;" und "&gt;",
# ggf. Konflikte mit HTML Tags zu vermeiden
s/</\&lt;/g
s/>/\&gt;/g

# Kommentare
s/\(\*(.+)\*\)/<!--\1-->/g

s/^#### (.*)$/<h4>\1<\/h4>/ 
s/^### (.*)$/<h3>\1<\/h3>/ 
s/^## (.*)$/<h2>\1<\/h2>/ 
s/^# (.*)$/<h1>\1<\/h1>/ 

# Fett
# Temporäres ersetzen von "Escaped Sternen" durch ein nicht-genutztes Zeichen
s/\\\*/`/g 
s/\*([^ ])\*/<b>\1<\/b>/g
s/\*([^ ][^*]*[^ *])\*/<b>\1<\/b>/g
s/`/*/g

# Leere Zeilen konvertieren
s/^$/<br>/

# Alle Escapes entfernen
s/\\(.)/\1/g

# Monospaced
s/^:: (.*)$/<pre>\1<\/pre>/
s/^::$/<pre> <\/pre>/