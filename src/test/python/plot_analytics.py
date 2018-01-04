import matplotlib.pyplot as plt
import csv
import os
import argparse, sys
import json
from matplotlib.font_manager import FontProperties
from scipy.interpolate import spline
from scipy.optimize import curve_fit
from functions import *


'''
1. Reads in (x,y) points from a CSV

2. Computes best fit functions for desired types

3. Plots the input data

3. Plots the best fit functions on same domain as input

3. Saves plot as image

4. Displays the plot in a window
'''
def plot_data(input_file_path,
              output_file_path,
              plot_title,
              input_data_label,
              x_label,
              y_label,
              original_data   = True,
              exponential_fit = False,
              cubic_fit       = False,
              quadratic_fit   = False,
              n_log_n_fit     = False,
              logarithmic_fit = False,
              linear_fit      = False):

    # Arrays that will hold
    # x, y input values from CSV
    # file respectively
    x = []
    y = []

    # Dictionary of fits (also dictionaries)
    # JSON-like setup with nested dictionaries
    fits = {}

    # Open a CSV file in read mode
    with open(input_file_path,'r') as csvfile:
        plots = csv.reader(csvfile, delimiter=',')

        # Read in all lines (x,y points) from CSV file
        # and store them in x, y lists respectively
        for row in plots:
            x.append(float(row[0]))
            y.append(float(row[1]))

    # Plot the original input data
    if original_data:
        plt.scatter(x,y, marker='o', label=input_data_label)

    # Plot Exponential
    if exponential_fit:
        exp_coeffs, exp_extras = curve_fit(exponential_function, x, y)
        y_exp = exponential_function(x, exp_coeffs[0], exp_coeffs[1], exp_coeffs[2])

        exp = {
            'fit_type' : 'Exponential Fit',
            'equation' : "{}e^{}n+{}$".format(*to_latex(exp_coeffs)),
            'error'    : mean_absolute_percent_error(y, y_exp)
        }

        fits['exponential'] = exp
        plt.plot(x, y_exp, label=exp['fit_type'] + ": " + exp['equation'])

    # Plot Cubic
    if cubic_fit:
        cub_coeffs, cub_extras = curve_fit(cubic_function, x, y)
        y_cub = cubic_function(x, cub_coeffs[0], cub_coeffs[1], cub_coeffs[2], cub_coeffs[3])

        cub = {
            'fit_type' : 'Cubic Fit',
            'equation' : "${}n^3+{}n^2+{}n+{}$".format(*to_latex(cub_coeffs)),
            'error'    : mean_absolute_percent_error(y, y_cub)
        }

        fits['cubic'] = cub
        plt.plot(x, y_cub, label=cub['fit_type'] + ": " + cub['equation'])

    # Plot Quadratic
    if quadratic_fit:
        fit_type = "Quadratic Fit"

        quad_coeffs, quad_extras = curve_fit(quadratic_function, x, y)
        y_quad = quadratic_function(x, quad_coeffs[0], quad_coeffs[1], quad_coeffs[2])

        quad = {
            'fit_type' : 'Quadratic Fit',
            'equation' : "${}n^2+{}n+{}$".format(*to_latex(quad_coeffs)),
            'error'    : mean_absolute_percent_error(y, y_quad)
        }

        fits['quadratic'] = quad
        plt.plot(x, y_quad, label=quad['fit_type'] + ": " + quad['equation'])

    # Plot nlog n
    if n_log_n_fit:

        n_log_coeffs, n_log_extras = curve_fit(n_log_n_function, x, y)
        y_n_log = n_log_n_function(x, n_log_coeffs[0], n_log_coeffs[1])

        n_log = {
            'fit_type' : 'n log n Fit',
            'equation' : "${}nlog_2n+{}$".format(*to_latex(n_log_coeffs)),
            'error'    : mean_absolute_percent_error(y, y_n_log)
        }

        fits['n log n'] = n_log
        plt.plot(x, y_n_log, label=n_log['fit_type'] + ": " + n_log['equation'])

    # Plot Logarithmic
    if logarithmic_fit:

        log_coeffs, log_extras = curve_fit(logarithmic_function, x, y)
        y_log = logarithmic_function(x, log_coeffs[0], log_coeffs[1])

        log = {
            'fit_type' : 'Logarithmic Fit',
            'equation' : "${}log_2n+{}$".format(*to_latex(log_coeffs)),
            'error'    : mean_absolute_percent_error(y, y_log)
        }

        fits['logarithmic'] = log
        plt.plot(x, y_log, label=log['fit_type'] + ": " + log['equation'])

    # Plot Linear
    if linear_fit:

        lin_coeffs, lin_extras = curve_fit(linear_function, x, y)
        y_lin = linear_function(x, lin_coeffs[0], lin_coeffs[1])

        lin = {
            'fit_type' : 'Linear Fit',
            'equation' : "${}n+{}$".format(*to_latex(lin_coeffs)),
            'error'    : mean_absolute_percent_error(y, y_lin)
        }

        fits['linear'] = lin
        plt.plot(x, y_lin, label=lin['fit_type'] + ": " + lin['equation'])


    # Get the curve with the minimum error
    if fits:
        best_fit = min_err(fits)
        print("Best fit " + str(best_fit))


    # Label and title the plot
    plt.xlabel('$' + x_label + '$')
    plt.ylabel('$' + y_label + '$')
    plt.title(plot_title)

    # Create a legend and show the plot
    plt.legend(loc=9, bbox_to_anchor=(0.5, -0.1), ncol=1)
    plt.grid(True)

    # Save the plot as PNG then display it
    plt.savefig(output_file_path, bbox_inches='tight')

    # plt.show()


