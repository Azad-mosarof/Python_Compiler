import sys
from os.path import dirname, join
from com.chaquo.python import Python

def main(pyCode):
    file_dir = str(Python.getPlatform().getApplication().getFilesDir())

    file_name = join(dirname(file_dir), 'file.txt')

    try:
        #first save a reference to the original standard output
        original_stdout = sys.stdout

        #now open new file(file.txt) with intention to write data and change standard output to our file
        sys.stdout = open(file_name, 'w', encoding = 'utf8', errors= "ignore")

        #now execute our code using exec() method

        exec(pyCode)    #it will execute our code and save output in file

        #reset the standard output to its original value
        sys.stdout = original_stdout

        #open file and read and save in variable
        output = open(file_name, 'r').read()


    except Exception as e:
        sys.stdout = original_stdout
        output = e

    return str(output)