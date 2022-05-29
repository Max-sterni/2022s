using LinearAlgebra
using Plots

include("2-iterative_solvers.jl")


#---------------------- Discretization Matrix ----------------------
N = 20

D = Matrix{Float64}(4.0*I, N-2, N-2)
D[diagind(D, 1)] .= -1
D[diagind(D, -1)] .= -1

A = kron(Matrix{Float64}(I, N-2, N-2), D)
A[diagind(A, N-2)] .= -1
A[diagind(A, -(N-2))] .= -1

u = zeros( N, N )

#------------------------- Boundaries -----------------------------
walltemp =   #TODO
heatertemp = #TODO
windowtemp = #TODO

u[:, 1] .= walltemp
u[:, end] .= walltemp
u[1, :] .= walltemp
u[end, :] .= walltemp

_N = (N/10.0)
bWindow = range( Int( floor(_N*3) ), stop = Int( floor(_N*8) ) )
bHeater_1 = range( Int( floor(_N*2 ) ), stop = Int( floor(_N*4) ) )
bHeater_2 = range( Int( floor(_N*7) ), stop = Int( floor(_N*9) ) )
u[bWindow, end] .= #TODO
u[bHeater_1, 1] .= #TODO
u[bHeater_2, 1] .= #TODO

#---------------- solution vector and rhs vector ------------------
x = vec( @view u[2:end-1, 2:end-1] )

# rhs vector (add boundary conditions)
b = zeros( N - 2, N - 2 )
b[:, 1] +=   #TODO
b[:, end] += #TODO
b[1, :] +=   #TODO
b[end, :] += #TODO

b = vec(b);


#------------------------------ Solve Laplace Equation ------------------------------
#------------------------------ [ Exercise 2 ] ------------------------------
# TODO: Solve the Laplace Equation with gaußSeidel and Jacobi method

@time x[:, :], r = conjgrad(A, b, 1000)

plot(
    heatmap(#TODO,
            title="Temperature"),

    surface(#TODO,
            zlabel="Temperature", camera=(45, 45), fillalpha=0.9, colorbar=false),

    plot(#TODO
            title="Convergence of CG",
            xlabel="iterations", ylabel="residual norm"
            ),
    layout = (1, 3), size=(480*3, 480)
    )
#------------------------------ [ Exercise 2 ] ------------------------------
