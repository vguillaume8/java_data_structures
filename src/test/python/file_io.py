import csv


# Writes an x, y data set to a .csv file
# at a specified location
def write_csv(output_path, x, y):
    output_file_name = output_path

    f = open(output_file_name, "w")

    for i in xrange(len(x)):
        f.write("{}, {}\n".format(x[i], y[i]))

    f.close()


# TODO - Implement function that reads .csv with header and more than 2 columns


# Reads in and returns an x,y data
# set from a .csv file
def read_csv(input_file_path):

    # Arrays that will hold
    # x, y input values from CSV
    # file respectively
    x = []
    y = []

    # Open the .csv file in read mode
    with open(input_file_path,'r') as csvfile:
        plots = csv.reader(csvfile, delimiter=',')

        # Read in all lines (x,y points) from CSV file
        # and store them in x, y lists respectively
        for row in plots:
            x.append(float(row[0]))
            y.append(float(row[1]))

    return x, y