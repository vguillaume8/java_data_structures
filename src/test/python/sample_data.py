from functions import *
from file_io import write_csv
import random

# Applies random noise to a data set
# with a specified noise factor. Noise
# factor is the largest offset that can be
# applied to a point. We only use positive
# numbers to avoid negative numbers and issues
# with curve fitting functions that are
# not defined <= 0, such as log.
def apply_noise(y, noise_factor):

    # Add noise to the y-component of the evaluted function
    return [i + random.uniform(0, abs(noise_factor)) for i in y]


# Returns a list of numbers between specified
# upper and lower bounds
def get_range(lower_bound, upper_bound):
    return list(range(lower_bound, upper_bound))


def exponential_function_sample(lower_bound,
                                upper_bound,
                                noise_factor = 0,
                                A = 1,
                                B = -1,
                                C = 0):

    # Generate range from lower_bound to upper_bound
    # Evalute the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = exponential_function(x, A, B, C)

    # Apply noise
    y = apply_noise(y, noise_factor)

    return x, y


def cubic_function_sample(lower_bound,
                          upper_bound,
                          noise_factor = 0,
                          A = 1,
                          B = 0,
                          C = 0,
                          D = 0):

    # Generate range from lower_bound to upper_bound
    # Evalute the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = cubic_function(x, A, B, C, D)

    # Apply noise
    y = apply_noise(y, noise_factor)

    return x, y


def quadratic_function_sample(lower_bound,
                              upper_bound,
                              noise_factor = 0,
                              A = 1,
                              B = 0,
                              C = 0):

    # Generate range from lower_bound to upper_bound
    # Evalute the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = quadratic_function(x, A, B, C)

    # Apply noise
    y = apply_noise(y, noise_factor)

    return x, y


def n_log_n_function_sample(lower_bound,
                            upper_bound,
                            noise_factor = 0,
                            A = 1,
                            B = 0):

    # Generate range from lower_bound to upper_bound
    # Evalute the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = n_log_n_function(x, A, B)

    # Apply noise
    y = apply_noise(y, noise_factor)

    return x, y


def logarithmic_function_sample(lower_bound,
                                upper_bound,
                                noise_factor = 0,
                                A = 1,
                                B = 0):

    # Generate range from lower_bound to upper_bound
    # Evalute the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = logarithmic_function(x, A, B)

    # Apply noise
    y = apply_noise(y, noise_factor)

    return x, y


def linear_function_sample(lower_bound,
                           upper_bound,
                           noise_factor = 0,
                           A = 1,
                           B = 0):

    # Generate range from lower_bound to upper_bound
    # Evalute the function on the full range
    x = get_range(lower_bound, upper_bound)
    y = linear_function(x, A, B)

    # Apply noise
    y = apply_noise(y, noise_factor)

    return x, y


def generate_sample_data(lower_bound,
                         upper_bound,
                         noise_factor = 0,
                         exponential_fit = False,
                         cubic_fit       = False,
                         quadratic_fit   = False,
                         n_log_n_fit     = False,
                         logarithmic_fit = False,
                         linear_fit      = False):

    # For testing
    sample_data_directory = '../../../data/csv/'

    # Exponential
    if exponential_fit:
        x, y = exponential_function_sample(lower_bound, upper_bound, noise_factor = noise_factor)
        write_csv(sample_data_directory + "Exponential", x, y)

    # Cubic
    if cubic_fit:
        x, y = cubic_function_sample(lower_bound, upper_bound, noise_factor = noise_factor)
        write_csv(sample_data_directory + "Cubic", x, y)

    # Quadratic
    if quadratic_fit:
        x, y = quadratic_function_sample(lower_bound, upper_bound, noise_factor = noise_factor)
        write_csv(sample_data_directory + "Quadratic", x, y)

    # n log n
    if n_log_n_fit:
        x, y = n_log_n_function_sample(lower_bound, upper_bound, noise_factor = noise_factor)
        write_csv(sample_data_directory + "n log n", x, y)

    # Logarithmic
    if logarithmic_fit:
        x, y = logarithmic_function_sample(lower_bound, upper_bound, noise_factor = noise_factor)
        write_csv(sample_data_directory +"Logarithmic", x, y)

    # Linear
    if linear_fit:
        x, y = linear_function_sample(lower_bound, upper_bound, noise_factor = noise_factor)
        write_csv(sample_data_directory + "Linear", x, y)


if __name__ == "__main__":
    lower_bound  = 1
    upper_bound  = 75
    noise_factor = 100

    generate_sample_data(lower_bound,
                         upper_bound,
                         noise_factor,
                         exponential_fit  = False,
                         cubic_fit        = False,
                         quadratic_fit    = False,
                         n_log_n_fit      = True,
                         logarithmic_fit  = False,
                         linear_fit       = False)



