import csv


# Writes an x, y data set to a .csv file
# at a specified location
def write_csv(output_path, x, y):
    output_file_name = output_path + '.csv'

    f = open(output_file_name, "w")

    for i in xrange(len(x)):
        f.write("{}, {}\n".format(x[i], y[i]))

    f.close()


# Reads in and returns an x,y data
# set from a .csv file
def read_csv(input_path):
    return 1