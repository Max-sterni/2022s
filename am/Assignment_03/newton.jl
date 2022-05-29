using Plots


# 3a
f = (x) -> x^2
f_prime = (x) -> 2 * x

g = (x) -> x^2 + 2 * cos(x) - 5
g_prime = (x) -> 2x - 2 * sin(x)

h = (x) -> 2 * x^2 * ℯ^x + 2 * x^3
h_prime = (x) -> ℯ^x * (4 * x + 2 * x^2) + 6* x^2

function newton_method(func, func_prime, start::Float64, iter::Integer)::Float64
    x = start

    for _ in 0:iter
        println(x)
        println(f(x))
        println(f_prime(x))
        println()
        x = x - (f(x)/f_prime(x))  # 3a
    end

    return x
end;


function plot_results(func, x, start, root, label)
    pLine = plot(x, func, xlabel="x", ylabel="y", label="", legend=:bottomright, title=label)
    plot!(pLine, [0], seriestype="hline", label="")
    scatter!(pLine, [start], [func(start)], color=:blue, label="start")
    scatter!(pLine, [root], [func(root)], color=:red, label="root")
end;


f_root = newton_method(f, f_prime, 0.0, 10)
#g_root = newton_method(g, g_prime, -1.0, 10)
#h_root = newton_method(h, h_prime, -1/2, 10)
println(h_root)

pf = plot_results(f, -3:0.1:0, -pi/3, f_root, "f(x)")
pg = plot_results(g, -4:0.1:-1, -1, g_root, "g(x)")
ph = plot_results(h, -1:0.1:1, -1/2, h_root, "h(x)")


p = plot(pf, pg, ph, layout=(1, 3))
plot!(size=(1200, 480))
savefig(p, "ex_newton.png")


#newton_method(f, f_prime, 0, 10)  # 3b
