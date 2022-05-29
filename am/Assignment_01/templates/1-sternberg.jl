#=
    Scalar/Scalar Multiplication
    hint: you can use `exp(log(a)+log(b))` to multiply two positive float values
=#
function multiply(a::Float64, b::Float64)
    if a == 0 || b == 0
        0
    elseif (a < 0 || b < 0) && !(a < 0 && b < 0)
        -(exp(log(abs(a)) + log(abs(b))))
    else 
        exp(log(abs(a)) + log(abs(b)))
    end
end

#= Scalar/Vector Multiplication =#
function multiply(a::Float64, b::Array{Float64,1}) 
    map(x -> multiply(a, x), b)
end

#= Scalar/Matrix Multiplication =#
function multiply(a::Float64, b::Array{Float64,2})
    map(y -> map(x -> multiply(a, x), y), b)
end

#= Dot Product =#
function dot(r, c)
    foldr(+ ,map(x -> multiply(x[1], x[2]), zip(r, c)))
end

#= Vector/Matrix Multiplication =#
function multiply(a::Array{Float64,1}, b::Array{Float64,2})
    out = Array{Float64}(undef, size(a))
    for i in 1:size(a)[1]
        out[i] = dot(a, b[i, :])
    end
    return out
end

#= Matrix/Matrix Multiplication =#
function multiply(a::Array{Float64,2}, b::Array{Float64,2})
    out = Array{Float64, 2}(undef, (size(a)[1], size(b)[2]))
    for i in 1:size(b)[2] #Iterate the clomuns
        out[:, i] = multiply(b[:, i], a)
    end
    return out
end

#= Matrix/Matrix Addition =#
function add(a::Array{Int,2}, b::Array{Int,2})
    map(x -> x[1] + x[2], zip(a,b))
end

#= Matrix Transpose =#
function transpose(a::Array{Int,2})
    out = Array{Int, 2}(undef, (size(a)[2], size(a)[1]))
    for i in 1:size(a)[1]
        for j in 1:size(a)[2]
            out[j, i] = a[i, j]
        end
    end
    return out
end

#===============================================================================
        Test Cases (do not touch these!)
===============================================================================#
#= Scalar/Scalar Multiplication =#
@assert multiply( 1.0,  2.0) == ( 1.0 *  2.0)
@assert multiply( 1.0,  1.0) == ( 1.0 *  1.0)
@assert multiply( 0.0,  1.0) == ( 0.0 *  1.0)
@assert multiply(-1.0,  2.0) == (-1.0 *  2.0)
@assert multiply(-1.0,  1.0) == (-1.0 *  1.0)
@assert multiply(-0.0,  1.0) == (-0.0 *  1.0)
@assert multiply(-1.0, -2.0) == (-1.0 * -2.0)
@assert multiply(-1.0, -1.0) == (-1.0 * -1.0)
@assert multiply(-0.0, -1.0) == (-0.0 * -1.0)
@assert multiply( 1.0, -2.0) == ( 1.0 * -2.0)
@assert multiply( 1.0, -1.0) == ( 1.0 * -1.0)
@assert multiply( 0.0, -1.0) == ( 0.0 * -1.0)

#= Scalar/Vector Multiplication =#
@assert multiply(2.0, [1.0, 2.0]) == (2.0 * [1.0, 2.0])
@assert multiply(0.0, [1.0, 2.0]) == (0.0 * [1.0, 2.0])
@assert multiply(0.0, [1.0, 2.0]) != [0.0, 2.0]

#= Scalar/Matrix Multiplication =#
@assert multiply(0.0, [1.0  2.0]) == (0.0 * [1.0  2.0])
@assert multiply(2.0, [1.0  2.0]) == (2.0 * [1.0  2.0]) 

@assert multiply(1.0, [[1.0,2.0] [1.0,2.0]]) == (1.0 * [[1.0,2.0] [1.0,2.0]])
@assert multiply(1.0, [[1.0 2.0] [1.0 2.0]]) == (1.0 * [[1.0 2.0] [1.0 2.0]])
@assert multiply(2.0, [[1.0,2.0] [1.0,2.0]]) == (2.0 * [[1.0,2.0] [1.0,2.0]])
@assert multiply(2.0, [[1.0 2.0] [1.0 2.0]]) == (2.0 * [[1.0 2.0] [1.0 2.0]])
@assert multiply(0.0, [[1.0,2.0] [1.0,2.0]]) != [[0.0, 0.0] [1.0, 2.0]]
@assert multiply(0.0, [[1.0 2.0] [1.0 2.0]]) != [[0.0 0.0] [1.0 2.0]]

