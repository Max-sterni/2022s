# packages
using FileIO;
using Plots; pyplot();
using LinearAlgebra;
using Images;

# define color array & image size
window_width = 512;
window_height = 512;
color = zeros(3,window_width,window_height);

# define vertices - we treat z coordinate as 0 since we work in 2D
v_1 = [[0,256,0],[500,32,0],[500,480,0]];

# define colors
uv_1 = [[1.0, 0.0, 0.0], [0.0, 1.0, 0.0], [0.0, 0.0, 1.0]];

# barycentric interpolation
# return color for one point q
# arguments: vertices (v), point (q), colors we'd like to interpolate (uv)
function barycentric_interpolation(v, q, uv)
    # calculate vectors between vertices and q
    vectors = [[0,0,0],[0,0,0],[0,0,0]]
    for i in 1:3
        vectors[i] = v[i] - q
    end
    # get area of each triangle via crossproducts
    areas = [0.0, 0.0, 0.0]
    areas[1] = norm(cross(vectors[1], vectors[2])) / 2
    areas[2] = norm(cross(vectors[2], vectors[3])) / 2
    areas[3] = norm(cross(vectors[1], vectors[3])) / 2
    # get main triangle area
    A = norm(cross(v[2] - v[1], v[3] - v[1])) / 2
    # calculate barycentric coordinates 
    # (with sign for the more general version)
    lambda = [0.0, 0.0, 0.0]
    lambda[1] = areas[1] / A
    lambda[2] = areas[2] / A
    lambda[3] = areas[3] / A
    # apply interpolation factors (barycentric coordinates) if q inside triangle
    
    if lambda[1] + lambda[2] + lambda[3] ≈ 1
        return lambda[1] * uv[1] + lambda[2] * uv[2] + lambda[3] * uv[3]
    end
    return [1 1 1]
    # return color for point q
end

# iterate through points in triangle
# apply barycentric interpolation for each pixel (point q)
# returns color array filled with interpolated (color) values
function barycentric_interpolation_triangle(v, uv, x_max, y_max, c_array)
    for x = 1:x_max
        for y = 1:y_max
            c_array[:,x,y] = barycentric_interpolation(v, [x,y,0], uv);
        end
    end
    return c_array;
end

barycentric_interpolation_triangle(v_1, uv_1, window_height, window_width, color);
# barycentric_interpolation(v_1, [200, 256, 0] ,uv_1)
# convert color array to image
img = colorview(RGB, color);
# save image as PNG
save(File(format"PNG", joinpath(@__DIR__, "barycentric_interpolation_result.png")), img);