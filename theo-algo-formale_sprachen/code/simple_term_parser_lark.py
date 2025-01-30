from lark import Lark

GRAMMAR = """s: term
        term: term "+" term -> add
            | NUMBER -> no
    
        %import common.NUMBER
    """

l = Lark(GRAMMAR, start="s")
print(l.parse("1+2"))

"""
Tree(
    Token("RULE", "start"),
    [
        Tree(
            Token("RULE", "term"),
            [
                Tree(Token("RULE", "term"), [Token("NUMBER", "1")]),
                Tree(Token("RULE", "term"), [Token("NUMBER", "2")]),
            ],
        )
    ],
)


Tree(
    Token("RULE", "start"),
    [
        Tree(
            "add",
            [
                Tree("no", [Token("NUMBER", "1")]),
                Tree("no", [Token("NUMBER", "2")]),
            ],
        )
    ],
)
"""
