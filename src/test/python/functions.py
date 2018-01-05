import numpy
from operator import itemgetter
from math import floor, log10


# Converts list of coefficients
# to power of 10  notation
def to_latex(coeffs):
    latex_coeffs = [None] * len(coeffs)

    for i in range(len(coeffs)):
        latex_coeffs[i] = sci_notation(coeffs[i], 1)

    return latex_coeffs
    

# Converts a number from scientific
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
    if exponent == 0:
        return str(coeff)

    # LateX string
    return "{0:.{2}f}\cdot10^{{{1:d}}}".format(coeff, exponent, precision)


# f(x) = A * x^-B + C
def exponential_function(x, A, B, C):
    return A * numpy.exp(-B * x) + C


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
 _____   \    | _______________________ |
         /    |                         | = Mean Absolute Percent Error
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


# Given a dictionary of fits (also dictionaries)
# return the fit with the lowest error
def min_err(fits):
    min_fit = fits.itervalues().next()

    for key, value in fits.iteritems():
        if value['error'] < min_fit['error']:
            min_fit = value

    return min_fit