# -------------------------------------------------------------------------------


if __name__ == "__main__":

    parser = argparse.ArgumentParser()

    # Required arguments
    requiredNamed = parser.add_argument_group('required named arguments')
    requiredNamed.add_argument('--input-file-name', help='Name of valid input CSV file', required=True)
    requiredNamed.add_argument('--data-directory', help='Path for CSV and PNG files', required=True)

    # Optional arguments
    parser.add_argument('--plot-title', help='Title of your plot')
    parser.add_argument('--input-data-label', help='The name that appears in the legend to label the original data')
    parser.add_argument('--x-axis-label', help='X-axis label')
    parser.add_argument('--y-axis-label', help='Y-axis label')

    # Types of curve fits available
    parser.add_argument('--exponential-fit', help='If you would like an exponential curve fit')
    parser.add_argument('--cubic-fit', help='If you would like an cubic curve fit')
    parser.add_argument('--quadratic-fit', help='If you would like an quadratic curve fit')
    parser.add_argument('--n-log-n-fit', help='If you would like an n*log(n) curve fit')
    parser.add_argument('--linear-fit', help='If you would like an linear curve fit')
    parser.add_argument('--logarithmic-fit', help='If you would like an logarithmic curve fit')

    # Default parameters
    params = {
        'input_file_name' : '',
        'csv_directory': 'data/csv/',
        'png_directory': 'data/png/',
        'plot_title': 'Title',
        'input_data_label': 'Original Data',
        'x_axis_label': 'n',
        'y_axis_label': 'T(n)',
        'original_data': True,
        'exponential_fit': False,
        'cubic_fit': False,
        'quadratic_fit': False,
        'n_log_n_fit': False,
        'linear_fit': False,
        'logarithmic_fit': False
    }

    # Parse the command line arguments
    # and update defaults

    args = parser.parse_args()

    # Set mandatory params
    params['input_file_name'] = args.input_file_name
    params['data_directory'] = args.data_directory
    params['csv_directory'] = params['data_directory'] + 'csv/'
    params['png_directory'] = params['data_directory'] + 'png/'

    # Set optional params
    if args.plot_title:
        params['plot_title'] = args.plot_title

    if args.input_data_label:
        params['input_data_label'] = args.input_data_label

    if args.x_axis_label:
        params['x_axis_label'] = args.x_axis_label

    if args.y_axis_label:
        params['y_axis_label'] = args.y_axis_label

    if args.exponential_fit == 'True':
        params['exponential_fit'] = bool(args.exponential_fit)

    if args.cubic_fit == 'True':
        params['cubic_fit'] = bool(args.cubic_fit)

    if args.quadratic_fit == 'True':
        params['quadratic_fit'] = bool(args.quadratic_fit)

    if args.n_log_n_fit == 'True':
        params['n_log_n_fit'] = bool(args.n_log_n_fit)

    if args.linear_fit == 'True':
        params['linear_fit'] = bool(args.linear_fit)

    if args.logarithmic_fit == 'True':
        params['logarithmic_fit'] = bool(args.logarithmic_fit)


    # Call main with valid arguments
    # Run create the plot
    plot_data(input_file_path    = params['csv_directory'] + params['input_file_name'] + '.csv',
              output_file_path   = params['png_directory'] + params['input_file_name'] + '.png',
              plot_title         = params['plot_title'],
              input_data_label   = params['input_data_label'],
              x_label            = params['x_axis_label'],
              y_label            = params['y_axis_label'],
              original_data      = params['original_data'],
              exponential_fit    = params['exponential_fit'],
              cubic_fit          = params['cubic_fit'],
              quadratic_fit      = params['quadratic_fit'],
              n_log_n_fit        = params['n_log_n_fit'],
              linear_fit         = params['linear_fit'],
              logarithmic_fit    = params['logarithmic_fit'])
