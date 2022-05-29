using Test


include("multivariate_newton.jl")


function f(x)
    return [x[1]^2; x[2]^2]
end


function g(x)
    return [x[1]^2*x[2]^3 - x[1]*x[2]^3 - 1; x[1]^3 - x[1]*x[2]^3 - 4]
end


function h(x)
    return [3*x[1] - cos(x[2]*x[3]) - 3/2; 4*x[1]^2 - 625*x[2]^2 + 2*x[3] - 1; 20*x[3] + exp(-x[1]*x[2]) + 9]
end


@testset "Newton's method for roots" begin
    @testset "searching in two dimensions" begin
        @test isapprox(multivariate_newton(f, jacobian_f, [1, 1]), [0, 0]; atol=1e-3)
        @test isapprox(multivariate_newton(g, jacobian_g, [1, 1]), [1.9797137430906355, -0.344204959140632]; atol=1e-3)
    end
    @testset "searching in three dimensions" begin
        @test isapprox(multivariate_newton(h, jacobian_h, [1, 1, 1]), [0.00031377085634994104, 7314.379828667651, 0.004113033998436877]; atol=1e-3)
    end
end
