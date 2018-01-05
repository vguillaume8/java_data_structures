import numpy
from operator import itemgetter
from math import floor, log10
from scipy.optimize import curve_fit


# Returns a dictionary that represents
# the curve fit and important information
# regarding the fit
def fit_exponential(x,
                    y,
                    dependent_variable,
                    independent_variable,
                    name='Exponential Fit'):

    # Perform curve fit, getting coefficients for general log base-2 function
    coefficients, exp_extras = curve_fit(exponential_function, x, y)

    # Evaluate new function on same domain as input data set
    y_exp = exponential_function(x, coefficients[0], coefficients[1], coefficients[2])

    # Create the equation string
    equation = "$%s={}e^{}%s+{}$" % (dependent_variable, independent_variable)
    equation = equation.format(*to_latex(coefficients))

    # Create dictionary, conveniently storing
    # important information about the curve fit
    fit = {
        'name' :        name,
        'fit_type':     'exponential',
        'coefficients': coefficients,
        'equation':     equation,
        'x':            x,
        'y':            y_exp,
        'error':        mean_absolute_percent_error(y, y_exp)
    }

    return fit


# Returns a dictionary that represents
# the curve fit and important information
# regarding the fit
def fit_cubic(x,
              y,
              dependent_variable,
              independent_variable,
              name='Cubic Fit'):

    # Perform curve fit, getting coefficients for general log base-2 function
    coefficients, cub_extras = curve_fit(cubic_function, x, y)

    # Evaluate new function on same domain as input data set
    y_cub = cubic_function(x, coefficients[0], coefficients[1], coefficients[2], coefficients[3])

    # Create the equation string
    equation = "$%s={} \cdot %s^3 + {}\cdot %s^2+{} \cdot %s+{}$" % (dependent_variable, independent_variable, independent_variable, independent_variable)
    equation = equation.format(*to_latex(coefficients))

    # Create dictionary, conveniently storing
    # important information about the curve fit
    fit = {
        'name' :        name,
        'fit_type':     'cubic',
        'coefficients': coefficients,
        'equation':     equation,
        'x':            x,
        'y':            y_cub,
        'error':        mean_absolute_percent_error(y, y_cub)
    }

    return fit


# Returns a dictionary that represents
# the curve fit and important information
# regarding the fit
def fit_quadratic(x,
                  y,
                  dependent_variable,
                  independent_variable,
                  name='Quadratic Fit'):

    # Perform curve fit, getting coefficients for general log base-2 function
    coefficients, quad_extras = curve_fit(quadratic_function, x, y)

    # Evaluate new function on same domain as input data set
    y_quad = quadratic_function(x, coefficients[0], coefficients[1], coefficients[2])

    # Create the equation string
    equation = "$%s={} \cdot %s^2+{} \cdot %s+{}$" % (dependent_variable, independent_variable, independent_variable)
    equation = equation.format(*to_latex(coefficients))

    # Create dictionary, conveniently storing
    # important information about the curve fit
    fit = {
        'name' :        name,
        'fit_type':     'quadratic',
        'coefficients': coefficients,
        'equation':     equation,
        'x':            x,
        'y':            y_quad,
        'error':        mean_absolute_percent_error(y, y_quad)
    }

    return fit


# Returns a dictionary that represents
# the curve fit and important information
# regarding the fit
def fit_n_log_n(x,
                y,
                dependent_variable,
                independent_variable,
                name='n log n Fit'):

    # Perform curve fit, getting coefficients for general log base-2 function
    coefficients, n_log_extras = curve_fit(n_log_n_function, x, y)

    # Evaluate new function on same domain as input data set
    y_n_log = n_log_n_function(x, coefficients[0], coefficients[1])

    # Create the equation string
    equation = "$%s={}%s \cdot log_2(%s)+{}$" % (dependent_variable, independent_variable, independent_variable)
    equation = equation.format(*to_latex(coefficients))

    # Create dictionary, conveniently storing
    # important information about the curve fit
    fit = {
        'name' :        name,
        'fit_type':     'n log n',
        'coefficients': coefficients,
        'equation':     equation,
        'x':            x,
        'y':            y_n_log,
        'error':        mean_absolute_percent_error(y, y_n_log)
    }

    return fit


# Returns a dictionary that represents
# the curve fit and important information
# regarding the fit
def fit_linear(x,
               y,
               dependent_variable,
               independent_variable,
               name='Linear Fit'):

    # Perform curve fit, getting coefficients for general log base-2 function
    coefficients, lin_extras = curve_fit(linear_function, x, y)

    # Evaluate new function on same domain as input data set
    y_lin = linear_function(x, coefficients[0], coefficients[1])

    # Create the equation string
    equation = "$" + dependent_variable + "={}" + independent_variable + "+{}$"
    equation = equation.format(*to_latex(coefficients))

    # Create dictionary, conveniently storing
    # important information about the curve fit
    fit = {
        'name' :        name,
        'fit_type':     'linear',
        'coefficients': coefficients,
        'equation':     equation,
        'x':            x,
        'y':            y_lin,
        'error':        mean_absolute_percent_error(y, y_lin)
    }

    return fit


