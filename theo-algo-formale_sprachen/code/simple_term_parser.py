
#!/usr/bin/python3


import re
import argparse
import sys
from parsimonious.grammar import Grammar
from parsimonious import NodeVisitor
from parsimonious.nodes import Node


"""
Please note, PEGs do greedy matching and 
Parsimonious also does not support left-recursion; 
hence, the following left-recursive grammar is 
not valid!
TERM_GRAMMAR = Grammar(
    r " " "    
    term         = (term "+" term) / int_value
    int_value    = ~r"[0-9]+"
    " " "
)
"""

TERM_GRAMMAR = Grammar(
    r"""    
    term         = (int_value "+" term) / int_value
    int_value    = ~r"[0-9]+"
    """
)



class TermTreeVisitor (NodeVisitor):
    # Note that this is a bottom-up visitor.

    def generic_visit(
        self, node, visited_children): return visited_children or node

    def visit_int_value(self, node, _): 
        return int(node.text)

    def visit_term(self, node, visited_children): 
        [children] = visited_children
        if isinstance(children,int):
            return children
        else:
            (l,_,r) = children
            return l + r


   
def main():
    parser = argparse.ArgumentParser(
        description="""Reads and evaluates a term."""
    )
    parser.add_argument(
        '-f',
        '--file',
        help="a file with the term"
    )
    args = parser.parse_args()
    if args.file:
        file = args.file
    else:
        file = "theo-algo-formale_sprachen/code/term.txt"
    with open(file, mode="r") as f:
        file = f.read()

    print("\nSource:")
    print("=====================================================================")
    print(file)
    tree = TERM_GRAMMAR.parse(file)

    print("\nSyntaxtree:")
    print("=====================================================================")
    print(tree)

    print("\nAST:")
    print("=====================================================================")
    print(TermTreeVisitor().visit(tree))


if __name__ == '__main__':
    sys.exit(main())
