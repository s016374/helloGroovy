/**
 * Created by s016374 on 16/4/29.
 */

def sParameterLessClosure = "1 + 2 == ${-> 3}"
assert sParameterLessClosure == '1 + 2 == 3'

int fib(int n) {
    n < 2 ? 1 : fib(n-1) + fib(n-2)
}
println fib(-1)


def foo(Map args) { "${args.name}: ${args.age}" }
println foo(name: 'Marie', age: 1)