#= Dot Product =#
@assert dot([1.0,1.0], [1.0,1.0]) == 2.0
@assert dot([0.0,1.0], [1.0,1.0]) == 1.0
@assert dot([0.0,0.0], [1.0,1.0]) == 0.0
@assert dot([0.0,0.0], [0.0,0.0]) == 0.0
@assert dot([1.0 1.0], [1.0 1.0]) == 2.0
@assert dot([0.0 1.0], [1.0 1.0]) == 1.0
@assert dot([0.0 0.0], [1.0 1.0]) == 0.0
@assert dot([0.0 0.0], [0.0 0.0]) == 0.0
@assert dot([0.0,2.0], [1.0,2.0]) == 4.0
@assert dot([0.0,2.0], [1.0,1.0]) == 2.0

#= Vector/Matrix Multiplication =#
@assert multiply([0.0,0.0], [[1.0,1.0] [1.0,1.0]]) == [0.0,0.0]
@assert multiply([0.0,2.0], [[1.0,1.0] [1.0,1.0]]) == [2.0,2.0]
@assert multiply([1.0,2.0], [[1.0,1.0] [1.0,1.0]]) == [3.0,3.0]
@assert multiply([2.0,2.0], [[1.0,1.0] [1.0,1.0]]) == [4.0,4.0]
@assert multiply([0.0,0.0], [[1.0,2.0] [1.0,1.0]]) == [0.0,0.0]
@assert multiply([0.0,2.0], [[1.0,2.0] [1.0,1.0]]) == [2.0,2.0]
@assert multiply([1.0,2.0], [[1.0,2.0] [1.0,1.0]]) == [3.0,4.0]
@assert multiply([2.0,2.0], [[1.0,2.0] [1.0,1.0]]) == [4.0,6.0]

#= Matrix/Matrix Multiplication =#
@assert multiply([[0.0,0.0] [0.0,0.0]], [[1.0,1.0] [1.0,1.0]]) == [[0.0,0.0] [0.0,0.0]]
@assert multiply([[0.0,0.0] [1.0,1.0]], [[1.0,1.0] [1.0,1.0]]) == [[1.0,1.0] [1.0,1.0]]
@assert multiply([[1.0,1.0] [1.0,1.0]], [[1.0,1.0] [1.0,1.0]]) == [[2.0,2.0] [2.0,2.0]]
@assert multiply([[0.0,0.0] [0.0,1.0]], [[1.0,1.0] [1.0,1.0]]) == [[0.0,1.0] [0.0,1.0]]
@assert multiply([[0.0,1.0] [0.0,1.0]], [[1.0,1.0] [1.0,1.0]]) == [[0.0,2.0] [0.0,2.0]]
@assert multiply([[1.0,0.0] [1.0,0.0]], [[1.0,1.0] [1.0,1.0]]) == [[2.0,0.0] [2.0,0.0]]

#= Matrix Transpose =#
@assert transpose([[0] [0]]) == [0 0]'
@assert transpose([[1] [2]]) == [1 2]'
@assert transpose([[1,1] [2,2]]) == [1 2; 1 2]'
@assert transpose([[1,2] [3,4]]) == [1 3; 2 4]'
@assert transpose([[1,1,1,1] [2,2,2,2]]) == [1 2; 1 2; 1 2; 1 2]'
@assert transpose([[1] [2] [3] [4]]) == [1 2 3 4]'

#= Matrix/Matrix Addition =#
@assert add([[9,8,7] [6,5,4] [3,2,1]],
            [[1,2,3] [4,5,6] [7,8,9]]) == [[9,8,7] [6,5,4] [3,2,1]] + [[1,2,3] [4,5,6] [7,8,9]]
@assert add([[-1,-2,-3] [-4,-5,-6] [-7,-8,-9]],
            [[1,2,3] [4,5,6] [7,8,9]]) == [[-1,-2,-3] [-4,-5,-6] [-7,-8,-9]] + [[1,2,3] [4,5,6] [7,8,9]]
