from functions import *
from file_io import write_csv
import random
import os


# Applies random noise to a data set
# with a specified noise factor. Noise
# factor is the largest offset that can be
# applied to a point. We only use positive
# numbers to avoid negative numbers and issues
# with curve fitting functions that are
# not defined for non-natural numbers, such as log.
def apply_noise(y, noise_factor):

    # TODO - Change noise to be percent error based

    # Add noise to the y-component of the evaluated function
    return [i * (1 + random.uniform(0, abs(noise_factor))) for i in y]


# Returns a list of numbers between specified
# upper and lower bounds
def get_range(lower_bound, upper_bound):
    return list(range(lower_bound, upper_bound))


# Generate data for an exponential function
def exponential_function_sample(lower_bound,
                                upper_bound,
                                x_noise_factor = 0,
                                y_noise_factor = 0,
                                A = 1,
                                B = -1,
                                C = 0):

    # Generate range from lower_bound to upper_bound
    # Evaluate the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = exponential_function(x, A, B, C)

    # Apply noise
    x = apply_noise(x, x_noise_factor)
    y = apply_noise(y, y_noise_factor)

    return x, y


# Generate data for a cubic function
def cubic_function_sample(lower_bound,
                          upper_bound,
                          x_noise_factor = 0,
                          y_noise_factor = 0,
                          A = 1,
                          B = 0,
                          C = 0,
                          D = 0):

    # Generate range from lower_bound to upper_bound
    # Evaluate the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = cubic_function(x, A, B, C, D)

    # Apply noise
    x = apply_noise(x, x_noise_factor)
    y = apply_noise(y, y_noise_factor)

    return x, y


# Generate data for a quadratic function
def quadratic_function_sample(lower_bound,
                              upper_bound,
                              x_noise_factor = 0,
                              y_noise_factor = 0,
                              A = 1,
                              B = 0,
                              C = 0):

    # Generate range from lower_bound to upper_bound
    # Evaluate the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = quadratic_function(x, A, B, C)

    # Apply noise
    x = apply_noise(x, x_noise_factor)
    y = apply_noise(y, y_noise_factor)

    return x, y


# Generate data for a function in the form n*log_2(n)
def n_log_n_function_sample(lower_bound,
                            upper_bound,
                            x_noise_factor = 0,
                            y_noise_factor = 0,
                            A = 1,
                            B = 0):

    # Generate range from lower_bound to upper_bound
    # Evaluate the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = n_log_n_function(x, A, B)

    # Apply noise
    x = apply_noise(x, x_noise_factor)
    y = apply_noise(y, y_noise_factor)

    return x, y


# Generate data for a linear function
def linear_function_sample(lower_bound,
                           upper_bound,
                           x_noise_factor = 0,
                           y_noise_factor = 0,
                           A = 1,
                           B = 0):

    # Generate range from lower_bound to upper_bound
    # Evalute the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = linear_function(x, A, B)

    # Apply noise
    x = apply_noise(x, x_noise_factor)
    y = apply_noise(y, y_noise_factor)

    return x, y


# Generate data for a logarithmic function
def logarithmic_function_sample(lower_bound,
                                upper_bound,
                                x_noise_factor = 0,
                                y_noise_factor = 0,
                                A = 1,
                                B = 0):

    # Generate range from lower_bound to upper_bound
    # Evaluate the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = logarithmic_function(x, A, B)

    # Apply noise
    x = apply_noise(x, x_noise_factor)
    y = apply_noise(y, y_noise_factor)

    return x, y


