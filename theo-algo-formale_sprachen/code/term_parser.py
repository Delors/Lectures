
#!/usr/bin/python3


import re
import argparse
import sys
from parsimonious.grammar import Grammar
from parsimonious import NodeVisitor
from parsimonious.nodes import Node


"""
Please note, PEGs do greedy matching and 
Parsimonious also does not support left-recursion.
"""
"""
The following grammar in combination with a 
bottom-up visitor would evaluate a term like
    1+2-3+4
as follows:
    1+2-3+4 = 1+(2-(3+4)) = -4
which is not the expected result!
"""
TERM_GRAMMAR = Grammar(
    r"""    
    # ATTENTION: do not move int_value to the front!
    term         = termp / termm / int_value
    termp        = (int_value "+" term)
    termm        = (int_value "-" term) 
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
        return children
 
    def visit_termp(self, node, visited_children): 
        (l,_,r) =  visited_children
        result = l + r
        return result

    def visit_termm(self, node, visited_children): 
        (l,_,r) = visited_children
        result = l - r
        return result

   
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
