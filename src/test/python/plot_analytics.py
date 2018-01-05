import matplotlib.pyplot as plt
from matplotlib.offsetbox import AnchoredText
import csv
import os
import argparse, sys
import json
from matplotlib.font_manager import FontProperties
from scipy.interpolate import spline
from scipy.optimize import curve_fit
from functions import *
from file_io import read_csv

'''
Given a data set of (x,y) points in the form of a .csv file, and
optional parameters for fitting a curve to the data
this program does the following:

1. Reads in (x,y) points from a CSV

2. Computes best fit functions for desired types

3. Plots the input data

4. Plots the best fit functions on same domain as input

5. Saves plot as image
'''


# Gets a curve fit for all desired curves
def get_fits(desired_fits,
             x,
             y,
             dependent_variable,
             independent_variable):

    # This variable will hold
    # the dictionaries that represent
    # a curve fit and its associated information
    fits = {}

    # Loop through all the keys
    for key in desired_fits:

        # If the value is set to true,
        # perform the curve fit
        if desired_fits[key]:

            # Get the associated function, and evaluate with data sets x and y as inputs
            # with dependent and independent variable strings for the equation string
            fits[key] = curve_fit_functions[key](x, y, dependent_variable, independent_variable)

    return fits


# Plots x,y data and desired curve fits
def plot_data(output_file_path,
              plot_title,
              input_data_label,
              x_label,
              y_label,
              x_data,
              y_data,
              dependent_variable,
              independent_variable,
              plot_original_data,
              desired_fits):

    # Set up figure and axes
    f, ax = plt.subplots(1,1)

    # Dictionary of curve fits (also dictionaries)
    # JSON-like setup with nested dictionaries
    fits = get_fits(desired_fits, x_data, y_data, dependent_variable, independent_variable)

    # Plot the original input data
    if plot_original_data:
        ax.scatter(x_data, y_data, marker='o', label=input_data_label)

    # Get the curve with the minimum error
    if fits:

        # Plot the curve fits
        for key in fits:
            ax.plot(x_data, fits[key]['y'], label=fits[key]['equation'])

        # Get the curve with the
        # minimum error
        best_fit = min_err(fits)

        # Create text string for best fit annotation
        text = "Best fit: " + best_fit['fit_type'] + "\n" +\
               "Error: ${0:.3f}\%$".format(best_fit['error'])

        # Style it
        bbox_props = dict(boxstyle="round,pad=0.3", fc="white", ec="black", lw=2)

        # Annotate the plot at the top left corner
        ax.annotate(text, xy=(0.05, 0.85), xycoords='axes fraction', bbox=bbox_props)

    # Label and title the plot
    plt.xlabel('$' + x_label + '$')
    plt.ylabel('$' + y_label + '$')
    plt.title(plot_title)

    # Create a legend and show the plot
    plt.legend(loc=9, bbox_to_anchor=(0.5, -0.1), ncol=1)
    plt.grid(True)

    # Save the plot as PNG then display it
    plt.savefig(output_file_path, bbox_inches='tight')


# -------------------------------------------------------------------------------

# PROGRAM STARTS EXECUTION HERE
if __name__ == "__main__":

    # We use this to parse command line arguments
    parser = argparse.ArgumentParser()

    # Define required arguments
    requiredNamed = parser.add_argument_group('required named arguments')
    requiredNamed.add_argument('--input-file-name', help='Name of the input.csv file',   required=True)
    requiredNamed.add_argument('--data-directory',  help='Path for .csv and .png files', required=True)

    # Define optional arguments to customize the plot
    parser.add_argument('--plot-title',           help='Title of your plot')
    parser.add_argument('--input-data-label',     help='The name that appears in the legend to label the original data')
    parser.add_argument('--x-axis-label',         help='X-axis label')
    parser.add_argument('--y-axis-label',         help='Y-axis label')
    parser.add_argument('--dependent-variable',   help="Left side of equation string")
    parser.add_argument('--independent-variable', help="Right side of equation string")

    # Define optional arguments for fitting the data to different types of curves
    parser.add_argument('--exponential-fit', help='If you would like an exponential curve fit')
    parser.add_argument('--cubic-fit',       help='If you would like an cubic curve fit')
    parser.add_argument('--quadratic-fit',   help='If you would like an quadratic curve fit')
    parser.add_argument('--n-log-n-fit',     help='If you would like an n*log(n) curve fit')
    parser.add_argument('--linear-fit',      help='If you would like an linear curve fit')
    parser.add_argument('--logarithmic-fit', help='If you would like an logarithmic curve fit')

    # Set default configuration parameters
    # and store them in a dictionary
    # for easy lookup later
    params = {
        'input_file_name':       '',
        'csv_directory': '       data/csv/',
        'png_directory':        'data/png/',
        'plot_title':           'Title',
        'input_data_label':     'Original Data',
        'x_axis_label':         'n',
        'y_axis_label':         'T(n)',
        'dependent_variable':   'T(n)',
        'independent_variable': 'n',
        'original_data':        True,
        'exponential_fit':      False,
        'cubic_fit':            False,
        'quadratic_fit':        False,
        'n_log_n_fit':          False,
        'linear_fit':           False,
        'logarithmic_fit':      False
    }

    # Parse the command line arguments
    args = parser.parse_args()

    # Set mandatory parameters
    params['input_file_name'] = args.input_file_name
    params['data_directory']  = args.data_directory
    params['csv_directory']   = params['data_directory'] + 'csv/'
    params['png_directory']   = params['data_directory'] + 'png/'

    # Set optional parameters
    if args.plot_title:
        params['plot_title'] = args.plot_title

    if args.input_data_label:
        params['input_data_label'] = args.input_data_label

    if args.x_axis_label:
        params['x_axis_label'] = args.x_axis_label

    if args.y_axis_label:
        params['y_axis_label'] = args.y_axis_label

    if args.dependent_variable:
        params['dependent_variable'] = args.dependent_variable

    if args.independent_variable:
        params['independent_variable'] = args.independent_variable

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

    # Read the x and y components from the .csv file
    x_data, y_data = read_csv(params['csv_directory'] + params['input_file_name'] + '.csv')

    # Plot the data with the updated configuration parameters
    plot_data(output_file_path     = params['png_directory'] + params['input_file_name'] + '.png',
              plot_title           = params['plot_title'],
              input_data_label     = params['input_data_label'],
              x_label              = params['x_axis_label'],
              y_label              = params['y_axis_label'],
              x_data               = x_data,
              y_data               = y_data,
              dependent_variable   = params['dependent_variable'],
              independent_variable = params['independent_variable'],
              plot_original_data   = params['original_data'],
              desired_fits         = {
                  "exponential": params['exponential_fit'],
                  "cubic":       params['cubic_fit'],
                  "quadratic":   params['quadratic_fit'],
                  "n_log_n":     params['n_log_n_fit'],
                  "linear":      params['linear_fit'],
                  "logarithmic": params['logarithmic_fit']
    })