# Generates sample data sets on a specified domain
# for specified function types with optional noise.
def generate_sample_data(lower_bound,
                         upper_bound,
                         x_noise_factor     = 0,
                         y_noise_factor     = 0,
                         exponential_fit    = False,
                         cubic_fit          = False,
                         quadratic_fit      = False,
                         n_log_n_fit        = False,
                         logarithmic_fit    = False,
                         linear_fit         = False,
                         data_directory     = "./",
                         generate_png       = True):

    output_path = data_directory + "csv/"

    # Exponential
    if exponential_fit:
        name = "Exponential"
        x, y = exponential_function_sample(lower_bound,
                                           upper_bound,
                                           x_noise_factor = x_noise_factor,
                                           y_noise_factor = y_noise_factor)

        write_csv(output_path + name + ".csv", x, y)

        if generate_png:
            generate_plot(name, data_directory=data_directory, exponential_fit=True)

    # Cubic
    if cubic_fit:
        name = "Cubic"
        x, y = cubic_function_sample(lower_bound,
                                     upper_bound,
                                     x_noise_factor = x_noise_factor,
                                     y_noise_factor = y_noise_factor)

        write_csv(output_path + name + ".csv", x, y)

        if generate_png:
            generate_plot(name, data_directory=data_directory, cubic_fit=True)

    # Quadratic
    if quadratic_fit:
        name = "Quadratic"
        x, y = quadratic_function_sample(lower_bound,
                                         upper_bound,
                                         x_noise_factor = x_noise_factor,
                                         y_noise_factor = y_noise_factor)

        write_csv(output_path + name + ".csv", x, y)

        if generate_png:
            generate_plot(name, data_directory=data_directory, quadratic_fit=True)

    # n log n
    if n_log_n_fit:
        name = "n_log_n"
        x, y = n_log_n_function_sample(lower_bound,
                                       upper_bound,
                                       x_noise_factor = x_noise_factor,
                                       y_noise_factor = y_noise_factor)

        write_csv(output_path + name + ".csv", x, y)

        if generate_png:
            generate_plot(name, data_directory=data_directory, n_log_n_fit=True)

    # Linear
    if linear_fit:
        name = "Linear"
        x, y = linear_function_sample(lower_bound,
                                      upper_bound,
                                      x_noise_factor = x_noise_factor,
                                      y_noise_factor = y_noise_factor)

        write_csv(output_path + name + ".csv", x, y)

        if generate_png:
            generate_plot(name, data_directory=data_directory, linear_fit=True)

    # Logarithmic
    if logarithmic_fit:
        name = "Logarithmic"
        x, y = logarithmic_function_sample(lower_bound,
                                           upper_bound,
                                           x_noise_factor = x_noise_factor,
                                           y_noise_factor = y_noise_factor)

        write_csv(output_path + name + ".csv", x, y)

        if generate_png:
            generate_plot(name, data_directory=data_directory, logarithmic_fit=True)


# Generates a plot for the sample data
def generate_plot(plot_name,
                  data_directory='./',
                  exponential_fit = False,
                  cubic_fit       = False,
                  quadratic_fit   = False,
                  n_log_n_fit     = False,
                  linear_fit      = False,
                  logarithmic_fit = False):

    # Put pieces of command into list
    args = [
        "python",
        "plot_analytics.py",
        "--data-directory="  + data_directory,
        "--input-file-name=" + plot_name,
        "--exponential-fit=" + str(exponential_fit),
        "--cubic-fit="       + str(cubic_fit),
        "--quadratic-fit="   + str(quadratic_fit),
        "--n-log-n-fit="     + str(n_log_n_fit),
        "--linear-fit="      + str(linear_fit),
        "--logarithmic-fit=" + str(logarithmic_fit)
    ]

    # Concatenate all the pieces
    command = " ".join(args)

    # Execute the command
    os.system(command)

    return


# Main function
if __name__ == "__main__":

    # Parameters for data generation
    min     = 1
    max     = 50
    x_noise = 0.1
    y_noise = 0.1

    # Execute function to generate the data
    generate_sample_data(min,
                         max,
                         x_noise_factor   = x_noise,
                         y_noise_factor   = y_noise,
                         exponential_fit  = False,
                         cubic_fit        = True,
                         quadratic_fit    = True,
                         n_log_n_fit      = True,
                         logarithmic_fit  = True,
                         linear_fit       = True,
                         data_directory   = "../../../data/")



