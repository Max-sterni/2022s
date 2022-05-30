using CSV;
using DataFrames;
using Plots;
using LinearAlgebra; # only use this for Cramer's rule

# data prep
df = CSV.read("california_housing.csv", DataFrame);
# select variables of interest (income, house prices) & use subset of full df
df = df[1:750,8:9];
# convert to matrix for ols
data = Matrix(df);
# scale y
data[:,2] /= 10000.0;

# split into test/train set -> we could also use randomization here
data_train = data[1:650,:];
data_test = data[651:750,:];

# linear ordinary least squares: income vs house prices
# function returns the coefficients of the linear least square fit
# arguments: income (x), house prices (y)
function linear_least_square_fit(x,y)
    x = x'
    y = y'
    #Construct matrix A of size 650x2
    A = ones(650, 2)
    A[:, 2] = x
    #Construct matrix C for least square problem A'*A
    C = A' * A
    #Construct right hand vector b for the least square problem A'*y
    b = A' * y
    #Solve least square problem. Use the backslash operator
    #to solve the system
    return C \ b
end

# calculate & return approximation error for linear OLS
# arguments: income (x), house prices (y), and
# the coefficients of the linear polynomial (c_linear)
function linear_approximation_error(x,y,c_linear)
    error = 0;
    for (i,t) in enumerate(x)
            error += (c_linear[2] * t + c_linear[1] - y[i])^2
    end
    return error;
end

# quadratic ordinary least squares: income vs house prices
# function returns the coefficients of the quadratic least square fit
# arguments: income (x), house prices (y)
function quadratic_least_square_fit(x,y)
    x = x'
    y = y'
    #Construct matrix A of size 650x2
    A = ones(650, 3)
    A[:, 3] = x .^2
    A[:, 2] = x
    #Construct matrix C for least square problem A'*A
    C = A' * A
    #Construct right hand vector b for the least square problem A'*y
    b = A' * y
    #Solve least square problem. Use the backslash operator
    #to solve the system
    return C \ b
end

# calculate & return approximation error for quadratic OLS
# arguments: income (x), house prices (y), and
# the coefficients of the quadratic polynomial (c_quadratic)
function quadratic_approximation_error(x,y,c_quadratic)
    error = 0;
    for (i,t) in enumerate(x)
        error += (c_quadratic[3] * t^2 + c_quadratic[2] * t + c_quadratic[1] - y[i])^2
    end
    return error;
end

# Cramer's rule to solve linear system 
# arguments: matrix (A), vector (b)
# use det() from LinearAlgebra
# hint: use built-in function collect()
function cramersolve(A::Matrix, b::Matrix)
    # return results of solving system via Cramer's rule
    x = zeros(length(eachcol(A)))

    for i in 1:length(eachcol(A))
        B = copy(A)
        B[:, i] = b
        x[i] = det(B) / det(A)
    end
    return x
end

# cubic ordinary least squares: income vs house prices
# using Cramer's rule
# function returns the coefficients of the cubic least square fit
# arguments: income (x), house prices (y)
function cubic_least_square_fit(x,y)
    x = x'
    y = y'
    #Construct matrix A of size 650x2
    A = ones(650, 4)
    A[:, 4] = x .^3
    A[:, 3] = x .^2
    A[:, 2] = x
    #Construct matrix C for least square problem A'*A
    C = A' * A
    #Construct right hand vector b for the least square problem A'*y
    b = A' * y
    #Solve least square problem. Use the backslash operator
    #to solve the system
    return cramersolve(C, b)

end

# calculate & return approximation error for cubic OLS
# arguments: income (x), house prices (y), and
# the coefficients of the cubic polynomial (c_cubic)
function cubic_approximation_error(x,y,c_cubic)
    error = 0;
    for (i,t) in enumerate(x)
        error += (c_cubic[4] * t^3 + c_cubic[3] * t^2 + c_cubic[2] * t + c_cubic[1] - y[i])^2
    end
    return error;
end

# compare & plot linear, quadratic & cubic fits w.r.t. training data points
plt = scatter(data_train[:,1], data_train[:,2], color=:pink, label="Income vs house prices");
t = 0:0.1:15;

x_1 = reshape(data_train[:,1], 1, 650);
y_1 = reshape(data_train[:,2], 1, 650);
c_linear = linear_least_square_fit(x_1, y_1);
c_quadratic = quadratic_least_square_fit(x_1, y_1);
c_cubic = cubic_least_square_fit(x_1, y_1);

plinear(t) = c_linear[2] * t + c_linear[1];
pquadratic(t) = c_quadratic[3] * t * t + c_quadratic[2] * t + c_quadratic[1];
pcubic(t) = c_cubic[4] * t * t * t + c_cubic[3] * t * t + c_cubic[2] * t + c_cubic[1];

plot!(plt,t,plinear,color=:blue,label="Linear least square fit")
plot!(plt,t,pquadratic,color=:purple,label="Quadratic least square fit")
plot!(plt,t,pcubic,color=:black,label="Cubic least square fit")

display(plt)

# display approximation error for all three OLS
linear_fit_error = linear_approximation_error(x_1,y_1,c_linear);
println("Linear fit error:", linear_fit_error)
quadratic_fit_error = quadratic_approximation_error(x_1,y_1,c_quadratic);
println("Quadratic fit error:", quadratic_fit_error)
cubic_fit_error = cubic_approximation_error(x_1,y_1,c_cubic);
println("Cubic fit error:", cubic_fit_error)

# inference with quadratic ols
x_2 = reshape(data_test[:,1], 1, 100);
y_2 = reshape(data_test[:,2], 1, 100);

# visualize predicitions & calculate error
# function returns prediction & error
# hint: predictions are made by projecting new data on the curve
# we already did this when looking at the approximation error
# arguments: income (x), house prices (y), and
# the coefficients of the quadratic polynomial (c_quadratic)
function quadratic_ls_prediction(x,y,c_quadratic)

    predictions = ones(size(x)[2]);
    error = 0;
    for (i,t) in enumerate(x)
        # calculate predictions and store in predictions
        predictions[i] = c_quadratic[3] * t * t + c_quadratic[2] * t + c_quadratic[1]
        # calculate error 
        error += (c_quadratic[3] * t * t + c_quadratic[2] * t + c_quadratic[1] - y[i])^2
    end
    return predictions, error;
end

# plot ground truth (actual data points) and fitted polynom
 plt = scatter(data_test[:,1], data_test[:,2], color=:green, label="Income vs house prices");
t = 0:0.1:15;


 pquadratic(t) = c_quadratic[3] * t * t + c_quadratic[2] * t + c_quadratic[1];
 plot!(plt,t,pquadratic,color=:purple,label="Quadratic least square fit")

# plot predictions on curve
predictions_test, error_test = quadratic_ls_prediction(x_2,y_2,c_quadratic);
plt = scatter!(data_test[:,1], predictions_test, color=:pink, label="Income vs house prices")

println("Test set error:", error_test)
display(plt)