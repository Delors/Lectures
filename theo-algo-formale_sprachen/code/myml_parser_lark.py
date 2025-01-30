from lark import Lark

""" Das Ziel ist es folgende Texte zu parsen:

+ Überblick

Ich bin *Prof.* an der DHBW Mannheim [link: www.dhbw-mannheim.de]. Meine private Homepage finden sie hier: [link: www.michael-eichberg.de]

"""

GRAMMAR = r"""s: block+
        block: "+" WORD+ BLOCK_END? NL -> heading
            | "*" block "*" -> bold
            | "[link:" URL "]" -> link
            | WORD -> word
            | BLOCK_END -> line_end
        
        NL: /\r?\n/
        WORD: /[a-zA-Z]+/
        BLOCK_END: /[.:!?]/
        URL: /[a-zA-Z.-]+/
        %import common.WS_INLINE
        %ignore WS_INLINE
    """

l = Lark(GRAMMAR, start="s")
print(l.parse("""+ Wer bin ich?
Ich bin *Professor* an der DHBW Mannheim [link: www.dhbw-mannheim.de]. Meine private Homepage finden sie hier: [link: www.michael-eichberg.de]."""))

"""
Tree(
    Token("RULE", "s"),
    [
        Tree(
            "heading",
            [
                Token("WORD", "Wer"),
                Token("WORD", "bin"),
                Token("WORD", "ich"),
                Token("BLOCK_END", "?"),
                Token("NL", "\n"),
            ],
        ),
        Tree("word", [Token("WORD", "Ich")]),
        Tree("word", [Token("WORD", "bin")]),
        Tree("bold", [Tree("word", [Token("WORD", "Professor")])]),
        Tree("word", [Token("WORD", "an")]),
        Tree("word", [Token("WORD", "der")]),
        Tree("word", [Token("WORD", "DHBW")]),
        Tree("word", [Token("WORD", "Mannheim")]),
        Tree("link", [Token("URL", "www.dhbw-mannheim.de")]),
        Tree("line_end", [Token("BLOCK_END", ".")]),
        Tree("word", [Token("WORD", "Meine")]),
        Tree("word", [Token("WORD", "private")]),
        Tree("word", [Token("WORD", "Homepage")]),
        Tree("word", [Token("WORD", "finden")]),
        Tree("word", [Token("WORD", "sie")]),
        Tree("word", [Token("WORD", "hier")]),
        Tree("line_end", [Token("BLOCK_END", ":")]),
        Tree("link", [Token("URL", "www.michael-eichberg.de")]),
        Tree("line_end", [Token("BLOCK_END", ".")]),
    ],
)
"""