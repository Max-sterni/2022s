using Plots

function plot_function(fct, x_min, x_max, step_size)
    range = x_min:step_size:x_max
    return plot(fct, range)  
end

a1(t) = 1.5
a2(t) = 0
a3(t) = -t/150 + 1

v1(t) = 1.5 * t
v2(t) = 90
v3(t) = -1/300 * t^2 + t + 15

x1(t) = 3/4 * t^2
x2(t) = 90t - 2700
x3(t) = -1/900 * t^3 + 1/2*t^2 + 15*t + 1050

function a(t)
    if t <= 60
        return a1(t)
    elseif t <= 150
        return a2(t)
    else 
        return a3(t)
    end
end

function v(t)
    if t <= 60
        return v1(t)
    elseif t <= 150
        return v2(t)
    else 
        return v3(t)
    end
end

function x(t)
    if t <= 60
        return x1(t)
    elseif t <= 150
        return x2(t)
    else 
        return x3(t)
    end
end

function x_hat(t)
    sum = 0
    for i in 1:t
        sum += v(i)
    end
    return sum
end

function x_hat_inacurate(t)
    sum = 0
    for i in 1:t/5
        sum += v(5 * i)
    end
    return sum
end


function v_hat(t)
    sum = 0
    for i in 1:t
        sum += a(i)
    end
    return sum
end

function v_hat_error(t)
    abs(v_hat(t) - v(t))
end

function x_hat_error(t)
    abs(x_hat(t) - x(t))
end


accelaration = plot_function(a, 0.0, 300.0, 0.01)
velocity = plot_function(v, 0.0, 300.0, 0.01)
distance = plot_function(x, 0.0, 300.0, 0.01)

velocity_hat = plot_function(v_hat, 0.0, 300.0, 1.0)
distance_hat = plot_function(x_hat, 0.0, 300.0, 1.0)

velocity_hat_error = plot_function(v_hat_error, 0.0, 300.0, 1.0)
distance_hat_error = plot_function(x_hat_error, 0.0, 300.0, 1.0)

distance_hat_inacurate_error = plot(x_hat_inacurate, 0:300);


plot(accelaration, velocity, distance, velocity_hat, distance_hat, velocity_hat_error , distance_hat_error, distance_hat_inacurate_error,layout=8, title=["a" "v" "x" "v_hat" "x_hat" "v_hat_error" "x_hat_error" "x_hat_inaccurate"], 
     titlefont = font(8), legend=false, size = (800, 800))

