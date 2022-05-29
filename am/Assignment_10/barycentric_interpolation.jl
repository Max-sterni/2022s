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
    vectors = zeros(3, 3)
    for i in 1:3
        vectors[:, i] = v[i, :] - q
    end
    # get area of each triangle via crossproducts
    areas = zeros(3)
    for i in 1:3
        areas[:, i] = norm(cross(vectors[:, i], vectors[:, i % 3 + 1]) / 2)
    end
    # get main triangle area
    surrounding_vectors = [v_1[2, :] - v_1[1, :], v_1[3, :] - v_1[1, :]]
    area = norm(cross(surrounding_vectors[1], surrounding_vectors[2]) / 2)
    # calculate barycentric coordinates 
    # (with sign for the more general version)
    lambda = [areas[1] / area, areas[2] / area, areas[3] / area]
    # apply interpolation factors (barycentric coordinates) if q inside triangle
    if(all(lambda .< 1 && lambda .> 0))
        color = lambda[1] * uv[1] + lambda[2] * uv[2] + lambda[3] * uv[3] 
    else
        color = [1.0 1.0 1.0]
    end
    
    return color
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

# convert color array to image
img = colorview(RGB, color);
# save image as PNG
save(File(format"PNG", joinpath(@__DIR__, "barycentric_interpolation_result.png")), img);