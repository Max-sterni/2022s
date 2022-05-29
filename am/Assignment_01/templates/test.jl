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

function multiply(a::Float64, b::Array{Float64,2})
    map(y -> map(x -> multiply(a, x), y), b)
end

function dot(r::Float64, c::Float64)
    foldr(+ ,map(x -> multiply(x[1], x[2]), zip(r, c)))
end

function multiply(a::Array{Float64,1}, b::Array{Float64,2})
    out = Array{Float64}(undef, size(a))
    for i in 1:size(a)[1]
        out[i] = dot(a, b[i, :])
    end
    out
end