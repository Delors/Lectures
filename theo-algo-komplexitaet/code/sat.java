
// Intended to be run using Java > 23 (Tested with Java 23 and 24)
// May require --enable-preview to do so.

interface Expr {

    /**
     * An Optional True or Optional False if this expression 
     * definitively evaluates to the respective truth value 
     * with the given binding or an empty Optional otherwise.
     * 
     * Returning a truth value does not necessarily
     * require all variables to be bound to a definite
     * value. For example, Optional.empty will be returned,
     * if the truth value cannot be determined with the given
     * binding. E. g., if this expression represents a
     * variable for which the binding has no value,
     * Optional.empty is returned.
     * 
     * An expression such as "A ⋀ B" would return true
     * if A and B are both bound to "true"  and false 
     * if at least one of them is bound
     * to "false", and Optional.empty otherwise.
     */
    Optional<Boolean> isSolution(Map<Var, Boolean> binding);
}

class And implements Expr {

    private final Expr[] exprs;

    And(Expr... exprs) {
        this.exprs = exprs;
    }

    public Optional<Boolean> isSolution(Map<Var, Boolean> binding) {
        Optional<Boolean> r = Optional.of(true);
        for (var expr : this.exprs) {
            final var e = expr.isSolution(binding);
            if (!e.isPresent())
                r = Optional.empty();
            else if (!e.get())
                return e;
        }
        return r;
    }

    public String toString() {
        return Arrays.stream(exprs)
                .map(Expr::toString)
                .collect(Collectors.joining(" ⋀ "));
    }
}

class Or implements Expr {

    private final Expr[] exprs;

    Or(Expr... exprs) {
        this.exprs = exprs;
    }

    public Optional<Boolean> isSolution(Map<Var, Boolean> binding) {
        var r = Optional.of(false);
        for (var expr : this.exprs) {
            final var e = expr.isSolution(binding);
            if (!e.isPresent())
                r = Optional.empty();
            else if (e.get())
                return e;
        }
        return r;
    }

    public String toString() {
        return Arrays.stream(exprs)
                .map(Expr::toString)
                .collect(Collectors.joining(" ⋁ "));
    }
}

class Not implements Expr {

    private final Expr expr;

    Not(Expr expr) {
        this.expr = expr;
    }

    public Optional<Boolean> isSolution(Map<Var, Boolean> binding) {
        final var r = expr.isSolution(binding).map(b -> !b);
        return r;
    }

    public String toString() {
        return "¬" + expr;
    }
}

class Var implements Expr {

    private final String name;

    Var(String name) {
        this.name = name;
    }

    public Optional<Boolean> isSolution(Map<Var, Boolean> binding) {
        final var r = Optional.ofNullable(binding.get(this));
        return r;
    }

    public String toString() {
        return name;
    }

}

void main() {
    final Var A = new Var("a");
    final Var B = new Var("b");
    final Var C = new Var("c");
    final Var D = new Var("d");
    Stack<Var> vars = new Stack<>();
    vars.addAll(Arrays.asList(new Var[] { A, B, C, D }));
    Expr expr = new And(
            new Or(A, B),
            new Or(new Not(A), B),
            new Or(new Not(A), new Not(C)),
            new Or(C, D),
            new Or(new Not(C), new Not(D)));
    println("Finding solutions for: " + expr.toString());

    Map<Var, Boolean> solution = new HashMap<>();
    solve(expr, vars, solution);
}

void solve(Expr expr, Stack<Var> vars, Map<Var, Boolean> solution) {
    final var var = vars.pop();
    for (var v : new Boolean[] { true, false }) {
        solution.put(var, v);

        final var e = expr.isSolution(solution);
        // println("Current solution: " + solution+ " => "+ e);
        if (!e.isPresent()) {
            solve(expr, vars, solution);
        } else if (e.get()) {
            println("Solution found: " + solution);
        }
    }
    vars.push(var);
    solution.remove(var);
}