# Returns a dictionary that represents
# the curve fit and important information
# regarding the fit
def fit_logarithmic(x,
                    y,
                    dependent_variable,
                    independent_variable,
                    name='Logarithmic Fit'):

    # Perform curve fit, getting coefficients for general log base-2 function
    coefficients, log_extras = curve_fit(logarithmic_function, x, y)

    # Evaluate new function on same domain as input data set
    y_log = logarithmic_function(x, coefficients[0], coefficients[1])

    # Create the equation string
    equation = "$" + dependent_variable + "={}\cdot log_2(" + independent_variable + ")+{}$"
    equation = equation.format(*to_latex(coefficients))

    # Create dictionary, conveniently storing
    # important information about the curve fit
    fit = {
        'name' :        name,
        'fit_type':     'logarithmic',
        'coefficients': coefficients,
        'equation':     equation,
        'x':            x,
        'y':            y_log,
        'error':        mean_absolute_percent_error(y, y_log)
    }

    return fit


# Converts list of coefficients
# to power of 10 notation
def to_latex(coeffs, precision=1):

    # Allocate empty list thats the same
    # length as the coefficients list
    latex_coeffs = [None] * len(coeffs)

    # Convert each coefficient in the list
    # with designated precision
    for i in range(len(coeffs)):
        latex_coeffs[i] = sci_notation(coeffs[i], precision)

    return latex_coeffs
    

# Converts a single number from scientific
# notation to a power of 10 notation
def sci_notation(num,
                 decimal_digits = 1,
                 precision      = None,
                 exponent       = None):
    """
    Returns a string representation of the scientific
    notation of the given number formatted for use with
    LaTeX or Mathtext, with specified number of significant
    decimal digits and precision (number of decimal digits
    to show). The exponent to be used can also be specified
    explicitly.
    """
    if not exponent:
        exponent = int(floor(log10(abs(num))))

    coeff = round(num / float(10**exponent), decimal_digits)

    if not precision:
        precision = decimal_digits

    # Redundant to multiply a coefficient by 10 to 0th power
    # Simply return the scalar value
    if exponent == 0:
        return str(coeff)

    # LateX string
    return "{0:.{2}f}\cdot10^{{{1:d}}}".format(coeff, exponent, precision)


# f(x) = A * x^-B + C
def exponential_function(x, A, B, C):

    return A * numpy.exp(-1 * B * x) + C


# f(x) = Ax^3 + Bx^2 + Cx + D
def cubic_function(x, A, B, C, D):
    return A * numpy.power(x, 3) + B * numpy.power(x, 2) + C * numpy.power(x, 1) + D


# f(x) = Ax^2 + Bx + C
def quadratic_function(x, A, B, C):
    return A * numpy.power(x, 2) + B * numpy.power(x, 1) + C


# f(x) = Ax * log_2(x) + B
def n_log_n_function(x, A, B):
    return A * x * numpy.log2(x) + B


# f(x) = A * log_2(x) + B
def logarithmic_function(x, A, B):
    return A * numpy.log2(x) + B


# f(x) = Ax + B
def linear_function(x, A, B):
    return A * numpy.power(x, 1) + B


'''
         n
        ____
  100   \     | (actual_i - measured_i) |
 _____   \    | _______________________ | = Mean Absolute Percent Error
         /    |                         | 
   n    /___  |         actual_i        |
 
        i = 1
'''


# Computes mean absolute percent error
def mean_absolute_percent_error(measured, actual):
    s = 0
    n = len(measured)

    for i in xrange(n):
        s += abs((actual[i] - measured[i]) / actual[i])

    return (s * 100) / n


'''
         n
        ____                           2
   1    \      (actual_i - measured_i)
 _____   \     _______________________   = Mean Suqared Error
         /                              
   n    /___             1         
 
        i = 1
'''


# Computes mean absolute percent error
def mean_squared_error(measured, actual):
    s = 0
    n = len(measured)

    for i in xrange(n):
        s += (actual[i] - measured[i])**2

    return s / n


# Given a dictionary of curve fits
# return the fit with the lowest error
def min_err(fits):

    # Set min to first fit in the set
    min_fit = fits.itervalues().next()

    # Linear search for global minimum in the provided set
    for key, value in fits.iteritems():

        # Compare current to local min
        if value['error'] < min_fit['error']:
            min_fit = value

    return min_fit


# For use with deciding on
# which curve fit function
# to call on a data set
curve_fit_functions = {
    "exponential": fit_exponential,
    "cubic":       fit_cubic,
    "quadratic":   fit_quadratic,
    "n_log_n":     fit_n_log_n,
    "linear":      fit_linear,
    "logarithmic": fit_logarithmic
}