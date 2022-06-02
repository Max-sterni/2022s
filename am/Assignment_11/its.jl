using Distributions
using Plots

N = 4096*2 

U(a,b) = rand(Uniform(a,b), N)

module Example
    a=0
    b=1
    f="sin(x)/2"
    pdf(x) = sin(x)/2
    cdf(x) = (-cos(x)+1)/2
    inverse(x) = acos(1-2*x)
end
# TODO:
module A
    a = 0
    b = 0 
    f = "sin(x)"
    pdf(x) = 0
    cdf(x) = 0 
    inverse(x) = 0 
end

# TODO:
module B
    a = 0
    b = 0 
    f = "3*x^2"
    pdf(x) = 0
    cdf(x) = 0
    inverse(x) = 0 
end

# TODO:
module C
    a = 0
    b = 0 
    f = "exp(x)"
    pdf(x) = 0 
    cdf(x) = 0
    inverse(x) = 0
end

# plot helper
function plot_transformation(a,b,pdf,cdf,inverse,title)
    dist = U(a,b)
    i = inverse.(dist)
    x = minimum(i):10^-3:maximum(i)
    plot(
        plot(x, pdf.(x), title=title),
        plot(x, cdf.(x), title="f_CDF(x)"),
        histogram(i, title="transformation"),
        histogram(i, title="re-transformation"), # TODO: retrieve uniform distribution
        legend = false,
        layout = grid(1,4),
        size = (1200,300),
    )
end

modules = [ Example, A, B, C ]
plots = [plot_transformation(m.a,m.b,m.pdf,m.cdf,m.inverse,m.f) for m = modules]

# display distribution
nPlots = length(modules)
p = plot(plots..., layout = grid(nPlots,1), size = (1200, nPlots*300))
#savefig(p,"its.pdf")
display(p)
